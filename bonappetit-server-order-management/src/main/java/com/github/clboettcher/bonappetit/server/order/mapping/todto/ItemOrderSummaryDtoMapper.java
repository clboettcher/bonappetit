package com.github.clboettcher.bonappetit.server.order.mapping.todto;

import com.gihub.clboettcher.bonappetit.price_calculation.api.PriceCalculator;
import com.github.clboettcher.bonappetit.server.order.api.dto.read.ItemOrderSummaryDto;
import com.github.clboettcher.bonappetit.server.order.api.dto.read.SummaryDto;
import com.github.clboettcher.bonappetit.server.order.api.dto.read.SummaryEntryDto;
import com.github.clboettcher.bonappetit.server.order.entity.ItemOrderEntity;
import com.github.clboettcher.bonappetit.server.order.entity.OrderEntityStatus;
import org.joda.time.DateTime;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class ItemOrderSummaryDtoMapper {

    private static final Predicate<ItemOrderEntity> PREDICATE_CANCELLED = itemOrder ->
            itemOrder.getStatus().equals(OrderEntityStatus.CANCELLED);
    @Autowired
    private PriceCalculator priceCalculator;

    @Autowired
    private ItemOrderDtoMapper itemOrderDtoMapper;

    @Autowired
    private OptionOrderSummaryDtoMapper optionOrderSummaryDtoMapper;


    public ItemOrderSummaryDto mapToItemOrderSummaryDto(ItemOrderEntity itemOrder) {
        if (itemOrder == null) {
            return null;
        }

        return ItemOrderSummaryDto.builder()
                .itemTitle(itemOrder.getItemTitle())
                .totalPrice(this.priceCalculator.calculateTotalPrice(itemOrderDtoMapper.mapToItemOrderDto(itemOrder)))
                .optionOrders(optionOrderSummaryDtoMapper.mapToOptionOrderSummaryDtos(itemOrder.getAllOptionOrders()))
                .build();
    }

    public SummaryDto mapToSummaryDto(List<ItemOrderEntity> itemOrderEntities) {
        if (itemOrderEntities == null) {
            return null;
        }

        // Include only entities that are not cancelled
        itemOrderEntities = itemOrderEntities.stream().filter(PREDICATE_CANCELLED.negate())
                .collect(Collectors.toList());

        SummaryDto result = new SummaryDto();
        BigDecimal totalPrice = BigDecimal.ZERO;
        List<ItemOrderSummaryDto> orderSummaryDtos = new ArrayList<>();
        // Map to item order dtos and sum up the total price
        // Look for oldest and newest orders.
        DateTime oldestOrderTime = null;
        DateTime newestOrderTime = null;
        for (ItemOrderEntity itemOrderEntity : itemOrderEntities) {
            ItemOrderSummaryDto orderSummaryDto = mapToItemOrderSummaryDto(itemOrderEntity);

            totalPrice = totalPrice.add(orderSummaryDto.getTotalPrice());
            orderSummaryDtos.add(orderSummaryDto);

            DateTime currentOrderTime = new DateTime(itemOrderEntity.getOrderTime());
            if (oldestOrderTime == null || currentOrderTime.isBefore(oldestOrderTime)) {
                oldestOrderTime = currentOrderTime;
            }
            if (newestOrderTime == null || currentOrderTime.isAfter(newestOrderTime)) {
                newestOrderTime = currentOrderTime;
            }
        }

        Map<ItemOrderSummaryDto, Long> orderSummaryToCount = orderSummaryDtos.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        List<SummaryEntryDto> orderSummaries = this.createOrderSummaries(orderSummaryToCount);

        result.setTotalPrice(totalPrice);
        result.setOrderSummaries(orderSummaries);
        result.setOldestOrderTime(oldestOrderTime);
        result.setNewestOrderTime(newestOrderTime);
        result.setOrderCount(orderSummaries.stream()
                .mapToLong(SummaryEntryDto::getCount)
                .sum());

        return result;
    }

    private List<SummaryEntryDto> createOrderSummaries(Map<ItemOrderSummaryDto, Long> orderSummaryToCount) {
        return orderSummaryToCount.entrySet()
                .stream()
                .map(entry -> SummaryEntryDto.builder()
                        .count(entry.getValue())
                        .orderSummary(entry.getKey())
                        .totalPrice(this.calculateSummaryEntryTotalPrice(entry.getKey(), entry.getValue()))
                        .build()
                )
                .collect(Collectors.toList());
    }

    protected BigDecimal calculateSummaryEntryTotalPrice(ItemOrderSummaryDto summaryDto, Long count) {
        return summaryDto.getTotalPrice().multiply(BigDecimal.valueOf(count));
    }
}

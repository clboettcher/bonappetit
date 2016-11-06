package com.gihub.clboettcher.price_calculation.api.entity;


import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class ItemOrderPrices {

    private BigDecimal price;
    private List<OptionOrderPrices> optionOrderPrices;

}

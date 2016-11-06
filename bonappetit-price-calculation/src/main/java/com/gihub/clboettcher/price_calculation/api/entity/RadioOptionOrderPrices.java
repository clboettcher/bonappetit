package com.gihub.clboettcher.price_calculation.api.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class RadioOptionOrderPrices extends OptionOrderPrices {

    private BigDecimal selectedItemPriceDiff;

}

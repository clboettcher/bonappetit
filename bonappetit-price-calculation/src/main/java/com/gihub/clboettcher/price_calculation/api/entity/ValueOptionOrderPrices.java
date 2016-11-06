package com.gihub.clboettcher.price_calculation.api.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class ValueOptionOrderPrices extends OptionOrderPrices {

    private final int value;
    private final BigDecimal priceDiff;

}

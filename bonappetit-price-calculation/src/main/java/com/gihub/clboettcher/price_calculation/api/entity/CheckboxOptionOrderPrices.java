package com.gihub.clboettcher.price_calculation.api.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * An order for a checkbox option in the context of price calculation.
 * This class does not need a boolean property checked since options which have
 * not been checked are not considered in price calculation.
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class CheckboxOptionOrderPrices extends OptionOrderPrices {

    private BigDecimal priceDiff;

}

package com.github.clboettcher.bonappetit.serverentities.menu;

import com.github.clboettcher.bonappetit.core.printing.PrintStrategy;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 *
 */
public class IntegerOption extends Option {
    private BigDecimal priceDiff = BigDecimal.ZERO;
    private Integer defaultValue = 0;
    private PrintStrategy printStrategy = PrintStrategy.DEFAULT;

    public BigDecimal getPriceDiff() {
        return priceDiff;
    }

    public void setPriceDiff(BigDecimal priceDiff) {
        this.priceDiff = priceDiff;
    }

    public Integer getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Integer defaultValue) {
        this.defaultValue = defaultValue;
    }

    public PrintStrategy getPrintStrategy() {
        return printStrategy;
    }

    public void setPrintStrategy(PrintStrategy printStrategy) {
        this.printStrategy = printStrategy;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("Option#toString()", super.toString())
                .append("priceDiff", priceDiff)
                .append("defaultValue", defaultValue)
                .append("printStrategy", printStrategy)
                .toString();
    }
}

package com.github.clboettcher.bonappetit.serverentities.menu;

import com.github.clboettcher.bonappetit.common.printing.PrintStrategy;
import com.github.clboettcher.bonappetit.serverentities.AbstractEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Entity;
import java.math.BigDecimal;

/**
 * A single item of a {@link RadioOption}.
 */
@Entity
public class RadioItem extends AbstractEntity {

    /**
     * See {@link #getTitle()}.
     */
    private String title;

    /**
     * See {@link #getIndex()}.
     */
    private Integer index;

    /**
     * See {@link #getPriceDiff()}.
     */
    private BigDecimal priceDiff = new BigDecimal("0.00");

    /**
     * See {@link #getPrintStrategy()}.
     */
    private PrintStrategy printStrategy = PrintStrategy.DEFAULT;

    /**
     * No-op no-args constructor.
     */
    public RadioItem() {
        // No-op.
    }

    private RadioItem(Builder builder) {
        this.setId(builder.id);
        setTitle(builder.title);
        setIndex(builder.index);
        setPriceDiff(builder.priceDiff);
        setPrintStrategy(builder.printStrategy);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * @return The title / name of this item, e.g. "klein".
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title see {@link #getTitle()}.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return The zero based index to display this item at.
     */
    public Integer getIndex() {
        return index;
    }

    /**
     * @param index see {@link #getIndex()}.
     */
    public void setIndex(Integer index) {
        this.index = index;
    }

    /**
     * Returns the price difference of this radio item.
     * <p/>
     * The total price of an order for an item can be calculated
     * using the items price and the price diff of all options.
     *
     * @return The price difference.
     */
    public BigDecimal getPriceDiff() {
        return priceDiff;
    }

    /**
     * @param priceDiff see {@link #getPriceDiff()}.
     */
    public void setPriceDiff(BigDecimal priceDiff) {
        this.priceDiff = priceDiff;
    }

    /**
     * @return The strategy that determines how this item should be printed.
     */
    public PrintStrategy getPrintStrategy() {
        return printStrategy;
    }

    /**
     * @param printStrategy see {@link #getPrintStrategy()}.
     */
    public void setPrintStrategy(PrintStrategy printStrategy) {
        this.printStrategy = printStrategy;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .appendSuper(super.toString())
                .append("title", title)
                .append("index", index)
                .append("priceDiff", priceDiff)
                .append("printStrategy", printStrategy)
                .toString();
    }

    /**
     * {@code RadioItem} builder static inner class.
     */
    public static final class Builder {
        private long id;
        private String title;
        private Integer index;
        private BigDecimal priceDiff;
        private PrintStrategy printStrategy;

        private Builder() {
        }

        /**
         * Sets the {@code id} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code id} to set
         * @return a reference to this Builder
         */
        public Builder id(long val) {
            id = val;
            return this;
        }

        /**
         * Sets the {@code title} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code title} to set
         * @return a reference to this Builder
         */
        public Builder title(String val) {
            title = val;
            return this;
        }

        /**
         * Sets the {@code index} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code index} to set
         * @return a reference to this Builder
         */
        public Builder index(Integer val) {
            index = val;
            return this;
        }

        /**
         * Sets the {@code priceDiff} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code priceDiff} to set
         * @return a reference to this Builder
         */
        public Builder priceDiff(BigDecimal val) {
            priceDiff = val;
            return this;
        }

        /**
         * Sets the {@code printStrategy} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code printStrategy} to set
         * @return a reference to this Builder
         */
        public Builder printStrategy(PrintStrategy val) {
            printStrategy = val;
            return this;
        }

        /**
         * Returns a {@code RadioItem} built from the parameters previously set.
         *
         * @return a {@code RadioItem} built with parameters of this {@code RadioItem.Builder}
         */
        public RadioItem build() {
            return new RadioItem(this);
        }
    }
}

package com.github.clboettcher.bonappetit.common.dto.menu;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * A single item of a {@link RadioOptionDto}.
 */
public class RadioItemDto {

    /**
     * See {@link #getId()}.
     */
    private Long id;

    /**
     * See {@link #getTitle()}.
     */
    private String title;

    /**
     * See {@link #getPriceDiff()}.
     */
    private BigDecimal priceDiff;

    /**
     * No-op no-args constructor.
     */
    public RadioItemDto() {
        // No-op.
    }

    private RadioItemDto(Builder builder) {
        id = builder.id;
        setTitle(builder.title);
        setPriceDiff(builder.priceDiff);
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
     * @return The ID of this entity.
     */
    public long getId() {
        return id;
    }

    /**
     * @param id see {@link #getId()}.
     */
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        RadioItemDto rhs = (RadioItemDto) obj;
        return new EqualsBuilder()
                .append(this.id, rhs.id)
                .append(this.title, rhs.title)
                .append(this.priceDiff, rhs.priceDiff)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(this.id)
                .append(this.title)
                .append(this.priceDiff)
                .hashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", id)
                .append("title", title)
                .append("priceDiff", priceDiff)
                .toString();
    }


    /**
     * {@code RadioItemDto} builder static inner class.
     */
    public static final class Builder {
        private Long id;
        private String title;
        private BigDecimal priceDiff;

        private Builder() {
        }

        /**
         * Sets the {@code id} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code id} to set
         * @return a reference to this Builder
         */
        public Builder id(Long val) {
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
         * Returns a {@code RadioItemDto} built from the parameters previously set.
         *
         * @return a {@code RadioItemDto} built with parameters of this {@code RadioItemDto.Builder}
         */
        public RadioItemDto build() {
            return new RadioItemDto(this);
        }
    }
}

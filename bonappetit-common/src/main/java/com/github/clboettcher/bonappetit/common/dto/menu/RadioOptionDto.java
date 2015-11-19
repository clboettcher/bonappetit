package com.github.clboettcher.bonappetit.common.dto.menu;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Set;

/**
 * An option that consists of multiple items of which one must be selected.
 */
public class RadioOptionDto extends OptionDto {

    /**
     * See {@link #getDefaultSelectedId()}.
     */
    private Long defaultSelectedId;

    /**
     * See {@link #getRadioItemDtos()}.
     */
    private Set<RadioItemDto> radioItemDtos;

    /**
     * @return The ID of the {@link RadioItemDto} (contained by {@link #getRadioItemDtos()}) that should be
     * selected per default.
     */
    public Long getDefaultSelectedId() {
        return defaultSelectedId;
    }

    /**
     * @param defaultSelectedId see {@link #getDefaultSelectedId()}.
     */
    public void setDefaultSelectedId(Long defaultSelectedId) {
        this.defaultSelectedId = defaultSelectedId;
    }

    /**
     * @return The items that this option consists of.
     */
    public Set<RadioItemDto> getRadioItemDtos() {
        return radioItemDtos;
    }

    /**
     * @param radioItemDtos see {@link #getRadioItemDtos()}.
     */
    public void setRadioItemDtos(Set<RadioItemDto> radioItemDtos) {
        this.radioItemDtos = radioItemDtos;
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
        RadioOptionDto rhs = (RadioOptionDto) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(this.defaultSelectedId, rhs.defaultSelectedId)
                .append(this.radioItemDtos, rhs.radioItemDtos)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(this.defaultSelectedId)
                .append(this.radioItemDtos)
                .hashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .appendSuper(super.toString())
                .append("defaultSelectedId", defaultSelectedId)
                .append("radioItemDtos", radioItemDtos)
                .toString();
    }
}

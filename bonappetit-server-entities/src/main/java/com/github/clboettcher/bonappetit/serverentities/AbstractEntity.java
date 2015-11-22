package com.github.clboettcher.bonappetit.serverentities;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Abstract base class for all entities.
 */
public class AbstractEntity {
    /**
     * See {@link #getId()}.
     */
    @Id
    @GeneratedValue
    private long id;

    /**
     * @return The ID of this event.
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
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", id)
                .toString();
    }
}

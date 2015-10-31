package com.github.clboettcher.bonappetit.serverentities.menu;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 *
 */
public abstract class Option {
    private String title;
    private Integer index;


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("title", title)
                .append("index", index)
                .toString();
    }
}

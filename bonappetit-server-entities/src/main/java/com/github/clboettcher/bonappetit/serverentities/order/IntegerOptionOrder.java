package com.github.clboettcher.bonappetit.serverentities.order;

import com.github.clboettcher.bonappetit.serverentities.menu.IntegerOption;

/**
 * Created by c.boettcher on 16.03.2016.
 */
public class IntegerOptionOrder extends OptionOrder {
    private IntegerOption option;
    private Integer value;

    public IntegerOption getOption() {
        return option;
    }

    public void setOption(IntegerOption option) {
        this.option = option;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}

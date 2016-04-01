package com.github.clboettcher.bonappetit.serverentities.order;

import com.github.clboettcher.bonappetit.serverentities.menu.CheckboxOption;

/**
 * Created by c.boettcher on 16.03.2016.
 */
public class CheckboxOptionOrder extends OptionOrder {
    private CheckboxOption option;
    private Boolean checked;

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public CheckboxOption getOption() {

        return option;
    }

    public void setOption(CheckboxOption option) {
        this.option = option;
    }
}

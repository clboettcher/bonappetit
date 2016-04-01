package com.github.clboettcher.bonappetit.serverentities.order;

import com.github.clboettcher.bonappetit.serverentities.menu.RadioItem;

/**
 * Created by c.boettcher on 16.03.2016.
 */
public class RadioOptionOrder extends OptionOrder {
    private RadioItem selectedItem;

    public RadioItem getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(RadioItem selectedItem) {
        this.selectedItem = selectedItem;
    }
}

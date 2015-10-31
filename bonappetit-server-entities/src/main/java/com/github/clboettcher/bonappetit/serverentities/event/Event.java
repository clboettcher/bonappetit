package com.github.clboettcher.bonappetit.serverentities.event;

import com.github.clboettcher.bonappetit.serverentities.menu.Menu;
import com.github.clboettcher.bonappetit.serverentities.staff.StaffListing;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 */
public class Event {
    private String title;
    private Menu menu;
    private StaffListing staffListing;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public StaffListing getStaffListing() {
        return staffListing;
    }

    public void setStaffListing(StaffListing staffListing) {
        this.staffListing = staffListing;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("title", title)
                .append("menu", menu)
                .append("staffListing", staffListing)
                .toString();
    }
}

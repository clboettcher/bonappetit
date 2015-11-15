/*
* Copyright (c) 2015 Claudius Boettcher (pos.bonappetit@gmail.com)
*
* This file is part of BonAppetit. BonAppetit is an Android based
* Point-of-Sale client-server application for small restaurants.
*
* BonAppetit is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* BonAppetit is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with BonAppetit.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.github.clboettcher.bonappetit.common.dto.event;

import com.github.clboettcher.bonappetit.common.dto.menu.MenuDto;
import com.github.clboettcher.bonappetit.common.dto.staff.StaffListingDto;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * An event consisting of staff members and a menu.
 */
@XmlRootElement
public class EventDto {

    /**
     * See {@link #getId()}.
     */
    private long id;

    /**
     * See {@link #getTitle()}.
     */
    private String title;

    /**
     * See {@link #getMenuDto()}.
     */
    private MenuDto menuDto;

    /**
     * See {@link #getStaffListingDto()}.
     */
    private StaffListingDto staffListingDto;

    public EventDto() {
    }

    private EventDto(Builder builder) {
        setId(builder.id);
        setTitle(builder.title);
        setMenuDto(builder.menuDto);
        setStaffListingDto(builder.staffListingDto);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * @param id see {@link #getId()}.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return The ID of this event.
     */
    public long getId() {
        return id;
    }

    /**
     * @return The unique title / name of this event.
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
     * @return The menu that is served during this event.
     */
    public MenuDto getMenuDto() {
        return menuDto;
    }

    /**
     * @param menuDto see {@link #getMenuDto()}.
     */
    public void setMenuDto(MenuDto menuDto) {
        this.menuDto = menuDto;
    }

    /**
     * @return The listing of staff members that work in this event.
     */
    public StaffListingDto getStaffListingDto() {
        return staffListingDto;
    }

    /**
     * @param staffListingDto see {@link #getStaffListingDto()}.
     */
    public void setStaffListingDto(StaffListingDto staffListingDto) {
        this.staffListingDto = staffListingDto;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("title", title)
                .append("menu", menuDto)
                .append("staffListing", staffListingDto)
                .toString();
    }

    /**
     * {@code EventDto} builder static inner class.
     */
    public static final class Builder {
        private long id;
        private String title;
        private MenuDto menuDto;
        private StaffListingDto staffListingDto;

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
         * Sets the {@code menuDto} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code menuDto} to set
         * @return a reference to this Builder
         */
        public Builder menuDto(MenuDto val) {
            menuDto = val;
            return this;
        }

        /**
         * Sets the {@code staffListingDto} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code staffListingDto} to set
         * @return a reference to this Builder
         */
        public Builder staffListingDto(StaffListingDto val) {
            staffListingDto = val;
            return this;
        }

        /**
         * Returns a {@code EventDto} built from the parameters previously set.
         *
         * @return a {@code EventDto} built with parameters of this {@code EventDto.Builder}
         */
        public EventDto build() {
            return new EventDto(this);
        }
    }
}

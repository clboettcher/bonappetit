/*
 * Copyright (c) 2016 Claudius Boettcher (pos.bonappetit@gmail.com)
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
package com.github.clboettcher.bonappetit.domain.builder;

import com.github.clboettcher.bonappetit.domain.menu.RadioItem;
import com.github.clboettcher.bonappetit.domain.menu.RadioOption;

import java.util.Set;

public class RadioOptionBuilder {
    private RadioItem defaultSelected;
    private Set<RadioItem> radioItems;
    private long id;
    private String title;
    // 'index' is reserved in mysql
    private Integer index;

    private RadioOptionBuilder() {
    }

    public static RadioOptionBuilder aRadioOption() {
        return new RadioOptionBuilder();
    }

    public RadioOptionBuilder withDefaultSelected(RadioItem defaultSelected) {
        this.defaultSelected = defaultSelected;
        return this;
    }

    public RadioOptionBuilder withRadioItems(Set<RadioItem> radioItems) {
        this.radioItems = radioItems;
        return this;
    }

    public RadioOptionBuilder withId(long id) {
        this.id = id;
        return this;
    }

    public RadioOptionBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public RadioOptionBuilder withIndex(Integer index) {
        this.index = index;
        return this;
    }

    public RadioOptionBuilder but() {
        return aRadioOption().withDefaultSelected(defaultSelected).withRadioItems(radioItems).withId(id).withTitle(title).withIndex(index);
    }

    public RadioOption build() {
        RadioOption radioOption = new RadioOption();
        radioOption.setDefaultSelected(defaultSelected);
        radioOption.setRadioItems(radioItems);
        radioOption.setId(id);
        radioOption.setTitle(title);
        radioOption.setIndex(index);
        return radioOption;
    }
}

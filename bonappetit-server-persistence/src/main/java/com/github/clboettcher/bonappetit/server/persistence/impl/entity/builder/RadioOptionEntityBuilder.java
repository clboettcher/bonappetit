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
package com.github.clboettcher.bonappetit.server.persistence.impl.entity.builder;

import com.github.clboettcher.bonappetit.server.persistence.impl.entity.menu.RadioItemEntity;
import com.github.clboettcher.bonappetit.server.persistence.impl.entity.menu.RadioOptionEntity;

import java.util.Set;

public class RadioOptionEntityBuilder {
    private RadioItemEntity defaultSelected;
    private Set<RadioItemEntity> radioItems;
    private long id;
    private String title;
    // 'index' is reserved in mysql
    private Integer index;

    private RadioOptionEntityBuilder() {
    }

    public static RadioOptionEntityBuilder aRadioOptionEntity() {
        return new RadioOptionEntityBuilder();
    }

    public RadioOptionEntityBuilder withDefaultSelected(RadioItemEntity defaultSelected) {
        this.defaultSelected = defaultSelected;
        return this;
    }

    public RadioOptionEntityBuilder withRadioItems(Set<RadioItemEntity> radioItems) {
        this.radioItems = radioItems;
        return this;
    }

    public RadioOptionEntityBuilder withId(long id) {
        this.id = id;
        return this;
    }

    public RadioOptionEntityBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public RadioOptionEntityBuilder withIndex(Integer index) {
        this.index = index;
        return this;
    }

    public RadioOptionEntityBuilder but() {
        return aRadioOptionEntity().withDefaultSelected(defaultSelected).withRadioItems(radioItems).withId(id).withTitle(title).withIndex(index);
    }

    public RadioOptionEntity build() {
        RadioOptionEntity radioOptionEntity = new RadioOptionEntity();
        radioOptionEntity.setDefaultSelected(defaultSelected);
        radioOptionEntity.setRadioItems(radioItems);
        radioOptionEntity.setId(id);
        radioOptionEntity.setTitle(title);
        radioOptionEntity.setIndex(index);
        return radioOptionEntity;
    }
}

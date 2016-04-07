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
package de.cb.playground.androidannotations.app.db.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

@DatabaseTable(tableName = "books")
@Data
@NoArgsConstructor
public class Book {
    @DatabaseField(generatedId = true)
    private Long id;

    @DatabaseField(canBeNull = false)
    private String title;

    @DatabaseField(canBeNull = false)
    private DateTime published;

    @DatabaseField(foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true, canBeNull = false)
    private Author author;


    /**
     * Constructor setting the specified properties.
     *
     * @param title     The title.
     * @param published The date the book was published.
     * @param author    The author.
     */
    public Book(String title, DateTime published, Author author) {
        this.title = title;
        this.published = published;
        this.author = author;
    }
}

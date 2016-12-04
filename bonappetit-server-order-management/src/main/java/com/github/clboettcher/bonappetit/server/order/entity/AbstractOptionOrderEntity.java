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
package com.github.clboettcher.bonappetit.server.order.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DiscriminatorOptions;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Abstract base class for option order entities.
 * <p>
 * Option orders are orders for options. They are not used on their own but
 * always belong to an item order.
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "OPTION_ORDER_TYPE")
// Force usage of the discriminator value when querying a specific subclass to prevent
// loading of the wrong subtype.
@DiscriminatorOptions(force = true)
@Table(name = "OPTION_ORDER")
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractOptionOrderEntity {

    /**
     * The ID.
     */
    @Id
    @GeneratedValue
    @Column(name = "OPTION_ORDER_ID")
    private Long id;

    /**
     * The title / name of the referenced option.
     * <p>
     * Use this property rather than the title of
     * the option referenced in subclasses because the referenced
     * option might have changed since
     * the order was created.
     */
    @Column(name = "OPTION_TITLE", nullable = false)
    private String optionTitle;


    /**
     * The price difference of the referenced option or radio item.
     * <p>
     * The total price of an order for an item order can be calculated
     * using the items price and the price diff of all options.
     * <p>
     * Use this property rather than the price diff of the referenced option
     * because the referenced option might have changed since
     * the order was created.
     */
    @Column(name = "OPTION_PRICE_DIFF", nullable = false)
    private BigDecimal optionPriceDiff;
}

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
package com.github.clboettcher.bonappetit.server;

import com.github.clboettcher.bonappetit.server.entity.menu.CheckboxOption;
import com.github.clboettcher.bonappetit.server.entity.menu.IntegerOption;
import com.github.clboettcher.bonappetit.server.entity.menu.Item;
import com.github.clboettcher.bonappetit.server.entity.menu.RadioOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@EntityScan("com.github.clboettcher.bonappetit.server.entity")
@SpringBootApplication
public class BonappetitServerApplication extends RepositoryRestConfigurerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(BonappetitServerApplication.class);

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        super.configureRepositoryRestConfiguration(config);
        config.exposeIdsFor(Item.class, IntegerOption.class, CheckboxOption.class, RadioOption.class);
        LOGGER.info("Configured id export");
    }

    public static void main(String[] args) {
        SpringApplication.run(BonappetitServerApplication.class, args);
    }
}

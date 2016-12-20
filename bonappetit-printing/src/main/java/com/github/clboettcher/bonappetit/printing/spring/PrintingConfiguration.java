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
package com.github.clboettcher.bonappetit.printing.spring;

import com.github.clboettcher.bonappetit.printing.util.DateFormatter;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * The spring config for the module.
 */
@Configuration
@PropertySource({
        "classpath:/config/printing.properties",
        "classpath:/config/printing-${BONAPPETIT_ENV:PROD}.properties"
})
public class PrintingConfiguration {

    /**
     * The spring environment providing access to configuration properties.
     */
    @Autowired
    private Environment env;

    @Bean
    public DateFormatter dateFormatter() {
        return new DateFormatter(DateTimeZone.forID(env.getRequiredProperty("printing.timeZone.id")));
    }
}

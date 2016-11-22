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
package com.github.clboettcher.bonappetit.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Utilities for Jackson object mappers.
 */
public final class ObjectMapperFactory {
    /**
     * No instance
     */
    private ObjectMapperFactory() {
    }

    /**
     * @return New object mapper with standard settings
     */
    public static ObjectMapper create() {
        ObjectMapper mapper = new ObjectMapper();

        // Configure POJO to JSON features
        configureSerializationFeatures(mapper);

        // Configure JSON to POJO features
        configureDeserializationFeatures(mapper);

        //set up ISO 8601 date/time stamp format:
        final DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:sss'Z'", Locale.ENGLISH);
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        mapper.setDateFormat(df);

        return mapper;
    }

    private static void configureDeserializationFeatures(ObjectMapper mapper) {
        // Add Joda module so Jackson deserializes dates correctly
        mapper.registerModule(new JodaModule());

        // Ignore unknown properties in input data. This creates forward compatibility in case a backend adds new
        // attributes. Similar to JAXB deserialization which also ignores unknown properties by default.
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // Allow (non-standard) JSON comments in input data
        mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
    }

    private static void configureSerializationFeatures(ObjectMapper mapper) {
        // Pretty print
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);

        // Output empty objects
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        // Output dates as strings, not as arrays (AS_TIMESTAMP means actually as array)
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        // Do not output null elements. This can be changed on a class to class basis by using the appropriate
        // annotation @JsonSerialize(include = JsonSerialize.Inclusion.ALWAYS))
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }
}

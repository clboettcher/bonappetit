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
package com.github.clboettcher.bonappetit.core;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.IOException;
import java.util.List;

public final class JsonUtils {

    /**
     * No instance
     */
    private JsonUtils() {
    }

    /**
     * Reads the given resource ID and parses it as JSON list.
     *
     * @param <T>          The class reference of the type that the file content should be deserialized to.
     * @param jsonObject   The JSON object to parse.
     * @param objectMapper The object mapper to use.
     * @param clazz        The type that the file content should be deserialized to.
     * @return The read list.
     * @throws IOException If I/O error occur during reading the input stream.
     */
    public static <T> T parseJsonObject(String jsonObject, ObjectMapper objectMapper, Class<T> clazz) throws IOException {
        TypeFactory tf = objectMapper.getTypeFactory();
        JavaType javaType = tf.constructType(clazz);
        return objectMapper.readValue(jsonObject, javaType);
    }

    /**
     * Reads the given resource ID and parses it as JSON list.
     *
     * @param <T>          The class reference of the type that the list elements should be deserialized to.
     * @param jsonArray    The JSON array to parse.
     * @param objectMapper The object mapper to use.
     * @param clazz        The type that the list elements should be deserialized to.
     * @return The read list.
     * @throws IOException If I/O error occur during reading the input stream.
     */
    public static <T> List<T> parseJsonArray(String jsonArray, ObjectMapper objectMapper, Class<T> clazz) throws IOException {
        CollectionType valueType = objectMapper.getTypeFactory().constructCollectionType(List.class, clazz);
        return objectMapper.readValue(jsonArray, valueType);
    }
}

package com.github.clboettcher.bonappetit.core;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Common I/O functionality for easy access to resources.
 */
public final class BonAppetitResourceUtils {

    /**
     * No instances.
     */
    private BonAppetitResourceUtils() {
    }

    /**
     * Reads the given resource ID and parses it as JSON list.
     *
     * @param fileName File name in resources.
     * @param clazz    The type that the file content should be deserialized to.
     * @param <T>      The class reference of the type that the file content should be deserialized to.
     * @return The read list.
     * @throws IOException If I/O error occur during reading the input stream.
     */
    public static <T> T readResourceAsJsonObject(String fileName, Class<T> clazz) throws IOException {
        ObjectMapper objectMapper = ObjectMapperFactory.create();

        String toConvert = BonAppetitResourceUtils.readFileContentAsString(fileName);
        TypeFactory tf = objectMapper.getTypeFactory();
        JavaType javaType = tf.constructType(clazz);
        return objectMapper.convertValue(toConvert, javaType);
    }

    /**
     * Reads the given resource ID and parses it as JSON list.
     *
     * @param fileName File name in resources.
     * @param clazz    The type that the list elements should be deserialized to.
     * @param <T>      The class reference of the type that the list elements should be deserialized to.
     * @return The read list.
     * @throws IOException If I/O error occur during reading the input stream.
     */
    public static <T> List<T> readResourceAsJsonArray(String fileName, Class<T> clazz) throws IOException {
        ObjectMapper objectMapper = ObjectMapperFactory.create();

        String toConvert = BonAppetitResourceUtils.readFileContentAsString(fileName);
        CollectionType valueType = objectMapper.getTypeFactory().constructCollectionType(List.class, clazz);
        return objectMapper.readValue(toConvert, valueType);
    }

    /**
     * Read file from resources as string using UTF 8 encoding.
     *
     * @param fileName File name.
     * @return File content.
     * @throws IOException If file could not be read.
     */
    public static String readFileContentAsString(String fileName) throws IOException {
        try (InputStream stream = openResource(fileName)) {
            return org.apache.commons.io.IOUtils.toString(stream, "UTF-8");
        }
    }

    /**
     * Open stream to file in resources.
     *
     * @param fileName File name.
     * @return Stream. Has to be closed by caller.
     * @throws IOException If file could not be read.
     */
    public static InputStream openResource(String fileName) throws IOException {
        InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        if (stream == null) {
            throw new IllegalArgumentException(String.format("Resource '%s' not found", fileName));
        }
        return stream;
    }
}
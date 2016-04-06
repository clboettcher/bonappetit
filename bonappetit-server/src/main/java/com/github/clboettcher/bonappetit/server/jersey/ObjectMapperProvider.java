package com.github.clboettcher.bonappetit.server.jersey;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.clboettcher.bonappetit.common.databind.ObjectMapperFactory;

public class ObjectMapperProvider extends ContextResolver<ObjectMapper> {

    @Override
    public ObjectMapper create() {
        return ObjectMapperFactory.create();
    }
}

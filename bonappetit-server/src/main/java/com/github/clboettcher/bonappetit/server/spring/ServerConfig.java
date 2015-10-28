package com.github.clboettcher.bonappetit.server.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Spring context config for the server.
 */
@ComponentScan("com.github.clboettcher.bonappetit.server")
@Configuration
public class ServerConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerConfig.class);

    @PostConstruct
    public void sayHello() {
        LOGGER.info(String.format("Hello from %s", ServerConfig.class.getName()));
    }
}

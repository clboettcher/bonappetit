package com.github.clboettcher.bonappetit.server.jersey;

import com.github.clboettcher.bonappetit.server.api.HeartbeatService;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
 * Jersey config.
 */
public class ServerApplication extends ResourceConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerApplication.class);

    public ServerApplication() {
        LOGGER.info(String.format("Initializing Jersey application."));
        final WebApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        register(ctx.getBean(HeartbeatService.class));
    }
}

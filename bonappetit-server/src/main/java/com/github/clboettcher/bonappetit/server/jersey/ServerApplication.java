/*
* Copyright (c) 2015 Claudius Boettcher (pos.bonappetit@gmail.com)
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
package com.github.clboettcher.bonappetit.server.jersey;

import com.github.clboettcher.bonappetit.server.api.EventsService;
import com.github.clboettcher.bonappetit.server.api.HeartbeatService;
import org.glassfish.jersey.moxy.json.MoxyJsonConfig;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.ws.rs.ext.ContextResolver;
import java.util.HashMap;
import java.util.Map;

/**
 * Jersey config.
 */
public class ServerApplication extends ResourceConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerApplication.class);

    public ServerApplication() {
        LOGGER.info("Initializing Jersey application.");
        final WebApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        register(ctx.getBean(HeartbeatService.class));
        register(ctx.getBean(EventsService.class));
        register(createMoxyJsonConfigContextResolver());
    }

    /**
     * @return The {@link javax.ws.rs.ext.ContextResolver} that handles JSON message body writing.
     */
    private ContextResolver<MoxyJsonConfig> createMoxyJsonConfigContextResolver() {
        final MoxyJsonConfig moxyJsonConfig = new MoxyJsonConfig();

        Map<String, String> namespacePrefixMapper = new HashMap<>();
        namespacePrefixMapper.put("http://www.w3.org/2001/XMLSchema-instance", "xsi");
        moxyJsonConfig.setNamespacePrefixMapper(namespacePrefixMapper).setNamespaceSeparator(':');

        return moxyJsonConfig.resolver();
    }
}

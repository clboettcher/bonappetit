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
package com.github.clboettcher.bonappetit.server.jersey;

import com.github.clboettcher.bonappetit.server.ProjectProperties;
import com.github.clboettcher.bonappetit.server.menu.api.MenuManagement;
import com.github.clboettcher.bonappetit.server.order.api.OrderManagement;
import com.github.clboettcher.bonappetit.server.staff.api.StaffMemberManagement;
import com.github.clboettcher.bonappetit.server.users.api.UserManagement;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import io.swagger.models.Contact;
import io.swagger.models.Info;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;


/**
 * Jersey application config.
 */
@ApplicationPath("/api")
@Component
public class JerseyConfig extends ResourceConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(JerseyConfig.class);

    /**
     * Constructor registering the application components.
     */
    @Autowired
    public JerseyConfig(ApplicationContext context, ProjectProperties projectProperties) {
        LOGGER.info("Initializing jersey application.");
        register(ExceptionMapper.class);

        register(context.getBean(StaffMemberManagement.class));
        register(context.getBean(MenuManagement.class));
        register(context.getBean(OrderManagement.class));
        register(context.getBean(UserManagement.class));

        register(ObjectMapperProvider.class);
        initSwagger(projectProperties);
    }

    /**
     * Initializes the swagger {@link BeanConfig} and registers swagger related providers.
     *
     * @param projectProperties Metadata properties.
     */
    private void initSwagger(ProjectProperties projectProperties) {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setTitle(projectProperties.getTitle());
        beanConfig.setVersion(projectProperties.getVersion());
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost(projectProperties.getHost() + ":" + projectProperties.getPort());
        beanConfig.setBasePath("/api");
        // Setup classes that will swagger pick up
        beanConfig.setResourcePackage("com.github.clboettcher.bonappetit");
        // Actually produce the documentation
        beanConfig.setScan(true);
        // Pretty print swagger.json
        beanConfig.setPrettyPrint(true);

        Info info = new Info();
        Contact contact = new Contact();
        contact.setEmail(projectProperties.getContact().getMail());
        contact.setName(projectProperties.getContact().getName());
        info.setContact(contact);
        beanConfig.setInfo(info);

        // Add swagger core providers.
        register(ApiListingResource.class);
        register(SwaggerSerializers.class);
    }
}

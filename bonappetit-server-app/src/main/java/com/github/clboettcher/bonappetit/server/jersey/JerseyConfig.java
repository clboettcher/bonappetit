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

import com.github.clboettcher.bonappetit.server.impl.ManagementServiceImpl;
import com.github.clboettcher.bonappetit.server.menu.impl.MenuManagementImpl;
import com.github.clboettcher.bonappetit.server.staff.api.StaffMemberResource;
import com.github.clboettcher.bonappetit.server.staff.impl.StaffMemberResourceImpl;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import io.swagger.models.Contact;
import io.swagger.models.Info;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;


/**
 * Jersey application config.
 */
@ApplicationPath("/v1/api")
@Component
public class JerseyConfig extends ResourceConfig {

    /**
     * Constructor registering the application components.
     */
    public JerseyConfig() {
        register(StaffMemberResourceImpl.class);
        register(MenuManagementImpl.class);

        register(ManagementServiceImpl.class);
        register(ObjectMapperProvider.class);
        initSwagger();
    }

    /**
     * Initializes the swagger {@link BeanConfig} and registers swagger related providers.
     */
    private void initSwagger() {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setTitle("BonAppetit Server");
        beanConfig.setVersion("0.0.1-SNAPSHOT");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("/v1/api");
        // Setup classes that will swagger pick up
        beanConfig.setResourcePackage(StaffMemberResource.class.getPackage().getName());
        // Actually produce the documentation
        beanConfig.setScan(true);
        // Pretty print swagger.json
        beanConfig.setPrettyPrint(true);

        Info info = new Info();
        Contact contact = new Contact();
        contact.setEmail("pos.bonappetit@gmail.com");
        contact.setName("Claudius Böttcher");
        info.setContact(contact);
        beanConfig.setInfo(info);

        // Add swagger core providers.
        register(ApiListingResource.class);
        register(SwaggerSerializers.class);
    }
}

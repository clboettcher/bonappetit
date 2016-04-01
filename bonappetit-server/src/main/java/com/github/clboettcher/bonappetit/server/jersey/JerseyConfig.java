package com.github.clboettcher.bonappetit.server.jersey;

import com.github.clboettcher.bonappetit.server.api.ManagementService;
import com.github.clboettcher.bonappetit.server.impl.ManagementServiceImpl;
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
//@Configuration
@ApplicationPath("/v1/api")
@Component
public class JerseyConfig extends ResourceConfig {

    /**
     * Constructor registering the application components.
     */
    public JerseyConfig() {
        register(ManagementServiceImpl.class);
        initSwagger();
    }

    /**
     * Initializes the swagger {@link BeanConfig} and registers swagger related providers.
     */
    private void initSwagger() {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setTitle("BonAppetit Server");
        beanConfig.setVersion("1.0.0");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("/v1/api");
        // Setup classes that will swagger pick up
        beanConfig.setResourcePackage(ManagementService.class.getPackage().getName());
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

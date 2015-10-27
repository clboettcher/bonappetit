package com.github.clboettcher.bonappetit.server.jersey;

import com.github.clboettcher.bonappetit.server.impl.HeartbeatServiceImpl;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Jersey config.
 */
public class ServerApplication extends ResourceConfig {
    public ServerApplication() {
        // TODO retrieve from spring context if this does not work
        register(HeartbeatServiceImpl.class);
    }
}

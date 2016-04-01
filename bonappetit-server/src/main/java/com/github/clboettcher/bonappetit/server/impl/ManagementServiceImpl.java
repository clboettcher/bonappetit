package com.github.clboettcher.bonappetit.server.impl;

import com.github.clboettcher.bonappetit.server.api.ManagementService;
import org.springframework.stereotype.Component;

/**
 * Default impl of {@link ManagementService}.
 */
@Component
public class ManagementServiceImpl implements ManagementService {

    @Override
    public String ping() {
        return "pong";
    }
}

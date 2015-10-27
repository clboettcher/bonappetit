package com.github.clboettcher.bonappetit.server.impl;

import com.github.clboettcher.bonappetit.server.api.HeartbeatService;
import org.springframework.stereotype.Component;

/**
 * Default impl of {@link com.github.clboettcher.bonappetit.server.api.HeartbeatService}.
 */
@Component
public class HeartbeatServiceImpl implements HeartbeatService {
    @Override
    public String ping() {
        return "pong";
    }
}

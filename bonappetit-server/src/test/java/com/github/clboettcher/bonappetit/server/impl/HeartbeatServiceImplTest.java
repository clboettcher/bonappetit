package com.github.clboettcher.bonappetit.server.impl;


import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Tests for {@link HeartbeatServiceImpl}.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HeartbeatServiceImplTest.Context.class)
public class HeartbeatServiceImplTest {

    @Autowired
    private HeartbeatServiceImpl service;

    /**
     * Asserts that the heartbeat service answers pings with pongs.
     */
    @Test
    public void testPingPong() throws Exception {
        Assert.assertThat(service.ping(), Matchers.is("pong"));
    }

    @Configuration
    static class Context {
        @Bean
        public HeartbeatServiceImpl heartbeatServiceImpl() {
            return new HeartbeatServiceImpl();
        }
    }
}

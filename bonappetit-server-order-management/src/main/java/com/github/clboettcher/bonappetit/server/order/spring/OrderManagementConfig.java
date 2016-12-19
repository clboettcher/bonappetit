package com.github.clboettcher.bonappetit.server.order.spring;

import com.gihub.clboettcher.bonappetit.price_calculation.api.PriceCalculator;
import com.gihub.clboettcher.bonappetit.price_calculation.impl.PriceCalculatorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.math.BigDecimal;

@Configuration
public class OrderManagementConfig {

    /**
     * The spring environment providing access to configuration properties.
     */
    @Autowired
    private Environment env;

    @Bean
    public PriceCalculator priceCalculator() {
        BigDecimal foodDiscount = new BigDecimal(env.getProperty("prices.staffMembers.food.discount", "1"));
        BigDecimal drinkDiscount = new BigDecimal(env.getProperty("prices.staffMembers.drink.discount", "1"));
        return new PriceCalculatorImpl(foodDiscount, drinkDiscount);
    }
}

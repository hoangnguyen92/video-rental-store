package com.casumo.calculator;

import com.casumo.models.Order;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.junit.Assert.*;

public class PriceCalculatorTest {
    private PriceCalculator priceCalculator;
    private ObjectMapper mapper = Jackson.newObjectMapper();
    private List<Order> orders,lateOrders;

    @Before
    public void setUp() throws Exception {
        priceCalculator = new PriceCalculator();
        orders = mapper.readValue(fixture("fixtures/orders.json"), new TypeReference<List<Order>>(){});
        lateOrders = mapper.readValue(fixture("fixtures/late-orders.json"), new TypeReference<List<Order>>(){});
    }

    @Test
    public void calculateTotalExpectedCharge(){
        assertEquals(new BigDecimal(250), priceCalculator.calculateTotalExpectedCharge(orders));
    }

    @Test
    public void calculateTotalSurchargeCharge(){
        assertEquals(new BigDecimal(110), priceCalculator.calculateTotalSurchargeCharge(lateOrders));
    }


}
package com.casumo.calculator;

import com.casumo.models.Order;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.junit.Assert.assertEquals;

public class BonusPointCalculatorTest {
    private BonusPointCalculator bonusPointCalculator;
    private ObjectMapper mapper = Jackson.newObjectMapper();
    private List<Order> orders;

    @Before
    public void setUp() throws Exception {
        bonusPointCalculator = new BonusPointCalculator();
        orders = mapper.readValue(fixture("fixtures/orders.json"), new TypeReference<List<Order>>(){});
    }

    @Test
    public void calculateBonusPoint(){
        assertEquals(5, bonusPointCalculator.calculateBonusPoint(orders));
    }

}
package com.casumo.models;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.dropwizard.jackson.Jackson;

public class CustomerTest {

	private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
	private final Customer customer = new Customer(1, "Hoang Nguyen", 0);

	@Test
	public void serializesToJSON() throws Exception {
		assertThat(MAPPER.writeValueAsString(customer)).isEqualToIgnoringWhitespace(fixture("fixtures/customer.json"));
	}

	@Test
	public void deserializesFromJSON() throws Exception {
		assertThat(MAPPER.readValue(fixture("fixtures/customer.json"), Customer.class)).isEqualTo(customer);
	}

}
package com.casumo.models;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.casumo.enums.FilmType;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.dropwizard.jackson.Jackson;

public class FilmTest {

	private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
	private final Film film = new Film(1, "Matrix 11", FilmType.NEW);
	
	@Test
	public void serializesToJSON() throws Exception {
		assertThat(MAPPER.writeValueAsString(film)).isEqualToIgnoringWhitespace(fixture("fixtures/film.json"));
	}

	@Test
	public void deserializesFromJSON() throws Exception {
		assertThat(MAPPER.readValue(fixture("fixtures/film.json"), Film.class)).isEqualTo(film);
	}
	
}
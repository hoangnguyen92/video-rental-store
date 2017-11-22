package com.casumo.models;

import org.hibernate.validator.constraints.Length;

import com.casumo.enums.FilmType;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Film extends BaseModel {
	
	@Length(max = 150)
	private String title;
	
	private FilmType type;

	public Film() {

	}
	
	public Film(int id, String title, FilmType type) {
		super.setId(id);
		this.title = title;
		this.type = type;
	}

	@JsonProperty
	public String getTitle() {
		return title;
	}

	@JsonProperty
	public FilmType getType() {
		return type;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setType(FilmType type) {
		this.type = type;
	}
	
}

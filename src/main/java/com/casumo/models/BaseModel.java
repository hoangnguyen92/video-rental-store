package com.casumo.models;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class BaseModel {
	private int id;
	
	@JsonProperty
	public int getId() {
		return id;
	}
	
    public void setId(int id) {
		this.id = id;
	}

	@Override
    public int hashCode() {
    	return Objects.hash(getId());
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        BaseModel other = (BaseModel) object;

        return Objects.equals(getId(), other.getId());
    }
}

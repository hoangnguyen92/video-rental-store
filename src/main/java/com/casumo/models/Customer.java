package com.casumo.models;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Customer extends BaseModel{
	@Length(max = 150)
	private String name;
	private int bonusPoint;
	
	public Customer() {}
	
	public Customer(int id, String name, int bonusPoint) {
		super.setId(id);
		this.name = name;
		this.bonusPoint = bonusPoint;
	}

	@JsonProperty
	public String getName() {
		return name;
	}
	
	@JsonProperty
	public int getBonusPoint() {
		return bonusPoint;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBonusPoint(int bonusPoint) {
		this.bonusPoint = bonusPoint;
	}

	public void addBonusPoint(int bonusPoint) {
		this.bonusPoint += bonusPoint;
	}

}

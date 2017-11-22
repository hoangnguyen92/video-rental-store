package com.casumo.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

public class Rental extends BaseModel{
	private Customer customer;
	private List<Order> orders;
	private BigDecimal totalPrice;
	private BigDecimal totalSurcharges;

	public Rental() {}


	public Rental(int id, Customer customer, List<Order> orders, BigDecimal totalPrice, BigDecimal totalSurcharges) {
		super.setId(id);
		this.customer = customer;
		this.orders = orders;
		this.totalPrice = totalPrice;
		this.totalSurcharges = totalSurcharges;
	}

	@JsonProperty
	public Customer getCustomer() {
		return customer;
	}

	@JsonProperty
	public List<Order> getOrders() {
		return orders;
	}

	@JsonProperty
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	@JsonProperty
	public BigDecimal getTotalSurcharges() {
		return totalSurcharges;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public void setPrice(BigDecimal price) {
		this.totalPrice = price;
	}

	public void setTotalSurcharges(BigDecimal totalSurcharges) {
		this.totalSurcharges = totalSurcharges;
	}

}

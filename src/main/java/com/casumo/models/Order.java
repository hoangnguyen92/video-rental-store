package com.casumo.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Order extends BaseModel{
	private Film film;
	private int expectedDays;
	private BigDecimal price;
	private BigDecimal surcharge;
	private LocalDate rentDate;
	private LocalDate returnDate;

	public Order() {}

	public Order(int id, Film film, int expectedDays, BigDecimal price, BigDecimal surcharge, LocalDate rentDate, LocalDate returnDate) {
		super.setId(id);
		this.film = film;
		this.expectedDays = expectedDays;
		this.price = price;
        this.surcharge = surcharge;
		this.rentDate = rentDate;
		this.returnDate = returnDate;
	}

	public Order(int id, Film film, int expectedDays, LocalDate rentDate) {
		super.setId(id);
		this.film = film;
		this.expectedDays = expectedDays;
		this.rentDate = rentDate;
	}

	@JsonProperty
	public Film getFilm() {
	    return film;
	}

	@JsonProperty
	public int getExpectedDays() {
		return expectedDays;
	}

	@JsonProperty
	public BigDecimal getPrice() {
		return price;
	}

    @JsonProperty
    public BigDecimal getSurcharge() {
        return surcharge;
	}

	@JsonProperty
	public LocalDate getReturnDate() {
		return returnDate;
	}

	@JsonProperty
	public LocalDate getRentDate() {
		return rentDate;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public void setExpectedDays(int expectedDays) {
		this.expectedDays = expectedDays;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

    public void setSurcharge(BigDecimal surcharge) {
        this.surcharge = surcharge;
    }

    public void setRentDate(LocalDate rentDate) {
		this.rentDate = rentDate;
	}

	public void setReturnDate(LocalDate returnDate) {
	    this.returnDate = returnDate;
	}

}

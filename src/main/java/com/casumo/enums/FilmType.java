package com.casumo.enums;

public enum FilmType {
	NEW(40,1,2),
	REGULAR(30,3,1),
	OLD(30,5,1);

    private int price;
    private int initialDays;
    private int bonusPoint;

	FilmType(int price, int initialDays, int bonusPoint) {
        this.price = price;
        this.initialDays = initialDays;
        this.bonusPoint = bonusPoint;
	}

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getInitialDays() {
        return initialDays;
    }

    public void setInitialDays(int initialDays) {
        this.initialDays = initialDays;
    }

    public int getBonusPoint() {
        return bonusPoint;
    }

    public void setBonusPoint(int bonusPoint) {
        this.bonusPoint = bonusPoint;
    }
}

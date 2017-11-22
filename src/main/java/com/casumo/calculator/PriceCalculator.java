package com.casumo.calculator;


import com.casumo.enums.FilmType;
import com.casumo.models.Order;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.time.Period;
import java.util.List;

public class PriceCalculator {

    public BigDecimal calculateTotalExpectedCharge(List<Order> orders) {
        BigDecimal expectedSum = orders.stream()
                .map( o -> {
                    BigDecimal price = calculatePrice(o);
                    o.setSurcharge(price);
                    return price;
                })
                .reduce(BigDecimal::add)
                .get();


        return expectedSum;
    }

    public BigDecimal calculateTotalSurchargeCharge(List<Order> orders) {
        BigDecimal expectedSum = orders.stream()
                .map( o -> {
                    BigDecimal surcharge = calcuateSurcharge(o);
                    o.setSurcharge(surcharge);
                    return surcharge;
                })
                .reduce(BigDecimal::add)
                .get();


        return expectedSum;
    }

    private BigDecimal calculatePrice(Order order) {
        FilmType filmType = order.getFilm().getType();

        return BigDecimal.valueOf(Math.max(1L + order.getExpectedDays() - filmType.getInitialDays() , 1) * filmType.getPrice());
    }

    private BigDecimal calcuateSurcharge( Order order) throws InvalidParameterException{
        FilmType filmType = order.getFilm().getType();
        long additionalDays = 0;

        if(order.getRentDate() == null || order.getReturnDate() == null)
            throw new InvalidParameterException("Rent date or return date is missing!");

        Period period = Period.between(order.getRentDate(),order.getReturnDate());
        additionalDays = period.getDays() - order.getExpectedDays();

        return BigDecimal.valueOf(Math.max(additionalDays,0) * filmType.getPrice());
    }

}

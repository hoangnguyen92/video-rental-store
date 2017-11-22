package com.casumo.calculator;


import com.casumo.models.Order;

import java.util.List;

public class BonusPointCalculator {
    
    public int calculateBonusPoint(List<Order> orders) {
        int points = orders.stream()
                .mapToInt( o -> o.getFilm().getType().getBonusPoint())
                .sum();

        return points;
    }

}

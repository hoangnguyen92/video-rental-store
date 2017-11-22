package com.casumo.service;

import com.casumo.calculator.BonusPointCalculator;
import com.casumo.calculator.PriceCalculator;
import com.casumo.models.Rental;
import com.casumo.repositories.CustomerRepository;
import com.casumo.repositories.RentalRepository;

import javax.ws.rs.WebApplicationException;
import java.util.Optional;

public class RentalService {
	private PriceCalculator priceCalculator;
    private BonusPointCalculator bonusPointCalculator;
    private RentalRepository rentalRepository;
    private CustomerRepository customerRepository;
	
	public RentalService(PriceCalculator priceCalculator,
                         BonusPointCalculator bonusPointCalculator,
                         RentalRepository rentalRepository,
                         CustomerRepository customerRepository) {

		this.priceCalculator = priceCalculator;
		this.bonusPointCalculator = bonusPointCalculator;
		this.rentalRepository = rentalRepository;
		this.customerRepository = customerRepository;
	}
	
	public Rental rentFilms(Rental rental) {
        rental.setPrice(priceCalculator.calculateTotalExpectedCharge(rental.getOrders()));
		rental.getCustomer().addBonusPoint(bonusPointCalculator.calculateBonusPoint(rental.getOrders()));
        customerRepository.update(rental.getCustomer());
        return rentalRepository.insert(rental);
	}

    public Rental returnFilms(int rentalId) {
	    Optional<Rental> rentalOptional = rentalRepository.findById(rentalId);
        Rental rental = rentalOptional
                    .orElseThrow(() -> new WebApplicationException("Can not find rental with id: "+rentalId,404));

        rental.setTotalSurcharges(priceCalculator.calculateTotalSurchargeCharge(rental.getOrders()));
        return rental;
    }
}

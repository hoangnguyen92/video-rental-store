package com.casumo.service;

import com.casumo.calculator.BonusPointCalculator;
import com.casumo.calculator.PriceCalculator;
import com.casumo.models.Rental;
import com.casumo.repositories.CustomerRepository;
import com.casumo.repositories.RentalRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.WebApplicationException;
import java.math.BigDecimal;
import java.util.Optional;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RentalServiceTest {
    private static final PriceCalculator priceCalculator = mock(PriceCalculator.class);
    private static final BonusPointCalculator bonusPointCalculator= mock(BonusPointCalculator.class);
    private static final RentalRepository rentalRepository = mock(RentalRepository.class);
    private static final CustomerRepository customerRepository = mock(CustomerRepository.class);
    public static final BigDecimal expectedTotalPrice = new BigDecimal(250);
    public static final BigDecimal expectedTotalSurcharge = new BigDecimal(110);
    public static final int expectedBonusPoints = 5;
    private ObjectMapper mapper = Jackson.newObjectMapper();
    private RentalService rentalService;
    private Rental rental,lateRental;

    @Before
    public void setUp() throws Exception {
        rentalService = new RentalService(priceCalculator,bonusPointCalculator, rentalRepository, customerRepository);

        rental = mapper.readValue(fixture("fixtures/rental.json"), Rental.class);
        lateRental = mapper.readValue(fixture("fixtures/rental-late-orders.json"), Rental.class);

        when(priceCalculator.calculateTotalExpectedCharge(any())).thenReturn(expectedTotalPrice);
        when(priceCalculator.calculateTotalSurchargeCharge(any())).thenReturn(expectedTotalSurcharge);
        when(bonusPointCalculator.calculateBonusPoint(any())).thenReturn(expectedBonusPoints);
        when(customerRepository.update(any())).thenReturn(rental.getCustomer());
        when(rentalRepository.insert(any())).thenReturn(rental);
        when(rentalRepository.findById(1)).thenReturn(Optional.of(lateRental));
        when(rentalRepository.findById(2)).thenReturn(Optional.empty());
    }

    @Test
    public void rentFilms(){
        Rental actualRental = rentalService.rentFilms(rental);

        assertEquals(expectedTotalPrice, actualRental.getTotalPrice());
        assertEquals(expectedBonusPoints, actualRental.getCustomer().getBonusPoint());
        assertEquals(rental,actualRental);
    }

    @Test
    public void returnFilms(){
        Rental actualRental = rentalService.returnFilms(1);

        assertEquals(expectedTotalSurcharge, actualRental.getTotalSurcharges());
        assertEquals(rental,actualRental);
    }

    @Test(expected = WebApplicationException.class)
    public void returnFilmsWithException(){
        rentalService.returnFilms(2);
    }



}
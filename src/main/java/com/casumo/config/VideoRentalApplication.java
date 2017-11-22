package com.casumo.config;

import com.casumo.calculator.BonusPointCalculator;
import com.casumo.calculator.PriceCalculator;
import com.casumo.repositories.CustomerRepository;
import com.casumo.repositories.FilmRepository;
import com.casumo.repositories.RentalRepository;
import com.casumo.resources.CustomerResource;
import com.casumo.resources.FilmResource;

import com.casumo.resources.RentalResource;
import com.casumo.service.RentalService;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class VideoRentalApplication extends Application<VideoRentalApplicationConfiguration>{

	public static void main(String[] args) throws Exception {
		new VideoRentalApplication().run(args);
	}
	
	
	@Override
	public void run(VideoRentalApplicationConfiguration configuration, Environment environment) throws Exception {
		final FilmRepository filmRepository = new FilmRepository();
		final CustomerRepository customerRepository = new CustomerRepository();
		final RentalRepository rentalRepository = new RentalRepository();

		final PriceCalculator priceCalculator = new PriceCalculator();
		final BonusPointCalculator bonusPointCalculator = new BonusPointCalculator();

		final RentalService rentalService = new RentalService(priceCalculator,bonusPointCalculator,rentalRepository,customerRepository);

		final FilmResource filmResource = new FilmResource(filmRepository);
		final CustomerResource customerResource = new CustomerResource(customerRepository);
		final RentalResource rentalResource = new RentalResource(rentalService);

		environment.jersey().register(filmResource);
		environment.jersey().register(customerResource);
		environment.jersey().register(rentalResource);
	}

}

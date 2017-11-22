package com.casumo.resources;

import com.casumo.models.Rental;
import com.casumo.service.RentalService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/rental")
@Produces(MediaType.APPLICATION_JSON)
public class RentalResource {
	private final RentalService rentalService;

	public RentalResource(RentalService rentalService) {
		this.rentalService = rentalService;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Rental rentFilms(Rental rental) {
		return rentalService.rentFilms(rental);
	}

	@POST()
    @Path("/{rentalId}/return")
	@Consumes(MediaType.APPLICATION_JSON)
	public Rental returnFilm(@PathParam("rentalId") int rentalId) {
		return rentalService.returnFilms(rentalId);
	}
	
}

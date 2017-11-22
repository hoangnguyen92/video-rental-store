package com.casumo.resources;

import com.casumo.models.Film;
import com.casumo.repositories.FilmRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/films")
@Produces(MediaType.APPLICATION_JSON)
public class FilmResource {
	private final FilmRepository filmRepository;

	public FilmResource(FilmRepository filmRepository) {
		this.filmRepository = filmRepository;
	}

	@GET
	public List<Film> getAll() {
		return filmRepository.findAll();
	}

	@GET
    @Path("/{filmId}")
	public Film getById(@PathParam("filmId") int filmId) {
	    return checkIfFilmExist(filmId);
	}

	@POST
	public Film create(Film film) {
		return filmRepository.insert(film);
	}
	
	@PUT
    @Path("/{filmId}")
	public Film update(@PathParam("filmId") int filmId, Film film) {
        checkIfFilmExist(filmId);
        film.setId(filmId);
		return filmRepository.update(film);
	}

    @DELETE
    @Path("/{filmId}")
	public Film remove(@PathParam("filmId") int filmId) {
	    Film film = checkIfFilmExist(filmId);
		return filmRepository.remove(film);
	}

    private Film checkIfFilmExist(int filmId) {
        return filmRepository.findById(filmId)
                .orElseThrow(() -> new WebApplicationException("Can not find film with id:" + filmId,404));
    }
}

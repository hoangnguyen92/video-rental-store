package com.casumo.resources;

import com.casumo.enums.FilmType;
import com.casumo.models.Film;
import com.casumo.repositories.FilmRepository;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.*;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class FilmResourceTest {

	private static final FilmRepository filmRepository = mock(FilmRepository.class);

	@ClassRule
	public static final ResourceTestRule resources = ResourceTestRule.builder()
			.addResource(new FilmResource(filmRepository)).build();

	private static Film film, filmUpdated;
	private static List<Film> films;

    @BeforeClass
    public static void initObjects() throws Exception {
        film = new Film(1, "Test", FilmType.NEW);
        filmUpdated = new Film(1, "Test Updated", FilmType.NEW);
        films = new ArrayList<>();

        films.add(film);
    }

	@Before
	public void init() throws Exception {
		when(filmRepository.findAll()).thenReturn(films);
		when(filmRepository.findById(1)).thenReturn(Optional.of(film));
        when(filmRepository.findById(2)).thenReturn(Optional.empty());
		when(filmRepository.insert(any(Film.class))).thenReturn(film);
        when(filmRepository.update(film)).thenReturn(filmUpdated);
        when(filmRepository.remove(film)).thenReturn(film);
	}

	@After
	public void revert() throws Exception {
		reset(filmRepository);
	}

	@Test
	public void testFindAllFilms() {
		Response response = resources.target("/films").request().get();

		assertEquals(200,response.getStatus());

        List<Film> actualFilms = response.readEntity(new GenericType<List<Film>>(){});

        assertEquals(1, actualFilms.size());

        assertTrue(this.films.containsAll(actualFilms));
        verify(filmRepository).findAll();
    }

    @Test
    public void testFindFilmById() {
        Response response = resources.target("/films/1").request().get();

        assertEquals(200,response.getStatus());

        Film actualFilm = response.readEntity(Film.class);

        assertEquals(film, actualFilm);


        verify(filmRepository).findById(1);

    }

    @Test
    public void testFindFilmByIdWithException() {
        Response response = resources.target("/films/2").request().get();

        assertEquals(404,response.getStatus());

    }

    @Test
    public void testCreateFilm() {
        Response response = resources.target("/films").request().post(Entity.entity(film, MediaType.APPLICATION_JSON_TYPE));

        assertEquals(200,response.getStatus());

        Film actualFilm = response.readEntity(Film.class);

        assertEquals(film, actualFilm);

        verify(filmRepository).insert(film);

    }

    @Test
    public void testUpdateFilm() {
        Response response = resources.target("/films/1").request().put(Entity.entity(filmUpdated, MediaType.APPLICATION_JSON_TYPE));

        assertEquals(200,response.getStatus());

        Film actualFilm = response.readEntity(Film.class);

        assertEquals(filmUpdated.getTitle(), actualFilm.getTitle());

        verify(filmRepository).update(film);
    }

    @Test
    public void testUpdateFilmWithException() {
        Response response = resources.target("/films/2").request().put(Entity.entity(filmUpdated, MediaType.APPLICATION_JSON_TYPE));

        assertEquals(404,response.getStatus());

    }


    @Test
    public void testDeleteFilm() {
        Response response = resources.target("/films/1").request().delete();

        assertEquals(200,response.getStatus());

        Film actualFilm = response.readEntity(Film.class);

        assertEquals(film, actualFilm);

        verify(filmRepository).remove(film);
    }

    @Test
    public void testDeleteFilmWithException() {
        Response response = resources.target("/films/2").request().delete();

        assertEquals(404,response.getStatus());

    }

}

package com.casumo.repositories;

import com.casumo.enums.FilmType;
import com.casumo.models.Film;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class FilmRepositoryTest {
	
	private FilmRepository repository;
	private Film f;
	
	@Before
	public void init() {
		repository = new FilmRepository();
		f = new Film(1, "Test", FilmType.NEW);
		repository.insert(f);
	}
	
	@Test
	public void testInsert() {
		Optional<Film> actual = repository.findById(1);
		
		assertTrue(actual.isPresent());
		assertEquals(f, actual.orElse(new Film()));
	}

	@Test
	public void testRemove() {
		Film actual = repository.remove(f);
		
		assertEquals(f, actual);
		
		assertFalse(repository.findAll().contains(f));
	}

	@Test
	public void testFindAll() {
		List<Film> films = repository.findAll();
		
		assertEquals(1, films.size());
		
		assertTrue(films.contains(f));
	}

	@Test
	public void testUpdate() {
		f.setTitle("Test 2");
		
		Film f2 = repository.update(f);
		
		assertEquals("Test 2", f2.getTitle());
		
		assertEquals("Test 2", repository.findById(1).orElse(new Film()).getTitle());
	}

	@Test
	public void testGet() {
		Optional<Film> exist = repository.findById(1);
		Optional<Film> unexist = repository.findById(2);
		
		assertEquals(Optional.empty(), unexist);
		assertEquals(f, exist.orElse(new Film()));
	}

}

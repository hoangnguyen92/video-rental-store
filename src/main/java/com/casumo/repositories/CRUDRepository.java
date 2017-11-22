package com.casumo.repositories;

import com.casumo.models.BaseModel;

import java.util.*;

public abstract class CRUDRepository<T extends BaseModel> {
	private Set<T> repo = new HashSet<>();
	
	public T insert(T item) {
		repo.add(item);
		return item;
	}
	
	public T remove(T item) {
		repo.remove(item);
		return item;
	}
	
	public List<T> findAll() {
		return new ArrayList<>(repo);
	}
	
	public T update(T item) {
		return insert(item);
	}
	
	public Optional<T> findById(int id) {
		return repo.stream()
				.filter(i -> i.getId() == id)
				.findFirst();
	}
}

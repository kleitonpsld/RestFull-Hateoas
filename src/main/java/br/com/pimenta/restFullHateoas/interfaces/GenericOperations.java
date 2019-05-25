package br.com.pimenta.restFullHateoas.interfaces;

import java.util.List;

public interface GenericOperations<E> {
	
	E post(E entity);
	E get(Long id);
	void put(E entity);
	void delete(E entity);
	void patch(E entity);
	
	List<E> post(List<E> entities);
	void put(List<E> entities);
	void delete(List<E> entities);
	void patch(List<E> entities);
	List<E> get();
}

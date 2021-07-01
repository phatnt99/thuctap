package net.freetuts.backend.services;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface GenericService<T, ID extends Serializable> {

	<S extends T> S save(S entity);

	<S extends T> Iterable<S> saveAll(Iterable<S> entities);

	T findById(ID id);

	boolean existsById(ID id);

	Iterable<T> findAll();

	Iterable<T> findAllById(Iterable<ID> ids);

	void deleteById(ID id);

	void delete(T entity);

	void deleteAll(Iterable<? extends T> entities);

	Iterable<T> findAll(Sort sort);

	Page<T> findAll(Pageable pageable);

}

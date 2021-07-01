package net.freetuts.backend.services;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * The Interface CrudService.
 *
 * @param <T> the generic type
 */
public interface CrudService<T> {

	/**
	 * Gets the all.
	 *
	 * @param pageable the pageable
	 * @return the all
	 */
	Page<T> getAll(Pageable pageable);

	/**
	 * Gets the one.
	 *
	 * @param id the id
	 * @return the one
	 */
	T getOne(UUID id);
	
	/**
	 * Creates the one.
	 *
	 * @param entityDTO the entity DTO
	 * @return the t
	 */
	T createOne(Object entityDTO);

	/**
	 * Update one.
	 *
	 * @param entityDTO the entity DTO
	 */
	void updateOne(Object entityDTO);
	
	/**
	 * Delete one.
	 *
	 * @param id the id
	 */
	void deleteOne(UUID id);
}

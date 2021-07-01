package net.freetuts.backend.services.impl;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import net.freetuts.backend.repository.GenericReposity;
import net.freetuts.backend.services.GenericService;

/**
 * The Class GernericServiceImpl.
 *
 * @param <T> the generic type
 * @param <ID> the generic type
 */
public class GernericServiceImpl<T, ID extends Serializable>
		implements GenericService<T, ID> {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(MethodHandles.lookup().lookupClass());

	/** The generic repository. */
	private GenericReposity<T, ID> genericRepository;

	/** The em. */
	@PersistenceContext
	protected EntityManager em;

	/**
	 * Instantiates a new gerneric service impl.
	 *
	 * @param genericRepository the generic repository
	 */
	public GernericServiceImpl(GenericReposity<T, ID> genericRepository) {
		this.genericRepository = genericRepository;
	}

	/**
	 * Save.
	 *
	 * @param <S> the generic type
	 * @param entity the entity
	 * @return the s
	 */
	@Override
	public <S extends T> S save(S entity) {
		LOGGER.info("Save entity = {} ", entity);
		return genericRepository.save(entity);
	}

	/**
	 * Save all.
	 *
	 * @param <S> the generic type
	 * @param entities the entities
	 * @return the iterable
	 */
	@Override
	public <S extends T> Iterable<S> saveAll(Iterable<S> entities) {
		LOGGER.info("SaveAll entities = {} ", entities);
		return genericRepository.saveAll(entities);
	}

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the t
	 */
	@Override
	public T findById(ID id) {
		LOGGER.info("Find By Id = {} ", id);
		return genericRepository.findById(id).orElse(null);
	}

	/**
	 * Exists by id.
	 *
	 * @param id the id
	 * @return true, if successful
	 */
	@Override
	public boolean existsById(ID id) {
		LOGGER.info("Exists By Id = {} ", id);
		return genericRepository.existsById(id);
	}

	/**
	 * Find all.
	 *
	 * @return the iterable
	 */
	@Override
	public Iterable<T> findAll() {
		LOGGER.info("FindAll");
		return genericRepository.findAll();
	}

	/**
	 * Find all by id.
	 *
	 * @param ids the ids
	 * @return the iterable
	 */
	@Override
	public Iterable<T> findAllById(Iterable<ID> ids) {
		LOGGER.info("Find All By Id = {} ", ids);
		return genericRepository.findAllById(ids);
	}

	/**
	 * Delete by id.
	 *
	 * @param id the id
	 */
	@Override
	public void deleteById(ID id) {
		LOGGER.info("Delete By Id = {} ", id);
		genericRepository.deleteById(id);
	}

	/**
	 * Delete.
	 *
	 * @param entity the entity
	 */
	@Override
	public void delete(T entity) {
		LOGGER.info("Delete Entity= {} ", entity);
		genericRepository.delete(entity);
	}

	/**
	 * Delete all.
	 *
	 * @param entities the entities
	 */
	@Override
	public void deleteAll(Iterable<? extends T> entities) {
		LOGGER.info("Delete All= {} ", entities);
		genericRepository.deleteAll(entities);
	}

	/**
	 * Find all.
	 *
	 * @param sort the sort
	 * @return the iterable
	 */
	@Override
	public Iterable<T> findAll(Sort sort) {
		LOGGER.info("Find All By Sort = {} ", sort);
		return genericRepository.findAll(sort);
	}

	/**
	 * Find all.
	 *
	 * @param pageable the pageable
	 * @return the page
	 */
	@Override
	public Page<T> findAll(Pageable pageable) {
		LOGGER.info("Find All By Pageable = {} ", pageable);
		return genericRepository.findAll(pageable);
	}

}

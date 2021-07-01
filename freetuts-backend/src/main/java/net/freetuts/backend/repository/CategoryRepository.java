package net.freetuts.backend.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import net.freetuts.backend.entity.Category;

/**
 * The Interface CategoryRepository.
 */
public interface CategoryRepository extends JpaRepository<Category, UUID>,
		JpaSpecificationExecutor<Category> {
	
	/**
	 * Find by slug.
	 *
	 * @param slug the slug
	 * @return the optional
	 */
	Optional<Category> findBySlug(String slug);

	/**
	 * Find by id in.
	 *
	 * @param <T> the generic type
	 * @param ids the ids
	 * @param pageable the pageable
	 * @param type the type
	 * @return the page
	 */
	<T> Page<T> findByIdIn(Collection<UUID> ids, Pageable pageable, Class<T> type);
	
	/**
	 * Find by id in.
	 *
	 * @param <T> the generic type
	 * @param ids the ids
	 * @param type the type
	 * @return the list
	 */
	<T> List<T> findByIdIn(Collection<UUID> ids, Class<T> type);
	
	/**
	 * Find by id.
	 *
	 * @param <T> the generic type
	 * @param id the id
	 * @param type the type
	 * @return the optional
	 */
	<T> Optional<T> findById(UUID id, Class<T> type);
	
	/**
	 * Find by pcategory id.
	 *
	 * @param <T> the generic type
	 * @param id the id
	 * @param type the type
	 * @return the list
	 */
	<T> List<T> findByPcategoryId(UUID id, Class<T> type);
	
	/**
	 * Find by pcategory id in.
	 *
	 * @param <T> the generic type
	 * @param ids the ids
	 * @param type the type
	 * @return the list
	 */
	<T> List<T> findByPcategoryIdIn(Collection<UUID> ids, Class<T> type);
	
	/**
	 * Find by.
	 *
	 * @param <T> the generic type
	 * @param type the type
	 * @return the list
	 */
	<T> List<T> findBy(Class<T> type);
	
	/**
	 * Find by path starting with and pcategory id not null.
	 *
	 * @param path the path
	 * @return the list
	 */
	List<Category> findByPathStartingWithAndPcategoryIdNotNull(String path);
}

package net.freetuts.backend.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.freetuts.backend.dto.PostForCategoryDTO;
import net.freetuts.backend.entity.Post;

/**
 * The Interface PostRepository.
 */
@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {
	
	/**
	 * Find by slug.
	 *
	 * @param slug the slug
	 * @return the optional
	 */
	Optional<Post> findBySlug(String slug);
	
	/**
	 * Find all by category slug.
	 *
	 * @param categorySlug the category slug
	 * @param pageable the pageable
	 * @return the page
	 */
	Page<PostForCategoryDTO> findAllByCategorySlug(String categorySlug, Pageable pageable);
	
	/**
	 * Find all by category path containing and post type is.
	 *
	 * @param categorySlug the category slug
	 * @param type the type
	 * @param pageable the pageable
	 * @return the page
	 */
	Page<PostForCategoryDTO> findAllByCategoryPathContainingAndPostTypeIs(String categorySlug, String type, Pageable pageable);
	
	/**
	 * Find by postable id and post type.
	 *
	 * @param id the id
	 * @param type the type
	 * @return the post
	 */
	Post findByPostableIdAndPostType(UUID id, String type);
	
	/**
	 * Find by postable id.
	 *
	 * @param id the id
	 * @return the optional
	 */
	Optional<Post> findByPostableId(UUID id);
	
	/**
	 * Find by type.
	 *
	 * @param <T> the generic type
	 * @param type the type
	 * @param typ the typ
	 * @return the list
	 */
	<T> List<T> findByType(String type, Class<T> typ);
	
	/**
	 * Find top 5 by category path starting with order by updated at desc.
	 *
	 * @param catePath the cate path
	 * @return the list
	 */
	List<PostForCategoryDTO> findTop5ByCategoryPathStartingWithOrderByUpdatedAtDesc(String catePath);
	
}

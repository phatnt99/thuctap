package net.freetuts.backend.repository.specification;

import org.springframework.data.jpa.domain.Specification;

import net.freetuts.backend.entity.Category;
import net.freetuts.backend.entity.Category_;

/**
 * The Class CategorySpecification.
 */
public class CategorySpecification {
	
	/**
	 * Checks if is root.
	 *
	 * @return the specification
	 */
	public static Specification<Category> isRoot() {

		return (root, query, cb) -> cb.isNull(root.get(Category_.PCATEGORY));
	}
	
	/**
	 * Checks if is menu.
	 *
	 * @return the specification
	 */
	public static Specification<Category> isMenu() {

		return (root, query, cb) -> cb.equal(root.get(Category_.IS_MENU), true);
	}
	
	/**
	 * Checks if is top menu.
	 *
	 * @return the specification
	 */
	public static Specification<Category> isTopMenu() {
		
		return Specification.where(isRoot()).and(isMenu());
	}
}

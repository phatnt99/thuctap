package net.freetuts.frontend.services;

import java.util.UUID;

import net.freetuts.frontend.model.BaseModel;
import net.freetuts.frontend.model.PageAndSort;
import net.freetuts.frontend.utils.RestPage;

public interface CrudService<T extends BaseModel> {

	/**
	 * Gets the all.
	 *
	 * @param pageAndSort
	 *     the page and sort
	 * 
	 * @return the all
	 */
	RestPage<T> getAll(PageAndSort pageAndSort);

	/**
	 * Creates the one.
	 *
	 * @param post
	 *     the post
	 * 
	 * @return the post
	 */
	T createOne(T post);

	/**
	 * Gets the one.
	 *
	 * @param id
	 *     the id
	 * 
	 * @return the one
	 */
	T getOne(UUID id);

	/**
	 * Update one.
	 *
	 * @param post
	 *     the post
	 */
	void updateOne(T post);

	/**
	 * Delete one.
	 *
	 * @param id
	 *     the id
	 */
	void deleteOne(UUID id);
}

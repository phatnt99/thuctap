package net.freetuts.backend.utils.comparator;

import java.util.Comparator;

import net.freetuts.backend.entity.Category;

/**
 * The Class SortByOrder.
 */
public class SortByOrder implements Comparator<Category>{

	/**
	 * Compare.
	 *
	 * @param o1 the o 1
	 * @param o2 the o 2
	 * @return the int
	 */
	@Override
	public int compare(Category o1, Category o2) {
		return o1.getOrder() - o2.getOrder();
	}

}

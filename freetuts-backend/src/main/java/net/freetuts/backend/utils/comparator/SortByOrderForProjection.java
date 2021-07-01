package net.freetuts.backend.utils.comparator;

import java.util.Comparator;

import net.freetuts.backend.dto.SeriesView;

/**
 * The Class SortByOrderForProjection.
 */
public class SortByOrderForProjection implements Comparator<SeriesView> {

	/**
	 * Compare.
	 *
	 * @param o1 the o 1
	 * @param o2 the o 2
	 * @return the int
	 */
	@Override
	public int compare(SeriesView o1, SeriesView o2) {
		return o1.getOrder() - o2.getOrder();
	}

}

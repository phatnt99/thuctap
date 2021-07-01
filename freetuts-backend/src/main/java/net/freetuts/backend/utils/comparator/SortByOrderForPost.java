package net.freetuts.backend.utils.comparator;

import java.util.Comparator;

import net.freetuts.backend.entity.Post;

/**
 * The Class SortByOrderForPost.
 */
public class SortByOrderForPost implements Comparator<Post> {

	/**
	 * Compare.
	 *
	 * @param o1 the o 1
	 * @param o2 the o 2
	 * @return the int
	 */
	@Override
	public int compare(Post o1, Post o2) {
		return o1.getOrder() - o2.getOrder();
	}

}

package net.freetuts.frontend.services;

import java.util.List;
import java.util.UUID;

import net.freetuts.frontend.model.HomePage;
import net.freetuts.frontend.model.PageAndSort;
import net.freetuts.frontend.model.Post;
import net.freetuts.frontend.model.PostDisplay;
import net.freetuts.frontend.model.PostForCategoryDisplay;
import net.freetuts.frontend.utils.RestPage;

/**
 * The Interface PostService.
 */
public interface PostService extends CrudService<Post> {

	PostDisplay getOneBySlug(String slug);

	RestPage<PostForCategoryDisplay> getAllByCategorySlug(String categorySlug,
			PageAndSort pageAndSort);

	RestPage<PostForCategoryDisplay> getAllByParentCategorySlug(
			String categorySlug, PageAndSort pageAndSort);

	Post getOneByPostableId(UUID postableId);

	List<HomePage> getPostForHome();
}

package net.freetuts.frontend.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import net.freetuts.frontend.model.Category;
import net.freetuts.frontend.model.CategoryDisplay;
import net.freetuts.frontend.model.MetaCategoryDTO;
import net.freetuts.frontend.model.MetaEntry;
import net.freetuts.frontend.model.PageAndSort;
import net.freetuts.frontend.services.CategoryService;
import net.freetuts.frontend.utils.PostUtil;
import net.freetuts.frontend.utils.RestPage;
import net.freetuts.frontend.utils.UrlUtil;

/**
 * The Class CategoryServiceImpl.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

	/** The rest template. */
	@Autowired
	RestTemplate restTemplate;

	/**
	 * Gets the all.
	 *
	 * @param pageAndSort the page and sort
	 * @return the all
	 */
	@Override
	public RestPage<Category> getAll(PageAndSort pageAndSort) {

		ParameterizedTypeReference<RestPage<Category>> responseType = new ParameterizedTypeReference<RestPage<Category>>() {
		};

		ResponseEntity<RestPage<Category>> responsePosts = restTemplate
				.exchange(
						UrlUtil.buildPageAndSort(UrlUtil.CATE_REST,
								pageAndSort),
						HttpMethod.GET, null,
						responseType);

		return responsePosts.getBody();
	}

	/**
	 * Creates the one.
	 *
	 * @param category the category
	 * @return the category
	 */
	@Override
	public Category createOne(Category category) {

		category.setSlug(PostUtil.makeSlugV2(category.getName()));

		ResponseEntity<Category> responseCategory = restTemplate
				.postForEntity(UrlUtil.CATE_ADMIN_REST, category, Category.class);

		return responseCategory.getBody();
	}

	/**
	 * Gets the one.
	 *
	 * @param id the id
	 * @return the one
	 */
	@Override
	public Category getOne(UUID id) {
		ResponseEntity<Category> responsePost = restTemplate
				.getForEntity(UrlUtil.CATE_REST + "/" + id.toString(),
						Category.class);

		return responsePost.getBody();
	}

	/**
	 * Update one.
	 *
	 * @param category the category
	 */
	@Override
	public void updateOne(Category category) {

		restTemplate.put(UrlUtil.CATE_ADMIN_REST, category);
	}

	/**
	 * Delete one.
	 *
	 * @param id the id
	 */
	@Override
	public void deleteOne(UUID id) {

		restTemplate.delete(UrlUtil.CATE_ADMIN_REST + "/" + id.toString());
	}

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	@Override
	public List<Category> getAll() {
		ResponseEntity<Category[]> responseCategories = restTemplate
				.getForEntity(UrlUtil.CATE_REST + "/all", Category[].class);

		return Arrays.asList(responseCategories.getBody());
	}

	/**
	 * Gets the one by slug.
	 *
	 * @param slug the slug
	 * @return the one by slug
	 */
	@Override
	public CategoryDisplay getOneBySlug(String slug) {

		ResponseEntity<CategoryDisplay> responsePost = restTemplate
				.getForEntity(UrlUtil.CATE_REST + "/slug/" + slug,
						CategoryDisplay.class);

		return responsePost.getBody();
	}

	/**
	 * Gets the meta data.
	 *
	 * @param dto the dto
	 * @return the meta data
	 */
	@Override
	public List<MetaEntry> getMetaData(MetaCategoryDTO dto) {
		ResponseEntity<MetaEntry[]> responsePost = restTemplate
				.postForEntity(UrlUtil.CATE_REST + "/meta", dto,
						MetaEntry[].class);

		return Arrays.asList(responsePost.getBody());
	}

	/**
	 * Gets the all thread.
	 *
	 * @return the all thread
	 */
	@Override
	public List<Category> getAllThread() {
		ResponseEntity<Category[]> responseCategories = restTemplate
				.getForEntity(UrlUtil.CATE_REST + "/thread/all", Category[].class);

		return Arrays.asList(responseCategories.getBody());
	}
	
	/**
	 * Gets the by parent id.
	 *
	 * @param id the id
	 * @return the by parent id
	 */
	@Override
	public List<Category> getByParentId(UUID id) {
		ResponseEntity<Category[]> responseCategories = restTemplate
				.getForEntity(UrlUtil.CATE_REST + "/child/" + id.toString(), Category[].class);

		return Arrays.asList(responseCategories.getBody());
	}
	
	/**
	 * Gets the by path.
	 *
	 * @param path the path
	 * @return the by path
	 */
	@Override
	public List<Category> getByPath(String path) {
		ResponseEntity<Category[]> responseCategories = restTemplate.getForEntity(UrlUtil.CATE_REST + "/path/" + path,
				Category[].class);

		return Arrays.asList(responseCategories.getBody());
	}

}

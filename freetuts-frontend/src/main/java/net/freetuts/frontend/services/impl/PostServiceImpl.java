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

import net.freetuts.frontend.model.HomePage;
import net.freetuts.frontend.model.PageAndSort;
import net.freetuts.frontend.model.Post;
import net.freetuts.frontend.model.PostDisplay;
import net.freetuts.frontend.model.PostForCategoryDisplay;
import net.freetuts.frontend.services.PostService;
import net.freetuts.frontend.utils.PostUtil;
import net.freetuts.frontend.utils.RestPage;
import net.freetuts.frontend.utils.UrlUtil;

/**
 * The Class PostServiceImpl.
 */
@Service
public class PostServiceImpl implements PostService {

	/** The rest template. */
	@Autowired
	RestTemplate restTemplate;

	/**
	 * Gets the all.
	 *
	 * @param pageAndSort
	 *     the page and sort
	 * 
	 * @return the all
	 */
	@Override
	public RestPage<Post> getAll(PageAndSort pageAndSort) {

		ParameterizedTypeReference<RestPage<Post>> responseType = new ParameterizedTypeReference<RestPage<Post>>() {
		};

		ResponseEntity<RestPage<Post>> responsePosts = restTemplate
				.exchange(
						UrlUtil.buildPageAndSort(UrlUtil.POST_REST,
								pageAndSort),
						HttpMethod.GET, null,
						responseType);

		return responsePosts.getBody();
	}

	/**
	 * Creates the one.
	 *
	 * @param post
	 *     the post
	 * 
	 * @return the post
	 */
	@Override
	public Post createOne(Post post) {
		// TODO set status depend on button
		// post.setStatus(PostTypeEnum.);
		post.setStatus(1);

		post.setSlug(PostUtil.makeSlugV2(post.getTitle()));

		// check post type
		// if post type = 'POST'
		// nothing change
		// if post type = 'LESSON'
		// parent is chapter

		ResponseEntity<Post> responsePost = restTemplate
				.postForEntity(UrlUtil.POST_REST, post, Post.class);

		return responsePost.getBody();
	}

	/**
	 * Gets the one.
	 *
	 * @param id
	 *     the id
	 * 
	 * @return the one
	 */
	@Override
	public Post getOne(UUID id) {

		ResponseEntity<Post> responsePost = restTemplate
				.getForEntity(UrlUtil.POST_REST + "/" + id.toString(),
						Post.class);

		return responsePost.getBody();
	}

	/**
	 * Update one.
	 *
	 * @param post
	 *     the post
	 */
	@Override
	public void updateOne(Post post) {

		restTemplate.put(UrlUtil.POST_REST + "/" + post.getId(), post);
	}

	/**
	 * Delete one.
	 *
	 * @param id
	 *     the id
	 */
	@Override
	public void deleteOne(UUID id) {

		restTemplate.delete(UrlUtil.POST_REST + "/" + id.toString());
	}

	@Override
	public PostDisplay getOneBySlug(String slug) {

		ResponseEntity<PostDisplay> responsePost = restTemplate
				.getForEntity(UrlUtil.POST_REST + "/slug/" + slug,
						PostDisplay.class);

		// khúc này có the nhan 404
		
		return responsePost.getBody();
	}

	@Override
	public RestPage<PostForCategoryDisplay> getAllByCategorySlug(
			String categorySlug, PageAndSort pageAndSort) {

		ParameterizedTypeReference<RestPage<PostForCategoryDisplay>> responseType = new ParameterizedTypeReference<RestPage<PostForCategoryDisplay>>() {
		};

		ResponseEntity<RestPage<PostForCategoryDisplay>> responsePosts = restTemplate
				.exchange(
						UrlUtil.buildPageAndSort(
								UrlUtil.POST_REST + "/category/" + categorySlug,
								pageAndSort),
						HttpMethod.GET, null,
						responseType);

		return responsePosts.getBody();
	}
	
	public RestPage<PostForCategoryDisplay> getAllByParentCategorySlug(
			String categorySlug, PageAndSort pageAndSort) {
		ParameterizedTypeReference<RestPage<PostForCategoryDisplay>> responseType = new ParameterizedTypeReference<RestPage<PostForCategoryDisplay>>() {
		};

		ResponseEntity<RestPage<PostForCategoryDisplay>> responsePosts = restTemplate
				.exchange(
						UrlUtil.buildPageAndSort(
								UrlUtil.POST_REST + "/category/" + categorySlug + "/all",
								pageAndSort),
						HttpMethod.GET, null,
						responseType);

		return responsePosts.getBody();
	}

	@Override
	public Post getOneByPostableId(UUID postableId) {

		ResponseEntity<Post> responsePost = restTemplate
				.getForEntity(
						UrlUtil.POST_REST + "/postable/"
								+ postableId.toString(),
						Post.class);

		return responsePost.getBody();
	}

	@Override
	public List<HomePage> getPostForHome() {
		ResponseEntity<HomePage[]> responsePost = restTemplate
				.getForEntity(UrlUtil.POST_REST + "/home",
						HomePage[].class);

		return Arrays.asList(responsePost.getBody());
	}

}

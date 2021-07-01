package net.freetuts.backend.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.freetuts.backend.constant.PostTypeEnum;
import net.freetuts.backend.dto.BreadCrumb;
import net.freetuts.backend.dto.HomePage;
import net.freetuts.backend.dto.PostCreateDTO;
import net.freetuts.backend.dto.PostDisplayDTO;
import net.freetuts.backend.dto.PostForCategoryDTO;
import net.freetuts.backend.dto.PostUpdateDTO;
import net.freetuts.backend.entity.Category;
import net.freetuts.backend.entity.Post;
import net.freetuts.backend.exception.domain.PostNotFoundException;
import net.freetuts.backend.repository.CategoryRepository;
import net.freetuts.backend.repository.PostRepository;
import net.freetuts.backend.repository.specification.CategorySpecification;
import net.freetuts.backend.services.PostService;
import net.freetuts.backend.utils.ObjectUtil;

/**
 * The Class PostServiceImpl.
 */
@Service
public class PostServiceImpl implements PostService {

	/** The post repository. */
	@Autowired
	PostRepository postRepository;

	@Autowired
	CategoryRepository categoryRepository;

	/**
	 * Gets the all.
	 *
	 * @param pageable
	 *     the pageable
	 * 
	 * @return the all
	 */
	@Override
	public Page<Post> getAll(Pageable pageable) {

		return postRepository.findAll(pageable);
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
		return postRepository.findById(id).orElse(null);
	}

	/**
	 * Creates the one.
	 *
	 * @param entityDTO
	 *     the entity DTO
	 * 
	 * @return the post
	 */
	@Override
	public Post createOne(Object entityDTO) {
		PostCreateDTO postCreateDTO = (PostCreateDTO) entityDTO;
		Post          post          = ObjectUtil.copyPropertiesObject(
				postCreateDTO,
				Post.class);

		post.setCategory(categoryRepository
				.findById(postCreateDTO.getCategoryId()).orElseThrow());

		return postRepository.save(post);
	}

	/**
	 * Update one.
	 *
	 * @param entityDTO
	 *     the entity DTO
	 */
	@Override
	public void updateOne(Object entityDTO) {
		PostUpdateDTO dto  = (PostUpdateDTO) entityDTO;
		Post          post = postRepository.findById(dto.getId()).orElseThrow();

		// map dto to exist entity
		dto.mapToExistEntity(post);

		// change parent category
		if (dto.getCategoryId() != null && post.getCategory() == null ||
				post.getCategory() != null && !post.getCategory().getId()
						.equals(dto.getCategoryId())) {
			Category newCategory = categoryRepository
					.findById(dto.getCategoryId()).orElse(null);
			post.setCategory(newCategory);
		}

		postRepository.save(post);
	}

	/**
	 * Delete one.
	 *
	 * @param id
	 *     the id
	 */
	@Override
	public void deleteOne(UUID id) {

		postRepository.deleteById(id);
	}

	@Override
	public PostDisplayDTO getOneBySlug(String slug) {

		Post post = postRepository.findBySlug(slug)
				.orElseThrow(() -> new PostNotFoundException());

		BreadCrumb bc = buildBreadcrumb(post);

		PostDisplayDTO postDisplayDTO = new PostDisplayDTO();
		postDisplayDTO.setPost(post);
		postDisplayDTO.setBc(bc);

		return postDisplayDTO;
	}

	@Override
	public Page<PostForCategoryDTO> getByCategorySlug(String categorySlug,
			Pageable pageable) {

		return postRepository.findAllByCategorySlug(categorySlug, pageable);
	}

	@Override
	public Post getOneByPostableId(UUID pid) {

		return postRepository.findByPostableId(pid).orElse(null);
	}

	@Override
	public List<HomePage> getTop5PostEachCateForHome() {
		List<Category> topMenuCategory = categoryRepository
				.findAll(CategorySpecification.isTopMenu());

		List<HomePage> homePages = new ArrayList<HomePage>();

		// loop all category and select top 5 post order by updatedAt
		for (Category cate : topMenuCategory) {
			HomePage home = new HomePage();
			home.setCateName(cate.getName());
			List<PostForCategoryDTO> top5Post = postRepository
					.findTop5ByCategoryPathStartingWithOrderByUpdatedAtDesc(
							cate.getSlug());
			home.setPosts(top5Post);
			homePages.add(home);
		}
		return homePages;
	}

	@Override
	public Page<PostForCategoryDTO> getByParentCategorySlug(String categorySlug,
			Pageable pageable) {

		return postRepository.findAllByCategoryPathContainingAndPostTypeIs(
				categorySlug, PostTypeEnum.POST.getValue(), pageable);
	}

	private BreadCrumb buildBreadcrumb(Post post) {
		// build breadcrumb
		BreadCrumb bc = new BreadCrumb();
		// get slug entries
		if (post.getCategory() != null) {
			String[] slugEntries = post.getCategory().getPath().split("/");
			// since the last entry is the post's slug, just skip it
			for (String categorySlug : slugEntries) {
				Category parentCategory = categoryRepository
						.findBySlug(categorySlug).orElse(null);
				if (parentCategory == null)
					continue;
				bc.addEntry(parentCategory.getName(), parentCategory.getSlug());
			}
		}

		// if it has no parent category, just add it into last entry
		bc.addEntry(post.getTitle(), post.getSlug());

		return bc;
	}
}

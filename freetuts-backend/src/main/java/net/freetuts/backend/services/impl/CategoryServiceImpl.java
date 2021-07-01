package net.freetuts.backend.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.freetuts.backend.constant.PatternTypeEnum;
import net.freetuts.backend.constant.PostTypeEnum;
import net.freetuts.backend.dto.BreadCrumb;
import net.freetuts.backend.dto.CategoryCreateDTO;
import net.freetuts.backend.dto.CategoryDisplayDTO;
import net.freetuts.backend.dto.CategoryUpdateDTO;
import net.freetuts.backend.dto.MetaCategoryDTO;
import net.freetuts.backend.dto.MetaEntry;
import net.freetuts.backend.dto.SeriesHolder;
import net.freetuts.backend.dto.SeriesView;
import net.freetuts.backend.dto.ThreadSeriView;
import net.freetuts.backend.dto.ThreadView;
import net.freetuts.backend.entity.Category;
import net.freetuts.backend.entity.Post;
import net.freetuts.backend.exception.domain.CategoryNotFoundException;
import net.freetuts.backend.repository.CategoryRepository;
import net.freetuts.backend.repository.PostRepository;
import net.freetuts.backend.repository.specification.CategorySpecification;
import net.freetuts.backend.services.CategoryService;
import net.freetuts.backend.utils.ObjectUtil;
import net.freetuts.backend.utils.comparator.SortByOrder;
import net.freetuts.backend.utils.comparator.SortByOrderForProjection;

/**
 * The Class CategoryServiceImpl.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

	/** The category repository. */
	@Autowired
	private CategoryRepository categoryRepository;

	/** The post repository. */
	@Autowired
	private PostRepository postRepository;

	/**
	 * Gets the all.
	 *
	 * @param pageable the pageable
	 * @return the all
	 */
	@Override
	public Page<Category> getAll(Pageable pageable) {

		return categoryRepository.findAll(pageable);
	}

	/**
	 * Gets the one.
	 *
	 * @param id the id
	 * @return the one
	 */
	@Override
	public Category getOne(UUID id) {

		return categoryRepository.findById(id).orElseThrow();
	}

	/**
	 * Creates the one.
	 *
	 * @param entityDTO the entity DTO
	 * @return the category
	 */
	@Override
	public Category createOne(Object entityDTO) {

		CategoryCreateDTO categoryDTO = (CategoryCreateDTO) entityDTO;
		Category          category    = ObjectUtil.copyPropertiesObject(
				entityDTO,
				Category.class);

		// retrieve path from parentId
		if (categoryDTO.getParentId() != null) {
			Category parentCategory = categoryRepository
					.getOne(categoryDTO.getParentId());
			category.setPath(parentCategory.getPath());
			category.setPcategory(parentCategory);
		} else {
			category.setPath(null);
		}

		// save category entity
		Category savedCategory = categoryRepository.save(category);

		// retrieve pattern if present
		if (categoryDTO.getPattern() != null || PatternTypeEnum.USUAL.getValue()
				.equals(categoryDTO.getPattern())) {
			// create new post to store pattern temporary
			Post categoryPattern = new Post();
			categoryPattern.setPostableId(savedCategory.getId());
			categoryPattern.setPostType(PostTypeEnum.CATEGORY.getValue());
			categoryPattern.setType(categoryDTO.getPattern());

			postRepository.save(categoryPattern);
		}

		return savedCategory;
	}

	/**
	 * Update one.
	 *
	 * @param entityDTO the entity DTO
	 */
	@Override
	public void updateOne(Object entityDTO) {

		CategoryUpdateDTO categoryDTO = (CategoryUpdateDTO) entityDTO;
		Category          category    = categoryRepository
				.findById(categoryDTO.getId()).orElseThrow();

		categoryDTO.mapToExistEntity(category);

		// update parent
		if ((category.getPcategory() != null && !category.getPcategory().getId()
				.equals(categoryDTO.getParentId())) ||
				(categoryDTO.getParentId() != null
						&& category.getPcategory() == null)) {
			// parent has changed
			Category parent = categoryRepository
					.findById(categoryDTO.getParentId()).orElseThrow();

			category.setPcategory(parent);
			category.setPath(parent.getPath());
		}

		categoryRepository.save(category);

	}

	/**
	 * Delete one.
	 *
	 * @param id the id
	 */
	@Override
	public void deleteOne(UUID id) {

		categoryRepository.deleteById(id);

	}

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	// Get all for parent category select box
	@Override
	public List<Category> getAll() {

		return categoryRepository.findAll();
	}

	/**
	 * Gets the all is top menu.
	 *
	 * @return the all is top menu
	 */
	@Override
	public List<Category> getAllIsTopMenu() {
		List<Category> categories = categoryRepository
				.findAll(CategorySpecification.isTopMenu());

		Collections.sort(categories, new SortByOrder());

		return categories;
	}

	/**
	 * Gets the one by slug.
	 *
	 * @param slug the slug
	 * @return the one by slug
	 */
	@Override
	public CategoryDisplayDTO getOneBySlug(String slug) {

		Category category = categoryRepository.findBySlug(slug)
				.orElseThrow(() -> new CategoryNotFoundException());

		// pattern
		Post pattern = postRepository.findByPostableIdAndPostType(
				category.getId(), PostTypeEnum.CATEGORY.getValue());
		if (pattern != null) {
			category.setPattern(pattern.getType());
		} else {
			category.setPattern("USUAL");
		}
		// build breadcrumb
		BreadCrumb bc = new BreadCrumb();

		if (category.getPcategory() != null) {
			// has parent category
			String[] slugEntries = category.getPcategory().getPath().split("/");

			for (String categorySlug : slugEntries) {
				Category parentCategory = categoryRepository
						.findBySlug(categorySlug).orElse(null);
				if (parentCategory == null)
					continue;
				bc.addEntry(parentCategory.getName(), parentCategory.getSlug());
			}
		}

		// if no parent category provided, just add it into last entry
		bc.addEntry(category.getName(), category.getSlug());

		CategoryDisplayDTO categoryDisplayDTO = new CategoryDisplayDTO();
		categoryDisplayDTO.setCategory(category);
		categoryDisplayDTO.setBc(bc);

		return categoryDisplayDTO;
	}

	/**
	 * Gets the meta data.
	 *
	 * @param dto the dto
	 * @return the meta data
	 */
	@Override
	public List<MetaEntry> getMetaData(MetaCategoryDTO dto) {
		Category category = categoryRepository
				.findById(dto.getCategoryId())
				.orElseThrow();

		List<MetaEntry> entries = new ArrayList<MetaEntry>();
		// get pattern
		Post pattern = postRepository.findByPostableIdAndPostType(
				category.getId(), PostTypeEnum.CATEGORY.getValue());
		if (pattern == null) {
			// no pattern -> implicit list all child post
			return entries;
		}

		if ("TOPIC".equals(pattern.getType())) {
			// topic pattern
			// get all child categories of current
			List<Category> childCategory = category.getCcategory();
			for (Category cate : childCategory) {
				MetaEntry childEntry = new MetaEntry();
				childEntry.setLabel(cate.getName());
				// get all child of current child
				List<Category> childrens = cate.getCcategory();
				for (Category child : childrens) {
					// get thumbnail
					Post seriesPattern = postRepository
							.findByPostableId(child.getId()).orElse(null);

					childEntry.setOneEntry(child.getName(), child.getSlug(),
							seriesPattern != null ? seriesPattern.getThumbnail()
									: null);
				}
				entries.add(childEntry);
			}
		} else if ("THREAD".equals(pattern.getType())) {
			// thread pattern
			// just has 1 collection
			List<Category> childCategory = category.getCcategory();
			MetaEntry      meta          = new MetaEntry();
			for (Category cate : childCategory) {
				Post seriesPattern = postRepository
						.findByPostableId(cate.getId()).orElse(null);

				meta.setOneEntry(cate.getName(), cate.getSlug(),
						seriesPattern != null ? seriesPattern.getThumbnail()
								: null);
			}
			entries.add(meta);
		}

		return entries;
	}

	/**
	 * Gets the series by tutorial.
	 *
	 * @param id the id
	 * @return the series by tutorial
	 */
	public List<SeriesView> getSeriesByTutorial(UUID id) {

		// retrieve Pcategory of given chapter
		Category chapter = categoryRepository.findById(id).orElseThrow();

		List<SeriesView> series = categoryRepository
				.findByPcategoryId(chapter.getPcategory().getId(),
						SeriesView.class);

		Collections.sort(series, new SortByOrderForProjection());

		return series;
	}

	/**
	 * Gets the series by thread.
	 *
	 * @param id the id
	 * @return the series by thread
	 */
	@Override
	public List<SeriesHolder> getSeriesByThread(UUID id) {

		List<ThreadView>   threadViews = categoryRepository
				.findByPcategoryId(id, ThreadView.class);
		List<SeriesHolder> holders     = new ArrayList<SeriesHolder>();

		for (ThreadView view : threadViews) {
			SeriesHolder holder = new SeriesHolder();
			holder.setId(view.getId());
			holder.setName(view.getName());
			holder.setCcategory(view.getCcategory());
			// retrieve excerpt from post
			holder.setExcerpt(postRepository.findByPostableId(view.getId())
					.get().getExcerpt());
			holders.add(holder);
		}
		return holders;
	}

	/**
	 * Gets the series by chapter.
	 *
	 * @param id the id
	 * @return the series by chapter
	 */
	@Override
	public List<SeriesHolder> getSeriesByChapter(UUID id) {
		// retrieve Pcategory of given chapter
		Category chapter = categoryRepository.findById(id).orElseThrow();
		// Pcategory is Topic so Pcategory of Topic is Thread
		UUID threadId = chapter.getPcategory().getPcategory().getId();

		List<ThreadView>   threadViews = categoryRepository
				.findByPcategoryId(threadId, ThreadView.class);
		List<SeriesHolder> holders     = new ArrayList<SeriesHolder>();

		for (ThreadView view : threadViews) {
			SeriesHolder holder = new SeriesHolder();
			holder.setId(view.getId());
			holder.setName(view.getName());
			holder.setCcategory(view.getCcategory());
			// retrieve excerpt from post
			holder.setExcerpt(postRepository.findByPostableId(view.getId())
					.get().getExcerpt());
			holders.add(holder);
		}
		return holders;
	}

	/**
	 * Gets the thread.
	 *
	 * @return the thread
	 */
	@Override
	public List<Category> getThread() {
		// Get all category and their post
		// further improve: assign column for identity type of category
		List<Category> categories = categoryRepository.findAll();
		List<Category> result     = new ArrayList<Category>();
		for (Category cate : categories) {
			// get post
			Post pattern = postRepository.findByPostableId(cate.getId())
					.orElse(null);
			if (pattern != null && PatternTypeEnum.THREAD.getValue()
					.equals(pattern.getType())) {
				result.add(cate);
			}
		}
		return result;
	}

	/**
	 * Gets the serie by id.
	 *
	 * @param id the id
	 * @return the serie by id
	 */
	@Override
	public ThreadView getSerieById(UUID id) {
		// Get serie
		ThreadView threadViews = categoryRepository
				.findById(id, ThreadView.class).orElse(null);

		return threadViews;
	}

	/**
	 * Gets the thread series in lesson by chapter.
	 *
	 * @param id the id
	 * @return the thread series in lesson by chapter
	 */
	@Override
	public List<ThreadSeriView> getThreadSeriesInLessonByChapter(UUID id) {
		Category chapter = categoryRepository.findById(id).orElseThrow();
		// Pcategory is Topic so Pcategory of Topic is Thread
		UUID threadId = chapter.getPcategory().getPcategory().getId();

		return categoryRepository
				.findByPcategoryId(threadId, ThreadSeriView.class);
	}

	/**
	 * Gets the all chapter.
	 *
	 * @return the all chapter
	 */
	@Override
	public List<ThreadSeriView> getAllChapter() {
		// get all post type series
		List<Post> seriesPattern = postRepository
				.findByType(PatternTypeEnum.SERIES.getValue(), Post.class);

		// retrieve all ids
		List<UUID> threadIds = seriesPattern.stream()
				.map(pattern -> pattern.getPostableId())
				.collect(Collectors.toList());

		// find all chapter with parentid in threadIds
		List<ThreadSeriView> chapters = categoryRepository
				.findByPcategoryIdIn(threadIds, ThreadSeriView.class);

		return chapters;
	}

	/**
	 * Gets the by parent id.
	 *
	 * @param id the id
	 * @return the by parent id
	 */
	@Override
	public List<Category> getByParentId(UUID id) {

		return categoryRepository.findByPcategoryId(id, Category.class);

	}

	/**
	 * Gets the by path.
	 *
	 * @param path the path
	 * @return the by path
	 */
	@Override
	public List<Category> getByPath(String path) {

		return categoryRepository
				.findByPathStartingWithAndPcategoryIdNotNull(path);
	}

}

package net.freetuts.backend.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.freetuts.backend.constant.PostTypeEnum;
import net.freetuts.backend.dto.CategoryCreateDTO;
import net.freetuts.backend.dto.ChapterView;
import net.freetuts.backend.dto.IndividualTutorial;
import net.freetuts.backend.dto.PostableIdView;
import net.freetuts.backend.dto.Tutorial;
import net.freetuts.backend.dto.TutorialCreateDTO;
import net.freetuts.backend.dto.TutorialUpdateDTO;
import net.freetuts.backend.entity.Category;
import net.freetuts.backend.entity.Post;
import net.freetuts.backend.repository.CategoryRepository;
import net.freetuts.backend.repository.PostRepository;
import net.freetuts.backend.services.TutorialService;
import net.freetuts.backend.utils.ObjectUtil;

/**
 * The Class TutorialServiceImpl.
 */
@Service
public class TutorialServiceImpl implements TutorialService {

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
	public Page<Tutorial> getAll(Pageable pageable) {
		// get id collection from post where post_type = SERIES
		List<PostableIdView> tutorialIds = postRepository
				.findByType(PostTypeEnum.SERIES.getValue(), PostableIdView.class);
		List<UUID>           ids         = tutorialIds.stream()
				.map(PostableIdView::getPostableId)
				.collect(Collectors.toList());

		Page<Tutorial> res = categoryRepository.findByIdIn(
				ids,
				pageable,
				Tutorial.class);

		return res;
	}

	/**
	 * Creates the one.
	 *
	 * @param dto the dto
	 * @return the category
	 */
	@Override
	public Category createOne(TutorialCreateDTO dto) {
		// create category for tutorial instance
		CategoryCreateDTO categoryDTO = ObjectUtil.copyPropertiesObject(dto,
				CategoryCreateDTO.class);
		Category          tutorial    = ObjectUtil.copyPropertiesObject(
				categoryDTO,
				Category.class);

		// retrieve path from parentId
		if (categoryDTO.getParentId() != null) {
			Category parentCategory = categoryRepository
					.getOne(categoryDTO.getParentId());
			tutorial.setPath(parentCategory.getPath());
			tutorial.setPcategory(parentCategory);
		} else {
			tutorial.setPath(null);
		}

		tutorial = categoryRepository.save(tutorial);
		// retrieve pattern if present
		if (categoryDTO.getPattern() != null) {
			// pattern here is SERIES belong to CATEGORY
			// create new post to store pattern temporary
			Post categoryPattern = new Post();
			categoryPattern.setThumbnail(dto.getThumbnail());
			categoryPattern.setPostableId(tutorial.getId());
			categoryPattern.setPostType(PostTypeEnum.CATEGORY.getValue());
			categoryPattern.setType(categoryDTO.getPattern());

			postRepository.save(categoryPattern);
		}

		// create chapter, like category
		// just store name, parent Id
		for (String chaper : dto.getFields()) {
			if (chaper == null)
				continue;
			Category chaperCate = new Category();
			chaperCate.setName(chaper);
			// set parent
			chaperCate.setPcategory(tutorial);

			categoryRepository.save(chaperCate);
		}

		return tutorial;
	}

	/**
	 * Gets the one.
	 *
	 * @param id the id
	 * @return the one
	 */
	@Override
	public IndividualTutorial getOne(UUID id) {

		return categoryRepository.findById(id, IndividualTutorial.class)
				.orElseThrow();
	}

	/**
	 * Update one.
	 *
	 * @param dto the dto
	 */
	@Override
	public void updateOne(TutorialUpdateDTO dto) {
		Category tutorial = categoryRepository
				.findById(dto.getId()).orElseThrow();

		ModelMapper mapper = new ModelMapper();

		mapper.map(dto, tutorial);

		// update parent
		if ((tutorial.getPcategory() != null && !tutorial.getPcategory().getId()
				.equals(dto.getParentId())) ||
				(dto.getParentId() != null
						&& tutorial.getPcategory() == null)) {
			// parent has changed
			Category parent = categoryRepository
					.findById(dto.getParentId()).orElseThrow();

			tutorial.setPcategory(parent);
			tutorial.setPath(parent.getPath());
		}
		
		// update thumbnail
		Post pattern = postRepository.findByPostableId(tutorial.getId()).orElse(null);
		if(pattern != null) {
			pattern.setThumbnail(dto.getThumbnail());
		}
		
		List<Category> newChapters = tutorial.getCcategory();
		// update chapter
		if (dto.getCcategoryIds() != null && dto.getCcategoryIds()
				.size() < tutorial.getCcategory().size()) {
			List<Category> removeEntities = new ArrayList<Category>();
			// has remove some cater
			for (Category cate : tutorial.getCcategory()) {
				if (!dto.getCcategoryIds().contains(cate.getId())) {
					removeEntities.add(cate);
				}
			}

			newChapters.removeAll(removeEntities);
			categoryRepository.deleteAll(removeEntities);
		}

		// add new chapter
		for (String chapter : dto.getFields()) {
			if (chapter == null || chapter.isEmpty())
				continue;
			Category chaperCate = new Category();
			chaperCate.setName(chapter);
			// set parent
			chaperCate.setPcategory(tutorial);

			chaperCate = categoryRepository.save(chaperCate);
			newChapters.add(chaperCate);
		}

		// set newchapter list
		tutorial.setCcategory(newChapters);

		categoryRepository.save(tutorial);

	}

	/**
	 * Gets the all chapter by tutorial.
	 *
	 * @param id the id
	 * @return the all chapter by tutorial
	 */
	@Override
	public List<ChapterView> getAllChapterByTutorial(UUID id) {

		return categoryRepository.findByPcategoryId(id, ChapterView.class);
	}

}

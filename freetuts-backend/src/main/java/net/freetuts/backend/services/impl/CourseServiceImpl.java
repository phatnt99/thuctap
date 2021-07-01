package net.freetuts.backend.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.freetuts.backend.dto.BreadCrumb;
import net.freetuts.backend.dto.ComboCreateDTO;
import net.freetuts.backend.dto.ComboUpdateDTO;
import net.freetuts.backend.dto.CourseCreateDTO;
import net.freetuts.backend.dto.CourseDisplayDTO;
import net.freetuts.backend.dto.CourseResponse;
import net.freetuts.backend.dto.CourseUpdateDTO;
import net.freetuts.backend.entity.Category;
import net.freetuts.backend.entity.Course;
import net.freetuts.backend.entity.Post;
import net.freetuts.backend.exception.domain.CategoryNotFoundException;
import net.freetuts.backend.exception.domain.CourseNotFoundException;
import net.freetuts.backend.repository.CategoryRepository;
import net.freetuts.backend.repository.CourseRepository;
import net.freetuts.backend.repository.PostRepository;
import net.freetuts.backend.services.CourseService;
import net.freetuts.backend.utils.ObjectUtil;

/**
 * The Class CourseServiceImpl.
 */
@Service
public class CourseServiceImpl implements CourseService {

	/** The course repository. */
	@Autowired
	private CourseRepository courseRepository;

	/** The post repository. */
	@Autowired
	private PostRepository postRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	/**
	 * Gets the all.
	 *
	 * @param pageable
	 *     the pageable
	 * 
	 * @return the all
	 */
	@Override
	public Page<Course> getAll(Pageable pageable) {

		return courseRepository.findAll(pageable);
	}

	/**
	 * Gets the all course only.
	 *
	 * @return the all course only
	 */
	@Override
	public List<CourseResponse> getAllCourseOnly() {
		List<CourseResponse> listData = new ArrayList<CourseResponse>();
		List<Course>         courses  = courseRepository.findAllByType(0);
		for (Course item : courses) {
			CourseResponse courseResponse = ObjectUtil
					.copyPropertiesObject(item, CourseResponse.class);
			Post           post           = postRepository
					.findByPostableId(item.getId()).orElse(null);
			courseResponse.setPost(post != null ? post : new Post());
			listData.add(courseResponse);
		}
		return listData;
	}

	/**
	 * Gets the all combo only.
	 *
	 * @return the all combo only
	 */
	@Override
	public List<CourseResponse> getAllComboOnly() {
		List<CourseResponse> listData = new ArrayList<CourseResponse>();
		List<Course>         courses  = courseRepository.findAllByType(1);
		for (Course item : courses) {
			CourseResponse courseResponse = ObjectUtil
					.copyPropertiesObject(item, CourseResponse.class);
			Post           post           = postRepository
					.findByPostableId(item.getId()).orElse(null);
			courseResponse.setPost(post != null ? post : new Post());
			listData.add(courseResponse);
		}
		return listData;
	}

	/**
	 * Gets the all course.
	 *
	 * @return the all course
	 */
	@Override
	public List<CourseResponse> getAllCourse() {
		List<CourseResponse> listData = new ArrayList<CourseResponse>();
		List<Course>         courses  = courseRepository.findAll();
		for (Course item : courses) {
			CourseResponse courseResponse = ObjectUtil
					.copyPropertiesObject(item, CourseResponse.class);
			Post           post           = postRepository
					.findByPostableId(item.getId()).orElse(null);
			courseResponse.setPost(post != null ? post : new Post());
			listData.add(courseResponse);
		}
		return listData;
	}

	/**
	 * Creates the combo.
	 *
	 * @param entityDTO
	 *     the entity DTO
	 * 
	 * @return the course
	 */
	@Override
	public Course createCombo(ComboCreateDTO entityDTO) {

		Course       course     = ObjectUtil.copyPropertiesObject(entityDTO,
				Course.class);
		List<Course> listCourse = new ArrayList<Course>();

		for (UUID id : entityDTO.getCourseIds()) {
			listCourse.add(courseRepository.findById(id).get());
		}

		course.setCourses(listCourse);

		return courseRepository.save(course);
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
	public Course getOne(UUID id) {
		return courseRepository.findById(id).get();
	}

	@Override
	public CourseResponse getOneById(UUID id) {

		// System.out.println("vô");

		Course course = courseRepository.findById(id)
				.orElseThrow(() -> new CategoryNotFoundException(
						"Course Not Found For ID : " + id));

		Post           post           = postRepository.findByPostableId(id)
				.orElse(null);
		CourseResponse courseResponse = ObjectUtil.copyPropertiesObject(course,
				CourseResponse.class);
		courseResponse.setPost(post != null ? post : new Post());

		return courseResponse;

	}

	/**
	 * Creates the one.
	 *
	 * @param entityDTO
	 *     the entity DTO
	 * 
	 * @return the course
	 */
	@Override
	public Course createOne(Object entityDTO) {

		Course course = ObjectUtil.copyPropertiesObject(
				(CourseCreateDTO) entityDTO, Course.class);

		return courseRepository.save(course);
	}

	/**
	 * Update one.
	 *
	 * @param entityDTO
	 *     the entity DTO
	 */
	@Override
	public void updateOne(Object entityDTO) {
		Course course = ObjectUtil.copyPropertiesObject(
				(CourseUpdateDTO) entityDTO, Course.class);

		courseRepository.save(course);
	}

	/**
	 * Delete one.
	 *
	 * @param id
	 *     the id
	 */
	@Override
	public void deleteOne(UUID id) {

		courseRepository.deleteById(id);
	}

	/**
	 * Update combo.
	 *
	 * @param entityDTO
	 *     the entity DTO
	 * 
	 * @return the course
	 */
	@Override
	public Course updateCombo(ComboUpdateDTO entityDTO) {

		Course course = ObjectUtil.copyPropertiesObject(entityDTO,
				Course.class);

		List<Course> listCourse = new ArrayList<Course>();

		for (UUID id : entityDTO.getCourseIds()) {
			listCourse.add(courseRepository.findById(id).get());
		}

		course.setCourses(listCourse);

		return courseRepository.save(course);
	}

	/**
	 * Gets the all course only by category.
	 *
	 * @param id
	 *     the id
	 * 
	 * @return the all course only by category
	 */
	@Override
	public List<CourseResponse> getAllCourseOnlyByCategory(UUID id) {

		List<CourseResponse> listData = new ArrayList<CourseResponse>();
		List<Course>         courses  = courseRepository.findAllByType(0);

		// System.out.println("length courses = " + courses.size());

		for (Course item : courses) {
			Post post = postRepository.findByPostableId(item.getId())
					.orElse(null);

			// System.out.println("id " + id);
			// System.out.println("item id " + item.getId());
			// System.out.println("post id " + post.getId());
			// System.out.println("post.getCategory().getId() id" +
			// post.getCategory().getId());

			if (post != null && post.getCategory().getId().equals(id)) {

				// System.out.println("vô");

				CourseResponse courseResponse = ObjectUtil
						.copyPropertiesObject(item, CourseResponse.class);
				courseResponse.setPost(post);
				listData.add(courseResponse);
			}

		}

		// System.out.println("length result " + listData.size());
		return listData;
	}

	/**
	 * Gets the all combo only by slug.
	 *
	 * @param slug
	 *     the slug
	 * 
	 * @return the all combo only by slug
	 */
	@Override
	public List<CourseResponse> getAllComboOnlyBySlug(String slug) {

		List<CourseResponse> listData = new ArrayList<CourseResponse>();
		List<Course>         courses  = courseRepository.findAllByType(1);

		// System.out.println("length courses = " + courses.size());

		for (Course item : courses) {
			Post post = postRepository.findByPostableId(item.getId())
					.orElse(null);

			// System.out.println("id " + id);
			// System.out.println("item id " + item.getId());
			// System.out.println("post id " + post.getId());
			// System.out.println("post.getCategory().getId() id" +
			// post.getCategory().getId());

			if (post != null && post.getCategory().getSlug().equals(slug)) {

				// System.out.println("vô");

				CourseResponse courseResponse = ObjectUtil
						.copyPropertiesObject(item, CourseResponse.class);
				courseResponse.setPost(post);
				listData.add(courseResponse);
			}

		}

		// System.out.println("length result " + listData.size());
		return listData;
	}

	/**
	 * Gets the all course only by slug.
	 *
	 * @param slug
	 *     the slug
	 * 
	 * @return the all course only by slug
	 */
	@Override
	public List<CourseResponse> getAllCourseOnlyBySlug(String slug) {

		List<CourseResponse> listData = new ArrayList<CourseResponse>();
		List<Course>         courses  = courseRepository.findAllByType(0);

		// System.out.println("length courses = " + courses.size());

		for (Course item : courses) {
			Post post = postRepository.findByPostableId(item.getId())
					.orElse(null);

			// System.out.println("id " + id);
			// System.out.println("item id " + item.getId());
			// System.out.println("post id " + post.getId());
			// System.out.println("post.getCategory().getId() id" +
			// post.getCategory().getId());

			if (post != null && post.getCategory().getSlug().equals(slug)) {

				// System.out.println("vô");

				CourseResponse courseResponse = ObjectUtil
						.copyPropertiesObject(item, CourseResponse.class);
				courseResponse.setPost(post);
				listData.add(courseResponse);
			}

		}

		// System.out.println("length result " + listData.size());
		return listData;
	}

	/**
	 * Gets the course by slug.
	 *
	 * @param slug
	 *     the slug
	 * 
	 * @return the course by slug
	 */
	@Override
	public CourseDisplayDTO getCourseBySlug(String slug) {
		List<Course> courses = courseRepository.findAll();
		for (Course item : courses) {
			System.out.println("item id" + item.getId());

			Post post = postRepository.findByPostableId(item.getId())
					.orElse(null);
			if (post != null && post.getSlug().equals(slug)) {
				System.out.println("vô");
				CourseResponse courseResponse = ObjectUtil
						.copyPropertiesObject(item, CourseResponse.class);
				courseResponse.setPost(post);
				// with Breadcrumb
				CourseDisplayDTO dto = new CourseDisplayDTO();
				dto.setCourse(courseResponse);
				// create BreadCrumb
				BreadCrumb bc = new BreadCrumb();
				if (post.getCategory() != null) {
					String[] slugEntries = post.getCategory().getPath()
							.split("/");
					// since the last entry is the post's slug, just skip it
					for (String categorySlug : slugEntries) {
						Category parentCategory = categoryRepository
								.findBySlug(categorySlug).orElse(null);
						if (parentCategory == null)
							continue;
						bc.addEntry(parentCategory.getName(),
								parentCategory.getSlug());
					}
				}

				// if it has no parent category, just add it into last entry
				bc.addEntry(post.getTitle(), post.getSlug());
				dto.setBc(bc);
				return dto;
			}
		}

		throw new CourseNotFoundException("CourseNotFoundException");
	}
	
	@Override
	public List<CourseResponse> getTop5ByTypeOrderByUpdatedAtDesc(
			Integer type) {
		List<Course>         courses         = courseRepository
				.findTop5ByTypeOrderByUpdatedAtDesc(type);
		List<CourseResponse> listData = new ArrayList<CourseResponse>();
		
		for (Course item : courses) {
			Post post = postRepository.findByPostableId(item.getId())
					.orElse(null);

			if (post != null ) {

				// System.out.println("vô");

				CourseResponse courseResponse = ObjectUtil
						.copyPropertiesObject(item, CourseResponse.class);
				courseResponse.setPost(post);
				listData.add(courseResponse);
			}

		}

		// System.out.println("length result " + listData.size());
		return listData;
	}

}
package net.freetuts.frontend.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import net.freetuts.frontend.model.Course;
import net.freetuts.frontend.model.CourseDisplay;
import net.freetuts.frontend.model.CourseSideBar;
import net.freetuts.frontend.model.PageAndSort;
import net.freetuts.frontend.services.CourseService;
import net.freetuts.frontend.utils.RestPage;
import net.freetuts.frontend.utils.UrlUtil;

/**
 * The Class CourseServiceImpl.
 */
@Service
public class CourseServiceImpl implements CourseService {

	/** The rest template. */
	@Autowired
	private RestTemplate restTemplate;

	/**
	 * Gets the all.
	 *
	 * @param pageAndSort the page and sort
	 * @return the all
	 */
	@Override
	public RestPage<Course> getAll(PageAndSort pageAndSort) {
		ParameterizedTypeReference<RestPage<Course>> responseType = new ParameterizedTypeReference<RestPage<Course>>() {
		};

		ResponseEntity<RestPage<Course>> responsePosts = restTemplate.exchange(
				UrlUtil.buildPageAndSort(UrlUtil.COURSE_REST, pageAndSort), HttpMethod.GET, null, responseType);

		return responsePosts.getBody();
	}

	/**
	 * Creates the one.
	 *
	 * @param course the course
	 * @return the course
	 */
	@Override
	public Course createOne(Course course) {
		course.setType(0);
		ResponseEntity<Course> response = restTemplate.postForEntity(UrlUtil.COURSE_REST, course, Course.class);
		return response.getBody();
	}

	/**
	 * Update one.
	 *
	 * @param course the course
	 */
	@Override
	public void updateOne(Course course) {
		course.setType(0);
		restTemplate.put(UrlUtil.COURSE_REST + "/" + course.getId().toString(), course);
	}

	/**
	 * Gets the one.
	 *
	 * @param id the id
	 * @return the one
	 */
	@Override
	public Course getOne(UUID id) {
		ResponseEntity<Course> response = restTemplate.getForEntity(UrlUtil.COURSE_REST + "/" + id.toString(),
				Course.class);
		return response.getBody();
	}

	/**
	 * Delete one.
	 *
	 * @param id the id
	 */
	@Override
	public void deleteOne(UUID id) {

		restTemplate.delete(UrlUtil.COURSE_REST + "/" + id.toString());

	}

	/**
	 * Gets the all for combo.
	 *
	 * @return the all for combo
	 */
	@Override
	public List<Course> getAllForCombo() {
		ParameterizedTypeReference<List<Course>> responseType = new ParameterizedTypeReference<List<Course>>() {
		};

		ResponseEntity<List<Course>> response = restTemplate.exchange(UrlUtil.COURSE_REST + "/all-course",
				HttpMethod.GET, null, responseType);
		return response.getBody();
	}

	/**
	 * Creates the combo.
	 *
	 * @param course the course
	 * @return the course
	 */
	@Override
	public Course createCombo(Course course) {
		course.setType(1);
		ResponseEntity<Course> response = restTemplate.postForEntity(UrlUtil.COMBO_REST, course, Course.class);
		return response.getBody();
	}

	@Override
	public Course createUserCombo(Course course) {
		ResponseEntity<Course> response = restTemplate.postForEntity(UrlUtil.COMBO_REST, course, Course.class);
		return response.getBody();
	}

	/**
	 * Update combo.
	 *
	 * @param course the course
	 */
	@Override
	public void updateCombo(Course course) {
		course.setType(1);
		restTemplate.put(UrlUtil.COMBO_REST + "/" + course.getId().toString(), course);
	}

	/**
	 * Gets the combo.
	 *
	 * @param id the id
	 * @return the combo
	 */
	@Override
	public Course getCombo(UUID id) {
		ResponseEntity<Course> response = restTemplate.getForEntity(UrlUtil.COURSE_REST + "/" + id.toString(),
				Course.class);

		List<UUID> listIds = new ArrayList<UUID>();

		for (Course item : response.getBody().getCourses()) {
			listIds.add(item.getId());
		}

		response.getBody().setCourseIds(listIds);

		System.out.println("return = " + response.getBody().toString());

		return response.getBody();
	}

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	@Override
	public List<Course> getAll() {
		ParameterizedTypeReference<List<Course>> responseType = new ParameterizedTypeReference<List<Course>>() {
		};

		ResponseEntity<List<Course>> response = restTemplate.exchange(UrlUtil.COURSE_REST + "/all", HttpMethod.GET,
				null, responseType);
		return response.getBody();
	}

	/**
	 * Gets the all course only by category.
	 *
	 * @param id the id
	 * @return the all course only by category
	 */
	@Override
	public List<Course> getAllCourseOnlyByCategory(UUID id) {
		ResponseEntity<Course[]> response = restTemplate
				.getForEntity(UrlUtil.COURSE_REST + "/category/" + id.toString(), Course[].class);

		return Arrays.asList(response.getBody());

	}

	/**
	 * Gets the all course only by category slug.
	 *
	 * @param slug the slug
	 * @return the all course only by category slug
	 */
	@Override
	public List<Course> getAllCourseOnlyByCategorySlug(String slug) {
		ResponseEntity<Course[]> response = restTemplate.getForEntity(UrlUtil.COURSE_REST + "/category/slug/" + slug,
				Course[].class);

		return Arrays.asList(response.getBody());

	}

	/**
	 * Gets the all combo only by slug.
	 *
	 * @param slug the slug
	 * @return the all combo only by slug
	 */
	@Override
	public List<Course> getAllComboOnlyBySlug(String slug) {
		ResponseEntity<Course[]> response = restTemplate.getForEntity(UrlUtil.COURSE_REST + "/combo/slug/" + slug,
				Course[].class);

		return Arrays.asList(response.getBody());
	}

	/**
	 * Gets the course by slug.
	 *
	 * @param slug the slug
	 * @return the course by slug
	 */
	@Override
	public CourseDisplay getCourseBySlug(String slug) {
		ResponseEntity<CourseDisplay> response = restTemplate.getForEntity(UrlUtil.COURSE_REST + "/slug/" + slug,
				CourseDisplay.class);
		return response.getBody();
	}
	
	@Override
	public List<CourseSideBar> getTop5ByTypeOrderByUpdatedAtDesc(int i) {
		ResponseEntity<CourseSideBar[]> response = restTemplate.getForEntity(UrlUtil.COURSE_REST + "/top5" ,
				CourseSideBar[].class);
		return  Arrays.asList(response.getBody());
	}

}

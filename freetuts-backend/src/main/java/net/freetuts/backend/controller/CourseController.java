package net.freetuts.backend.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.freetuts.backend.dto.CourseCreateDTO;
import net.freetuts.backend.dto.CourseUpdateDTO;
import net.freetuts.backend.dto.PageableDTO;
import net.freetuts.backend.services.CourseService;
import net.freetuts.backend.utils.RestResponse;

/**
 * The Class CourseController.
 */
@RestController
@RequestMapping("/courses")
public class CourseController {

	/** The course service. */
	@Autowired
	private CourseService courseService;

	/**
	 * Gets the all course.
	 *
	 * @return the all course
	 */
	@GetMapping
	public ResponseEntity<?> getAllCourse(@ModelAttribute PageableDTO pageableDTO) {

		return ResponseEntity.ok().body(courseService.getAll(pageableDTO.get()));
	}
	
	/**
	 * Gets the all course without paginate.
	 *
	 * @return the all course without paginate
	 */
	@GetMapping("/all")
	public ResponseEntity<?> getAllCourseWithoutPaginate() {
		
		return RestResponse.sendOk(courseService.getAllCourse());
	}
	
	/**
	 * Gets the all for combo.
	 *
	 * @return the all for combo
	 */
	@GetMapping("/all-course")
	public ResponseEntity<?> getAllForCombo() {

		return ResponseEntity.ok().body(courseService.getAllCourseOnly());
	}
	
	/**
	 * Gets the course.
	 *
	 * @param id the id
	 * @return the course
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> getCourse(@PathVariable UUID id) {

		return ResponseEntity.ok().body(courseService.getOneById(id));
	}

	@GetMapping("/top5")
	public ResponseEntity<?> getTop5Course() {

		return ResponseEntity.ok().body(courseService.getTop5ByTypeOrderByUpdatedAtDesc(0));
	}

	/**
	 * Creates the one course.
	 *
	 * @param courseCreateDTO the course create DTO
	 * @return the response entity
	 */
	@PostMapping
	public ResponseEntity<?> createOneCourse(@RequestBody CourseCreateDTO courseCreateDTO) {

		System.out.println(courseCreateDTO.toString());

		return RestResponse.sendCreated(courseService.createOne(courseCreateDTO));
	}

	/**
	 * Update one course.
	 *
	 * @param courseUpdateDTO the course update DTO
	 * @return the response entity
	 */
	@PutMapping("/{id}")
	public ResponseEntity<?> updateOneCourse(@RequestBody CourseUpdateDTO courseUpdateDTO) {

		System.out.println("update = " + courseUpdateDTO.toString());
		courseService.updateOne(courseUpdateDTO);
		
		return RestResponse.sendNoContent();
	}

	/**
	 * Delete one course.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteOneCourse(@PathVariable UUID id) {
		courseService.deleteOne(id);

		return RestResponse.sendNoContent();
	}

	/**
	 * Gets the all course only by category.
	 *
	 * @param id the id
	 * @return the all course only by category
	 */
	@GetMapping("/category/{id}")
	public ResponseEntity<?> getAllCourseOnlyByCategory(@PathVariable UUID id)
	{
		System.out.println("category id" + id.toString());
		return RestResponse.sendOk(courseService.getAllCourseOnlyByCategory(id));
	}
	
	/**
	 * Gets the all course only by category slug.
	 *
	 * @param slug the slug
	 * @return the all course only by category slug
	 */
	@GetMapping("/category/slug/{slug}")
	public ResponseEntity<?> getAllCourseOnlyByCategorySlug(@PathVariable String slug)
	{
		
		return RestResponse.sendOk(courseService.getAllCourseOnlyBySlug(slug));
	}
	
	/**
	 * Gets the all course only by category.
	 *
	 * @param slug the slug
	 * @return the all course only by category
	 */
	@GetMapping("/combo/slug/{slug}")
	public ResponseEntity<?> getAllCourseOnlyByCategory(@PathVariable String slug)
	{
		
		return RestResponse.sendOk(courseService.getAllComboOnlyBySlug(slug));
	}
	
	/**
	 * Gets the course by slug.
	 *
	 * @param slug the slug
	 * @return the course by slug
	 */
	@GetMapping("/slug/{slug}")
	public ResponseEntity<?> getCourseBySlug(@PathVariable String slug)
	{
		System.out.println("slug" + slug);
		return RestResponse.sendOk(courseService.getCourseBySlug(slug));
	}
}

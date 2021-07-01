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

import net.freetuts.backend.dto.PageableDTO;
import net.freetuts.backend.dto.TutorialCreateDTO;
import net.freetuts.backend.dto.TutorialUpdateDTO;
import net.freetuts.backend.services.CategoryService;
import net.freetuts.backend.services.TutorialService;
import net.freetuts.backend.utils.RestResponse;

/**
 * The Class TutorialController.
 */
@RestController
@RequestMapping("/tutorials")
public class TutorialController {

	/** The tut service. */
	@Autowired
	private TutorialService tutService;

	/** The category service. */
	@Autowired
	private CategoryService categoryService;

	/**
	 * Gets the all tutorials.
	 *
	 * @param dto the dto
	 * @return the all tutorials
	 */
	@GetMapping
	public ResponseEntity<?> getAllTutorials(
			@ModelAttribute PageableDTO dto) {

		return RestResponse.sendOk(tutService.getAll(dto.get()));
	}

	/**
	 * Creates the one tutorial.
	 *
	 * @param dto the dto
	 * @return the response entity
	 */
	@PostMapping
	public ResponseEntity<?> createOneTutorial(
			@RequestBody TutorialCreateDTO dto) {

		return RestResponse.sendOk(tutService.createOne(dto));
	}

	/**
	 * Update one tutorial.
	 *
	 * @param dto the dto
	 * @return the response entity
	 */
	@PutMapping
	public ResponseEntity<?> updateOneTutorial(
			@RequestBody TutorialUpdateDTO dto) {

		tutService.updateOne(dto);

		return RestResponse.sendNoContent();
	}

	/**
	 * Gets the one tutorial.
	 *
	 * @param id the id
	 * @return the one tutorial
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> getOneTutorial(@PathVariable UUID id) {

		return RestResponse.sendOk(tutService.getOne(id));
	}

	/**
	 * Gets the all chapter by tutorial.
	 *
	 * @param tid the tid
	 * @return the all chapter by tutorial
	 */
	@GetMapping("/{tid}/chapters")
	public ResponseEntity<?> getAllChapterByTutorial(
			@PathVariable UUID tid) {

		return RestResponse.sendOk(tutService.getAllChapterByTutorial(tid));
	}

	/**
	 * Gets the series by tutorial.
	 *
	 * @param id the id
	 * @return the series by tutorial
	 */
	@GetMapping("/chapter/{id}/series")
	public ResponseEntity<?> getSeriesByTutorial(@PathVariable UUID id) {

		return RestResponse.sendOk(categoryService.getSeriesByTutorial(id));
	}
	
	/**
	 * Gets the serie by id.
	 *
	 * @param id the id
	 * @return the serie by id
	 */
	@GetMapping("/series/{id}")
	public ResponseEntity<?> getSerieById(@PathVariable UUID id) {
		
		return RestResponse.sendOk(categoryService.getSerieById(id));
	}

	/**
	 * Gets the thread id by chapter.
	 *
	 * @param id the id
	 * @return the thread id by chapter
	 */
	@GetMapping("/chapter/{id}/thread")
	public ResponseEntity<?> getThreadIdByChapter(@PathVariable UUID id) {

		return RestResponse.sendOk(categoryService.getSeriesByChapter(id));
	}
	
	/**
	 * Gets the all chapter.
	 *
	 * @return the all chapter
	 */
	@GetMapping("/chapter/all")
	public ResponseEntity<?> getAllChapter() {

		return RestResponse.sendOk(categoryService.getAllChapter());
	}
	
	/**
	 * Gets the thread series by chapter.
	 *
	 * @param id the id
	 * @return the thread series by chapter
	 */
	@GetMapping("/chapter/{id}/thread-series")
	public ResponseEntity<?> getThreadSeriesByChapter(@PathVariable UUID id) {
		
		return RestResponse.sendOk(categoryService.getThreadSeriesInLessonByChapter(id));
	}
	
	/**
	 * Delete one category.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteOneCategory(@PathVariable UUID id) {

		categoryService.deleteOne(id);
		
		return RestResponse.sendNoContent();
	}
}

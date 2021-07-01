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
import org.springframework.web.bind.annotation.RestController;

import net.freetuts.backend.dto.CategoryCreateDTO;
import net.freetuts.backend.dto.CategoryUpdateDTO;
import net.freetuts.backend.dto.MetaCategoryDTO;
import net.freetuts.backend.dto.PageableDTO;
import net.freetuts.backend.services.CategoryService;
import net.freetuts.backend.utils.RestResponse;
import net.freetuts.backend.utils.URL;

/**
 * The Class CategoryController.
 */
@RestController
public class CategoryController {

	/** The category service. */
	@Autowired
	private CategoryService categoryService;

	/**
	 * Gets the all category.
	 *
	 * @param dto the dto
	 * @return the all category
	 */
	@GetMapping(URL.CATE)
	private ResponseEntity<?> getAllCategory(
			@ModelAttribute PageableDTO dto) {

		return RestResponse.sendOk(categoryService.getAll(dto.get()));
	}
	
	/**
	 * Gets the one category.
	 *
	 * @param id the id
	 * @return the one category
	 */
	@GetMapping(URL.CATE + "/{id}")
	private ResponseEntity<?> getOneCategory(@PathVariable UUID id) {

		return RestResponse.sendOk(categoryService.getOne(id));
	}

	/**
	 * Creates the one category.
	 *
	 * @param dto the dto
	 * @return the response entity
	 */
	@PostMapping(URL.ADMIN_CATE)
	public ResponseEntity<?> createOneCategory(@RequestBody CategoryCreateDTO dto) {

		return RestResponse.sendCreated(categoryService.createOne(dto));
	}
	
	/**
	 * Update one category.
	 *
	 * @param dto the dto
	 * @return the response entity
	 */
	@PutMapping(URL.ADMIN_CATE)
	public ResponseEntity<?> updateOneCategory(@RequestBody CategoryUpdateDTO dto) {

		categoryService.updateOne(dto);
		
		return RestResponse.sendNoContent();
	}
	
	/**
	 * Delete one category.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@DeleteMapping(URL.ADMIN_CATE + "/{id}")
	public ResponseEntity<?> deleteOneCategory(@PathVariable UUID id) {

		categoryService.deleteOne(id);
		
		return RestResponse.sendNoContent();
	}
	
	/**
	 * Gets the all category without paginate.
	 *
	 * @return the all category without paginate
	 */
	@GetMapping(URL.CATE + "/all")
	private ResponseEntity<?> getAllCategoryWithoutPaginate() {
		
		return RestResponse.sendOk(categoryService.getAll());
	}
	
	/**
	 * Gets the category by slug.
	 *
	 * @param slug the slug
	 * @return the category by slug
	 */
	@GetMapping(URL.CATE + "/slug/{slug}")
	public ResponseEntity<?> getCategoryBySlug(@PathVariable String slug) {
		
		return RestResponse.sendOk(categoryService.getOneBySlug(slug));
	}
	
	/**
	 * Gets the category meta data.
	 *
	 * @param dto the dto
	 * @return the category meta data
	 */
	@PostMapping(URL.CATE + "/meta")
	public ResponseEntity<?> getCategoryMetaData(@RequestBody MetaCategoryDTO dto) {
		
		return RestResponse.sendOk(categoryService.getMetaData(dto));
	}
	
	/**
	 * Gets the all series by thread.
	 *
	 * @param tid the tid
	 * @return the all series by thread
	 */
	@GetMapping(URL.CATE + "/thread/{tid}/series")
	public ResponseEntity<?> getAllSeriesByThread(@PathVariable UUID tid) {
		
		return RestResponse.sendOk(categoryService.getSeriesByThread(tid));
	}
	
	/**
	 * Gets the all thread.
	 *
	 * @return the all thread
	 */
	@GetMapping(URL.CATE + "/thread/all")
	public ResponseEntity<?> getAllThread() {
		
		return RestResponse.sendOk(categoryService.getThread());
	}
	
	/**
	 * Gets the by parent id.
	 *
	 * @param id the id
	 * @return the by parent id
	 */
	@GetMapping(URL.CATE + "/child/{id}")
	private ResponseEntity<?> getByParentId(@PathVariable UUID id) {
		
		return RestResponse.sendOk(categoryService.getByParentId(id));
	}
	
	/**
	 * Gets the by path.
	 *
	 * @param path the path
	 * @return the by path
	 */
	@GetMapping(URL.CATE + "/path/{path}")
	private ResponseEntity<?> getByPath(@PathVariable String path) {
		
		return RestResponse.sendOk(categoryService.getByPath(path));
	}
}

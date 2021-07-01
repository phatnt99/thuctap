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
import net.freetuts.backend.dto.PostCreateDTO;
import net.freetuts.backend.dto.PostUpdateDTO;
import net.freetuts.backend.services.PostService;
import net.freetuts.backend.utils.RestResponse;

/**
 * The Class PostController.
 */
@RestController
@RequestMapping("/posts")
public class PostController {

	/** The post service. */
	@Autowired
	private PostService postService;

	/**
	 * Gets the all post.
	 *
	 * @param pageableDTO
	 *     the pageable DTO
	 * 
	 * @return the all post
	 */
	@GetMapping
	public ResponseEntity<?> getAllPost(
			@ModelAttribute PageableDTO pageableDTO) {

		return RestResponse.sendOk(postService.getAll(pageableDTO.get()));
	}

	/**
	 * Gets the one post.
	 *
	 * @param id
	 *     the id
	 * 
	 * @return the one post
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> getOnePost(@PathVariable("id") UUID id) {

		return RestResponse.sendOk(postService.getOne(id));
	}

	/**
	 * Creates the one post.
	 *
	 * @param dto
	 *     the dto
	 * 
	 * @return the response entity
	 */
	@PostMapping
	public ResponseEntity<?> createOnePost(@RequestBody PostCreateDTO dto) {

		return RestResponse.sendCreated(postService.createOne(dto));
	}

	/**
	 * Update one post.
	 *
	 * @param dto
	 *     the dto
	 * 
	 * @return the response entity
	 */
	@PutMapping("/{id}")
	public ResponseEntity<?> updateOnePost(@RequestBody PostUpdateDTO dto) {

		postService.updateOne(dto);

		return RestResponse.sendNoContent();
	}

	/**
	 * Delete one post.
	 *
	 * @param id
	 *     the id
	 * 
	 * @return the response entity
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteOnePost(@PathVariable UUID id) {

		postService.deleteOne(id);

		return RestResponse.sendNoContent();
	}

	@GetMapping("/slug/{slug}")
	public ResponseEntity<?> getPostBySlug(@PathVariable String slug) {

		return RestResponse.sendOk(postService.getOneBySlug(slug));
	}

	@GetMapping("/category/{slug}")
	public ResponseEntity<?> getPostByCategorySlug(@PathVariable String slug,
			@ModelAttribute PageableDTO dto) {

		return RestResponse.sendOk(postService.getByCategorySlug(slug, dto.get()));
	}
	
	@GetMapping("/category/{slug}/all")
	public ResponseEntity<?> getPostByParentCategorySlug(@PathVariable String slug,
			@ModelAttribute PageableDTO dto) {

		return RestResponse.sendOk(postService.getByParentCategorySlug(slug, dto.get()));
	}
	
	@GetMapping("/postable/{pid}")
	public ResponseEntity<?> getPostByPostableId(@PathVariable UUID pid) {

		return RestResponse.sendOk(postService.getOneByPostableId(pid));
	}
	
	@GetMapping("/home")
	public ResponseEntity<?> getTop5PostForHome() {
		
		return RestResponse.sendOk(postService.getTop5PostEachCateForHome());
	}
}

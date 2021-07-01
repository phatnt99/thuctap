package net.freetuts.frontend.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.freetuts.frontend.constant.PostTypeEnum;
import net.freetuts.frontend.model.PageAndSort;
import net.freetuts.frontend.model.Post;
import net.freetuts.frontend.services.CategoryService;
import net.freetuts.frontend.services.CourseService;
import net.freetuts.frontend.services.PostService;
import net.freetuts.frontend.services.TutorialService;

/**
 * The Class PostController.
 */
@Controller
@RequestMapping(value = {
		"/{locale:en|fr|vi}/", "/admin/posts"
})
public class PostController {

	/** The post service. */
	@Autowired
	private PostService postService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private TutorialService tutService;

	@Autowired
	private CourseService courseService;

	/**
	 * Show list page.
	 *
	 * @param model
	 *     the model
	 * @param pageAndSort
	 *     the page and sort
	 * 
	 * @return the string
	 */
	@GetMapping
	public String showListPage(Model model,
			@ModelAttribute PageAndSort pageAndSort) {

		model.addAttribute("posts", postService.getAll(pageAndSort));
		model.addAttribute("pagination", pageAndSort);

		return "post-list-page";
	}

	/**
	 * Show create page.
	 *
	 * @param model
	 *     the model
	 * 
	 * @return the string
	 */
	@GetMapping("/create")
	public String showCreatePage(Model model) {

		model.addAttribute("post", new Post());
		model.addAttribute("categories", categoryService.getAll());
		model.addAttribute("postTypes", PostTypeEnum.values());

		return "post-create-page";
	}

	/**
	 * Creates the post.
	 *
	 * @param post
	 *     the post
	 * @param model
	 *     the model
	 * 
	 * @return the string
	 */
	@PostMapping("/create")
	public String createPost(@ModelAttribute Post post, Model model) {

		Post createdPost = postService.createOne(post);

		model.addAttribute("post", createdPost);

		return "redirect:/admin/posts/create";
	}

	@PostMapping("/create/change-type")
	public String changeTypePost(Post post, Model model) {

		model.addAttribute("post", post);

		switch (post.getPostType()) {
		case "POST":
			model.addAttribute("categories", categoryService.getAll());
			break;
		case "COURSE":
			model.addAttribute("categories", categoryService.getAll());
			model.addAttribute("courses", courseService.getAll());
			break;
		case "LESSON":
			model.addAttribute("chapters", tutService.getAllChapter());
			break;
		default:
			break;
		}

		return "post-create-page :: frag-field";
	}

	/**
	 * Show update page.
	 *
	 * @param id
	 *     the id
	 * @param model
	 *     the model
	 * 
	 * @return the string
	 */
	@GetMapping("/{id}")
	public String showUpdatePage(@PathVariable("id") UUID id, Model model) {

		model.addAttribute("post", postService.getOne(id));
		model.addAttribute("categories", categoryService.getAll());
		
		return "post-detail-page";
	}

	/**
	 * Update post.
	 *
	 * @param post
	 *     the post
	 * @param model
	 *     the model
	 * 
	 * @return the string
	 */
	@PostMapping("/{id}")
	public String updatePost(@ModelAttribute Post post, Model model) {

		postService.updateOne(post);

		return "redirect:/admin/posts/" + post.getId();
	}

	/**
	 * Delete post.
	 *
	 * @param id
	 *     the id
	 * 
	 * @return the string
	 */
	@GetMapping("/delete/{id}")
	public String deletePost(@PathVariable("id") UUID id) {

		postService.deleteOne(id);

		return "redirect:/admin/posts";
	}

}

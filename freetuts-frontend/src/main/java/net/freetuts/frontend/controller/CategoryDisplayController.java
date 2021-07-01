package net.freetuts.frontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import net.freetuts.frontend.model.CategoryDisplay;
import net.freetuts.frontend.model.MetaCategoryDTO;
import net.freetuts.frontend.model.PageAndSort;
import net.freetuts.frontend.model.Post;
import net.freetuts.frontend.services.CategoryService;
import net.freetuts.frontend.services.DisplayService;
import net.freetuts.frontend.services.PostService;
import net.freetuts.frontend.services.TutorialService;

/**
 * The Class CategoryDisplayController.
 */
@Controller
@RequestMapping("/category")
public class CategoryDisplayController {

	/** The categogy service. */
	@Autowired
	private CategoryService categogyService;

	/** The tut service. */
	@Autowired
	private TutorialService tutService;

	/** The post service. */
	@Autowired
	private PostService postService;

	/** The display service. */
	@Autowired
	private DisplayService displayService;

	/**
	 * Show one category.
	 *
	 * @param slug the slug
	 * @param model the model
	 * @param pageAndSort the page and sort
	 * @return the string
	 */
	@GetMapping("/{slug}")
	public String showOneCategory(@PathVariable String slug, Model model,
			@ModelAttribute PageAndSort pageAndSort) {

		CategoryDisplay categoryDisplay = categogyService.getOneBySlug(slug);

		model.addAttribute("category", categoryDisplay.getCategory());
		model.addAttribute("breadcrumb", categoryDisplay.getBc().getEntries());
		model.addAttribute("menu", displayService.getMenu());

		switch (categoryDisplay.getCategory().getPattern()) {
		case "TOPIC":
			processTopic(categoryDisplay, model);
			break;
		case "THREAD":
			processThread(categoryDisplay, model);
			break;
		case "SERIES":
			processSeries(categoryDisplay, model);
			break;
		case "USUAL":

			break;
		default:
			break;
		}

		String viewBuilder = "category-"
				+ categoryDisplay.getCategory().getPattern().toLowerCase()
				+ "-page";
		return viewBuilder;
	}

	/**
	 * Process topic.
	 *
	 * @param categoryDisplay the category display
	 * @param model the model
	 */
	private void processTopic(CategoryDisplay categoryDisplay, Model model) {
		// get meta data of category
		// meta now is only agenda
		model.addAttribute("meta", categogyService.getMetaData(
				new MetaCategoryDTO(categoryDisplay.getCategory().getId())));
	}

	/**
	 * Process thread.
	 *
	 * @param categoryDisplay the category display
	 * @param model the model
	 */
	private void processThread(CategoryDisplay categoryDisplay, Model model) {
		processTopic(categoryDisplay, model);
		// fetch content of post (is known as pattern for category)
		Post post = postService
				.getOneByPostableId(categoryDisplay.getCategory().getId());
		if (post != null)
			model.addAttribute("content", post.getContent());
		// fetch series
		model.addAttribute("series", tutService
				.getAllSeriesByThread(categoryDisplay.getCategory().getId()));

	}
	
	/**
	 * Process series.
	 *
	 * @param categoryDisplay the category display
	 * @param model the model
	 */
	private void processSeries(CategoryDisplay categoryDisplay, Model model) {
		// first we need get thread id of this serie to retrieve related series
		//processTopic(categoryDisplay, model);
		model.addAttribute("meta", categogyService.getMetaData(
				new MetaCategoryDTO(categoryDisplay.getCategory().getParentId())));
		
		// then fetch all chapters and related posts
		model.addAttribute("serie", tutService
				.getOneSerie(categoryDisplay.getCategory().getId()));
		
		// fetch content of post (is known as pattern for category)
		Post post = postService
				.getOneByPostableId(categoryDisplay.getCategory().getId());
		if (post != null) {
			model.addAttribute("content", post.getContent());
			model.addAttribute("thumbnail", post.getThumbnail());
		}
		
	}
}

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

import net.freetuts.frontend.model.Category;
import net.freetuts.frontend.model.PageAndSort;
import net.freetuts.frontend.services.CategoryService;

/**
 * The Class CategoryController.
 */
@Controller
@RequestMapping("/admin/categories")
public class CategoryController {

	/** The category service. */
	@Autowired
	private CategoryService categoryService;

	/**
	 * Show list page.
	 *
	 * @param model the model
	 * @param pageAndSort the page and sort
	 * @return the string
	 */
	@GetMapping
	public String showListPage(Model model,
			@ModelAttribute PageAndSort pageAndSort) {

		model.addAttribute("categories", categoryService.getAll(pageAndSort));
		model.addAttribute("pagination", pageAndSort);

		return "category-list-page";
	}

	/**
	 * Show create page.
	 *
	 * @param model the model
	 * @return the string
	 */
	@GetMapping("/create")
	public String showCreatePage(Model model) {

		model.addAttribute("category", new Category());
		model.addAttribute("parent", categoryService.getAll());
		model.addAttribute("patterns", new String[] {
				"TOPIC", "THREAD"
		});
		return "category-create-page";
	}

	/**
	 * Show update page.
	 *
	 * @param id the id
	 * @param model the model
	 * @return the string
	 */
	@GetMapping("/{id}")
	public String showUpdatePage(@PathVariable("id") UUID id, Model model) {

		model.addAttribute("category", categoryService.getOne(id));
		model.addAttribute("parent", categoryService.getAll());

		return "category-detail-page";
	}

	/**
	 * Creates the category.
	 *
	 * @param category the category
	 * @param model the model
	 * @return the string
	 */
	@PostMapping("/create")
	public String createCategory(@ModelAttribute Category category,
			Model model) {

		categoryService.createOne(category);

		return "redirect:/admin/categories/create";
	}

	/**
	 * Update category.
	 *
	 * @param category the category
	 * @param model the model
	 * @return the string
	 */
	@PostMapping("/{id}")
	public String updateCategory(@ModelAttribute Category category,
			Model model) {

		categoryService.updateOne(category);

		return "redirect:/admin/categories/" + category.getId().toString();
	}

	/**
	 * Delete category.
	 *
	 * @param id the id
	 * @return the string
	 */
	@GetMapping("/delete/{id}")
	public String deleteCategory(@PathVariable UUID id) {

		categoryService.deleteOne(id);

		return "redirect:/admin/categories";
	}
}

package net.freetuts.frontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.freetuts.frontend.model.Tutorial;
import net.freetuts.frontend.services.CategoryService;

/**
 * The Class SeriesController.
 */
@Controller
@RequestMapping("/admin/series")
public class SeriesController {
	
	/** The category service. */
	@Autowired
	private CategoryService categoryService;
	
	/**
	 * Show create page.
	 *
	 * @param model the model
	 * @return the string
	 */
	@GetMapping("/create")
	public String showCreatePage(Model model) {
		Tutorial tutorial = new Tutorial();
		model.addAttribute("tutorial", tutorial);
		model.addAttribute("parent", categoryService.getAll());

		return "tutorial-create-page";
	}
}

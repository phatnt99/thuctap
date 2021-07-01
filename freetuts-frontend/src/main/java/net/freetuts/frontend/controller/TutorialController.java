package net.freetuts.frontend.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.freetuts.frontend.model.PageAndSort;
import net.freetuts.frontend.model.Post;
import net.freetuts.frontend.model.Tutorial;
import net.freetuts.frontend.model.TutorialView.Ccategory;
import net.freetuts.frontend.services.CategoryService;
import net.freetuts.frontend.services.PostService;
import net.freetuts.frontend.services.TutorialService;

/**
 * The Class TutorialController.
 */
@Controller
@RequestMapping("/admin/tutorials")
public class TutorialController {

	/** The category service. */
	@Autowired
	private CategoryService categoryService;

	/** The tut service. */
	@Autowired
	private TutorialService tutService;
	
	/** The post service. */
	@Autowired
	private PostService postService;

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

		model.addAttribute("tutorials", tutService.getAllForView(pageAndSort));
		model.addAttribute("pagination", pageAndSort);

		return "tutorial-list-page";
	}

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
		model.addAttribute("parent", categoryService.getAllThread());

		return "tutorial-create-page";
	}

	/**
	 * Show edit page.
	 *
	 * @param id the id
	 * @param model the model
	 * @param req the req
	 * @return the string
	 */
	@GetMapping("/{id}")
	public String showEditPage(@PathVariable UUID id, Model model,
			HttpServletRequest req) {
		Tutorial tut = tutService.getOne(id);
		model.addAttribute("parent", categoryService.getAll());
		// set ccategory list for manipulate
		req.getSession().setAttribute("ccategory", tut.getCcategory());
		// get pattern thumbnail
		Post post = postService
				.getOneByPostableId(tut.getId());
		if (post != null)
			tut.setThumbnail(post.getThumbnail());
		
		model.addAttribute("tutorial", tut);
		
		return "tutorial-detail-page";
	}

	/**
	 * Update tutorial.
	 *
	 * @param tut the tut
	 * @return the string
	 */
	@PostMapping("/{id}")
	public String updateTutorial(Tutorial tut) {

		tutService.updateOne(tut);

		return "redirect:/admin/tutorials/" + tut.getId();
	}

	/**
	 * Creates the tutorial.
	 *
	 * @param tut the tut
	 * @param model the model
	 * @return the string
	 */
	@PostMapping("/create")
	public String createTutorial(@ModelAttribute Tutorial tut, Model model) {

		tutService.createOne(tut);

		return "redirect:/admin/tutorials/create";
	}
	
	/**
	 * Delete tutorial.
	 *
	 * @param id the id
	 * @return the string
	 */
	@GetMapping("/delete/{id}")
	public String deleteTutorial(@PathVariable UUID id) {

		tutService.deleteOne(id);

		return "redirect:/admin/tutorials";
	}

	/**
	 * Adds the new field.
	 *
	 * @param tut the tut
	 * @return the string
	 */
	@PostMapping("/add-field")
	public String addNewField(Tutorial tut) {

		tut.setCountField(tut.getCountField() + 1);

		return "tutorial-" + tut.getTemplate() + "-page :: frag-field";
	}

	/**
	 * Removes the field.
	 *
	 * @param tut the tut
	 * @return the string
	 */
	@PostMapping("/remove-field")
	public String removeField(Tutorial tut) {

		// set removeIdx value to null
		tut.removeField(tut.getRemoveIdx());

		tut.setCountField(tut.getCountField() - 1);

		return "tutorial-" + tut.getTemplate() + "-page :: frag-field";
	}

	/**
	 * Removes the old field.
	 *
	 * @param tut the tut
	 * @param req the req
	 * @return the string
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("/remove-old-field")
	public String removeOldField(Tutorial tut, HttpServletRequest req) {

		List<Ccategory> ccategory = (List<Ccategory>) req.getSession()
				.getAttribute("ccategory");

		req.getSession().setAttribute("ccategory",
				ccategory.stream()
						.filter(cate -> !cate.getId().equals(tut.getRemoveId()))
						.collect(Collectors.toList()));
		
		tut.setCcategory((List<Ccategory>) req.getSession()
				.getAttribute("ccategory"));

		return "tutorial-detail-page :: frag-old-field";
	}
}

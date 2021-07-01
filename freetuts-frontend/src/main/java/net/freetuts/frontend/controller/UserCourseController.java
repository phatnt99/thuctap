package net.freetuts.frontend.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.freetuts.frontend.model.Category;
import net.freetuts.frontend.model.Course;
import net.freetuts.frontend.model.Invoice;
import net.freetuts.frontend.services.CategoryService;
import net.freetuts.frontend.services.CourseService;
import net.freetuts.frontend.services.DisplayService;

/**
 * The Class UserCourseController.
 */
@Controller
@RequestMapping(value = { "/{locale:en|fr|vi}/", "/khoa-hoc" })
public class UserCourseController {

	/** The course service. */
	@Autowired
	private CourseService courseService;

	/** The category service. */
	@Autowired
	private CategoryService categoryService;
	
	/** The display service. */
	@Autowired
	private DisplayService displayService;

	/**
	 * Show user create combo page.
	 *
	 * @param model the model
	 * @return the string
	 */
	@GetMapping("/tao-combo")
	public String showUserCreateComboPage(Model model) {

		List<Category> listCategory = categoryService.getByPath("khoa-hoc");

		// System.out.println("length category : " + listCategory.size());

		Map<String, List<Course>> data = new HashMap<String, List<Course>>();

		for (Category item : listCategory) {

			// System.out.println("category id : " + item.getId());

			List<Course> course = courseService.getAllCourseOnlyByCategory(item.getId());

			// System.out.println("length course : " + course.size());

			if (course.size() > 0)
				data.put(item.getName().toUpperCase(), course);
		}

		model.addAttribute("categories", listCategory);
		model.addAttribute("data", data);
		model.addAttribute("menu", displayService.getMenu());

		return "user-create-combo";
	}

	/**
	 * Update create combo page.
	 *
	 * @param data the data
	 * @param model the model
	 * @return the string
	 */
	@PostMapping("/tao-combo/add-field")
	public String UpdateCreateComboPage(Course data, Model model) {
		System.out.println("data" + data.toString());
		List<Course> selectedCourses = new ArrayList<Course>();
		
		for(UUID id : data.getCourseIds())
		{
			selectedCourses.add(courseService.getOne(id));
		}
		
		model.addAttribute("selectedCourses", selectedCourses);
		model.addAttribute("idSelected", data.getCourseIds());

		return "user-create-combo :: frag-field";
	}
	
	/**
	 * Show confirm create combo page.
	 *
	 * @param listId the list id
	 * @param model the model
	 * @return the string
	 */
	@GetMapping("/tao-combo/confirm")
	public String showConfirmCreateComboPage(@RequestParam("ids") String listId, Model model) {
		System.out.println("data" + listId);
		
		String[] idSelected = (listId.replaceAll(" ", "")).split(",");
		
		List<Course> selectedCourses = new ArrayList<Course>();
		
		Integer totalPrice = 0;
		
		Integer finalPrice = 0;
		
		for(String id : idSelected)
		{
			Course course = courseService.getOne(UUID.fromString(id));
			System.out.println("post " + course.getPost().getTitle());
			selectedCourses.add(course);
			totalPrice += course.getPrice();
			finalPrice += course.getPrice() * course.getDiscount() / 100;
		}
		
		finalPrice = totalPrice - finalPrice;
		
		model.addAttribute("selectedCourses", selectedCourses);
		model.addAttribute("totalPrice", totalPrice);
		model.addAttribute("finalPrice", finalPrice);
		model.addAttribute("invoice", new Invoice(1, listId));
		model.addAttribute("menu", displayService.getMenu());

		return "confirm-create-combo-page";
	}
}

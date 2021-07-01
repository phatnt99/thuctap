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

import net.freetuts.frontend.model.Course;
import net.freetuts.frontend.model.PageAndSort;
import net.freetuts.frontend.services.CourseService;

/**
 * The Class CourseController.
 */
@Controller
@RequestMapping(value = { "/{locale:en|fr|vi}/", "/admin/courses" })
public class CourseController {

	/** The course service. */
	@Autowired
	private CourseService courseService;

	/**
	 * Show course page.
	 *
	 * @param model the model
	 * @param pageAndSort the page and sort
	 * @return the string
	 */
	@GetMapping
	public String showCoursePage(Model model, @ModelAttribute PageAndSort pageAndSort) {
		
		model.addAttribute("courses", courseService.getAll(pageAndSort));
		model.addAttribute("pagination", pageAndSort);

		return "course";
	}

	/**
	 * Show course detail page.
	 *
	 * @param id the id
	 * @param model the model
	 * @return the string
	 */
	@GetMapping("/{id}")
	public String showCourseDetailPage(@PathVariable("id") UUID id, Model model) {

		model.addAttribute("course", courseService.getOne(id));

		return "course-detail-page";
	}

	/**
	 * Show create page.
	 *
	 * @param model the model
	 * @return the string
	 */
	@GetMapping("/create")
	public String showCreatePage(Model model) {

		model.addAttribute("newCourse", new Course());

		return "course-create-page";
	}

	/**
	 * Creates the course.
	 *
	 * @param course the course
	 * @param model the model
	 * @return the string
	 */
	@PostMapping("/create")
	public String createCourse(@ModelAttribute Course course, Model model) {

		System.out.println(course.toString());

		Course newCourse = courseService.createOne(course);

		model.addAttribute("newCourse", newCourse);

		return "course-create-page";
	}

	/**
	 * Update course.
	 *
	 * @param course the course
	 * @return the string
	 */
	@PostMapping("/{id}")
	public String updateCourse(@ModelAttribute Course course) {

		System.out.println("update = " + course.toString());
		courseService.updateOne(course);

		return "redirect:/admin/courses/" + course.getId().toString();
	}
	
	/**
	 * Delete course.
	 *
	 * @param id the id
	 * @param model the model
	 * @return the string
	 */
	@GetMapping("/delete/{id}")
	public String deleteCourse(@PathVariable("id") UUID id, Model model) {

		courseService.deleteOne(id);

		return "redirect:/admin/courses";
	}
	
	/**
	 * Show combo create page.
	 *
	 * @param model the model
	 * @return the string
	 */
	@GetMapping("/combo/create")
	public String showComboCreatePage(Model model) {

		model.addAttribute("listCourses", courseService.getAllForCombo());
		model.addAttribute("newCourse", new Course());

		return "combo-create-page";
	}
	
	/**
	 * Show combo detail page.
	 *
	 * @param id the id
	 * @param model the model
	 * @return the string
	 */
	@GetMapping("/combo/{id}")
	public String showComboDetailPage(@PathVariable("id") UUID id, Model model) {
		
		System.out.println("voo");

		model.addAttribute("course", courseService.getCombo(id));
		model.addAttribute("listCourses", courseService.getAllForCombo());

		return "combo-detail-page";
	}
	
	/**
	 * Creates the combo.
	 *
	 * @param course the course
	 * @param model the model
	 * @return the string
	 */
	@PostMapping("/combo/create")
	public String createCombo(@ModelAttribute Course course, Model model) {

		System.out.println(course.getCourseIds().toString());

		courseService.createCombo(course);

		return "redirect:/admin/courses";
	}
	
	/**
	 * Update combo.
	 *
	 * @param course the course
	 * @return the string
	 */
	@PostMapping("/combo/{id}")
	public String updateCombo(@ModelAttribute Course course) {

		System.out.println("update combo = " + course.toString());
		courseService.updateCombo(course);

		return "redirect:/admin/courses";
	}

}

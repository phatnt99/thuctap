package net.freetuts.frontend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import net.freetuts.frontend.constant.PostTypeEnum;
import net.freetuts.frontend.model.Category;
import net.freetuts.frontend.model.CategoryDisplay;
import net.freetuts.frontend.model.Course;
import net.freetuts.frontend.model.CourseDisplay;
import net.freetuts.frontend.model.Invoice;
import net.freetuts.frontend.model.MetaCategoryDTO;
import net.freetuts.frontend.model.PageAndSort;
import net.freetuts.frontend.model.Post;
import net.freetuts.frontend.model.PostDisplay;
import net.freetuts.frontend.services.CategoryService;
import net.freetuts.frontend.services.CourseService;
import net.freetuts.frontend.services.InvoiceService;
import net.freetuts.frontend.services.PostService;
import net.freetuts.frontend.services.TutorialService;

/**
 * The Class RouteController.
 */
@Controller
public class RouteController {

	/** The post service. */
	@Autowired
	private PostService postService;

	/** The tut service. */
	@Autowired
	private TutorialService tutService;

	/** The categogy service. */
	@Autowired
	private CategoryService categogyService;

	/** The course service. */
	@Autowired
	private CourseService courseService;

	/** The invoice service. */
	@Autowired
	private InvoiceService invoiceService;

	/**
	 * Show one post.
	 *
	 * @param slug the slug
	 * @param model the model
	 * @return the string
	 */
	@GetMapping("/{slug}.html")
	public String showOnePost(@PathVariable String slug, Model model) {

		PostDisplay postDisplay = postService.getOneBySlug(slug);

		model.addAttribute("post", postDisplay.getPost());
		model.addAttribute("breadcrumb", postDisplay.getBc().getEntries());

		// get pattern
		String prefix = postDisplay.getPost().getPostType().toLowerCase();
		if (PostTypeEnum.LESSON.getValue().toLowerCase().equals(prefix)) {
			model.addAttribute("series", tutService
					.getSeries(postDisplay.getPost().getCategoryId()));
			model.addAttribute("relatedSeries",
					tutService.getRelatedSeriesForLesson(
							postDisplay.getPost().getCategoryId()));
		}
		return prefix + "-page";
	}

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
		model.addAttribute("pagination", pageAndSort);
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
			processUsual(categoryDisplay, model, pageAndSort);
			break;
		default:
			processUsual(categoryDisplay, model, pageAndSort);
			break;
		}

		String viewBuilder = "category-"
				+ categoryDisplay.getCategory().getPattern().toLowerCase()
				+ "-page";
		return viewBuilder;
	}

	/**
	 * Show course.
	 *
	 * @param slug the slug
	 * @param model the model
	 * @return the string
	 */
	@GetMapping("/khoa-hoc/{slug}.html")
	public String showCourse(@PathVariable String slug, Model model) {
		List<Category> listCategory = categogyService.getByPath("khoa-hoc");

		CourseDisplay coursDisplay = courseService.getCourseBySlug(slug);

		Course course = coursDisplay.getCourse();

		Category category = categogyService
				.getOne(course.getPost().getCategoryId());

		List<Invoice> invoices = invoiceService
				.getTop10InvoiceByCourseId(course.getId());

		Map<String, List<Course>> data = new HashMap<String, List<Course>>();

		List<Course> courses = courseService
				.getAllCourseOnlyByCategorySlug(category.getSlug());

		if (courses.size() > 0)
			data.put(category.getName().toUpperCase(), courses);

		List<Course> combo = courseService
				.getAllComboOnlyBySlug(category.getSlug());

		model.addAttribute("categories", listCategory);
		model.addAttribute("course", course);
		model.addAttribute("currentCategory", category.getSlug());
		model.addAttribute("invoice", new Invoice(1, course.getId()));
		model.addAttribute("invoices", invoices);
		model.addAttribute("data", data);
		model.addAttribute("breadcrumb", coursDisplay.getBc().getEntries());
		if (combo != null && combo.size() > 0)
			model.addAttribute("combo", combo);

		return "course-page";
	}

	/**
	 * Show course root.
	 *
	 * @param model the model
	 * @return the string
	 */
	@GetMapping("/khoa-hoc")
	public String showCourseRoot(Model model) {
		List<Category> listCategory = categogyService.getByPath("khoa-hoc");

		// System.out.println("length category : " + listCategory.size());

		Map<String, List<Course>> data = new HashMap<String, List<Course>>();

		for (Category item : listCategory) {

			// System.out.println("category id : " + item.getId());

			List<Course> course = courseService
					.getAllCourseOnlyByCategory(item.getId());

			// System.out.println("length course : " + course.size());

			if (course.size() > 0)
				data.put(item.getName().toUpperCase(), course);
		}

		model.addAttribute("categories", listCategory);
		model.addAttribute("currentCategory", "khoa-hoc");
		model.addAttribute("data", data);

		return "user-all-courses";
	}

	/**
	 * Show child course.
	 *
	 * @param slug the slug
	 * @param model the model
	 * @return the string
	 */
	@GetMapping("/khoa-hoc/{slug}")
	public String showChildCourse(@PathVariable String slug, Model model) {
		List<Category> listCategory = categogyService.getByPath("khoa-hoc");

		Category category = categogyService.getOneBySlug(slug).getCategory();

		Map<String, List<Course>> data = new HashMap<String, List<Course>>();

		List<Course> course = courseService
				.getAllCourseOnlyByCategorySlug(slug);

		System.out.println("length course : " + course.size());

		if (course.size() > 0)
			data.put(category.getName().toUpperCase(), course);

		List<Course> combo = courseService.getAllComboOnlyBySlug(slug);

		model.addAttribute("categories", listCategory);
		model.addAttribute("data", data);
		model.addAttribute("currentCategory", slug);
		model.addAttribute("currentCategoryName", category.getName());
		if (combo != null && combo.size() > 0)
			model.addAttribute("combo", combo);

		return "user-all-courses";
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
		// processTopic(categoryDisplay, model);
		model.addAttribute("meta", categogyService.getMetaData(
				new MetaCategoryDTO(
						categoryDisplay.getCategory().getParentId())));

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

	/**
	 * Process usual.
	 *
	 * @param categoryDisplay the category display
	 * @param model the model
	 * @param pageable the pageable
	 */
	private void processUsual(CategoryDisplay categoryDisplay, Model model, PageAndSort pageable) {
		// just get all post in category
		model.addAttribute("posts", postService.getAllByParentCategorySlug(
				categoryDisplay.getCategory().getSlug(), pageable));
		// and get child category
		if (categoryDisplay.getCategory().isHasChild() || categoryDisplay.getCategory().getParentId() == null) {
			model.addAttribute("categories", categogyService
					.getByParentId(categoryDisplay.getCategory().getId()));
		} else {
			model.addAttribute("categories", categogyService
					.getByParentId(categoryDisplay.getCategory().getParentId()));
		}

	}
}

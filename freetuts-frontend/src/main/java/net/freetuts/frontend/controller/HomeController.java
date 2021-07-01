package net.freetuts.frontend.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.freetuts.frontend.services.DisplayService;
import net.freetuts.frontend.services.PostService;

/**
 * The Class HomeController.
 */
@Controller
@RequestMapping(value = {
		"/{locale:en|fr|vi}/", "/"
})
public class HomeController {
	
	/** The display service. */
	@Autowired
	private DisplayService displayService;
	
	/** The post service. */
	@Autowired
	private PostService postService;

	/**
	 * Index.
	 *
	 * @param model the model
	 * @return the string
	 */
	@GetMapping
	public String index(Model model) {
		
		model.addAttribute("menu", displayService.getMenu());
		
		model.addAttribute("homePosts", postService.getPostForHome());

		return "home";
	}

	/**
	 * Gets the user.
	 *
	 * @param model the model
	 * @return the user
	 */
	@RequestMapping(value = {
			"/home2"
	})
	public String getuser(Model model) {
		// ResponseEntity<UserJson> response =
		// restTemplate.getForEntity(UrlUtil.USER_REST + "/1", UserJson.class);
		// model.addAttribute("getuser1", response.getBody());

		model.addAttribute("date", LocalDate.now());
		return "home2";
	}
	
	/**
	 * Gets the admin dashboard.
	 *
	 * @return the admin dashboard
	 */
	@GetMapping("/menu3")
	public String getAdminDashboard() {
		
		return "dashboard";
	}
}

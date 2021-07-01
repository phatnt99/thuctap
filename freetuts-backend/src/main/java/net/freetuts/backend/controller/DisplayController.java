package net.freetuts.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.freetuts.backend.dto.Menu;
import net.freetuts.backend.entity.Category;
import net.freetuts.backend.services.CategoryService;
import net.freetuts.backend.utils.RestResponse;

/**
 * The Class DisplayController.
 */
@RestController
@RequestMapping("/display")
public class DisplayController {
	
	/** The category service. */
	@Autowired
	private CategoryService categoryService;
	
	/**
	 * Gets the menu.
	 *
	 * @return the menu
	 */
	@GetMapping("/menu")
	public ResponseEntity<?> getMenu() {
		Menu menu = new Menu();
		// get all top menu category
		List<Category> topMenuCategory = categoryService.getAllIsTopMenu();
		
		menu.initWithTopMenu(topMenuCategory);
		
		return RestResponse.sendOk(menu);
	}
}

package net.freetuts.frontend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import net.freetuts.frontend.display.Menu;

import net.freetuts.frontend.services.DisplayService;

import net.freetuts.frontend.utils.UrlUtil;

/**
 * The Class DisplayServiceImpl.
 */
@Service
public class DisplayServiceImpl implements DisplayService {

	/** The rest template. */
	@Autowired
	private RestTemplate restTemplate;

	/**
	 * Gets the menu.
	 *
	 * @return the menu
	 */
	@Override
	public Menu getMenu() {

		ResponseEntity<Menu> response = restTemplate.getForEntity(
				UrlUtil.DISPLAY_REST + "/menu", Menu.class);
		
		Menu menu = response.getBody();
		// process course
		menu.processCourse();
		return response.getBody();
	}

}

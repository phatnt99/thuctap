package net.freetuts.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.freetuts.backend.dto.ComboCreateDTO;
import net.freetuts.backend.dto.ComboUpdateDTO;
import net.freetuts.backend.services.CourseService;
import net.freetuts.backend.utils.RestResponse;

/**
 * The Class ComboController.
 */
@RestController
@RequestMapping("/courses/combo")
public class ComboController {
	
	/** The course service. */
	@Autowired
	private CourseService courseService;
	

	/**
	 * Creates the one combo.
	 *
	 * @param comboCreateDTO the combo create DTO
	 * @return the response entity
	 */
	@PostMapping
	public ResponseEntity<?> createOneCombo(@RequestBody ComboCreateDTO comboCreateDTO) {

		System.out.println(comboCreateDTO.toString());
		return RestResponse.sendCreated(courseService.createCombo(comboCreateDTO));
	}
	
	/**
	 * Update one combo.
	 *
	 * @param comboUpdateDTO the combo update DTO
	 * @return the response entity
	 */
	@PutMapping("/{id}")
	public ResponseEntity<?> updateOneCombo(@RequestBody ComboUpdateDTO comboUpdateDTO) {

		courseService.updateCombo(comboUpdateDTO);
		
		return RestResponse.sendNoContent();
	}

}

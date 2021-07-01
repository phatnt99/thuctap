package net.freetuts.frontend.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import net.freetuts.frontend.model.PageAndSort;
import net.freetuts.frontend.model.Report;
import net.freetuts.frontend.services.ReportService;

/**
 * The Class ReportController.
 */
@Controller
public class ReportController {

	/** The report service. */
	@Autowired
	ReportService reportService;

	/**
	 * Gets the all report.
	 *
	 * @param model the model
	 * @param pageAndSort the page and sort
	 * @return the all report
	 */
	@GetMapping("/admin/reports")
	public String getAllReport(Model model,
			@ModelAttribute PageAndSort pageAndSort) {
		model.addAttribute("reports", reportService.getAll(pageAndSort));
		model.addAttribute("pagination", pageAndSort);

		return "report-list-page";
	}
	
	/**
	 * Show detail.
	 *
	 * @param id the id
	 * @param model the model
	 * @return the string
	 */
	@GetMapping("/admin/reports/{id}")
	public String showDetail(@PathVariable("id") UUID id, Model model) {
		Report report = reportService.getOne(id);
		model.addAttribute("report", report);

		return "report-detail-page";
	}
	
 

	/**
	 * Creates the coupon.
	 *
	 * @param report the report
	 * @return the response entity
	 */
	@PostMapping( "/report")
	public ResponseEntity<?> createCoupon(Report report) {
		String content = report.getContent();
		reportService.createOne(report);
		return new ResponseEntity<>(content, HttpStatus.CREATED);
	}

}

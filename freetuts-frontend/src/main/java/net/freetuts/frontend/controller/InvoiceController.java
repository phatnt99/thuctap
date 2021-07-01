package net.freetuts.frontend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.freetuts.frontend.model.Course;
import net.freetuts.frontend.model.Invoice;
import net.freetuts.frontend.services.CourseService;
import net.freetuts.frontend.services.InvoiceService;

/**
 * The Class InvoiceController.
 */
@Controller
@RequestMapping(value = { "/{locale:en|fr|vi}/", "/invoices" })
public class InvoiceController {

	/** The invoice service. */
	@Autowired
	private InvoiceService invoiceService;

	/** The course service. */
	@Autowired
	private CourseService courseService;

	/**
	 * Creates the invoice.
	 *
	 * @param invoice the invoice
	 * @param model the model
	 * @return the string
	 */
	@PostMapping("/create")
	public String createInvoice(@ModelAttribute Invoice invoice, Model model) {

		System.out.println(invoice.toString());

		Invoice newInvoice = invoiceService.createOne(invoice);
		
		String url = newInvoice.getCourse().getPost().getSlug() + ".html";

		return "redirect:/khoa-hoc/" + url;
	}

	/**
	 * Register user combo.
	 *
	 * @param invoice the invoice
	 * @param model the model
	 * @return the string
	 */
	@PostMapping("/register-user-combo")
	public String registerUserCombo(@ModelAttribute Invoice invoice, Model model) {

		System.out.println(invoice.getListIds().toString());

		Course combo = new Course();

		combo.setPrice(invoice.getTotalPrice().intValue());

		combo.setCourseName("Tu tao combo " + invoice.getListIds());

		combo.setType(3);
		
		List<UUID> courseIds = new ArrayList<UUID>();
		
		String[] idSelected = (invoice.getListIds().replaceAll(" ", "")).split(",");

		for (String id : idSelected) {
			courseIds.add(UUID.fromString(id));
		}

		combo.setCourseIds(courseIds);

		Course newCombo = courseService.createUserCombo(combo);

		if (newCombo != null) {
			invoice.setCourseId(newCombo.getId());
			invoiceService.createInvoiceCombo(invoice);
			return "redirect:/khoa-hoc";
		}
		return "redirect:/khoa-hoc/tao-combo/confirm?ids=" + invoice.getListIds().toString();

	}

}

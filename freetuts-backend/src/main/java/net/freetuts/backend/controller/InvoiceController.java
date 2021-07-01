package net.freetuts.backend.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.freetuts.backend.dto.InvoiceCreateDTO;
import net.freetuts.backend.services.InvoiceService;
import net.freetuts.backend.utils.RestResponse;

/**
 * The Class InvoiceController.
 */
@RestController
@RequestMapping("/invoices")
public class InvoiceController {
	
	/** The invoice service. */
	@Autowired
	private InvoiceService invoiceService;
	
	/**
	 * Creates the one invoice.
	 *
	 * @param invoiceCreateDTO the invoice create DTO
	 * @return the response entity
	 */
	@PostMapping
	public ResponseEntity<?> createOneInvoice(@RequestBody InvoiceCreateDTO invoiceCreateDTO) {

		System.out.println(invoiceCreateDTO.toString());

		return RestResponse.sendCreated(invoiceService.createOne(invoiceCreateDTO));
	}
	
	/**
	 * Gets the all invoice by course id.
	 *
	 * @param id the id
	 * @return the all invoice by course id
	 */
	@GetMapping("/course/{id}")
	public ResponseEntity<?> getAllInvoiceByCourseId(@PathVariable UUID id)
	{
		System.out.println("course id" + id.toString());
		return RestResponse.sendOk(invoiceService.getAllInvoiceByCourseId(id));
	}
	
	/**
	 * Creates the invoice combo.
	 *
	 * @param invoiceCreateDTO the invoice create DTO
	 * @return the response entity
	 */
	@PostMapping("/course")
	public ResponseEntity<?> createInvoiceCombo(@RequestBody InvoiceCreateDTO invoiceCreateDTO)
	{
		System.out.println(invoiceCreateDTO.toString());

		return RestResponse.sendCreated(invoiceService.createInvoiceCombo(invoiceCreateDTO));
	}
	

}

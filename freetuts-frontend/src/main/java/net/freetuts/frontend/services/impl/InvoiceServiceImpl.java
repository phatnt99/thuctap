package net.freetuts.frontend.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import net.freetuts.frontend.model.Invoice;
import net.freetuts.frontend.model.PageAndSort;
import net.freetuts.frontend.services.InvoiceService;
import net.freetuts.frontend.utils.PaymentUtil;
import net.freetuts.frontend.utils.RestPage;
import net.freetuts.frontend.utils.UrlUtil;

/**
 * The Class InvoiceServiceImpl.
 */
@Service
public class InvoiceServiceImpl implements InvoiceService{
	
	/** The rest template. */
	@Autowired
	private RestTemplate restTemplate;

	/**
	 * Gets the all.
	 *
	 * @param pageAndSort the page and sort
	 * @return the all
	 */
	@Override
	public RestPage<Invoice> getAll(PageAndSort pageAndSort) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Creates the one.
	 *
	 * @param invoice the invoice
	 * @return the invoice
	 */
	@Override
	public Invoice createOne(Invoice invoice) {
		invoice.setStatus(0);
		if(PaymentUtil.POST_OFFICE == invoice.getPaymentMethod())
			invoice.setTotalPrice(invoice.getTotalPrice() + PaymentUtil.FEE_POST_OFFICE);
		else if(PaymentUtil.PAYPAL == invoice.getPaymentMethod())
			invoice.setTotalPrice(invoice.getTotalPrice() * PaymentUtil.USD);
		ResponseEntity<Invoice> response = restTemplate.postForEntity(UrlUtil.INVOICE_REST, invoice, Invoice.class);
		return response.getBody();
	}
	
	/**
	 * Creates the invoice combo.
	 *
	 * @param invoice the invoice
	 * @return the invoice
	 */
	@Override
	public Invoice createInvoiceCombo(Invoice invoice) {
		invoice.setStatus(0);
		if(PaymentUtil.POST_OFFICE == invoice.getPaymentMethod())
			invoice.setTotalPrice(invoice.getTotalPrice() + PaymentUtil.FEE_POST_OFFICE);
		else if(PaymentUtil.PAYPAL == invoice.getPaymentMethod())
			invoice.setTotalPrice(invoice.getTotalPrice() * PaymentUtil.USD);
		ResponseEntity<Invoice> response = restTemplate.postForEntity(UrlUtil.INVOICE_REST + "/course", invoice, Invoice.class);
		return response.getBody();
	}

	/**
	 * Gets the one.
	 *
	 * @param id the id
	 * @return the one
	 */
	@Override
	public Invoice getOne(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Update one.
	 *
	 * @param post the post
	 */
	@Override
	public void updateOne(Invoice post) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Delete one.
	 *
	 * @param id the id
	 */
	@Override
	public void deleteOne(UUID id) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Gets the top 10 invoice by course id.
	 *
	 * @param id the id
	 * @return the top 10 invoice by course id
	 */
	@Override
	public List<Invoice> getTop10InvoiceByCourseId(UUID id) {
		ResponseEntity<Invoice[]> response = restTemplate
				.getForEntity(UrlUtil.INVOICE_REST + "/course/" + id.toString(), Invoice[].class);

		return Arrays.asList(response.getBody());
	}

}

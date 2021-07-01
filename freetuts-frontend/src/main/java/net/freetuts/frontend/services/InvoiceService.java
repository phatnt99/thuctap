package net.freetuts.frontend.services;

import java.util.List;
import java.util.UUID;

import net.freetuts.frontend.model.Invoice;

public interface InvoiceService extends CrudService<Invoice>{
	
	List<Invoice> getTop10InvoiceByCourseId(UUID id);
	
	Invoice createInvoiceCombo(Invoice invoice);

}

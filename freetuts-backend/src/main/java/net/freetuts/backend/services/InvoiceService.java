package net.freetuts.backend.services;

import java.util.List;
import java.util.UUID;

import net.freetuts.backend.dto.InvoiceResponse;
import net.freetuts.backend.entity.Invoice;

public interface InvoiceService extends CrudService<InvoiceResponse>{
	
	Invoice createInvoiceCombo(Object entityDTO);
	
	List<Invoice> getAllInvoiceByCourseId(UUID id);

}

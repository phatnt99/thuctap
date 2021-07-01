package net.freetuts.backend.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.freetuts.backend.dto.CourseResponse;
import net.freetuts.backend.dto.InvoiceCreateDTO;
import net.freetuts.backend.dto.InvoiceResponse;
import net.freetuts.backend.entity.Invoice;
import net.freetuts.backend.repository.CourseRepository;
import net.freetuts.backend.repository.InvoiceRepository;
import net.freetuts.backend.repository.PostRepository;
import net.freetuts.backend.services.InvoiceService;
import net.freetuts.backend.utils.ObjectUtil;

/**
 * The Class InvoiceServiceImpl.
 */
@Service
public class InvoiceServiceImpl implements InvoiceService {

	/** The invoice repository. */
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	/** The course repository. */
	@Autowired
	private CourseRepository courseRepository;
	
	/** The post repository. */
	@Autowired
	private PostRepository postRepository;
	
	/**
	 * Gets the all.
	 *
	 * @param pageable the pageable
	 * @return the all
	 */
	@Override
	public Page<InvoiceResponse> getAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Gets the one.
	 *
	 * @param id the id
	 * @return the one
	 */
	@Override
	public InvoiceResponse getOne(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Creates the one.
	 *
	 * @param entityDTO the entity DTO
	 * @return the invoice response
	 */
	@Override
	public InvoiceResponse createOne(Object entityDTO) {
		InvoiceCreateDTO invoiceCreateDTO = (InvoiceCreateDTO) entityDTO;
		Invoice invoice = ObjectUtil.copyPropertiesObject(invoiceCreateDTO, Invoice.class);
		
		invoice.setCourse(courseRepository.findById(invoiceCreateDTO.getCourseId()).orElseThrow(null));
		
		InvoiceResponse result = ObjectUtil.copyPropertiesObject(invoiceRepository.save(invoice), InvoiceResponse.class);
		
		CourseResponse courseResponse = ObjectUtil.copyPropertiesObject(invoice.getCourse(), CourseResponse.class);
		
		courseResponse.setPost(postRepository.findByPostableId(invoiceCreateDTO.getCourseId()).orElseThrow(null));
		
		result.setCourse(courseResponse);

		return result;
	}

	/**
	 * Update one.
	 *
	 * @param entityDTO the entity DTO
	 */
	@Override
	public void updateOne(Object entityDTO) {
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
	 * Gets the all invoice by course id.
	 *
	 * @param id the id
	 * @return the all invoice by course id
	 */
	@Override
	public List<Invoice> getAllInvoiceByCourseId(UUID id) {
		List<Invoice> invoices = invoiceRepository.findAllByCourseIdOrderByCreatedAtDesc(id);

		return invoices;
	}

	/**
	 * Creates the invoice combo.
	 *
	 * @param entityDTO the entity DTO
	 * @return the invoice
	 */
	@Override
	public Invoice createInvoiceCombo(Object entityDTO) {
		InvoiceCreateDTO invoiceCreateDTO = (InvoiceCreateDTO) entityDTO;
		Invoice invoice = ObjectUtil.copyPropertiesObject(invoiceCreateDTO, Invoice.class);
		invoice.setCourse(courseRepository.findById(invoiceCreateDTO.getCourseId()).orElseThrow(null));
		return invoiceRepository.save(invoice);
	}

}

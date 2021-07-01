package net.freetuts.backend.services.impl;

import java.lang.invoke.MethodHandles;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.freetuts.backend.entity.Report;
import net.freetuts.backend.exception.domain.ReportNotFoundException;
import net.freetuts.backend.repository.ReportRepository;
import net.freetuts.backend.services.ReportService;

/**
 * The Class ReportServiceImpl.
 */
@Service
public class ReportServiceImpl extends GernericServiceImpl<Report, UUID>
		implements ReportService {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(MethodHandles.lookup().lookupClass());

	/**
	 * Instantiates a new report service impl.
	 *
	 * @param genericRepository the generic repository
	 */
	@Autowired
	public ReportServiceImpl(ReportRepository genericRepository) {
		super(genericRepository);
	}

	/**
	 * Gets the all.
	 *
	 * @param pageable the pageable
	 * @return the all
	 */
	@Override
	public Page<Report> getAll(Pageable pageable) {
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
	public Report getOne(UUID id) {
		LOGGER.info("findCouponById = {}", id);

		Report report = findById(id);

		if (report == null) {
			throw new ReportNotFoundException(
					"Report Not Found For ID : " + id);
		}

		return report;		 
	}

	/**
	 * Creates the one.
	 *
	 * @param entityDTO the entity DTO
	 * @return the report
	 */
	@Override
	public Report createOne(Object entityDTO) {
		return save((Report) entityDTO);

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

}

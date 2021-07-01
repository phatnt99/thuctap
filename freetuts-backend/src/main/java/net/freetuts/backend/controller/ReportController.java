package net.freetuts.backend.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.freetuts.backend.utils.URL;
import net.freetuts.backend.dto.PageableDTO;
import net.freetuts.backend.dto.ReportDTO;
import net.freetuts.backend.entity.Report;
import net.freetuts.backend.services.ReportService;
import net.freetuts.backend.utils.ObjectUtil;
import net.freetuts.backend.utils.RestResponse;

/**
 * The Class ReportController.
 */
@RestController
public class ReportController {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ReportController.class);

	/** The report service. */
	@Autowired
	ReportService reportService;

	/**
	 * Creates the report.
	 *
	 * @param createReportDTO the create report DTO
	 * @return the response entity
	 */
	@PostMapping(URL.REPORT)
	public ResponseEntity<?> createReport(
			@RequestBody ReportDTO createReportDTO) {

		LOGGER.info("Create New Report = {} ", createReportDTO);

		Report createReport = ObjectUtil.copyPropertiesObject(createReportDTO,
				Report.class);

		Report newReport = reportService.createOne(createReport);

		ReportDTO reportDTO = ObjectUtil.copyPropertiesObject(newReport,
				ReportDTO.class);

		return RestResponse.sendCreated(reportDTO);

	}

	/**
	 * Find all reports by page.
	 *
	 * @param pageableDTO the pageable DTO
	 * @return the response entity
	 */
	@GetMapping(URL.ADMIN_REPORT)
	public ResponseEntity<?> findAllReportsByPage(
			@ModelAttribute PageableDTO pageableDTO) {
		LOGGER.info("Find All Reports By Page");

		Page<Report> couponFindList = reportService.findAll(pageableDTO.get());

		return RestResponse.sendOk(couponFindList);

	}

 
 

	/**
	 * Find report by ID.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@GetMapping(URL.ADMIN_REPORT + "/{id}")
	public ResponseEntity<?> findReportByID(
			@PathVariable String id) {

		LOGGER.info("Find Report By ID = {}  ", id);

		UUID uuid = UUID.fromString(id);

		Report couponFindById = reportService.getOne(uuid);

		ReportDTO couponDTO = ObjectUtil.copyPropertiesObject(couponFindById,
				ReportDTO.class);

		return RestResponse.sendOk(couponDTO);

	}

}

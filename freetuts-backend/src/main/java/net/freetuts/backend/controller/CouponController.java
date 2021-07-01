package net.freetuts.backend.controller;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.freetuts.backend.utils.URL;
import net.freetuts.backend.dto.CouponDTO;
import net.freetuts.backend.dto.PageableDTO;
import net.freetuts.backend.entity.Coupon;
import net.freetuts.backend.services.CouponService;
import net.freetuts.backend.utils.ObjectUtil;
import net.freetuts.backend.utils.RestResponse;

/**
 * The Class CouponController.
 */
//@Api(value = "CouponController", tags = "REST APIs related to Coupon Entity.")
@RestController
public class CouponController {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(CouponController.class);

	/** The coupon service. */
	@Autowired
	CouponService couponService;

 

	/**
	 * Creates the coupon.
	 *
	 * @param createCouponDTO the create coupon DTO
	 * @return the response entity
	 */
	@PostMapping(URL.ADMIN_COUPON)
	public ResponseEntity<?> createCoupon(
			@RequestBody CouponDTO createCouponDTO) {

		LOGGER.info("Create New Coupon = {} ", createCouponDTO);

		Coupon createCoupon = ObjectUtil.copyPropertiesObject(createCouponDTO, Coupon.class);

		Coupon newCoupon = couponService.createCoupon(createCoupon);

		CouponDTO couponDTO = ObjectUtil.copyPropertiesObject(newCoupon, CouponDTO.class);

		return RestResponse.sendCreated(couponDTO);

	}


	// @PostMapping(URL.ADMIN_COUPON)
	// public ResponseEntity<?> createCouponList(
	// @RequestBody List<CouponDTO> createCouponListDTO) {
	//
	// LOGGER.info("Create List Coupon = {} ", createCouponListDTO);
	//
	// List<Coupon> createCouponList = ObjectUtil.mapList(createCouponListDTO,
	// Coupon.class);
	//
	// List<Coupon> newCouponList = couponService
	// .createCouponList(createCouponList);
	//
	// List<CouponDTO> newCouponDTOList = ObjectUtil.mapList(newCouponList,
	// CouponDTO.class);
	//
	// return new ResponseEntity<>(newCouponDTOList, HttpStatus.CREATED);
	//
	// }

	/**
	 * Find all coupons by page.
	 *
	 * @param pageableDTO the pageable DTO
	 * @return the response entity
	 */
	@GetMapping(URL.COUPON)
	public ResponseEntity<?> findAllCouponsByPage(
			@ModelAttribute PageableDTO pageableDTO) {
		LOGGER.info("Find All Coupons By Page");

		Page<Coupon> couponFindList = couponService.findAll(pageableDTO.get());

		return RestResponse.sendOk(couponFindList);

	}

	/**
	 * Find coupon by code or ID.
	 *
	 * @param couponCodeOrCouponId the coupon code or coupon id
	 * @return the response entity
	 */
	@GetMapping(URL.COUPON + "/{couponCodeOrCouponId}")
	public ResponseEntity<?> findCouponByCodeOrID(
			@PathVariable String couponCodeOrCouponId) {
		CouponDTO couponDTO = null;

		try {
			UUID id = UUID.fromString(couponCodeOrCouponId);
			LOGGER.info("Find Coupon By ID = {}  ", id);

			Coupon couponFindById = couponService.findCouponById(id);

			couponDTO = ObjectUtil.copyPropertiesObject(couponFindById, CouponDTO.class);

		} catch (IllegalArgumentException e) {

			String couponCode = couponCodeOrCouponId;

			LOGGER.info("Find Coupon By Code = {}  ", couponCode);

			Coupon couponFindByCode = couponService
					.findCouponByCode(couponCode);

			couponDTO = ObjectUtil.copyPropertiesObject(couponFindByCode, CouponDTO.class);

		}

		return RestResponse.sendOk(couponDTO);

	}

	/**
	 * Update coupon.
	 *
	 * @param updateCouponDTO the update coupon DTO
	 * @return the response entity
	 */
	@PutMapping(URL.ADMIN_COUPON)
	public ResponseEntity<?> updateCoupon(
			@RequestBody CouponDTO updateCouponDTO) {

		LOGGER.info("Update Coupon = {} ", updateCouponDTO);

		Coupon updateCoupon = ObjectUtil.copyPropertiesObject(updateCouponDTO, Coupon.class);

		couponService.updateCoupon(updateCoupon);

		return RestResponse.sendNoContent();

	}

	/**
	 * Delete coupon list.
	 *
	 * @param deleteIdCouponList the delete id coupon list
	 * @return the response entity
	 */
	@DeleteMapping(URL.ADMIN_COUPON)
	public ResponseEntity<?> deleteCouponList(
			@RequestBody List<UUID> deleteIdCouponList) {

		LOGGER.info("Delete Coupon List = {}  ", deleteIdCouponList);

		List<Coupon> deleteCoupons = ObjectUtil
				.copyPropertiesList(deleteIdCouponList, Coupon.class);

		couponService.deleteCouponList(deleteCoupons);

		return RestResponse.sendNoContent();

	}

	/**
	 * Delete coupon.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@DeleteMapping(URL.ADMIN_COUPON + "/{id}")
	public ResponseEntity<?> deleteCoupon(
			@PathVariable UUID id) {

		LOGGER.info("Delete Coupon, ID = {}  ", id);

		Coupon deletedCoupon = new Coupon(id);

		couponService.deleteCoupon(deletedCoupon);

		return RestResponse.sendNoContent();
	}

}

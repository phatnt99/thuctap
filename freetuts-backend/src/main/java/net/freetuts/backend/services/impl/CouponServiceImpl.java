package net.freetuts.backend.services.impl;

import java.lang.invoke.MethodHandles;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.freetuts.backend.entity.Coupon;
import net.freetuts.backend.exception.domain.CouponExistException;
import net.freetuts.backend.exception.domain.CouponNotFoundException;
import net.freetuts.backend.repository.CouponRepository;
import net.freetuts.backend.services.CouponService;
import net.freetuts.backend.utils.ObjectUtil;

/**
 * The Class CouponServiceImpl.
 */
@Service
@Transactional
public class CouponServiceImpl extends GernericServiceImpl<Coupon, UUID>
		implements CouponService {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(MethodHandles.lookup().lookupClass());

	/** The coupon repository. */
	@Autowired
	private CouponRepository couponRepository;

	/**
	 * Instantiates a new coupon service impl.
	 *
	 * @param couponRepository the coupon repository
	 */
	@Autowired
	public CouponServiceImpl(CouponRepository couponRepository) {
		super(couponRepository);
	}

	/**
	 * Creates the coupon.
	 *
	 * @param createCoupon the create coupon
	 * @return the coupon
	 */
	@Override
	public Coupon createCoupon(Coupon createCoupon) {

		String couponCode = createCoupon.getCode();

		Coupon couponFindByCode = couponRepository.findByCode(couponCode);

		if (couponFindByCode != null) {
			throw new CouponExistException(
					"Existed Coupon For Code : " + couponCode);
		}

		return save(createCoupon);

	}

	/**
	 * Creates the coupon list.
	 *
	 * @param createCouponList the create coupon list
	 * @return the list
	 */
	@Override
	public List<Coupon> createCouponList(List<Coupon> createCouponList) {

		Coupon couponFindByCode = null;
		String couponCode       = null;

		for (Coupon createCoupon : createCouponList) {
			couponCode       = createCoupon.getCode();
			couponFindByCode = couponRepository.findByCode(couponCode);

			if (couponFindByCode != null) {
				throw new CouponExistException(
						"Existed Coupon For Code : " + couponCode);
			}
		}

		return (List<Coupon>) saveAll(createCouponList);
	}

	/**
	 * Find coupon by id.
	 *
	 * @param id the id
	 * @return the coupon
	 */
	@Override
	public Coupon findCouponById(UUID id) {
		LOGGER.info("findCouponById = {}", id);

		Coupon coupon = findById(id);

		if (coupon == null) {
			throw new CouponNotFoundException(
					"Coupon Not Found For ID : " + id);
		}

		return coupon;
	}

	/**
	 * Find coupon by code.
	 *
	 * @param couponCode the coupon code
	 * @return the coupon
	 */
	@Override
	public Coupon findCouponByCode(String couponCode) {

		Coupon coupon = couponRepository.findByCode(couponCode);

		if (coupon == null) {
			throw new CouponNotFoundException(
					"No Coupon Found For Code : " + couponCode);
		}

		return coupon;
	}

	/**
	 * Update coupon.
	 *
	 * @param updateCoupon the update coupon
	 * @return the coupon
	 */
	@Override
	public Coupon updateCoupon(Coupon updateCoupon) {

		LOGGER.info("updateCoupon = {}", updateCoupon);

		UUID id = updateCoupon.getId();

		Coupon findCoupon = this.findCouponById(id);

		ObjectUtil.copyNonNullProperties(updateCoupon, findCoupon);

		findCoupon.setUpdatedAt(LocalDateTime.now());
 

		return save(findCoupon);
	}

	/**
	 * Delete coupon.
	 *
	 * @param deletedCoupon the deleted coupon
	 */
	@Override
	public void deleteCoupon(Coupon deletedCoupon) {

		LOGGER.info("deleteCoupon =  {}", deletedCoupon);

		UUID id = deletedCoupon.getId();

		Coupon findCoupon = this.findCouponById(id);

		couponRepository.delete(findCoupon);
	}

	/**
	 * Delete coupon list.
	 *
	 * @param deleteCoupons the delete coupons
	 */
	@Override
	public void deleteCouponList(List<Coupon> deleteCoupons) {

		List<Coupon> deleteCouponFindById = new ArrayList<>();

		Coupon couponFindById = null;
		for (Coupon coupon : deleteCoupons) {
			couponFindById = this.findCouponById(coupon.getId());
			deleteCouponFindById.add(couponFindById);
		}

		deleteAll(deleteCouponFindById);
	}

}

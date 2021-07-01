package net.freetuts.backend.services;

import java.util.List;
import java.util.UUID;

import net.freetuts.backend.entity.Coupon;

public interface CouponService extends GenericService<Coupon, UUID> {

	Coupon createCoupon(Coupon createCoupon);

	List<Coupon> createCouponList(List<Coupon> createCouponList);

	Coupon updateCoupon(Coupon updateCoupon);

	Coupon findCouponById(UUID id);

	Coupon findCouponByCode(String couponCode);

	void deleteCoupon(Coupon deletedCoupon);

	void deleteCouponList(List<Coupon> deleteCoupons);
}

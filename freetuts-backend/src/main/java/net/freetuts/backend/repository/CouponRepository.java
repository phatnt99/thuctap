package net.freetuts.backend.repository;

import java.util.UUID;

import net.freetuts.backend.entity.Coupon;

public interface CouponRepository extends GenericReposity<Coupon, UUID> {

	Coupon findByCode(String couponCode);
}

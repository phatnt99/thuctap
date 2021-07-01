package net.freetuts.frontend.services;

import java.util.List;

import net.freetuts.frontend.model.Coupon;

public interface CouponService extends CrudService<Coupon> {
	
	List<Coupon> getAll();
}

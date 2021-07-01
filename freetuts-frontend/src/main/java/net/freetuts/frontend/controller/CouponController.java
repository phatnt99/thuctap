package net.freetuts.frontend.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.freetuts.frontend.model.Coupon;
import net.freetuts.frontend.model.PageAndSort;
import net.freetuts.frontend.services.CouponService;

/**
 * The Class CouponController.
 */
@Controller
@RequestMapping("/admin/coupons")
public class CouponController {

	/** The coupon service. */
	@Autowired
	CouponService couponService;

	/**
	 * Gets the all coupon.
	 *
	 * @param model the model
	 * @param pageAndSort the page and sort
	 * @return the all coupon
	 */
	@GetMapping
	public String getAllCoupon(Model model,
			@ModelAttribute PageAndSort pageAndSort) {
		model.addAttribute("coupons", couponService.getAll(pageAndSort));
		model.addAttribute("pagination", pageAndSort);

		return "coupon-list-page";
	}

	/**
	 * Delete coupon.
	 *
	 * @param id the id
	 * @return the string
	 */
	@GetMapping("/delete/{id}")
	public String deleteCoupon(@PathVariable("id") UUID id) {

		couponService.deleteOne(id);

		return "redirect:/admin/coupons";
	}

	/**
	 * Show update page.
	 *
	 * @param id the id
	 * @param model the model
	 * @return the string
	 */
	@GetMapping("/{id}")
	public String showUpdatePage(@PathVariable("id") UUID id, Model model) {

		Coupon coupon = couponService.getOne(id);
		model.addAttribute("coupon", coupon);

		return "coupon-detail-page";
	}

	/**
	 * Update coupon.
	 *
	 * @param coupon the coupon
	 * @return the string
	 */
	@PostMapping("/{id}")
	public String updateCoupon(@ModelAttribute Coupon coupon) {
		couponService.updateOne(coupon);
		return "redirect:/admin/coupons/" + coupon.getId();
	}

	/**
	 * Show create page.
	 *
	 * @param model the model
	 * @return the string
	 */
	@GetMapping("/create")
	public String showCreatePage(Model model) {

		model.addAttribute("coupon", new Coupon());

		return "coupon-create-page";
	}

	/**
	 * Creates the coupon.
	 *
	 * @param coupon the coupon
	 * @param model the model
	 * @return the string
	 */
	@PostMapping("/create")
	public String createCoupon(@ModelAttribute Coupon coupon, Model model) {

		Coupon createdcoupon = couponService.createOne(coupon);

		model.addAttribute("coupon", createdcoupon);

		return "coupon-create-page";
	}

}

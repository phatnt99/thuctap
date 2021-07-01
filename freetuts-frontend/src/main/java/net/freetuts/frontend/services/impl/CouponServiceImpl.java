package net.freetuts.frontend.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import net.freetuts.frontend.model.Coupon;
import net.freetuts.frontend.model.PageAndSort;
import net.freetuts.frontend.services.CouponService;
import net.freetuts.frontend.utils.RestPage;
import net.freetuts.frontend.utils.UrlUtil;

/**
 * The Class CouponServiceImpl.
 */
@Service
public class CouponServiceImpl implements CouponService {

	/** The rest template. */
	@Autowired
	RestTemplate restTemplate;

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	@Override
	public List<Coupon> getAll() {
		ResponseEntity<Coupon[]> response = restTemplate
				.getForEntity(UrlUtil.COUPON_REST, Coupon[].class);

		return Arrays.asList(response.getBody());

	}

	/**
	 * Gets the all.
	 *
	 * @param pageAndSort the page and sort
	 * @return the all
	 */
	@Override
	public RestPage<Coupon> getAll(PageAndSort pageAndSort) {

		String url = UrlUtil
				.buildPageAndSort(UrlUtil.COUPON_REST,
						pageAndSort);

		ResponseEntity<RestPage<Coupon>> responsePosts = restTemplate
				.exchange(
						url,
						HttpMethod.GET, null,
						new ParameterizedTypeReference<RestPage<Coupon>>() {
						});

		return responsePosts.getBody();

	}

	/**
	 * Creates the one.
	 *
	 * @param coupon the coupon
	 * @return the coupon
	 */
	@Override
	public Coupon createOne(Coupon coupon) {

		String url = UrlUtil.COUPON_ADMIN_REST;

		ResponseEntity<Coupon> responsePost = restTemplate.postForEntity(url,
				coupon, Coupon.class);

		return responsePost.getBody();
	}

	/**
	 * Gets the one.
	 *
	 * @param id the id
	 * @return the one
	 */
	@Override
	public Coupon getOne(UUID id) {

		String url = UrlUtil.COUPON_REST + "/" + id.toString();

		ResponseEntity<Coupon> responseCoupon = restTemplate.getForEntity(
				url, Coupon.class);

		return responseCoupon.getBody();
	}

	/**
	 * Update one.
	 *
	 * @param post the post
	 */
	@Override
	public void updateOne(Coupon post) {
		String url = UrlUtil.COUPON_ADMIN_REST;

		restTemplate.put(url, post);

	}

	/**
	 * Delete one.
	 *
	 * @param id the id
	 */
	@Override
	public void deleteOne(UUID id) {
		String url = UrlUtil.COUPON_ADMIN_REST + "/" + id.toString();
		restTemplate.delete(url);

	}

}

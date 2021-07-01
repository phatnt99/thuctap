package net.freetuts.frontend.services.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import net.freetuts.frontend.model.PageAndSort;
import net.freetuts.frontend.model.Report;
import net.freetuts.frontend.services.ReportService;
import net.freetuts.frontend.utils.RestPage;
import net.freetuts.frontend.utils.UrlUtil;

/**
 * The Class ReportServiceImpl.
 */
@Service
public class ReportServiceImpl implements ReportService {

	/** The rest template. */
	@Autowired
	RestTemplate restTemplate;

	/**
	 * Gets the all.
	 *
	 * @param pageAndSort the page and sort
	 * @return the all
	 */
	@Override
	public RestPage<Report> getAll(PageAndSort pageAndSort) {

		String url = UrlUtil
				.buildPageAndSort(UrlUtil.REPORT_ADMIN_REST,
						pageAndSort);

		ResponseEntity<RestPage<Report>> responsePosts = restTemplate
				.exchange(
						url,
						HttpMethod.GET, null,
						new ParameterizedTypeReference<RestPage<Report>>() {
						});

		return responsePosts.getBody();

	}

	/**
	 * Creates the one.
	 *
	 * @param post the post
	 * @return the report
	 */
	@Override
	public Report createOne(Report post) {
		String url = UrlUtil.REPORT_REST;

		ResponseEntity<Report> responsePost = restTemplate.postForEntity(url,
				post, Report.class);

		return responsePost.getBody();
	}

	/**
	 * Gets the one.
	 *
	 * @param id the id
	 * @return the one
	 */
	@Override
	public Report getOne(UUID id) {
		String url = UrlUtil.REPORT_ADMIN_REST + "/" + id.toString();

		ResponseEntity<Report> response = restTemplate.getForEntity(
				url, Report.class);

		return response.getBody();

	}

	/**
	 * Update one.
	 *
	 * @param post the post
	 */
	@Override
	public void updateOne(Report post) {
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

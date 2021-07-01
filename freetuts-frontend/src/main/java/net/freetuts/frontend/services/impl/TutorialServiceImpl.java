package net.freetuts.frontend.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import net.freetuts.frontend.model.Chapter;
import net.freetuts.frontend.model.PageAndSort;
import net.freetuts.frontend.model.Post;
import net.freetuts.frontend.model.Series;
import net.freetuts.frontend.model.SlimSeries;
import net.freetuts.frontend.model.Tutorial;
import net.freetuts.frontend.model.TutorialView;
import net.freetuts.frontend.services.TutorialService;
import net.freetuts.frontend.utils.PostUtil;
import net.freetuts.frontend.utils.RestPage;
import net.freetuts.frontend.utils.UrlUtil;

/**
 * The Class TutorialServiceImpl.
 */
@Service
public class TutorialServiceImpl implements TutorialService {

	/** The rest template. */
	@Autowired
	RestTemplate restTemplate;

	/**
	 * Gets the all for view.
	 *
	 * @param pageAndSort the page and sort
	 * @return the all for view
	 */
	public RestPage<TutorialView> getAllForView(PageAndSort pageAndSort) {

		ParameterizedTypeReference<RestPage<TutorialView>> responseType = new ParameterizedTypeReference<RestPage<TutorialView>>() {
		};

		ResponseEntity<RestPage<TutorialView>> responsePosts = restTemplate
				.exchange(
						UrlUtil.buildPageAndSort(UrlUtil.TUT_REST,
								pageAndSort),
						HttpMethod.GET, null,
						responseType);

		// analys post
		for (int i = 0; i < responsePosts.getBody().getContent().length; i++) {
			Long sum = 0L;
			for (TutorialView.Ccategory ccate : responsePosts.getBody()
					.getContent()[i].getCcategory()) {
				sum += ccate.getCountPost();
			}
			responsePosts.getBody().getContent()[i].setCountPost(sum);
		}

		return responsePosts.getBody();
	}

	/**
	 * Creates the one.
	 *
	 * @param tut the tut
	 * @return the tutorial
	 */
	@Override
	public Tutorial createOne(Tutorial tut) {
		tut.setSlug(PostUtil.makeSlugV2(tut.getName()));
		// set Pattern default is SERIES
		tut.setPattern("SERIES");

		ResponseEntity<Tutorial> responseCategory = restTemplate
				.postForEntity(UrlUtil.TUT_REST, tut, Tutorial.class);

		return responseCategory.getBody();
	}

	/**
	 * Gets the one.
	 *
	 * @param id the id
	 * @return the one
	 */
	@Override
	public Tutorial getOne(UUID id) {
		ResponseEntity<Tutorial> responsePost = restTemplate
				.getForEntity(UrlUtil.TUT_REST + "/" + id.toString(),
						Tutorial.class);

		return responsePost.getBody();
	}

	/**
	 * Update one.
	 *
	 * @param tut the tut
	 */
	@Override
	public void updateOne(Tutorial tut) {

		restTemplate.put(UrlUtil.TUT_REST, tut);
	}

	/**
	 * Delete one.
	 *
	 * @param id the id
	 */
	@Override
	public void deleteOne(UUID id) {
		
		restTemplate.delete(UrlUtil.TUT_REST + "/" + id.toString());
	}

	/**
	 * Gets the all.
	 *
	 * @param pageAndSort the page and sort
	 * @return the all
	 */
	@Override
	public RestPage<Tutorial> getAll(PageAndSort pageAndSort) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Gets the series.
	 *
	 * @param chapterId the chapter id
	 * @return the series
	 */
	@Override
	public List<Post> getSeries(UUID chapterId) {
		ResponseEntity<Chapter[]> responseCategories = restTemplate
				.getForEntity(
						UrlUtil.TUT_REST + "/chapter/" + chapterId.toString()
								+ "/series",
						Chapter[].class);

		List<Chapter> chapters = Arrays.asList(responseCategories.getBody());
		// flat list for select box
		List<Post> result = new ArrayList<Post>();
		for (Chapter chapter : chapters) {
			Post lesson = new Post();
			lesson.setTitle(chapter.getName());
			result.add(lesson);
			// traverse all post inside chapter
			chapter.getPosts().stream().forEach(entry -> result.add(entry));
		}

		return result;
	}

	/**
	 * Gets the all series by thread.
	 *
	 * @param id the id
	 * @return the all series by thread
	 */
	@Override
	public List<Series> getAllSeriesByThread(UUID id) {
		ResponseEntity<Series[]> responseCategories = restTemplate
				.getForEntity(
						UrlUtil.CATE_REST + "/thread/" + id.toString()
								+ "/series",
						Series[].class);

		return Arrays.asList(responseCategories.getBody());
	}

	/**
	 * Gets the one serie.
	 *
	 * @param id the id
	 * @return the one serie
	 */
	@Override
	public Series getOneSerie(UUID id) {
		ResponseEntity<Series> serie = restTemplate
				.getForEntity(UrlUtil.TUT_REST + "/series/" + id.toString(),
						Series.class);

		return serie.getBody();
	}

	/**
	 * Gets the related series for lesson.
	 *
	 * @param chapterId the chapter id
	 * @return the related series for lesson
	 */
	public List<SlimSeries> getRelatedSeriesForLesson(UUID chapterId) {
		ResponseEntity<SlimSeries[]> responseCategories = restTemplate
				.getForEntity(
						UrlUtil.TUT_REST + "/chapter/" + chapterId.toString()
								+ "/thread-series",
						SlimSeries[].class);

		return Arrays.asList(responseCategories.getBody());
	}

	/**
	 * Gets the all chapter.
	 *
	 * @return the all chapter
	 */
	public List<SlimSeries> getAllChapter() {
		ResponseEntity<SlimSeries[]> responseCategories = restTemplate
				.getForEntity(
						UrlUtil.TUT_REST + "/chapter/all",
						SlimSeries[].class);

		return Arrays.asList(responseCategories.getBody());
	}

}

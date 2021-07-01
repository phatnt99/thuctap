package net.freetuts.frontend.services;

import java.util.List;
import java.util.UUID;

import net.freetuts.frontend.model.PageAndSort;
import net.freetuts.frontend.model.Post;
import net.freetuts.frontend.model.Series;
import net.freetuts.frontend.model.SlimSeries;
import net.freetuts.frontend.model.Tutorial;
import net.freetuts.frontend.model.TutorialView;
import net.freetuts.frontend.utils.RestPage;

public interface TutorialService extends CrudService<Tutorial> {
	RestPage<TutorialView> getAllForView(PageAndSort pageAndSort);

	List<SlimSeries> getAllChapter();
	
	List<Post> getSeries(UUID tutId);
	
	List<Series> getAllSeriesByThread(UUID id);
	
	Series getOneSerie(UUID id);
	
	List<SlimSeries> getRelatedSeriesForLesson(UUID chapterId);
}

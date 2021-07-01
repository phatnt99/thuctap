package net.freetuts.backend.dto;

import java.util.List;
import java.util.UUID;

public interface SeriesView {
	
	UUID getId();

	String getName();

	List<SeriesLesson> getPosts();
	
	Integer getOrder();
}

interface SeriesLesson {
	UUID getId();

	String getTitle();

	String getSlug();
}

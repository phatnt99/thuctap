package net.freetuts.backend.dto;

import java.util.UUID;

public interface PostForCategoryDTO {

	UUID getId();

	String getTitle();

	String getThumbnail();

	String getExcerpt();
	
	String getSlug();
}

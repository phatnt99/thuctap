package net.freetuts.backend.dto;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;

public interface Tutorial {
	UUID getId();

	String getName();

	String getDescription();

	CategoryView getPcategory();

	List<CategoryWithCountPostView> getCcategory();

	@Value("#{target.ccategory.size()}")
	Long getCountChapter();

	interface CategoryView {
		String getName();
	}

	interface CategoryWithCountPostView {
		UUID getId();

		@Value("#{target.posts.size()}")
		Long getCountPost();
	}
}

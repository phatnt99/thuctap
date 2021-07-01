package net.freetuts.backend.dto;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;

public interface IndividualTutorial {
	UUID getId();
	
	String getSlug();

	String getName();

	String getDescription();

	@Value("#{target.pcategory.id}")
	UUID getParentId();

	List<Chapter> getCcategory();

	interface Chapter {
		UUID getId();

		String getName();
	}
}

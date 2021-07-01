package net.freetuts.backend.dto;

import java.util.List;
import java.util.UUID;

public interface ThreadView {
	UUID getId();

	String getName();

	List<SeriesView> getCcategory();
}
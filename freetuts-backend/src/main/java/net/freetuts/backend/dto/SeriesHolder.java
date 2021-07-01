package net.freetuts.backend.dto;

import java.util.List;
import java.util.UUID;

public class SeriesHolder implements ThreadView {
	private UUID             id;
	private String           name;
	private List<SeriesView> ccategory;
	private String           excerpt;

	public String getExcerpt() {
		return excerpt;
	}

	public void setExcerpt(String excerpt) {
		this.excerpt = excerpt;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SeriesView> getCcategory() {
		return ccategory;
	}

	public void setCcategory(List<SeriesView> ccategory) {
		this.ccategory = ccategory;
	}

}

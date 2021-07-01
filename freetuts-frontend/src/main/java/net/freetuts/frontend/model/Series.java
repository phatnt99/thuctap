package net.freetuts.frontend.model;

import java.util.List;
import java.util.UUID;

public class Series {
	private UUID          id;
	private String        name;
	private String        excerpt;
	private List<Chapter> ccategory;

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

	public String getExcerpt() {
		return excerpt;
	}

	public void setExcerpt(String excerpt) {
		this.excerpt = excerpt;
	}

	public List<Chapter> getCcategory() {
		return ccategory;
	}

	public void setCcategory(List<Chapter> ccategory) {
		this.ccategory = ccategory;
	}

}

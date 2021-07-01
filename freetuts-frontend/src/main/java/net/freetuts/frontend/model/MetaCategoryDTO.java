package net.freetuts.frontend.model;

import java.util.UUID;

public class MetaCategoryDTO {
	private UUID   categoryId;

	public UUID getCategoryId() {
		return categoryId;
	}

	public MetaCategoryDTO(UUID categoryId) {
		super();
		this.categoryId = categoryId;
	}

	public void setCategoryId(UUID categoryId) {
		this.categoryId = categoryId;
	}
}

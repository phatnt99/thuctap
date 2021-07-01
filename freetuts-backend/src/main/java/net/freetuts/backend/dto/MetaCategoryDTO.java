package net.freetuts.backend.dto;

import java.util.UUID;

public class MetaCategoryDTO {
	private UUID   categoryId;

	public UUID getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(UUID categoryId) {
		this.categoryId = categoryId;
	}
}

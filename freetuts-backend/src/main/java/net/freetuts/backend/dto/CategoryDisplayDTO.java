package net.freetuts.backend.dto;

import net.freetuts.backend.entity.Category;

public class CategoryDisplayDTO {
	Category   category;
	BreadCrumb bc;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public BreadCrumb getBc() {
		return bc;
	}

	public void setBc(BreadCrumb bc) {
		this.bc = bc;
	}

}

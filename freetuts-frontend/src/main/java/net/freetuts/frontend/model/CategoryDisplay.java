package net.freetuts.frontend.model;

import net.freetuts.frontend.display.BreadCrumb;

public class CategoryDisplay {
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

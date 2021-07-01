package net.freetuts.backend.dto;

import java.util.ArrayList;
import java.util.List;

import net.freetuts.backend.entity.Category;

public class Menu {

	List<MenuItem> items;

	public Menu() {
		super();
		items = new ArrayList<MenuItem>();
	}

	public void initWithTopMenu(List<Category> topMenuCategory) {
		for (Category topCategory : topMenuCategory) {
			traverseCategoryAndGetChildren(topCategory, items);
		}
	}

	public List<MenuItem> getItems() {
		return items;
	}

	public void setItems(List<MenuItem> items) {
		this.items = items;
	}

	public void traverseCategoryAndGetChildren(Category category,
			List<MenuItem> items) {
		if (!category.getIsMenu())
			return;

		MenuItem item = new MenuItem();
		item.setLabel(category.getName());
		item.setLink(category.getSlug());
		if (category.getCcategory().size() > 0) {
			List<MenuItem> childOfCurrent = new ArrayList<MenuItem>();
			for (Category child : category.getCcategory()) {
				traverseCategoryAndGetChildren(child, childOfCurrent);
			}
			item.setChild(childOfCurrent);
		}

		items.add(item);
	}

	class MenuItem {
		private String         label;
		private String         link;
		private List<MenuItem> child;

		public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
		}

		public String getLink() {
			return link;
		}

		public void setLink(String link) {
			this.link = link;
		}

		public List<MenuItem> getChild() {
			return child;
		}

		public void setChild(List<MenuItem> child) {
			this.child = child;
		}

	}
}

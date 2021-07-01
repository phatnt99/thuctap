package net.freetuts.frontend.display;

import java.util.List;
import java.util.stream.Collectors;

public class Menu {

	List<MenuItem> items;

	public List<MenuItem> getItems() {
		return items;
	}

	public void setItems(List<MenuItem> items) {
		this.items = items;
	}

	public void processCourse() {
		for (MenuItem item : items) {
			if ("khoa-hoc".equals(item.link)) {
				item.setChild(item.getChild().stream().map(entry -> {
					entry.setLink("khoa-hoc/" + entry.link);
					return entry;
				}).collect(Collectors.toList()));
			}
		}
	}

	static class MenuItem {
		private String         label;
		private String         link;
		private List<MenuItem> child;
		private boolean        isContainer;

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

		public boolean getIsContainer() {

			return this.link.equals("lap-trinh");
		}

		public boolean isContainer() {
			return isContainer;
		}

		public void setContainer(boolean isContainer) {
			this.isContainer = isContainer;
		}

	}
}

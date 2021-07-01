package net.freetuts.frontend.display;

import java.util.ArrayList;
import java.util.List;

public class BreadCrumb {

	List<BCEntry> entries;

	public BreadCrumb() {
		super();
		entries = new ArrayList<BCEntry>();
	}

	public BreadCrumb addEntry(String label, String link) {
		BCEntry entry = new BCEntry(label, link);

		entries.add(entry);

		return this;
	}

	public List<BCEntry> getEntries() {
		return entries;
	}

	public void setEntries(List<BCEntry> entries) {
		this.entries = entries;
	}

	static class BCEntry {

		String label;
		String link;

		public BCEntry() {
			super();
		}

		public BCEntry(String label, String link) {
			super();
			this.label = label;
			this.link  = link;
		}

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

	}
}

package net.freetuts.frontend.model;

import java.util.ArrayList;
import java.util.List;

public class MetaEntry {
	private String      label;
	private List<Entry> entries;

	public MetaEntry() {
		super();
		entries = new ArrayList<MetaEntry.Entry>();
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<Entry> getEntries() {
		return entries;
	}

	public void setOneEntry(String label, String value, String img) {
		entries.add(new Entry(label, value, img));
	}

	public void setEntries(List<Entry> entries) {
		this.entries = entries;
	}

	static class Entry {
		private String label;
		private String link;
		private String img;

		public Entry() {
			super();
		}

		public Entry(String label, String link, String img) {
			super();
			this.label = label;
			this.link  = link;
			this.img   = img;
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

		public String getImg() {
			return img;
		}

		public void setImg(String img) {
			this.img = img;
		}

	}

}

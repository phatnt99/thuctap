package net.freetuts.frontend.model;

import java.util.List;
import java.util.UUID;

public class TutorialView extends Tutorial {
	private Pcategory       pcategory;
	private Long            countChapter;
	private Long            countPost;
	private List<Ccategory> ccategory;

	public Pcategory getPcategory() {
		return pcategory;
	}

	public void setPcategory(Pcategory pcategory) {
		this.pcategory = pcategory;
	}

	public List<Ccategory> getCcategory() {
		return ccategory;
	}

	public void setCcategory(List<Ccategory> ccategory) {
		this.ccategory = ccategory;
	}

	public Long getCountChapter() {
		return countChapter;
	}

	public void setCountChapter(Long countChapter) {
		this.countChapter = countChapter;
	}

	public Long getCountPost() {
		return countPost;
	}

	public void setCountPost(Long countPost) {
		this.countPost = countPost;
	}

	public static class Ccategory {
		private UUID   id;
		private Long   countPost;
		private String name;

		public Long getCountPost() {
			return countPost;
		}

		public void setCountPost(Long countPost) {
			this.countPost = countPost;
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
	}

}

class Pcategory {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

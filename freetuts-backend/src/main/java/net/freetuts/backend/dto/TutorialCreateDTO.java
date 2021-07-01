package net.freetuts.backend.dto;

public class TutorialCreateDTO extends CategoryCreateDTO {
	private String   thumbnail;
	private String[] fields;

	public String[] getFields() {
		return fields;
	}

	public void setFields(String[] fields) {
		this.fields = fields;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
}

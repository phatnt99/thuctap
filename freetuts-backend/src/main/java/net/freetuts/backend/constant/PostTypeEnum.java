package net.freetuts.backend.constant;

public enum PostTypeEnum {
	POST("POST"),
	CATEGORY("CATEGORY"),
	SERIES("SERIES"),
	COURSE("COURSE");

	private String value;

	PostTypeEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

	public static PostTypeEnum fromString(String val) {

		switch (val) {
		case "POST":
			return PostTypeEnum.POST;
		case "CATEGORY":
			return PostTypeEnum.CATEGORY;
		case "COURSE":
			return PostTypeEnum.COURSE;
		default:
			return null;
		}
	}
}

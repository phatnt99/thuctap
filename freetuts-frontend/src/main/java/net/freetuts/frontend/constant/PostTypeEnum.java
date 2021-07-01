package net.freetuts.frontend.constant;

public enum PostTypeEnum {
	POST("POST"),
	LESSON("LESSON"),
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
		case "LESSON":
			return PostTypeEnum.LESSON;
		case "COURSE":
			return PostTypeEnum.COURSE;
		default:
			return null;
		}
	}
}

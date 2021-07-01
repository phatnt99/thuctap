package net.freetuts.backend.constant;

public enum PostStatusEnum {
	PUBLISH(1),
	DRAFT(2);
	
	private Integer value;
	
	PostStatusEnum(Integer value) {
		this.value = value;
	}
	
	public Integer getValue() {
		return this.value;
	}
	
	public static PostStatusEnum fromInt(Integer val) {
		switch(val) {
		case 1: return PostStatusEnum.PUBLISH;
		case 2: return PostStatusEnum.DRAFT;
		default: return null;
		}
	}
	
}

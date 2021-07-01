package net.freetuts.backend.constant;

import java.util.ArrayList;
import java.util.List;

public enum PatternTypeEnum {
	USUAL("USUAL"),
	TOPIC("TOPIC"),
	THREAD("THREAD"),
	SERIES("SERIES"),
	LESSON("LESSON");

	private String value;

	PatternTypeEnum(String val) {
		this.value = val;
	}

	public String getValue() {
		return this.value;
	}

	public List<String> getCollection() {
		List<String> result = new ArrayList<String>();
		for (PatternTypeEnum e : PatternTypeEnum.values()) {
			result.add(e.getValue());
		}
		return result;
	}
}

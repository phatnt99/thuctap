package net.freetuts.frontend.model;

import java.io.Serializable;

public class Report extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private String            content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}

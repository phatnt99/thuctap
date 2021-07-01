package net.freetuts.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Report extends BaseEntity {

	@Column(columnDefinition="TEXT")
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}

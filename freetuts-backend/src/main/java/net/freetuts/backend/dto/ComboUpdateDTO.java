package net.freetuts.backend.dto;

import java.util.List;
import java.util.UUID;

public class ComboUpdateDTO {

	private UUID id;

	private String courseName;

	private Integer studyMethod;

	private String courseLink;

	private String linkName;

	private Integer discount;

	private Integer price;

	private Integer type;

	private List<UUID> courseIds;

	public ComboUpdateDTO() {

	}

	public ComboUpdateDTO(UUID id, String courseName, Integer studyMethod, String courseLink, String linkName,
			Integer discount, Integer price, Integer type, List<UUID> courseIds) {
		super();
		this.id = id;
		this.courseName = courseName;
		this.studyMethod = studyMethod;
		this.courseLink = courseLink;
		this.linkName = linkName;
		this.discount = discount;
		this.price = price;
		this.type = type;
		this.courseIds = courseIds;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Integer getStudyMethod() {
		return studyMethod;
	}

	public void setStudyMethod(Integer studyMethod) {
		this.studyMethod = studyMethod;
	}

	public String getCourseLink() {
		return courseLink;
	}

	public void setCourseLink(String courseLink) {
		this.courseLink = courseLink;
	}

	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public List<UUID> getCourseIds() {
		return courseIds;
	}

	public void setCourseIds(List<UUID> courseIds) {
		this.courseIds = courseIds;
	}

	@Override
	public String toString() {
		return "ComboUpdateDTO [id=" + id + ", courseName=" + courseName + ", studyMethod=" + studyMethod
				+ ", courseLink=" + courseLink + ", linkName=" + linkName + ", discount=" + discount + ", price="
				+ price + ", type=" + type + ", courseIds=" + courseIds + "]";
	}

}

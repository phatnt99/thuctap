package net.freetuts.backend.dto;

import java.util.List;
import java.util.UUID;

public class ComboCreateDTO {

	private String courseName;

	private Integer studyMethod;

	private String courseLink;

	private String linkName;

	private Integer discount;

	private Integer price;

	private List<UUID> courseIds;

	private Integer type;

	public ComboCreateDTO() {

	}

	public ComboCreateDTO(String courseName, Integer studyMethod, String courseLink, String linkName, Integer discount,
			Integer price, List<UUID> courseIds, Integer type) {
		super();
		this.courseName = courseName;
		this.studyMethod = studyMethod;
		this.courseLink = courseLink;
		this.linkName = linkName;
		this.discount = discount;
		this.price = price;
		this.courseIds = courseIds;
		this.type = type;
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

	public List<UUID> getCourseIds() {
		return courseIds;
	}

	public void setCourseIds(List<UUID> courseIds) {
		this.courseIds = courseIds;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "ComboCreateDTO [courseName=" + courseName + ", studyMethod=" + studyMethod + ", courseLink="
				+ courseLink + ", linkName=" + linkName + ", discount=" + discount + ", price=" + price + ", courseIds="
				+ courseIds + ", type=" + type + "]";
	}

}

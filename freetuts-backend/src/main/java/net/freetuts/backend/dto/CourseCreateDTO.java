package net.freetuts.backend.dto;

public class CourseCreateDTO {

	private String courseName;

	private Integer studyMethod;

	private String courseLink;

	private String linkName;

	private Integer discount;

	private Integer price;

	private Integer type;

	public CourseCreateDTO() {

	}

	public CourseCreateDTO(String courseName, Integer studyMethod, String courseLink, String linkName, Integer discount,
			Integer price, Integer type) {
		super();
		this.courseName = courseName;
		this.studyMethod = studyMethod;
		this.courseLink = courseLink;
		this.linkName = linkName;
		this.discount = discount;
		this.price = price;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "CourseCreateDTO [courseName=" + courseName + ", studyMethod=" + studyMethod + ", courseLink="
				+ courseLink + ", linkName=" + linkName + ", discount=" + discount + ", price=" + price + ", type="
				+ type + "]";
	}

}

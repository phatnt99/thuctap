package net.freetuts.backend.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import net.freetuts.backend.entity.Course;
import net.freetuts.backend.entity.Post;

public class CourseResponse {

	private UUID id;

	private String courseName;

	private Integer studyMethod;

	private String courseLink;

	private String linkName;

	private Integer discount;

	private Integer price;

	private Integer type;

	private LocalDateTime updatedAt;

	private LocalDateTime createdAt;

	private List<Course> courses;

	private List<UUID> courseIds;

	private Post post;

	public CourseResponse() {

	}

	public CourseResponse(UUID id, String courseName, Integer studyMethod, String courseLink, String linkName,
			Integer discount, Integer price, Integer type, LocalDateTime updatedAt, LocalDateTime createdAt, List<Course> courses,
			List<UUID> courseIds, Post post) {
		super();
		this.id = id;
		this.courseName = courseName;
		this.studyMethod = studyMethod;
		this.courseLink = courseLink;
		this.linkName = linkName;
		this.discount = discount;
		this.price = price;
		this.type = type;
		this.updatedAt = updatedAt;
		this.createdAt = createdAt;
		this.courses = courses;
		this.courseIds = courseIds;
		this.post = post;
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

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public List<UUID> getCourseIds() {
		return courseIds;
	}

	public void setCourseIds(List<UUID> courseIds) {
		this.courseIds = courseIds;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	@Override
	public String toString() {
		return "CourseResponse [id=" + id + ", courseName=" + courseName + ", studyMethod=" + studyMethod
				+ ", courseLink=" + courseLink + ", linkName=" + linkName + ", discount=" + discount + ", price="
				+ price + ", type=" + type + ", updatedAt=" + updatedAt + ", createdAt=" + createdAt + ", courses="
				+ courses + ", courseIds=" + courseIds + ", post=" + post + "]";
	}

}

package net.freetuts.backend.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * The Class Course.
 */
@Entity
@Table(name = "course")
public class Course extends BaseEntity {

	/** The course name. */
	@Column(name = "course_name", nullable = false, length = 255)
	private String courseName;

	/** The study method. */
	@Column(name = "study_method")
	private Integer studyMethod;

	/** The link name. */
	@Column(name = "link_name")
	private String linkName;

	/** The course link. */
	@Column(name = "course_link")
	private String courseLink;

	/** The discount. */
	@Column(name = "discount")
	private Integer discount;

	/** The price. */
	@Column(name = "price", nullable = false)
	private Integer price;

	/** The type. */
	@Column(name = "type", nullable = false)
	private Integer type;

	/** The courses. */
	@ManyToMany(cascade ={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(name = "combo_courses", joinColumns = @JoinColumn(name = "course_id"), inverseJoinColumns = @JoinColumn(name = "combo_course_id"))
	private List<Course> courses;

	/**
	 * Instantiates a new course.
	 */
	public Course() {

	}

	/**
	 * Instantiates a new course.
	 *
	 * @param courseName the course name
	 * @param studyMethod the study method
	 * @param linkName the link name
	 * @param courseLink the course link
	 * @param discount the discount
	 * @param price the price
	 * @param type the type
	 * @param courses the courses
	 */
	public Course(String courseName, Integer studyMethod, String linkName, String courseLink, Integer discount,
			Integer price, Integer type, List<Course> courses) {
		super();
		this.courseName = courseName;
		this.studyMethod = studyMethod;
		this.linkName = linkName;
		this.courseLink = courseLink;
		this.discount = discount;
		this.price = price;
		this.type = type;
		this.courses = courses;
	}

	/**
	 * Gets the course name.
	 *
	 * @return the course name
	 */
	public String getCourseName() {
		return courseName;
	}

	/**
	 * Sets the course name.
	 *
	 * @param courseName the new course name
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	/**
	 * Gets the study method.
	 *
	 * @return the study method
	 */
	public Integer getStudyMethod() {
		return studyMethod;
	}

	/**
	 * Sets the study method.
	 *
	 * @param studyMethod the new study method
	 */
	public void setStudyMethod(Integer studyMethod) {
		this.studyMethod = studyMethod;
	}

	/**
	 * Gets the link name.
	 *
	 * @return the link name
	 */
	public String getLinkName() {
		return linkName;
	}

	/**
	 * Sets the link name.
	 *
	 * @param linkName the new link name
	 */
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	/**
	 * Gets the course link.
	 *
	 * @return the course link
	 */
	public String getCourseLink() {
		return courseLink;
	}

	/**
	 * Sets the course link.
	 *
	 * @param courseLink the new course link
	 */
	public void setCourseLink(String courseLink) {
		this.courseLink = courseLink;
	}

	/**
	 * Gets the discount.
	 *
	 * @return the discount
	 */
	public Integer getDiscount() {
		return discount;
	}

	/**
	 * Sets the discount.
	 *
	 * @param discount the new discount
	 */
	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public Integer getPrice() {
		return price;
	}

	/**
	 * Sets the price.
	 *
	 * @param price the new price
	 */
	public void setPrice(Integer price) {
		this.price = price;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * Gets the courses.
	 *
	 * @return the courses
	 */
	public List<Course> getCourses() {
		return courses;
	}

	/**
	 * Sets the courses.
	 *
	 * @param courses the new courses
	 */
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Course [courseName=" + courseName + ", studyMethod=" + studyMethod + ", linkName=" + linkName
				+ ", courseLink=" + courseLink + ", discount=" + discount + ", price=" + price + ", type=" + type
				+ ", courses=" + courses + "]";
	}

}
package net.freetuts.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The Class Invoice.
 */
@Entity
@Table(name = "invoice")
public class Invoice extends BaseEntity {

	/** The full name. */
	@Column(name = "full_name", nullable = false, length = 255)
	private String fullName;

	/** The phone. */
	@Column(name = "phone", nullable = false)
	private String phone;

	/** The email. */
	@Column(name = "email", nullable = false)
	private String email;

	/** The facebook. */
	@Column(name = "facebook", nullable = false)
	private String facebook;

	/** The payment method. */
	@Column(name = "payment_method", nullable = false)
	private Integer paymentMethod;

	/** The payment detail. */
	@Column(name = "payment_detail")
	private String paymentDetail;

	/** The totalprice. */
	@Column(name = "total_price", nullable = false)
	private Double totalPrice;

	/** The status. */
	@Column(name = "status", nullable = false)
	private Integer status;

	/** The course. */
	@OneToOne
	@JoinColumn(name = "course_id")
	private Course course;

	/**
	 * Instantiates a new invoice.
	 */
	public Invoice() {

	}

	public Invoice(String fullName, String phone, String email, String facebook, Integer paymentMethod,
			String paymentDetail, Double totalPrice, Integer status, Course course) {
		super();
		this.fullName = fullName;
		this.phone = phone;
		this.email = email;
		this.facebook = facebook;
		this.paymentMethod = paymentMethod;
		this.paymentDetail = paymentDetail;
		this.totalPrice = totalPrice;
		this.status = status;
		this.course = course;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public Integer getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(Integer paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getPaymentDetail() {
		return paymentDetail;
	}

	public void setPaymentDetail(String paymentDetail) {
		this.paymentDetail = paymentDetail;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@Override
	public String toString() {
		return "Invoice [fullName=" + fullName + ", phone=" + phone + ", email=" + email + ", facebook=" + facebook
				+ ", paymentMethod=" + paymentMethod + ", paymentDetail=" + paymentDetail + ", totalPrice=" + totalPrice
				+ ", status=" + status + ", course=" + course + "]";
	}

}
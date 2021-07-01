package net.freetuts.backend.dto;

import java.util.UUID;

public class InvoiceCreateDTO {

	private String fullName;

	private String phone;

	private String email;

	private String facebook;

	private Integer paymentMethod;

	private String paymentDetail;

	private Double totalPrice;

	private Integer status;

	private UUID courseId;

	public InvoiceCreateDTO() {

	}

	public InvoiceCreateDTO(String fullName, String phone, String email, String facebook, Integer paymentMethod,
			String paymentDetail, Double totalPrice, Integer status, UUID courseId) {
		super();
		this.fullName = fullName;
		this.phone = phone;
		this.email = email;
		this.facebook = facebook;
		this.paymentMethod = paymentMethod;
		this.paymentDetail = paymentDetail;
		this.totalPrice = totalPrice;
		this.status = status;
		this.courseId = courseId;
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

	public UUID getCourseId() {
		return courseId;
	}

	public void setCourseId(UUID courseId) {
		this.courseId = courseId;
	}

	@Override
	public String toString() {
		return "InvoiceCreateDTO [fullName=" + fullName + ", phone=" + phone + ", email=" + email + ", facebook="
				+ facebook + ", paymentMethod=" + paymentMethod + ", paymentDetail=" + paymentDetail + ", totalPrice="
				+ totalPrice + ", status=" + status + ", courseId=" + courseId + "]";
	}

}

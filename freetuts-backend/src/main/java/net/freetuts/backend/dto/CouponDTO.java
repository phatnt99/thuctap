package net.freetuts.backend.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public class CouponDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1592808114655276578L;

	private UUID    id;
	private String  code;
	private Integer value;
	private String  description;
	private String  url;
	private LocalDateTime    updatedAt;
	private boolean expired;
	private LocalDateTime    createdAt;

	public CouponDTO() {
		super();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

 

}

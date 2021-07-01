package net.freetuts.backend.entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
// @Data
// @EqualsAndHashCode(callSuper = true)
// @ToString(callSuper = true)
// @AllArgsConstructor
// @NoArgsConstructor
// @Builder()
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "code", name = "UK_code"))
public class Coupon extends BaseEntity {

	private String  code;
	private Integer value;
	private String  description;
	private String  url;
	private boolean expired;

	public Coupon(UUID id) {
		super.setId(id);

	}

	public Coupon() {
		super();
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
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

}

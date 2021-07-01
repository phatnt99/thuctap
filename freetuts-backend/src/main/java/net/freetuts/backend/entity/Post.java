package net.freetuts.backend.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "post")
public class Post extends BaseEntity {

	private String title;

	private String slug;

	private String thumbnail;

	@Lob
	@Column(columnDefinition = "TEXT")
	private String excerpt;

	@Lob
	@Column(columnDefinition = "TEXT")
	private String content;

	private Integer status;

	private String path;

	@Type(type = "org.hibernate.type.UUIDCharType")
	@Column(columnDefinition = "CHAR(36)")
	private UUID postableId;

	@Column(name = "post_type")
	private String postType;

	@Column(name = "type")
	private String type;

	@ManyToOne
	@JoinColumn(name = "category_id", referencedColumnName = "id")
	private Category category;

	@Column(name = "order_num",columnDefinition = "integer default 1")
	private Integer order;

	public Post() {
		super();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getExcerpt() {
		return excerpt;
	}

	public void setExcerpt(String excerpt) {
		this.excerpt = excerpt;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPostType() {
		return postType;
	}

	public void setPostType(String postType) {
		this.postType = postType;
	}

	public UUID getPostableId() {
		return postableId;
	}

	public void setPostableId(UUID postableId) {
		this.postableId = postableId;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	// accessors
	public UUID getCategoryId() {
		if (this.category != null)
			return this.category.getId();
		return null;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

}
package net.freetuts.backend.dto;

import java.io.Serializable;
import java.util.UUID;

import net.freetuts.backend.entity.Post;

public class PostUpdateDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private UUID id;

	private String title;

	private String slug;

	private String thumbnail;

	private String excerpt;

	private String content;

	private Integer status;

	private String path;

	private Integer type;

	private UUID categoryId;

	private String postType;

	private UUID postableId;

	private Integer order;

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(UUID categoryId) {
		this.categoryId = categoryId;
	}

	public void mapToExistEntity(Post entity) {
		entity.setTitle(this.getTitle());
		entity.setSlug(this.getSlug());
		entity.setContent(this.getContent());
		entity.setThumbnail(this.getThumbnail());
		entity.setOrder(this.getOrder());
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
}

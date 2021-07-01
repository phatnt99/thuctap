package net.freetuts.frontend.model;

import java.util.UUID;

public class Post extends BaseModel {

	private String title;

	private String slug;

	private String thumbnail;

	private String excerpt;

	private String content;

	private Integer status;

	private String path;

	private String postType;

	private UUID categoryId;

	private UUID postableId;

	private Integer order;

	// for create post
	private String selectedType;

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

	public void setIsPublish(boolean isPublish) {
		if (isPublish)
			this.status = 1;
		else this.status = 2;
	}

	public boolean getIsPublish() {
		if (this.status != null)
			return this.status == 1;
		return false;
	}

	public String getPostType() {
		return postType == null ? "POST" : postType;
	}

	public void setPostType(String postType) {
		this.postType = postType;
	}

	public UUID getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(UUID categoryId) {
		this.categoryId = categoryId;
	}

	public UUID getPostableId() {
		return postableId;
	}

	public void setPostableId(UUID postableId) {
		this.postableId = postableId;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getSelectedType() {
		return selectedType;
	}

	public void setSelectedType(String selectedType) {
		this.selectedType = selectedType;
	}

}

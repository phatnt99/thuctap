package net.freetuts.frontend.model;

import java.util.UUID;

public class Category extends BaseModel {

	private String  name;
	private String  description;
	private String  slug;
	private String  path;
	private Boolean isMenu;
	private UUID    parentId;
	private String  parentName;
	private String  pattern;
	private Integer order;
	private boolean hasChild;

	public boolean isHasChild() {
		return hasChild;
	}

	public void setHasChild(boolean hasChild) {
		this.hasChild = hasChild;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UUID getParentId() {
		return parentId;
	}

	public void setParentId(UUID parentId) {
		this.parentId = parentId;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		if (parentName.isEmpty())
			this.parentName = "Top Category";

		this.parentName = parentName;
	}

	public Boolean getIsMenu() {
		return isMenu;
	}

	public void setIsMenu(Boolean isMenu) {
		this.isMenu = isMenu;
	}

	public String getPattern() {
		return pattern != null && pattern != "" ? pattern : "USUAL";
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

}

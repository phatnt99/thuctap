package net.freetuts.backend.dto;

import java.util.UUID;

import net.freetuts.backend.entity.Category;

public class CategoryUpdateDTO {

	private UUID   id;
	private String name;
	private String description;
	private String slug;
	private Boolean isMenu;
	private UUID   parentId;
	private Integer order;

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

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Boolean getIsMenu() {
		return isMenu;
	}

	public void setIsMenu(Boolean isMenu) {
		this.isMenu = isMenu;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}
	
	public void mapToExistEntity(Category category) {
		category.setName(this.getName());
		category.setDescription(this.getDescription());
		category.setIsMenu(this.getIsMenu());
		category.setOrder(this.getOrder());
	}
}

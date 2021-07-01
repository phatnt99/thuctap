package net.freetuts.frontend.model;

import java.util.List;
import java.util.UUID;

import net.freetuts.frontend.model.TutorialView.Ccategory;

public class Tutorial extends Category {
	private String     thumbnail;
	private String[]   fields;
	private UUID       parentId;
	private Integer    countField;
	private Integer    removeIdx;
	private List<UUID> ccategoryIds;
	// edit page
	private List<Ccategory> ccategory;
	// indicate which template (create, update) we are in
	private String template;
	// id of old field to remove
	private UUID removeId;

	public Tutorial() {
		super();
		fields = new String[100];
		setCountField(0);
	}

	public String[] getFields() {
		return fields;
	}

	public void setFields(String[] fields) {
		this.fields = fields;
	}

	public UUID getParentId() {
		return parentId;
	}

	public void setParentId(UUID parentId) {
		this.parentId = parentId;
	}

	public Integer getCountField() {
		return countField;
	}

	public void setCountField(Integer countField) {
		this.countField = countField;
	}

	public Integer getRemoveIdx() {
		return removeIdx;
	}

	public void setRemoveIdx(Integer removeIdx) {
		this.removeIdx = removeIdx;
	}

	public void removeField(Integer index) {
		this.fields[index] = null;
	}

	public List<Ccategory> getCcategory() {
		return ccategory;
	}

	public void setCcategory(List<Ccategory> ccategory) {
		this.ccategory = ccategory;
	}

	public List<UUID> getCcategoryIds() {
		return ccategoryIds;
	}

	public void setCcategoryIds(List<UUID> ccategoryIds) {
		this.ccategoryIds = ccategoryIds;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public UUID getRemoveId() {
		return removeId;
	}

	public void setRemoveId(UUID removeId) {
		this.removeId = removeId;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

}

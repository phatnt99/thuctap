package net.freetuts.backend.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.freetuts.backend.utils.comparator.SortByOrderForPost;

@Entity
public class Category extends BaseEntity {

	private String name;
	@Lob
	@Column(columnDefinition = "TEXT")
	private String description;
	// slug is automatic generate base on name
	// so name must be unique
	private String  slug;
	private String  path;
	private Boolean isMenu;
	@Transient
	private String  pattern;
	@Transient
	private boolean hasChild;

	@ManyToOne
	@JoinColumn(name = "parent_id")
	@JsonIgnore
	private Category pcategory;

	@OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<Post> posts;

	@OneToMany(mappedBy = "pcategory", cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<Category> ccategory;

	@Column(name = "order_num", columnDefinition = "integer default 1")
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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		if (path == null)
			this.path = this.slug;
		else
			this.path = path + "/" + this.slug;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public void setPathFromParent(String path) {

	}

	public Category getPcategory() {
		return pcategory;
	}

	public void setPcategory(Category pcategory) {
		this.pcategory = pcategory;
	}

	// custom getters
	public UUID getParentId() {
		if (this.pcategory != null)
			return this.pcategory.getId();

		return null;
	}

	public String getParentName() {
		if (this.pcategory != null)
			return this.pcategory.name;

		return "";
	}

	public Boolean getIsMenu() {
		return isMenu == null ? false : isMenu;
	}

	public void setIsMenu(Boolean isMenu) {
		this.isMenu = isMenu;
	}

	public List<Category> getCcategory() {
		return ccategory != null ? ccategory : new ArrayList<Category>();
	}

	public void setCcategory(List<Category> ccategory) {
		this.ccategory = ccategory;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public boolean getHasChild() {
		return this.ccategory != null ? this.ccategory.size() > 0 : false;
	}

	public List<Post> getPosts() {
		// with order
		Collections.sort(posts, new SortByOrderForPost());
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

}

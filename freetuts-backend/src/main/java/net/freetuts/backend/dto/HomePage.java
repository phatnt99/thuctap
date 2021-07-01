package net.freetuts.backend.dto;

import java.util.List;

public class HomePage {
	private String                   cateName;
	private List<PostForCategoryDTO> posts;

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public List<PostForCategoryDTO> getPosts() {
		return posts;
	}

	public void setPosts(List<PostForCategoryDTO> posts) {
		this.posts = posts;
	}

}

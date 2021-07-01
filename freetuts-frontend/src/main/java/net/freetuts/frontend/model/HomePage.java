package net.freetuts.frontend.model;

import java.util.List;

public class HomePage {
	private String                       cateName;
	private List<PostForCategoryDisplay> posts;

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public List<PostForCategoryDisplay> getPosts() {
		return posts;
	}

	public void setPosts(List<PostForCategoryDisplay> posts) {
		this.posts = posts;
	}

}

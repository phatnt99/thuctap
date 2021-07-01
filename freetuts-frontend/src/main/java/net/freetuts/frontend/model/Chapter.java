package net.freetuts.frontend.model;

import java.util.List;
import java.util.UUID;

public class Chapter {
	private UUID       id;
	private String     name;
	private List<Post> posts;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

}

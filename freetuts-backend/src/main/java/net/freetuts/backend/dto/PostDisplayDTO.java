package net.freetuts.backend.dto;

import net.freetuts.backend.entity.Post;

public class PostDisplayDTO {
	Post       post;
	BreadCrumb bc;

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public BreadCrumb getBc() {
		return bc;
	}

	public void setBc(BreadCrumb bc) {
		this.bc = bc;
	}

}

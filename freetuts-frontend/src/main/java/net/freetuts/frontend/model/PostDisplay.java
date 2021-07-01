package net.freetuts.frontend.model;

import net.freetuts.frontend.display.BreadCrumb;

public class PostDisplay {
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

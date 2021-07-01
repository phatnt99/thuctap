package net.freetuts.frontend.model;

import net.freetuts.frontend.display.BreadCrumb;

public class CourseDisplay {
	private Course     course;
	private BreadCrumb bc;

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public BreadCrumb getBc() {
		return bc;
	}

	public void setBc(BreadCrumb bc) {
		this.bc = bc;
	}

}

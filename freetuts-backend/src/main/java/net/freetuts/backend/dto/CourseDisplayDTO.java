package net.freetuts.backend.dto;

public class CourseDisplayDTO {
	private CourseResponse course;
	private BreadCrumb     bc;

	public CourseResponse getCourse() {
		return course;
	}

	public void setCourse(CourseResponse course) {
		this.course = course;
	}

	public BreadCrumb getBc() {
		return bc;
	}

	public void setBc(BreadCrumb bc) {
		this.bc = bc;
	}

}

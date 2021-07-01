package net.freetuts.frontend.services;

import java.util.List;
import java.util.UUID;

import net.freetuts.frontend.model.Course;
import net.freetuts.frontend.model.CourseDisplay;
import net.freetuts.frontend.model.CourseSideBar;
import net.freetuts.frontend.model.PageAndSort;
import net.freetuts.frontend.utils.RestPage;

public interface CourseService {

	Course createOne(Course course);

	RestPage<Course> getAll(PageAndSort pageAndSort);

	List<Course> getAll();
	
	Course getOne(UUID id);

	void updateOne(Course course);

	void deleteOne(UUID id);

	List<Course> getAllForCombo();

	Course createCombo(Course course);

	Course getCombo(UUID id);

	void updateCombo(Course course);
	
	List<Course> getAllCourseOnlyByCategory(UUID id);
	
	List<Course> getAllCourseOnlyByCategorySlug(String slug);
	
	List<Course> getAllComboOnlyBySlug(String slug);
	
	CourseDisplay getCourseBySlug(String slug);
	
	Course createUserCombo(Course course);
	
	List<CourseSideBar> getTop5ByTypeOrderByUpdatedAtDesc(int i);


}

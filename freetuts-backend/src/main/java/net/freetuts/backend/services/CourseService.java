package net.freetuts.backend.services;

import java.util.List;
import java.util.UUID;

import net.freetuts.backend.dto.ComboCreateDTO;
import net.freetuts.backend.dto.ComboUpdateDTO;
import net.freetuts.backend.dto.CourseDisplayDTO;
import net.freetuts.backend.dto.CourseResponse;
import net.freetuts.backend.entity.Course;

public interface CourseService extends CrudService<Course>{
	
	List<CourseResponse> getAllCourseOnly();

	List<CourseResponse> getAllComboOnly();

	List<CourseResponse> getAllCourse();

	List<CourseResponse> getAllCourseOnlyByCategory(UUID id);
	
	List<CourseResponse> getAllCourseOnlyBySlug(String slug);

	List<CourseResponse> getAllComboOnlyBySlug(String slug);
	
	List<CourseResponse> getTop5ByTypeOrderByUpdatedAtDesc(Integer type);

	Course createCombo(ComboCreateDTO entityDTO);

	Course updateCombo(ComboUpdateDTO entityDTO);
	
	CourseDisplayDTO getCourseBySlug(String slug);
	
	CourseResponse getOneById(UUID id);

}
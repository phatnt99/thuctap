package net.freetuts.backend.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.freetuts.backend.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {
	List<Course> findAllByType(Integer type);
	
	List<Course> findTop5ByTypeOrderByUpdatedAtDesc(Integer type);

}
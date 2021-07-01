package net.freetuts.backend.services;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.freetuts.backend.dto.ChapterView;
import net.freetuts.backend.dto.IndividualTutorial;
import net.freetuts.backend.dto.Tutorial;
import net.freetuts.backend.dto.TutorialCreateDTO;
import net.freetuts.backend.dto.TutorialUpdateDTO;
import net.freetuts.backend.entity.Category;

public interface TutorialService {
	Page<Tutorial> getAll(Pageable pageable);
	
	List<ChapterView> getAllChapterByTutorial(UUID id);

	Category createOne(TutorialCreateDTO dto);
	
	IndividualTutorial getOne(UUID id);
	
	void updateOne(TutorialUpdateDTO dto);
}

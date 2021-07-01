package net.freetuts.backend.services;

import java.util.List;
import java.util.UUID;

import net.freetuts.backend.dto.CategoryDisplayDTO;
import net.freetuts.backend.dto.MetaCategoryDTO;
import net.freetuts.backend.dto.MetaEntry;
import net.freetuts.backend.dto.SeriesHolder;
import net.freetuts.backend.dto.SeriesView;
import net.freetuts.backend.dto.ThreadSeriView;
import net.freetuts.backend.dto.ThreadView;
import net.freetuts.backend.entity.Category;

public interface CategoryService extends CrudService<Category> {
	
	List<Category> getAll();
	
	List<Category> getAllIsTopMenu();
	
	List<ThreadSeriView> getAllChapter();
	
	CategoryDisplayDTO getOneBySlug(String slug);
	
	List<MetaEntry> getMetaData(MetaCategoryDTO dto);
	
	List<Category> getThread();
	
	List<SeriesView> getSeriesByTutorial(UUID id);
		
	List<SeriesHolder> getSeriesByThread(UUID id);
	
	ThreadView getSerieById(UUID id);
	
	List<SeriesHolder> getSeriesByChapter(UUID id);
	
	List<ThreadSeriView> getThreadSeriesInLessonByChapter(UUID id);
	
	List<Category> getByParentId(UUID id);
	
	List<Category> getByPath(String path);
	
}

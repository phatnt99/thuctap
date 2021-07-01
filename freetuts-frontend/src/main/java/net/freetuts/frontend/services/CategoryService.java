package net.freetuts.frontend.services;

import java.util.List;
import java.util.UUID;

import net.freetuts.frontend.model.Category;
import net.freetuts.frontend.model.CategoryDisplay;
import net.freetuts.frontend.model.MetaCategoryDTO;
import net.freetuts.frontend.model.MetaEntry;

public interface CategoryService extends CrudService<Category> {
	List<Category> getAll();
	
	List<Category> getAllThread();

	CategoryDisplay getOneBySlug(String slug);

	List<MetaEntry> getMetaData(MetaCategoryDTO dto);
	
	List<Category> getByPath(String path);
	
	List<Category> getByParentId(UUID id);
}

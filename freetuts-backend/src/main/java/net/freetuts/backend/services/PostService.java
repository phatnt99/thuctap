package net.freetuts.backend.services;


import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.freetuts.backend.dto.HomePage;
import net.freetuts.backend.dto.PostDisplayDTO;
import net.freetuts.backend.dto.PostForCategoryDTO;
import net.freetuts.backend.entity.Post;

/**
 * The Interface PostService.
 */
public interface PostService extends CrudService<Post> {
	
	Post getOneByPostableId(UUID pid);
	
	PostDisplayDTO getOneBySlug(String slug);
	
	Page<PostForCategoryDTO> getByCategorySlug(String categorySlug, Pageable pageable);
	
	Page<PostForCategoryDTO> getByParentCategorySlug(String categorySlug, Pageable pageable);
	
	List<HomePage> getTop5PostEachCateForHome();
}

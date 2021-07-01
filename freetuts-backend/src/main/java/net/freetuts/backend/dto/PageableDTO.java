package net.freetuts.backend.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public class PageableDTO {
	
	Integer page;
	Integer size;

	PageableDTO() {
		this.page = 0;
		this.size = 20;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Pageable get() {
		
		return PageRequest.of(this.page, this.size, Sort.by(Direction.DESC, "updatedAt"));
	}
}

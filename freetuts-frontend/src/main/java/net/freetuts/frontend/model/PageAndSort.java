package net.freetuts.frontend.model;

public class PageAndSort {

	Integer page;
	Integer size;
	String  sortable;
	String  direction;

	public PageAndSort() {
		this.page = 0;
		this.size = 5;
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

	public String getSortable() {
		return sortable;
	}

	public void setSortable(String sortable) {
		this.sortable = sortable;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

}

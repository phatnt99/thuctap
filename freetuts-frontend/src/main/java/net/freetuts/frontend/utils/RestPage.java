package net.freetuts.frontend.utils;

public class RestPage<T> {
	
	private T[] content;
	
	private Integer number;
	
	private boolean first;
	
	private boolean last;
	
	private Integer totalElements;
	
	private Integer totalPages;

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public T[] getContent() {
		return content;
	}

	public void setContent(T[] content) {
		this.content = content;
	}

	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

	public boolean isLast() {
		return last;
	}

	public void setLast(boolean last) {
		this.last = last;
	}

	public Integer getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(Integer totalElements) {
		this.totalElements = totalElements;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}
	

}
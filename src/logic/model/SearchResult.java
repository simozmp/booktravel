package logic.model;

import logic.bean.FiltersSet;
import logic.model.Attraction;

import java.util.List;

public class SearchResult {
	
	// List
	private List<Attraction> list;
	
	// FiltersSet
	private FiltersSet filters;
	
	public SearchResult(FiltersSet filters) {
		this.filters = filters;
		updateList();
	}

	// Purpose of the existence of this method: re-usage of the object
	public void setFilters(FiltersSet filters) {
		this.filters = filters;
	}

	public void updateList() {
		// TODO: implement
	}
	
	private void orderList() {
		// TODO: implement
	}
}

package logic.controller;

import logic.bean.FiltersSet;
import logic.controller.QuestionaryViewController;
import logic.model.SearchResult;

public class QuestionaryController {
	
	// User filters
	private FiltersSet filters;
	
	// AttractionsList
	private SearchResult attractionsList;
	
	// View controller reference
	private QuestionaryViewController viewController;
	
	public QuestionaryController(QuestionaryViewController viewController) {
		this.viewController = viewController;
		viewController.questionary();
	}
	
	public void setFilters(FiltersSet filters) {
		this.filters = filters;
		attractionsList = new SearchResult(filters);
	}
}

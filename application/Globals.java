package application;

import flashcards.Category;

public class Globals {
	private static Category selectedCategory;

	public static Category getSelectedCategory() {
		return selectedCategory;
	}

	public static void setSelectedCategory(Category selectedCategory) {
		Globals.selectedCategory = selectedCategory;
	}
	
	
}

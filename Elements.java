package Assignment.TextAnalyser;

/*
 * Simple class to handle choices from checkboxes
 */
public class Elements {
	
	private boolean headers, paragraphs, links, lists, everything;
	
	public Elements() {
		//default assumes the user wants raw html
		everything = true;
	}
	
	public Elements(boolean headers, boolean paragraphs, boolean links, boolean lists) {
		this.headers = headers;
		this.paragraphs = paragraphs;
		this.links = links;
		this.lists = lists;
	}

	public boolean wantsHeaders() {
		return headers;
	}

	public boolean wantsParagraphs() {
		return paragraphs;
	}

	public boolean wantsLinks() {
		return links;
	}

	public boolean wantsLists() {
		return lists;
	}
	
	public boolean wantsEverything() {
		return everything;
	}
	
	public boolean hasChosen() {
		boolean chosen = false;
		
		if (headers || paragraphs || links || lists || everything) 
			chosen = true;
		
		return chosen;
	}
}

package Assignment.TextAnalyser;

/*
 * A simple class to store Element selection options.
 */
public class Elements {
	
	private boolean headers, paragraphs, links, lists, everything;
	
	public Elements() {
		//default assumes the user wants raw html
		everything = true;
	}
	
	/**
	 * Creates an Elements object.
	 * @param headers  Whether the user wants to analyse 'h1', 'h2', 'h3', 'h4', 'h5' and 'h6' elements.
	 * @param paragraphs  Whether the user wants to analyse 'p' elements.
	 * @param links  Whether the user wants to analyse 'a' elements.
//	 * @param lists  Whether the user wants to analyse 'li' elements (includes both 'ul' and 'ol'). 
	 */
	public Elements( boolean headers, boolean paragraphs, boolean links, boolean lists ) {
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
		
		if ( headers || paragraphs || links || lists || everything ) 
			chosen = true;
		
		return chosen;
	}
}

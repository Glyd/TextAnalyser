package Assignment.TextAnalyser;

/**
 * A simple class to store analysis options. 
 */

class OptionHandler {
	
	/*Handles the user's analysis choices */

	private boolean letters, numbers, specials, all;
	
	/**
	 * Creates an OptionHandler object.
	 * @param letters  Whether the user wants to analyse alphabet characters.
	 * @param numbers  Whether the users wants to analyse numeric characters.
	 * @param specials  Whether the user wants to analyse non-alphanumeric characters, e.g. punctuation, accented characters.
	 */
	public OptionHandler( boolean letters, boolean numbers, boolean specials ) {
		setLetters( letters );
		setNumbers( numbers );
		setSpecials( specials );

		if ( letters && numbers && specials ) {
			all = true;
		}
	}
	
	public OptionHandler () {
		setLetters( true );
		setNumbers( true );
		setSpecials( true );
		all = true;
	}

	public boolean doLetters() {
		return letters;
	}

	public void setLetters( boolean letters ) {
		this.letters = letters;
	}

	public boolean doNumbers() {
		return numbers;
	}

	public void setNumbers( boolean numbers ) {
		this.numbers = numbers;
	}

	public boolean doSpecials() {
		return specials;
	}

	public void setSpecials( boolean specials ) {
		this.specials = specials;
	}
	
	public boolean doAll() {
		return all;
	}
}

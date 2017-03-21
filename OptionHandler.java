package Assignment.TextAnalyser;

class OptionHandler {
	
	/*Handles the user's analysis choices */

	private boolean letters, numbers, specials, all;

	public OptionHandler(boolean letters, boolean numbers, boolean specials) {
		setLetters(letters);
		setNumbers(numbers);
		setSpecials(specials);

		if (letters && numbers && specials) 
			all = true;
	}

	public boolean doLetters() {
		return letters;
	}

	public void setLetters(boolean letters) {
		this.letters = letters;
	}

	public boolean doNumbers() {
		return numbers;
	}

	public void setNumbers(boolean numbers) {
		this.numbers = numbers;
	}

	public boolean doSpecials() {
		return specials;
	}

	public void setSpecials(boolean specials) {
		this.specials = specials;
	}
	
	public boolean doAll() {
		return all;
	}
}

package Assignment.TextAnalyser;

/**
 * A class to detect whether a character should be analysed, and if it is special. 
 */
public class CharacterMatcher {
	
	char alphabet[] = new char[]{ 'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r',
			's','t','u','v','w','x','y','z' };
	
	char numeric[] = new char[]{ '1','2','3','4','5','6','7','8','9' };
	
	char[] alphanumeric = new char[ 37 ];
	
	public CharacterMatcher() {
		String alphanumericString = "";
		StringBuilder stringBuilder = new StringBuilder();
		alphanumericString = stringBuilder.append( alphabet ).append( numeric ).toString(); //append arrays when analysing alphanumeric
		
		alphanumeric = alphanumericString.toCharArray();
	}
	
	/**
	 * Checks if the passed character is part of the matchers the user selected. 
	 * @param ch  The character to check.
	 * @param matcher  A char array of characters to check the character against.
	 * @return True if the character is valid for the option selected, false if the characters should not be analysed. 
	 */
	public boolean doesCharMatchOptions( char ch, char[] matcher ) {
		boolean charFound = false;
		for ( char character: matcher ) {
			if ( ch == character )
				charFound = true;
		}
		
		return charFound;
	}
	
	
	/**
	 * Checks whether the passed character is non-alphanumeric.
	 * @param ch  The character to check. 
	 * @return  Return true if the character non-alphanumeric, and false if it is alphanumeric. 
	 */
	public boolean isSpecial ( char ch ) {
		boolean isSpecial = true;
		for ( char character: alphanumeric ) {
			if ( ch == character ) {
				isSpecial = false;
			}
		}
		return isSpecial;
	}
}


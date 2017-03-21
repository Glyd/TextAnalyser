package Assignment.TextAnalyser;

public class CharacterMatcher {
	
	char alphabet[] = new char[]{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r',
			's','t','u','v','w','x','y','z'};
	
	char numeric[] = new char[]{'1','2','3','4','5','6','7','8','9'};
	
	char[] alphanumeric = new char[37];
	
	public CharacterMatcher() {
		String x = "";
		StringBuilder stringBuilder = new StringBuilder();
		x = stringBuilder.append(alphabet).append(numeric).toString(); //append arrays when analysing alphanumeric
		
		alphanumeric = x.toCharArray();
	}
	
	public boolean charMatch(char ch, char[] matcher) {
		boolean charFound = false;
		for (char character: matcher) {
			if (ch == character)
				charFound = true;
		}
		
		return charFound;
	}
	
	public boolean isSpecial (char ch) {
		boolean isSpecial = true;
		for (char character: alphanumeric) {
			if (ch == character) {
				isSpecial = false;
			}
		}
		return isSpecial;
	}
}


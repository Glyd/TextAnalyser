package Assignment.TextAnalyser;

import java.util.HashMap;

public class TextAnalyser {
	
	Matchers matchers;
	Analyser analyser;
	
	public TextAnalyser() {
		matchers = new Matchers();
	}

	public void analyse(String input, Options options) {
		HashMap<Character,Integer> map = inputToHashmap(input, options);
		
		StringBuilder builder = new StringBuilder();
		
		analyser = new Analyser(map,input);
		builder.append(analyser.buildGraph());
		builder.append(analyser.countCharacters());
		builder.append(analyser.countWords(input));
		
		ResultsScreen screen = new ResultsScreen(builder.toString());
	}

	private HashMap<Character,Integer> inputToHashmap(String input, Options options) {
		HashMap<Character, Integer> map = new HashMap<>();
		char[] matcher = new char[0];
		
		if (!options.doAll() && options.doLetters() && options.doNumbers()) {
			matcher = matchers.alphanumeric;
		} else if (!options.doAll() && options.doLetters()) {
			matcher = matchers.alphabet;
		} else if (!options.doAll() && options.doNumbers()) {
			matcher = matchers.numeric;
		}
		
		for (char ch : input.toCharArray()) {
			
			if (map.containsKey(ch)) {
				int val = map.get(ch);
				map.put(ch, val + 1);	
			} else {
				if (options.doAll() 
						|| matchers.charMatch(ch, matcher) 
						|| (matchers.isSpecial(ch) && options.doSpecials())) { //if doing all character, or matches selection
					map.put(ch, 1);
				}
			}
		}
		
		return map;
	}
}

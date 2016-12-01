package Assignment.TextAnalyser;

import java.util.HashMap;

public class TextAnalyser {
	
	Matchers matchers;
	
	public TextAnalyser() {
		matchers = new Matchers();
	}
	
	public void analyse(String input, Options options) {
		HashMap<Character,Integer> map = inputToHashmap(input, options);
		
		System.out.println(map);
		
		buildGraph(map, input);
	}
	
	private void buildGraph(HashMap<Character,Integer> map, String input) {
		
		for (int j = 0; j < input.length(); j++) { 
			if (input.charAt(j) == (' ')) { //skip spaces
				j++;
			} else if (map.get(input.charAt(j)) != null) {
				System.out.print(input.charAt(j) + " | ");
				for (int i = 0; i < map.get(input.charAt(j)); i++) { //prints x (value of letter in HashMap) times
					System.out.print("x");
				}
				System.out.println();
			}
		}
				
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
				System.out.println(ch + " / " + val);
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


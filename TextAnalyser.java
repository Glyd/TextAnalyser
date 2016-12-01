package Assignment.TextAnalyser;

import java.util.HashMap;

public class TextAnalyser {
	/**
	 * Turns string in to a HashMap of characters and their values. 
	 */
	private HashMap<Character,Integer> completeMap;
	
	private void analyse(String input, char[] matcher) {
			
		HashMap<Character,Integer> map = inputToHashmap(input);
		
		if (matcher != null) { // either numbers or letters
			
			input = Matchers.keepOnlyMatching(input,matcher);
			
			for (int j = 0; j < matcher.length; j++) { 
					if (map.get(matcher[j]) != null) {
					System.out.print("Character: " + matcher[j] + " count: ");
					System.out.print(map.get(matcher[j]));
					input = input.replace(matcher[j]+"",""); //removes character from input
					System.out.println();
				}
			}
			
			//appends current map to complete for use in graph
			if (completeMap != null) { //only appends if map isnt empty (first analysis)
				completeMap.putAll(map);
			} else { //assigns current map if it's the first time running
				completeMap = map;
			}
		} else { //special characters to be analyzed, numbers and letters should be removed so no need for matcher
			
			//remove all letters and numbers
			
			input = Matchers.removeAllButSpecial(input);
			
			System.out.println(input);
			
			for (int j = 0; j < input.length(); j++) { 
				if (map.get(input.charAt(j)) != null) {
					System.out.print("Character: " + input.charAt(j) + " count: ");
					System.out.print(map.get(input.charAt(j)));
					input = input.replace(input.charAt(j)+"",""); //removes character from input
					System.out.println();
				}
			}
			
			completeMap = map;
		}
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
				input = input.replace(input.charAt(j)+""," "); //removes character from input, adds placeholder
				System.out.println("");
			} else if (map.get(input.charAt(j)) == null){ //number was removed from map, skip it
				j++; 
			}
		}
				
	}
	
	public void analyseOptions(String input, boolean letters, boolean numbers, boolean special) {
		
		if (letters) {
			analyse(input, Matchers.alphabet);
		}
		if (numbers) {
			analyse(input, Matchers.numeric);
		}
		if (special) {
			analyse(input, null);
		}
		
		completeMap = Matchers.keepOnlyMatching(completeMap, letters, numbers, special);
		
		buildGraph(completeMap, input);
	}
	
	private HashMap<Character,Integer> inputToHashmap(String input) {
		HashMap<Character, Integer> map = new HashMap<>();
				
		for (char ch : input.toCharArray()) {
			if (map.containsKey(ch)) {
				int val = map.get(ch);
				map.put(ch, val + 1);	
			} else {
				map.put(ch, 1);
			}
		}
		
		return map;
	}
}

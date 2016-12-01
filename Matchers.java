package Assignment;

import java.util.HashMap;

public class Matchers {
	
	static char alphabet[] = new char[]{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r',
			's','t','u','v','w','x','y','z'};
	
	static char numeric[] = new char[]{'1','2','3','4','5','6','7','8','9'};
	
	public static String keepOnlyMatching(String input, char[] matcher) {
		for (int j = 0; j < input.length(); j++) { // go through whole input
			boolean isFound = false;
			for (int i = 0; i < matcher.length; i++) { //for each item in matcher
				if (matcher[i] == input.charAt(j)) {
					isFound = true;
					break;
				}
			}
			if (!isFound) {
				input = input.replace(input.charAt(j), ' ');
			}
		}
		
		return input;
	}
	
	public static String removeAllButSpecial(String input) {
		
		StringBuilder sb = new StringBuilder(64);
		sb.append(alphabet);
		sb.append(numeric);
	
		char matcher[] = sb.toString().toCharArray();
		
		for (int j = 0; j < input.length(); j++) { // go through whole input
			boolean isFound = false;
			for (int i = 0; i < matcher.length; i++) { //for each item in matcher
				if (matcher[i] == input.charAt(j)) {
					isFound = true;
					break;
				}
			}
			if (isFound) {
				input = input.replace(input.charAt(j), ' ');
			}
		}
		
		return input;
	}
	
	public static HashMap<Character,Integer> keepOnlyMatching(HashMap<Character,Integer> map, boolean letters, boolean numbers, boolean special) {
		
		if (letters == false) {
			map = removeFromMap(map,alphabet);
		}
		if (numbers == false) {
			map = removeFromMap(map,numeric);
		}
		if (special == false) {
			map = removeSpecialFromMap(map);
		}
		
		return map;
	}

	private static HashMap<Character,Integer> removeFromMap(HashMap<Character,Integer> map, char[] matcher) {
		
		for (int j = 0; j < map.size(); j++) { // go through whole map
			boolean isFound = false;
			for (int i = 0; i < matcher.length; i++) { //for each item in matcher
				int result = map.getOrDefault(matcher[i], -1); //-1 if the item was not found
				if (result != -1)
					isFound = true; //if not -1, it has been found
			}
			if (isFound) {
				map.remove(matcher[j]); // remove if found
			}
		}
		
		return map;
	}
	
	private static HashMap<Character,Integer> removeSpecialFromMap(HashMap<Character,Integer> map) {
		
			char[] matcher = mergeArrays(alphabet,numeric);
		
			for (int j = 0; j < map.size(); j++) { // go through whole map
				boolean isFound = false;
				for (int i = 0; i < matcher.length; i++) { //for each item in matcher
					int result = map.getOrDefault(matcher[i], -1);
					if (result != -1)
						isFound = true;
				}
				if (!isFound) {
					map.remove(matcher[j]);
				}
			}
		
		return map;
	}
	
	public static char[] mergeArrays(char[] arrayone, char[] arraytwo) {
		
		if (arrayone[0] == ' ') { //if arrayone is empty (chose to analyse something they didnt input)
			
		}
		
		char[] merged = new char[arrayone.length+arraytwo.length];
		
		for (int i = 0; i < merged.length; i++) {
			if (i < arrayone.length)
				merged[i] = arrayone[i];
			else
				merged[i] = arraytwo[i];
		}
		
		return merged;
	}
}

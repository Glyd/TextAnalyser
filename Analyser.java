package Assignment.TextAnalyser;

import java.util.HashMap;

public class Analyser {
	
	/**Initialize class with the map and input (removing any duplicate characters). */

	HashMap<Character,Integer> map;
	String input;
	
	public Analyser(HashMap<Character,Integer> map, String input) {
		this.map = map;
		this.input = input;
	}
	
	public void countCharacters() {
		
		for (int j = 0; j <input.length(); j++) { //for each letter in string
				if(!input.equals(null) && map.getOrDefault(input.charAt(j), -1)!= -1) {
					System.out.println(input.charAt(j) + " was found " + map.get(input.charAt(j)) + " time(s).");
				}
		}
	}
	
	public void buildGraph() {
		
		for (int j = 0; j <input.length(); j++) { //for each letter in string
				if(!input.equals(null) && map.getOrDefault(input.charAt(j), -1)!= -1) {
					System.out.print(input.charAt(j) + " | ");
					for (int h = 0; h < map.get(input.charAt(j)); h++) {
						System.out.print("x");
					}
					System.out.println();
				}
		}
	}
}

package Assignment.TextAnalyser;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;

import org.omg.CORBA_2_3.portable.InputStream;

public class Analyser {
	
	/**Initialize class with the map and input (removing any duplicate characters). */

	HashMap<Character,Integer> map;
	String input;
	
	public Analyser(HashMap<Character,Integer> map, String input) {
		this.map = map;
		this.input = input;
	}
	
	public void countCharacters() {
		
		int highestVal = 0;
		char highestChar =' ';
		
		for (int j = 0; j <input.length(); j++) { //for each letter in string
				if(!input.equals(null) && map.getOrDefault(input.charAt(j), -1)!= -1) {
					System.out.println(input.charAt(j) + " was found " + map.get(input.charAt(j)) + " time(s).");
					if (map.get(input.charAt(j)) > highestVal) {
						highestVal = map.get(input.charAt(j));
						highestChar = input.charAt(j);
					}
				}
		}
		
		System.out.println("The most commonly occuring character was " + highestChar + " with " + highestVal + " occurences.");
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
	
	public static String readFile(String path, Charset encoding) 
			  throws IOException { //to catch file errors
	  
		java.io.InputStream stream = Files.newInputStream(Paths.get(path), StandardOpenOption.READ);
		String text = stream.toString();
		stream.close();
	  
	  return text;
	}
	
	public void countWords(String input) {
		String largestWord = "";
		
		String[] words = input.split("\\s+");
		
		for (int i = 0; i < words.length; i++) {
			if (words[i].length() > largestWord.length()) {
				largestWord = words[i];
			}
		}
		
		System.out.println("You entered: " + words.length + " words");
		System.out.println("The longest word was " + largestWord +", which has " + largestWord.length() + " characters.");
	}
}

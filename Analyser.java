package Assignment.TextAnalyser;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DecimalFormat;
import java.util.HashMap;

import org.omg.CORBA.Environment;
import org.omg.CORBA_2_3.portable.InputStream;

public class Analyser {
	
	/**Initialize class with the map and input (removing any duplicate characters). */

	HashMap<Character,Integer> map;
	String input, wholeInput;
	
	public Analyser(HashMap<Character,Integer> map, String input) {
		this.map = map;
		wholeInput = input;
		this.input = removeDuplicates(input);
	}
	
	public StringBuilder countCharacters() {
		
		int highestVal = 0;
		char highestChar =' ';
		StringBuilder builder = new StringBuilder();
		
		for (int j = 0; j <input.length(); j++) { //for each letter in string
				if(!input.equals(null) && map.getOrDefault(input.charAt(j), -1)!= -1) {
					
					double relativeFreq = (double)map.get(input.charAt(j))/(double)wholeInput.length();
					DecimalFormat df = new DecimalFormat("#.##");
					relativeFreq = Double.parseDouble(df.format(relativeFreq));
					
					// number of times that char occurs, number of unique characters, total characters
					
					builder.append(input.charAt(j) + " was found " + map.get(input.charAt(j)) + " time(s). Relative frequency: " 
							+ relativeFreq);
					builder.append(System.getProperty("line.separator")); //get newline, platform independent way
					
					if (map.get(input.charAt(j)) > highestVal) {
						highestVal = map.get(input.charAt(j));
						highestChar = input.charAt(j);
					}
				}
		}
		
		builder.append("The most commonly occuring character was " + highestChar + " with " + highestVal + " occurences.");
		builder.append(System.getProperty("line.separator"));
		
		return builder;
	}
	
	public StringBuilder buildGraph() {
		
		StringBuilder builder = new StringBuilder();
		
		for (int j = 0; j <input.length(); j++) { //for each letter in string
				if(!input.equals(null) && map.getOrDefault(input.charAt(j), -1)!= -1) {
					StringBuilder values = new StringBuilder();
					for (int h = 0; h < ((double)map.get(input.charAt(j))/(double)wholeInput.length())*100; h++) {
						
						values.append("x");
					}
					
					builder.append(input.charAt(j) + " | " + values.toString());
					builder.append(System.getProperty("line.separator"));
				}
		}
		
		return builder;
	}
	
	public static String readFile(String path, Charset encoding) 
			  throws IOException { //to catch file errors
	  
		java.io.InputStream stream = Files.newInputStream(Paths.get(path), StandardOpenOption.READ);
		String text = stream.toString();
		stream.close();
	  
	  return text;
	}
	
	public StringBuilder countWords(String input) {
		
		input = input.replaceAll("[^A-Za-z]+", " "); //replace all non alphabet characters with spaces
		StringBuilder builder = new StringBuilder();
		String largestWord = "";
		
		String[] words = input.split("\\s+");
		
		//check if each word is larger than the current largest
		for (int i = 0; i < words.length; i++) { 
			if (words[i].length() > largestWord.length()) {
				largestWord = words[i];
			}
		}
		
		builder.append("You entered: " + words.length + " words");
		builder.append(System.getProperty("line.separator"));
		builder.append("The longest word was " + largestWord +", which has " + largestWord.length() + " characters.");
		builder.append(System.getProperty("line.separator"));
		
		return builder;
	}
	

	private String removeDuplicates(String input) {
	    boolean charFound[] = new boolean[256];
	    StringBuilder sb = new StringBuilder(charFound.length);
	    
	    for (int i = 0; i < input.length(); i++) {
	        char ch = input.charAt(i);
	        if (!charFound[ch]) {
	            charFound[ch] = true;
	            sb.append(ch);
	        }
	    }

	    return sb.toString();
	}
}

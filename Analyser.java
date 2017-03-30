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

/**
 * This class provides the bulk of the analysis options, building the results as StringBuilder objects so they can be printed on the ResultsScreen. 
 */

public class Analyser {
	
	/**Initialize class with the map and input ( removing any duplicate characters ). */

	HashMap<Character,Integer> map;
	String input, wholeInput;
	/**
	 * Creates an object of the Analyser class.
	 * @param  map  A HashMap containing all characters and the times they were found. 
	 * @param  input  The String object you want analyse.
	 */
	public Analyser( HashMap<Character,Integer> map, String input ) {
		this.map = map;
		wholeInput = input;
		
		this.input = removeDuplicates( input );
	}
	
	/**
	 * Counts the number of characters that occur in the String that was passed to the class.
	 * @return  A multi-line StringBuilder detailed the characters found and relative frequency. 
	 */
	public StringBuilder countCharacters() {
		
		int highestVal = 0;
		char highestChar =' ';
		StringBuilder builder = new StringBuilder();
		
		for ( int j = 0; j <input.length(); j++ ) { //for each letter in string
				if( !input.equals( null ) && map.getOrDefault( input.charAt( j ), -1 ) != -1 ) {
					
					double relativeFreq = ( double ) map.get( input.charAt( j ) ) / ( double ) wholeInput.length();
					DecimalFormat df = new DecimalFormat( "#.##" );
					relativeFreq = Double.parseDouble( df.format( relativeFreq ) );
					
					// number of times that char occurs, number of unique characters, total characters
					
					if ( input.charAt( j ) == ' ' )
						builder.append( '␣' + " was found " + map.get( input.charAt( j ) ) + " time( s ). Relative frequency: " 
								+ relativeFreq );
					else if ( input.charAt( j ) == '\n' )
						builder.append( "↵" + " was found " + map.get( input.charAt( j ) ) + " time( s ). Relative frequency: " 
								+ relativeFreq );
					else
						builder.append( input.charAt( j ) + " was found " + map.get( input.charAt( j ) ) + " time( s ). Relative frequency: " 
								+ relativeFreq );
					
					builder.append( System.getProperty( "line.separator" ) ); //get newline, platform independent way
					
					if ( map.get( input.charAt( j ) ) > highestVal ) {
						highestVal = map.get( input.charAt( j ) );
						highestChar = input.charAt( j );
						
					if ( highestChar == ' ' )
						highestChar = '␣';
					else if ( highestChar == '\n' )
						highestChar = '↵';
				}
			}
		}
		builder.append( System.getProperty( "line.separator" ) );
		builder.append( "The most commonly occuring character was " + highestChar + " with " + highestVal + " occurences." );
		builder.append( System.getProperty( "line.separator" ) );
		builder.append( System.getProperty( "line.separator" ) );
		return builder;
	}
	
	/**
	 * Builds a graph based on the characters found and the number of occurrences as a percentage.
	 * @return A multi-line graph as a StringBuilder ready to print. 
	 */
	public StringBuilder buildGraph() {
		
		StringBuilder builder = new StringBuilder();
		
		for ( int j = 0; j <input.length(); j++ ) { //for each letter in string
				if( !input.equals( null ) && map.getOrDefault( input.charAt( j ), -1 ) != -1 ) {
					StringBuilder values = new StringBuilder();
					for ( int h = 0; h < ( ( double )map.get( input.charAt( j ) )/( double )wholeInput.length() ) * 100; h++ ) {
						values.append( "x" );
					}
					
					if ( input.charAt( j ) == ' ' )
						builder.append( '␣' + " | " + values.toString() );
					else if  ( input.charAt( j ) == '\n' )
						builder.append( "↵" + " | " + values.toString() );
					else
						builder.append( input.charAt( j ) + " | " + values.toString() );
						
					builder.append( System.getProperty( "line.separator" ) );
				}
		}
		
		builder.append( System.getProperty( "line.separator" ) );
		return builder;
	}
	
	/**
	 * Counts the number of words in a given String.
	 * @param  input  The String to count the number of words from.
	 * @return  A generated StringBuilder detailing the number of words found and the longest word. 
	 */
	public StringBuilder countWords( String input ) {
		
		input = input.replaceAll( "[ ^A-Za-z ]+", " " ); //replace all non alphabet characters with spaces
		StringBuilder builder = new StringBuilder();
		String largestWord = "";
		
		String[] words = input.split( "\\s+" );
		
		//check if each word is larger than the current largest
		for ( int i = 0; i < words.length; i++ ) { 
			if ( words[ i ].length() > largestWord.length() ) {
				largestWord = words[ i ];
			}
		}
		if (  largestWord.length() > 0  ) {
			builder.append( "You entered: " + words.length + " word( s )" );
			builder.append( System.getProperty( "line.separator" ) );
			builder.append( System.getProperty( "line.separator" ) );
			builder.append( "The longest word was " + largestWord +", which has " + largestWord.length() + " character( s )." );
		} else {
			builder.append( "No words were found!" );
		}
		
		builder.append( System.getProperty( "line.separator" ) );
		builder.append( System.getProperty( "line.separator" ) );
		
		return builder;
	}
	
	/**
	 * Removes all but one occurrence of each character in a String.
	 * @param  input  The String object you want to remove all but one occurrence of a character.
	 * @return  A trimmed version of the given String that only has one occurrence of each character found.
	 */
	private String removeDuplicates( String input ) {
	    boolean charFound[] = new boolean[ 256 ];
	    StringBuilder sb = new StringBuilder( charFound.length );
	    
	    for ( int i = 0; i < input.length(); i++ ) {
	    	
	    	String ch;
	    	
	    	if ( input.charAt( i ) == '␣' )
	    		ch = " ";
	    	else if ( input.charAt( i ) == '↵' )
	    		ch = System.getProperty( "line.separator" );
	    	else 
	    		ch = input.charAt( i )+"";
	    	
	        if ( !charFound[ ch.charAt( 0 ) ] ) {
	            charFound[ ch.charAt( 0 ) ] = true;
	            sb.append( ch );
	        }
	    }

	    return sb.toString();
	}
	
	
	/**
	 * Turns the text input into an HashMap containing all found characters and the number of times they were found.
	 * @param input  The full String to be turned into a HashMap.
	 * @param options  An OptionHandler containing the options the user chose to analyse - does not handle error if all false, validation should be done on HomeWindow.
	 * @return A generated HashMap of all found characters and the number of times they were found.
	 */
	public static HashMap<Character,Integer> inputToHashmap( String input, OptionHandler options ) {
		HashMap<Character, Integer> map = new HashMap<>();
		CharacterMatcher matchers = new CharacterMatcher();
		char[] matcher = new char[ 0 ];
		
		if ( !options.doAll() && options.doLetters() && options.doNumbers() ) {
			matcher = matchers.alphanumeric;
		} else if ( !options.doAll() && options.doLetters() ) {
			matcher = matchers.alphabet;
		} else if ( !options.doAll() && options.doNumbers() ) {
			matcher = matchers.numeric;
		}
		
		for (  char ch : input.toCharArray()  ) {
			
			if (  map.containsKey(  ch  )  ) {
				int val = map.get(  ch  );
				
				map.put(  ch, val + 1  );	
			} else {
				if (  options.doAll() 
						|| matchers.doesCharMatchOptions( ch, matcher ) 
						|| ( matchers.isSpecial( ch ) && options.doSpecials() )  ) { //if doing all character, or matches selection
					map.put(  ch, 1  );
				}
			}
		}
		
		return map;
	}
}

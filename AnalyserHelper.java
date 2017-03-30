package Assignment.TextAnalyser;

import java.io.IOException;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
/**
 * A class that simplifies the use of Analyser by doing all analysis functions in a single method call, as well as handling the URL analysis.
 * Makes the use of Analyser much cleaner.
 */
public class AnalyserHelper {
	
	Analyser analyser;
	
	/**
	 * A method to cleanly build and display the results string using the Analyser functions and ResultsScreen.
	 * @param input  The full String input that should be analysed.
	 * @param options  An OptionHandler that has been initialised with the desired options.
	 */
	public void analyse( String input, OptionHandler options ) {
		HashMap<Character,Integer> map = Analyser.inputToHashmap( input, options );
		
		StringBuilder builder = new StringBuilder();
		
		analyser = new Analyser( map,input );
		builder.append( analyser.buildGraph() );
		builder.append( analyser.countCharacters() );
		builder.append( analyser.countWords( input ) );
		
		ResultsWindow screen = new ResultsWindow( builder.toString() );
	}
	
	/**
	 * A method to connect to a URL and extract the desired elements, then analyse them.
	 * @param url  The full URL that you wish to analyse the text from. 
	 * @param elements  An Elements object initialised with the desired options.
	 */
	public void analyseURL(  String url, Elements elements  ) {
		
		OptionHandler options = new OptionHandler();
		
		try {
			Document text;
			String endText = "";
			StringBuilder stringBuilder = new StringBuilder();
			
			if (  elements.wantsEverything()  ) {
				//Parse - connect to url, turn it to string. Then get the HTML and turn it into a string. JSoup automatically removes HTML tags.
				endText = Jsoup.parse(  Jsoup.connect(  url  ).get().text()  ).getElementsByTag(  "html"  ).text();
			} else {
				text = Jsoup.connect(  url  ).get();
				
				if ( elements.wantsHeaders() ) {
					stringBuilder.append(  text.getElementsByTag(  "h1"  ).text() + " "  );
					stringBuilder.append(  text.getElementsByTag(  "h2"  ).text() + " "  );
					stringBuilder.append(  text.getElementsByTag(  "h3"  ).text() + " "  );
					stringBuilder.append(  text.getElementsByTag(  "h4"  ).text() + " "  );
					stringBuilder.append(  text.getElementsByTag(  "h5"  ).text() + " "  );
					stringBuilder.append(  text.getElementsByTag(  "h6"  ).text() + " "  );
				}
				
				if (  elements.wantsParagraphs()  ) {
					stringBuilder.append(  text.getElementsByTag(  "p"  ).text() + " "  );
				}	
				
				if ( elements.wantsLinks() ) {
					stringBuilder.append(  text.getElementsByTag(  "a"  ).text() + " "  );
				}			
				
				if ( elements.wantsLists() ) {
					stringBuilder.append(  text.getElementsByTag(  "li"  ).text() + " "  );
				}
				
				endText = stringBuilder.toString();
			}
					
			System.out.println(  endText  );
			
			analyse(  endText, options  );
		} catch (  IOException e1  ) {
			ErrorWindow error = new ErrorWindow(  "There was an error with your URL."  );
		}
	}
}

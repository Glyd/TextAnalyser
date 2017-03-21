package Assignment.TextAnalyser;

import java.io.IOException;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class AnalysisHelper {
	
	CharacterMatcher matchers;
	Analyser analyser;
	
	public AnalysisHelper() {
		matchers = new CharacterMatcher();
	}

	public void analyse(String input, OptionHandler options) {
		HashMap<Character,Integer> map = inputToHashmap(input, options);
		
		StringBuilder builder = new StringBuilder();
		
		analyser = new Analyser(map,input);
		builder.append(analyser.buildGraph());
		builder.append(analyser.countCharacters());
		builder.append(analyser.countWords(input));
		
		ResultsWindow screen = new ResultsWindow(builder.toString());
	}
	
	public void analyseURL(String url, Elements elements) {
		
		OptionHandler options = new OptionHandler(true, true, true);
		
		try {
			Document text;
			String endText="";
			StringBuilder stringBuilder = new StringBuilder();
			
			if (elements.wantsEverything()) {
				//Parse - connect to url, turn it to string. Then get the body element and turn it into a string
				endText = Jsoup.parse(Jsoup.connect(url).get().text()).getElementsByTag("body").text();
			} else {
				
				text = Jsoup.connect(url).get();
				
				if (elements.wantsHeaders()) {
					stringBuilder.append(text.getElementsByTag("h1").text());
					stringBuilder.append(text.getElementsByTag("h2").text());
					stringBuilder.append(text.getElementsByTag("h3").text());
					stringBuilder.append(text.getElementsByTag("h4").text());
					stringBuilder.append(text.getElementsByTag("h5").text());
					stringBuilder.append(text.getElementsByTag("h6").text());
				}
				
				if (elements.wantsParagraphs()) {
					stringBuilder.append(text.getElementsByTag("p").text());
				}	
				
				if (elements.wantsLinks()) {
					stringBuilder.append(text.getElementsByTag("a").text());
				}			
				
				if (elements.wantsLists()) {
					stringBuilder.append(text.getElementsByTag("li").text());
				}
				
				endText = stringBuilder.toString();
			}
			
			
			System.out.println(endText);
			
			analyse(endText, options);
		} catch (IOException e1) {
			ErrorWindow error = new ErrorWindow("There was an error with your URL.");
		}
	}

	private HashMap<Character,Integer> inputToHashmap(String input, OptionHandler options) {
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

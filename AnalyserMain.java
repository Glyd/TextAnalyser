package Assignment.TextAnalyser;

import java.util.Scanner;

public class AnalyserMain {

	public static void main(String[] args) {
		boolean active = true;
		Scanner scanner = new Scanner(System.in);
		
		while(active) {
			System.out.println("Hello! Would you like to input text, or read from a file?");
			System.out.println("Press 1 for inputting text, or 2 for file.");
			
			String choice = scanner.next();
			
			if (!choice.equals("1")
					&& !choice.equals("2")) {
				System.out.println("Sorry, please try again.");
				while (!choice.equals("1") 
						&& !choice.equals("2")) {
					System.out.println("Press 1 for inputting text, or 2 for file.");
					choice = scanner.next();
				}
			}
			
			if (choice.equals("1")) { 
				System.out.println("Input the text you would like to analyse on a single line.");
				Scanner scanner2 = new Scanner(System.in);
				String input = scanner2.nextLine().toLowerCase();
				
				TextAnalyser analyse = new TextAnalyser(); 
				boolean letters, numbers, specials;
				letters = numbers = specials = false;
				
				System.out.println("Would you like to analyse letters? Input Y or N");
				if (scanner.next().toLowerCase().charAt(0) == 'y') 
					letters = true;
				System.out.println("Would you like to analyse numbers? Input Y or N");
				if (scanner.next().toLowerCase().charAt(0) == 'y') 
					numbers = true;
				System.out.println("Would you like to analyse special characters? Input Y or N");
				if (scanner.next().toLowerCase().charAt(0) == 'y') 
					specials = true;
				
				analyse.analyseOptions(input, letters, numbers, specials);
			}
		}
		
	}

}

package Assignment.TextAnalyser;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import javax.naming.Context;
import javax.swing.JFileChooser;

public class AnalyserMain {

	public static void main(String[] args) {
		boolean active = true;
		Scanner scanner = new Scanner(System.in);
		String input ="";
		
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
				input = scanner2.nextLine().toLowerCase();
				
				analyse(input, scanner);
			}
			else if (choice.equals("2")) {
				
				FilePicker filePicker;
				filePicker = new FilePicker();
				
				
				File file = filePicker.getFile();
				System.out.println("Please choose a text file.");
				
				try {
					if (fileValid(file.getAbsolutePath())) {
						System.out.println("You selected the file located at " + file.getAbsolutePath());
						input = Analyser.readFile(file.getAbsolutePath(), StandardCharsets.UTF_8);
						
						analyse(input, scanner);
					}
				} catch (IOException e) {
					System.out.println("Sorry, something went wrong with the file you chose. Please try again.");
				}
			}
		}
	}
	
	private static boolean fileValid(String path) {
		
		boolean valid = false;
		
		if (path.endsWith(".txt") ||
				path.endsWith(".rtf") ||
				path.endsWith(".doc") ||
				path.endsWith(".docx") ||
				path.endsWith(".sh") ) {
				
				valid = true;
			}
			
		
		
		return valid;
	}
	
	public static void analyse(String input, Scanner scanner) {
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
		
		if (!letters && !numbers && !specials) {
			System.out.println("You denied all analysis options. Please select at least one.");
			System.out.println();
		} else {
			Options options = new Options(letters,numbers,specials);
			analyse.analyse(input, options);
			System.out.println();
		}
	}
}


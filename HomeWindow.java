package Assignment.TextAnalyser;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.swing.JTextArea;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.UIManager;
/**
 * The main screen of the application containing the text box, analysis options, button for URL analysis and file browser.
 */
public class HomeWindow {

	private JFrame frame;
	private JTextField chooseFileTextField;
	boolean letters, numbers, specials;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeWindow window = new HomeWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HomeWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(33, 150, 243));
		frame.setBounds(100, 100, 700, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel enterTextLabel = DefaultComponentFactory.getInstance().createTitle("Enter text to be analysed below...");
		enterTextLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 13));
		enterTextLabel.setForeground(new Color(255, 255, 255));
		enterTextLabel.setBounds(30, 30, 720, 16);
		frame.getContentPane().add(enterTextLabel);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(30, 58, 645, 137);
		textArea.setLineWrap(true);
		textArea.setToolTipText("Enter some text here!");
		JScrollPane scrollBar = new JScrollPane (textArea);
		scrollBar.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
		scrollBar.setBounds(30, 58, 645, 137);
		frame.getContentPane().add(scrollBar);
		
		JLabel chooseFileLabel = DefaultComponentFactory.getInstance().createTitle("Or choose a text file...");
		chooseFileLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 13));
		chooseFileLabel.setForeground(new Color(255, 255, 255));
		chooseFileLabel.setBounds(74, 336, 153, 16);
		frame.getContentPane().add(chooseFileLabel);
		
		chooseFileTextField = new JTextField();
		chooseFileTextField.setBounds(74, 359, 141, 26);
		frame.getContentPane().add(chooseFileTextField);
		chooseFileTextField.setColumns(10);
		
		JButton chooseFileButton = new JButton("BROWSE...");
		chooseFileButton.setFont(new Font("Helvetica Neue", Font.BOLD, 13));
		chooseFileButton.setBackground(UIManager.getColor("Button.background"));
		
		chooseFileButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
		    	        "Text Files", "txt", "sh","rtf");
				fileChooser.setFileFilter(filter);
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				int result = fileChooser.showOpenDialog(frame);
				if (result == JFileChooser.APPROVE_OPTION) {
				    File selectedFile = fileChooser.getSelectedFile();
				    chooseFileTextField.setText(selectedFile.getAbsolutePath());
			    	try {
						textArea.setText(new Scanner(selectedFile).useDelimiter("\\Z").next());
					} catch (FileNotFoundException e1) {
						System.out.println("File was unavailable. Please try again.");
					}
				}
			}
		});
		chooseFileButton.setBounds(213, 359, 117, 29);
		frame.getContentPane().add(chooseFileButton);
		
		JRadioButton lettersRadio = new JRadioButton("Analyse Letters");
		lettersRadio.setBackground(UIManager.getColor("CheckBox.select"));
		lettersRadio.setFont(new Font("Helvetica Neue", Font.BOLD, 13));
		lettersRadio.setForeground(new Color(255, 255, 255));
		lettersRadio.setBounds(30, 207, 141, 23);
		frame.getContentPane().add(lettersRadio);
		
		JRadioButton numbersRadio = new JRadioButton("Analyse Numbers");
		numbersRadio.setFont(new Font("Helvetica Neue", Font.BOLD, 13));
		numbersRadio.setForeground(new Color(255, 255, 255));
		numbersRadio.setBounds(271, 207, 148, 23);
		frame.getContentPane().add(numbersRadio);
		
		JRadioButton specialsRadio = new JRadioButton("Analyse Special Characters");
		specialsRadio.setFont(new Font("Helvetica Neue", Font.BOLD, 13));
		specialsRadio.setForeground(new Color(255, 255, 255));
		specialsRadio.setBounds(475, 207, 200, 23);
		frame.getContentPane().add(specialsRadio);
		
		JLabel mainTitle = new JLabel("TEXT ANALYSER");
		mainTitle.setForeground(new Color(255, 255, 255));
		mainTitle.setFont(new Font("Helvetica", Font.BOLD, 16));
		mainTitle.setBounds(271, 6, 141, 16);
		frame.getContentPane().add(mainTitle);
		
		JButton analyseButton = new JButton("ANALYSE");
		analyseButton.setFont(new Font("Helvetica Neue", Font.BOLD, 13));
		analyseButton.setBackground(UIManager.getColor("CheckBoxMenuItem.background"));
		analyseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (textArea.getText().equals("")) {
					ErrorWindow error = new ErrorWindow("You have not input any text or chosen a file.");
				} else if (!lettersRadio.isSelected() && !numbersRadio.isSelected() && !specialsRadio.isSelected()) {
					ErrorWindow error = new ErrorWindow("You have not selected any analysis options.");
				} else {
					letters = lettersRadio.isSelected();
					numbers = numbersRadio.isSelected();
					specials = specialsRadio.isSelected();
					
					String input = textArea.getText();
					
					if (!numbers)
						input = input.replaceAll("[0-9]", ""); //remove all numeric characters
					
					if(!specials && letters && numbers) 
						input = input.replaceAll("[^A-Za-z0-9]", ""); //remove non alphanumeric
					
					if (!specials && letters && !numbers) 
						input = input.replaceAll("[^A-Za-z]", ""); //remove non alphabetic
					
					if (!specials && !letters && numbers)
						input = input.replaceAll("[^0-9]", ""); //remove non numeric
					
					if (!letters)
						input = input.replaceAll("[A-Za-z]", ""); //remove alphabetic
					
					OptionHandler options = new OptionHandler(letters,numbers,specials);
					AnalyserHelper analyse = new AnalyserHelper();
					analyse.analyse(input.toLowerCase(), options);
				}
			}
		});
		
		analyseButton.setBounds(248, 246, 175, 70);
		frame.getContentPane().add(analyseButton);
		
		
		
		JButton clearButton = new JButton("CLEAR");
		clearButton.setBackground(UIManager.getColor("Button.background"));
		clearButton.setBounds(558, 25, 117, 29);
		frame.getContentPane().add(clearButton);
		
		JButton urlAnalyseButton = new JButton("ANALYSE A URL");
		urlAnalyseButton.setFont(new Font("Helvetica Neue", Font.BOLD, 13));
		urlAnalyseButton.setBackground(UIManager.getColor("Button.background"));
		urlAnalyseButton.setBounds(410, 348, 221, 37);
		frame.getContentPane().add(urlAnalyseButton);
		
		urlAnalyseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ElementSelector elementSelector = new ElementSelector();
			}	
		});
		
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
			}
		});
	}
}

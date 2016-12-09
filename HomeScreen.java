package Assignment.TextAnalyser;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.ReadOnlyFileSystemException;
import java.util.Scanner;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomeScreen {

	private JFrame frame;
	private JTextField chooseFileText;
	boolean letters, numbers, specials;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeScreen window = new HomeScreen();
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
	public HomeScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel enterTextLabel = DefaultComponentFactory.getInstance().createTitle("Enter text to be analysed below...");
		enterTextLabel.setBounds(30, 30, 720, 16);
		frame.getContentPane().add(enterTextLabel);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(30, 58, 645, 137);
		textArea.setLineWrap(true);
		textArea.setToolTipText("Enter some text here!");
		JScrollPane scroll = new JScrollPane (textArea);
		scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
		scroll.setBounds(30, 58, 645, 137);
		frame.getContentPane().add(scroll);
		
		JLabel chooseFileLabel = DefaultComponentFactory.getInstance().createTitle("Or choose a text file...");
		chooseFileLabel.setBounds(30, 207, 720, 16);
		frame.getContentPane().add(chooseFileLabel);
		
		chooseFileText = new JTextField();
		chooseFileText.setEditable(false);
		chooseFileText.setBounds(30, 235, 538, 26);
		frame.getContentPane().add(chooseFileText);
		chooseFileText.setColumns(10);
		
		JButton chooseFileButton = new JButton("BROWSE...");
		
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
				    chooseFileText.setText(selectedFile.getAbsolutePath());
			    	try {
						textArea.setText(new Scanner(selectedFile).useDelimiter("\\Z").next());
					} catch (FileNotFoundException e1) {
						System.out.println("File was unavailable. Please try again.");
					}
				}
			}
		});
		chooseFileButton.setBounds(558, 235, 117, 29);
		frame.getContentPane().add(chooseFileButton);
		
		JRadioButton lettersRadio = new JRadioButton("Analyse Letters");
		lettersRadio.setBounds(30, 288, 141, 23);
		frame.getContentPane().add(lettersRadio);
		
		JRadioButton numbersRadio = new JRadioButton("Analyse Numbers");
		numbersRadio.setBounds(274, 288, 148, 23);
		frame.getContentPane().add(numbersRadio);
		
		JRadioButton specialsRadio = new JRadioButton("Analyse Special Characters");
		specialsRadio.setBounds(475, 288, 200, 23);
		frame.getContentPane().add(specialsRadio);
		
		JLabel mainTitle = new JLabel("TEXT ANALYSER");
		mainTitle.setBounds(300, 6, 108, 16);
		frame.getContentPane().add(mainTitle);
		
		JButton analyseButton = new JButton("ANALYSE");
		analyseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!lettersRadio.isSelected() && !numbersRadio.isSelected() && !specialsRadio.isSelected() || textArea.getText().equals("")) {
					ErrorWindow error = new ErrorWindow("Ensure you have selecting something to analyse, as well as at least one option.");
				} else {
					letters = lettersRadio.isSelected();
					numbers = numbersRadio.isSelected();
					specials = specialsRadio.isSelected();
					
					String input = textArea.getText();
					
					if (!numbers)
						input = input.replaceAll("[0-9]", "");
					
					if(!specials && letters && numbers) 
						input = input.replaceAll("[^A-Za-z0-9]", "");
					
					if (!specials && letters && !numbers)
						input = input.replaceAll("[^A-Za-z]", " ");
					
					if (!specials && !letters && numbers)
						input = input.replaceAll("[^0-9]", "");
					
					if (!letters)
						input = input.replaceAll("[A-Za-z]", "");
					
					if(specials)
						input = input.replaceAll("[\n]", "");
					
					Options options = new Options(letters,numbers,specials);
					AnalysisHelper analyse = new AnalysisHelper();
					analyse.analyse(input.toLowerCase(), options);
				}
			}
		});
		
		analyseButton.setBounds(274, 353, 175, 70);
		frame.getContentPane().add(analyseButton);
	}
}

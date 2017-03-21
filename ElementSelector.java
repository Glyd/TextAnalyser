package Assignment.TextAnalyser;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class ElementSelector {

	private JFrame frame;
	String url;
	private JTextField urlTextField;

	/**
	 * Create the application.
	 */
	public ElementSelector() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(33, 150, 243));
		frame.setBounds(100, 100, 207, 364);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblEnterUrlTo = new JLabel("Enter URL to analyse");
		lblEnterUrlTo.setForeground(UIManager.getColor("Button.highlight"));
		lblEnterUrlTo.setBounds(6, 6, 195, 16);
		frame.getContentPane().add(lblEnterUrlTo);
		
		urlTextField = new JTextField();
		urlTextField.setBounds(6, 22, 195, 26);
		frame.getContentPane().add(urlTextField);
		urlTextField.setColumns(10);
		
		JLabel labelChoose = new JLabel("Choose elements to analyse.");
		labelChoose.setForeground(UIManager.getColor("Button.highlight"));
		labelChoose.setBounds(6, 58, 195, 16);
		frame.getContentPane().add(labelChoose);
		
		JCheckBox checkHeaders = new JCheckBox("Headers");
		checkHeaders.setForeground(UIManager.getColor("Button.highlight"));
		checkHeaders.setBounds(27, 86, 128, 23);
		frame.getContentPane().add(checkHeaders);
		
		JCheckBox checkParagraphs = new JCheckBox("Paragraphs");
		checkParagraphs.setForeground(UIManager.getColor("Button.highlight"));
		checkParagraphs.setBounds(27, 121, 128, 23);
		frame.getContentPane().add(checkParagraphs);
		
		JCheckBox checkLinks = new JCheckBox("Links");
		checkLinks.setForeground(UIManager.getColor("Button.highlight"));
		checkLinks.setBounds(27, 156, 128, 23);
		frame.getContentPane().add(checkLinks);
		
		JCheckBox checkLists = new JCheckBox("List Items");
		checkLists.setForeground(UIManager.getColor("Button.highlight"));
		checkLists.setBounds(27, 191, 128, 23);
		frame.getContentPane().add(checkLists);
		
		JCheckBox checkAll = new JCheckBox("Everything");
		checkAll.setForeground(UIManager.getColor("Button.highlight"));
		checkAll.setBounds(27, 226, 128, 23);
		frame.getContentPane().add(checkAll);
		
		JButton btnAnalyse = new JButton("Analyse");
		btnAnalyse.setBounds(27, 273, 128, 29);
		frame.getContentPane().add(btnAnalyse);
		
		btnAnalyse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				url = urlTextField.getText();
				Elements elements;
		
				if (!checkAll.isSelected()) {
					elements = new Elements(
						checkHeaders.isSelected(),
						checkParagraphs.isSelected(),
						checkLinks.isSelected(),
						checkLists.isSelected());
				} else {
					elements = new Elements();
				}
				AnalysisHelper analyse = new AnalysisHelper();
				try {
					if (elements.hasChosen()){
						analyse.analyseURL(url, elements);
						frame.dispose();
					}
					else {
						ErrorWindow error = new ErrorWindow("You haven't chosen anything to analyse.");
					}
				} catch (java.lang.IllegalArgumentException f) {
					ErrorWindow error = new ErrorWindow("You must supply a valid url.");
				} catch (java.lang.ArrayIndexOutOfBoundsException g) {
					ErrorWindow error = new ErrorWindow("There was too much text.");
				}
			}
		});
		
		frame.setVisible(true);
	}
}
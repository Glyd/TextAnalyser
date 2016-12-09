package Assignment.TextAnalyser;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextArea;

public class ResultsScreen {

	private JFrame frame;

	/**
	 * Create the application.
	 */
	public ResultsScreen(String text) {
		initialize(text);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String text) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		JTextArea textArea = new JTextArea();
		frame.getContentPane().add(textArea, "2, 2, fill, fill");
		
		textArea.setText(text);
		
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		frame.setVisible(true);
	}

}

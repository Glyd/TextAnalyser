package Assignment.TextAnalyser;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ErrorWindow {

	private JFrame frame;

	/**
	 * Create the application.
	 */
	public ErrorWindow(String message) {
		initialize(message);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String message) {
		frame = new JFrame();
		frame.setBounds(350, 225, 506, 128);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		frame.getContentPane().add(btnOk, BorderLayout.SOUTH);
		
		JLabel lblError = new JLabel("Error!");
		lblError.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblError, BorderLayout.NORTH);
		
		JLabel lblSomethingWentWrong = new JLabel("Ensure you have selecting something to analyse, as well as at least one option.");
		lblSomethingWentWrong.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		
		if(message != null) {
			lblSomethingWentWrong.setText(message);
		}
		
		lblSomethingWentWrong.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblSomethingWentWrong, BorderLayout.CENTER);
		
		frame.setVisible(true);
	}

}

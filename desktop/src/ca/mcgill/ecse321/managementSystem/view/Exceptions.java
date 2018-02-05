package ca.mcgill.ecse321.managementSystem.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Exceptions extends JFrame {

	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public Exceptions(String ErrorMessage) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100,445, 269);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel label = new JLabel(ErrorMessage);
		
		label.setBounds(56, 67, 297, 36);
		contentPane.add(label);
		
		JButton btnContinue = new JButton("Continue");
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnContinue.setBounds(165, 156, 93, 23);
		contentPane.add(btnContinue);
	}
}

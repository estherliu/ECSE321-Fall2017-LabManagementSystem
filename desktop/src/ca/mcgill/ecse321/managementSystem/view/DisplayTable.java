package ca.mcgill.ecse321.managementSystem.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class DisplayTable extends JFrame {
	
	private JPanel contentPane;
	
    public DisplayTable(String content){
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JTextArea textArea=new JTextArea(content);
		
		textArea.setBounds(100, 100, 500, 500);
		contentPane.add(textArea);
		
		JButton btnContinue = new JButton("Continue");
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {			
					Displaying dis;
					try {
					    dispose();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
			}
		});
		btnContinue.setBounds(136, 64, 93, 23);
		contentPane.add(btnContinue);
	    	
    	
    	
    	
    }
}

package ca.mcgill.ecse321.managementSystem.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse321.managementSystem.application.ManagementSystem;

import javax.swing.JEditorPane;
import java.awt.Font;

public class Editing extends JFrame {
	
	private JPanel contentPane;
	
    public Editing(String name){
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel textArea=new JLabel("input the progress:");		
		textArea.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		textArea.setBounds(100, 87, 499, 33);
		contentPane.add(textArea);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setBounds(100, 131, 540, 301);
		contentPane.add(editorPane);
		
		JButton btnConfirm = new JButton("confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					addprogress(name, editorPane.getText());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dispose();
			}
		});
		btnConfirm.setBounds(335, 476, 93, 23);
		contentPane.add(btnConfirm);
		
		
			    	
    	
    	
    	
    }
    
    private void addprogress(String name, String progress) throws Exception {
		ManagementSystem.tcpconnection.exec("addprogress_" + progress + "_" + name);
		Exceptions frame1 = new Exceptions("create success! name:" + name + " progress:" + progress);
		frame1.setVisible(true);

	}
	
	
}

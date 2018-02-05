package ca.mcgill.ecse321.managementSystem.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse321.managementSystem.application.ManagementSystem;


import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.List;

import javax.swing.JFormattedTextField;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.JEditorPane;

public class MainPanel extends JFrame{

	private JPanel contentPane;

	// name
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	private JCheckBox chckbxResearcher;
	private JCheckBox chckbxResearchAssistant;
	private JFormattedTextField formattedTextField;
	private JFormattedTextField formattedTextField_1;
	private JFormattedTextField formattedTextField_2;

	// confirm
	private JButton btnConfirm;
	private JButton btnConfirm_1;
	private JButton btnConfirm_2;
	private JButton btnConfirm_3;
	private JButton btnUpdateStuff;
	private JButton btnUpdateEquipment;
	private JButton btnUpdateSupply;
	private JButton btnUpdateExpense;
	
	 //update progress
    private JTextField textField_4;
    private JEditorPane editorPane;
    private JButton button;
    private JButton btnGetStaffProgress;
   

	// logout
	private JButton btnLogOut;


	/**
	 * Create the frame.
	 * 
	 * @throws Exception
	 */
	public MainPanel() throws Exception {
		setting();
		listen();

	}

	private void listen() throws Exception {
		btnLogOut.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					logout();
				} catch (Exception e1) {
				}
			}
		});

		
		
		 //update progress
		 button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						addprogress(textField_4.getText(),editorPane.getText());
						   
					} catch (Exception e1) {
						Exceptions frame = new Exceptions("add progress falis.");
						frame.setVisible(true);
					}
				}

				
			});
		 
		 //check progress
		 btnGetStaffProgress.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {											
						displayProgressTable();						   
					} catch (Exception e1) {
						Exceptions frame = new Exceptions("check progress falis.");
						frame.setVisible(true);
					}
				}
										
								
			});
		 
		 
		 
			
		// create staff
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String name = textField.getText();
					if (chckbxResearcher.isSelected() && chckbxResearchAssistant.isSelected()) {
						Exceptions frame = new Exceptions("You cannot have 2 roles.");
						frame.setVisible(true);
					} else if (chckbxResearcher.isSelected()) {
						addStaff(name, "researcher");
					} else if (chckbxResearchAssistant.isSelected()) {
						addStaff(name, "researchAssistant");
					} else {
						Exceptions frame = new Exceptions("You must select one role.");
						frame.setVisible(true);
					}
				} catch (Exception e1) {
				}
			}
		});

		btnUpdateStuff.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					displayStaffTable();
				} catch (Exception e1) {
				}
			}
		});

		// create equipment
		btnConfirm_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String name = textField_1.getText();
					int quantity = ((Number) formattedTextField.getValue()).intValue();
					addEquipment(name, quantity);
				} catch (Exception e1) {
					System.out.print(e1);
				}
			}
		});

		// create supply
		btnConfirm_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String name = textField_2.getText();
					int quantity = ((Number) formattedTextField_1.getValue()).intValue();
					System.out.print(quantity);
					addSupplies(name, quantity);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		// get equipment table
		btnUpdateEquipment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					displayEquipmentTable();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		// get supply table
		btnUpdateSupply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					displaySupplyTable();

				} catch (Exception e1) {

				}
			}

			

		});

		// create expense
		btnConfirm_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String name = textField_3.getText();
					double amount = ((Number) formattedTextField_2.getValue()).doubleValue();
					System.out.print(amount);
					addExpense(name, amount);
					

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		// get expense table
		btnUpdateExpense.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					displayExpenseTable();
				} catch (Exception e1) {
					System.out.print(e1);
				}
			}
		});
	}
	
	
	private void displayProgressTable() throws Exception {
		String staffname=textField_4.getText();
		String[] list=ManagementSystem.tcpconnection.exec("checkprogress"+"_"+staffname);
		
		 String content=staffname+"\n";
		for(String progress: list){
	    	 String detail=progress;
	    	 content+=detail+"\n";
				System.out.print(content);
	    	 
	    	}
		
			DisplayTable table=new DisplayTable(content);
		    table.setVisible(true);
		 	 contentPane.repaint();						
		
	    }	
	
	
	private void displayExpenseTable() throws Exception {
		System.out.print("111");
		String[] expenselist = ManagementSystem.tcpconnection.exec("checkexpenses");
		String content = "account balance:" + ManagementSystem.tcpconnection.exec("getbalance")[0] + "\n";
		for (int i = 0; i < expenselist.length; i++) {
			if (i%2==0) {
				content+=expenselist[i]+" ";
			}else {
				content+=expenselist[i]+"\n";
			}
		}
		DisplayTable table = new DisplayTable(content);
		table.setVisible(true);
		contentPane.repaint();

	}
	
	private void displaySupplyTable() throws Exception{
		System.out.print("nooo");
		String[] supplylist = ManagementSystem.tcpconnection.exec("checksupplies");
		String content = "";
		for (int i = 0; i < supplylist.length; i++) {
			if (i%2==0) {
				content+=supplylist[i]+" ";
			}else {
				content+=supplylist[i]+"\n";
			}
		}

		DisplayTable table = new DisplayTable(content);
		table.setVisible(true);
		contentPane.repaint();

	}

	public void displayStaffTable() throws Exception{

		String[] stafflist = ManagementSystem.tcpconnection.exec("checkstaffs");
		String content = "";
		for (int i = 0; i < stafflist.length; i++) {
			if (i%2==0) {
				content+=stafflist[i]+" ";
			}else {
				content+=stafflist[i]+"\n";
			}
		}
		DisplayTable table = new DisplayTable(content);
		table.setVisible(true);
		contentPane.repaint();
	}

	private void displayEquipmentTable() throws Exception{

		String[] equipmentlist = ManagementSystem.tcpconnection.exec("checkequipment");
		String content = "";
		for (int i = 0; i < equipmentlist.length; i++) {
			if (i%2==0) {
				content+=equipmentlist[i]+" : ";
			}else {
				content+=equipmentlist[i]+"\n";
			}
		}

		DisplayTable table = new DisplayTable(content);
		table.setVisible(true);
		contentPane.repaint();

	}
	
	public void addExpense(String reason, double amount) throws Exception{
		ManagementSystem.tcpconnection.exec("addexpense_"+reason+"_"+amount);
		Exceptions frame1 = new Exceptions("add success! reason:" + reason + " amount:" + amount);
		frame1.setVisible(true);
	}

	public void addSupplies(String name, int quantity) throws Exception {
		ManagementSystem.tcpconnection.exec("addsupplies_" + name + "_" + quantity);
		Exceptions frame1 = new Exceptions("add success! name:" + name + " quantity:" + quantity);
		frame1.setVisible(true);
	}

	public void addEquipment(String name, int quantity) throws Exception {
		ManagementSystem.tcpconnection.exec("addequipments_" + name + "_" + quantity);
		Exceptions frame1 = new Exceptions("add success! name:" + name + " quantity:" + quantity);
		frame1.setVisible(true);
	}

	public void addStaff(String name, String role) throws Exception {
		ManagementSystem.tcpconnection.exec("addstaff_" + name + "_" + role);
		Exceptions frame1 = new Exceptions("create success! name:" + name + " role:" + role);
		frame1.setVisible(true);
	}
	private void addprogress(String name,String progress) throws Exception {
		ManagementSystem.tcpconnection.exec("addprogress_" + progress + "_" + name);
		Exceptions frame1 = new Exceptions("create success! name:" + name + " progress:" + progress);
		frame1.setVisible(true);
		
	}

	public void logout() throws Exception {

		ManagementSystem.tcpconnection.exec("logout");
		Login frame = new Login();
		frame.setVisible(true);
		dispose();
	}

	public void setting() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 846, 603);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		    chckbxResearcher = new JCheckBox("researcher");
	        chckbxResearcher.setBounds(99, 117, 139, 29);
	        contentPane.add(chckbxResearcher);
	        
	        chckbxResearchAssistant = new JCheckBox("research assistant");
	        chckbxResearchAssistant.setBounds(99, 154, 166, 29);
	        contentPane.add(chckbxResearchAssistant);
	        
	        
        btnUpdateStuff = new JButton("get stuff list");
        btnUpdateStuff.setBounds(613, 343, 161, 29);
        contentPane.add(btnUpdateStuff);
        
        btnUpdateEquipment = new JButton("get equipment list");
        btnUpdateEquipment.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnUpdateEquipment.setBounds(613, 388, 161, 29);
        contentPane.add(btnUpdateEquipment);
        
        btnUpdateSupply = new JButton("get supply list");
        btnUpdateSupply.setBounds(613, 433, 162, 29);
        contentPane.add(btnUpdateSupply);
        
        btnUpdateExpense = new JButton("get expense list");
        btnUpdateExpense.setBounds(612, 478, 162, 29);
        contentPane.add(btnUpdateExpense);
        
        
        textField = new JTextField();
		textField.setBounds(39, 79, 166, 26);
		textField.setColumns(10);
		contentPane.add(textField);
        
		
		JLabel lblAddStuff = new JLabel("add stuff ");
		lblAddStuff.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAddStuff.setBounds(39, 16, 84, 20);
		contentPane.add(lblAddStuff);
		
		JLabel lblName = new JLabel("name");
		lblName.setBounds(28, 52, 69, 20);
		contentPane.add(lblName);
		
		btnLogOut = new JButton("log out");
		btnLogOut.setBounds(15, 502, 115, 29);
		contentPane.add(btnLogOut);
		
		JLabel lblNewLabel = new JLabel("role type");
		lblNewLabel.setBounds(28, 121, 69, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblAddEquipment = new JLabel("add equipment");
		lblAddEquipment.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAddEquipment.setBounds(300, 12, 149, 29);
		contentPane.add(lblAddEquipment);
		
		JLabel label = new JLabel("name");
		label.setBounds(286, 52, 69, 20);
		contentPane.add(label);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(300, 79, 166, 26);
		contentPane.add(textField_1);
		
		JLabel lblQuantity = new JLabel("quantity");
		lblQuantity.setBounds(286, 117, 69, 20);
		contentPane.add(lblQuantity);
		
	    formattedTextField = new JFormattedTextField(NumberFormat.getIntegerInstance());
		formattedTextField.setBounds(300, 142, 79, 26);
		contentPane.add(formattedTextField);
		
		JLabel lblAddSupply = new JLabel("add supply");
		lblAddSupply.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAddSupply.setBounds(57, 254, 149, 29);
		contentPane.add(lblAddSupply);
		
		JLabel label_1 = new JLabel("name");
		label_1.setBounds(78, 296, 69, 20);
		contentPane.add(label_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(60, 332, 166, 26);
		contentPane.add(textField_2);
		
		JLabel label_2 = new JLabel("quantity");
		label_2.setBounds(54, 374, 69, 20);
		contentPane.add(label_2);
		
		formattedTextField_1 = new JFormattedTextField(NumberFormat.getIntegerInstance());
		formattedTextField_1.setBounds(60, 405, 79, 26);
		contentPane.add(formattedTextField_1);
		
		JLabel lblAddExpense = new JLabel("add expense");
		lblAddExpense.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAddExpense.setBounds(300, 246, 129, 44);
		contentPane.add(lblAddExpense);
		
		btnConfirm = new JButton("confirm");
		btnConfirm.setBounds(30, 181, 100, 29);
		contentPane.add(btnConfirm);
		
		btnConfirm_1 = new JButton("confirm");
		btnConfirm_1.setBounds(329, 181, 115, 29);
		contentPane.add(btnConfirm_1);
		
		btnConfirm_2 = new JButton("confirm");
		btnConfirm_2.setBounds(61, 447, 115, 29);
		contentPane.add(btnConfirm_2);
		
		JLabel lblReasonName = new JLabel("reason name");
		lblReasonName.setBounds(329, 296, 100, 20);
		contentPane.add(lblReasonName);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(324, 332, 166, 26);
		contentPane.add(textField_3);
		
		JLabel lblAmount = new JLabel("amount");
		lblAmount.setBounds(318, 374, 69, 20);
		contentPane.add(lblAmount);
		
	    formattedTextField_2 = new JFormattedTextField(NumberFormat.getNumberInstance());
		formattedTextField_2.setBounds(336, 405, 79, 26);
		contentPane.add(formattedTextField_2);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(15, 236, 530, 26);
		contentPane.add(separator);
		
		btnConfirm_3 = new JButton("confirm");
		btnConfirm_3.setBounds(334, 447, 115, 29);
		contentPane.add(btnConfirm_3);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(553, 16, 10, 491);
		contentPane.add(separator_1);
		
		JLabel lblUpdateProgress = new JLabel("update progress");
		lblUpdateProgress.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUpdateProgress.setForeground(Color.BLACK);
		lblUpdateProgress.setBounds(630, 12, 144, 29);
		contentPane.add(lblUpdateProgress);
		
		JLabel lblStaffname = new JLabel("staff name");
		lblStaffname.setBounds(578, 52, 94, 20);
		contentPane.add(lblStaffname);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(608, 79, 166, 26);
		contentPane.add(textField_4);
		
		JLabel lblNewProgress = new JLabel("new progress");
		lblNewProgress.setBounds(578, 121, 115, 20);
		contentPane.add(lblNewProgress);
		
		editorPane = new JEditorPane();
		editorPane.setBounds(603, 154, 183, 98);
		contentPane.add(editorPane);
		
		button = new JButton("confirm");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button.setBounds(560, 256, 88, 29);
		contentPane.add(button);
		
		btnGetStaffProgress = new JButton("get staff progress");
		btnGetStaffProgress.setBounds(663, 256, 161, 29);
		contentPane.add(btnGetStaffProgress);
	}
}

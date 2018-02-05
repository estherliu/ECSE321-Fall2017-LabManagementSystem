package ca.mcgill.ecse321.managementSystem.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse321.managementSystem.application.ManagementSystem;

public class Displaying extends JFrame {

	private JPanel contentPane;
	private JCheckBox chckbxResearcher;
	private JCheckBox chckbxResearchAssistant;
	private JButton btnConfirm;
	private JTextField nameText;
	private JScrollPane scrollPane;
	private JLabel title;
	private JLabel optionLabel;
	private JButton BtnRemove;
	private JTextField formattedTextField;
	private String[] staffcontent;
	private String[] equipcontent;
	private String[] supplycontent;
	private String[] expensecontent;
	private String currentName;
	private String distype;
	private JLabel lblCurrentBudget;

	public Displaying(String type) throws Exception {

		this.distype = type;
		generalSetting();

		if (type == "staff") {

			chckbxResearcher = new JCheckBox("researcher");
			chckbxResearcher.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
			chckbxResearcher.setBounds(106, 114, 165, 29);
			contentPane.add(chckbxResearcher);

			chckbxResearchAssistant = new JCheckBox("research assistant");
			chckbxResearchAssistant.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
			chckbxResearchAssistant.setBounds(105, 156, 166, 29);
			contentPane.add(chckbxResearchAssistant);

			JButton btnAddProgress = new JButton("add progress\r\n");
			btnAddProgress.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
			btnAddProgress.setBounds(557, 461, 130, 23);
			btnAddProgress.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						if (currentName != null) {
							Editing addpro = new Editing(currentName);
							addpro.setVisible(true);
						}
					} catch (Exception e1) {
					}
				}
			});

			contentPane.add(btnAddProgress);

			// add staff
			btnConfirm.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						String name = nameText.getText();
						if (name.equals("")) {
							Exceptions frame = new Exceptions("The name cannot be empty.");
							frame.setVisible(true);
						} else {
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
							updateStaff();
						}
					} catch (Exception e1) {
					}
				}
			});

			updateStaff();

		} else if (type == "equip") {
			title.setText("ADD EQUIPMENT");
			optionLabel.setText("quantity");

			formattedTextField = new JTextField();
			formattedTextField.setBounds(106, 114, 165, 29);
			contentPane.add(formattedTextField);

			// add equipment
			btnConfirm.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					try {
						if (isInteger(formattedTextField.getText())) {
							String name = nameText.getText();
							if (name.equals("")) {
								Exceptions frame = new Exceptions("The name cannot be empty.");
								frame.setVisible(true);
							} else {
								String quantityS = formattedTextField.getText();
								int quantity = Integer.parseInt(quantityS);
								addEquipment(name, quantity);
								updateEquip();
							}
						} else {
							Exceptions frame1 = new Exceptions("Invalid quantity input.");
							frame1.setVisible(true);
						}
					} catch (Exception e1) {
						System.out.print(e1);
					}

				}

			});

			// check equipment list
			updateEquip();

		} else if (type == "supply") {
			title.setText("ADD SUPPLY");
			optionLabel.setText("quantity");

			formattedTextField = new JTextField();
			formattedTextField.setBounds(106, 114, 165, 29);
			contentPane.add(formattedTextField);

			// add supplies
			btnConfirm.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					try {
						if (isInteger(formattedTextField.getText())) {
							String name = nameText.getText();
							if (name.equals("")) {
								Exceptions frame = new Exceptions("The name cannot be empty.");
								frame.setVisible(true);
							} else {
								String quantityS = formattedTextField.getText();
								int quantity = Integer.parseInt(quantityS);
								addSupplies(name, quantity);
								updateSupply();
							}
						} else {
							Exceptions frame1 = new Exceptions("Invalid quantity input.");
							frame1.setVisible(true);
						}
					} catch (Exception e1) {
						System.out.print(e1);
					}

				}
			});

			// check supply list
			updateSupply();

		} else if (type == "expense") {
			title.setText("ADD EXPENSE");
			optionLabel.setText("amount");

			formattedTextField = new JTextField();
			formattedTextField.setBounds(106, 114, 165, 29);
			contentPane.add(formattedTextField);

			BtnRemove.setVisible(false);
			// add supplies
			btnConfirm.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					try {
						String name = nameText.getText();
						if (name.equals("")) {
							Exceptions frame = new Exceptions("The name cannot be empty.");
							frame.setVisible(true);
						} else {
							String amountS = formattedTextField.getText();
							double amount = Double.parseDouble(amountS);
							addExpense(name, amount);
							updateExpense();
						}
					} catch (Exception e1) {
						Exceptions frame = new Exceptions("Invalid amount.");
						frame.setVisible(true);
					}

				}
			});
			
			//String h=Arrays.toString(ManagementSystem.tcpconnection.exec("getbalance"));
			lblCurrentBudget = new JLabel("current budget: " );
			lblCurrentBudget.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
			lblCurrentBudget.setBounds(364, 510, 243, 27);
			contentPane.add(lblCurrentBudget);	
			
			// check expense list
		    updateExpense();
		}

		JLabel pic2 = new JLabel(new ImageIcon("image/im2.png"));
		pic2.setBounds(0, 0, 830, 564);
		contentPane.add(pic2);

	}

	private void generalSetting() {
		// TODO Auto-generated method stub
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 846, 603);
		contentPane = new JPanel();
		contentPane.setForeground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(230, 245, 255));

		JLabel pic = new JLabel(new ImageIcon("image/im3.png"));
		pic.setBounds(-15, 297, 271, 243);
		contentPane.add(pic);

		// need to change
		BtnRemove = new JButton("remove");
		BtnRemove.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		BtnRemove.setBounds(433, 461, 91, 23);
		BtnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (distype == "staff") {
						removeStaff(currentName);
						updateStaff();
					} else if (distype == "equip") {
						removeEquipment(currentName);
						updateEquip();
					} else if (distype == "supply") {
						removeSupply(currentName);
						updateSupply();
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(BtnRemove);

		// need to change
		title = new JLabel("ADD STAFF\r\n");
		title.setBackground(Color.GRAY);
		title.setFont(new Font("Tahoma", Font.PLAIN, 20));
		title.setBounds(86, 10, 170, 27);
		contentPane.add(title);

		// need to change
		optionLabel = new JLabel("role ");
		optionLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		optionLabel.setBounds(27, 118, 69, 20);
		contentPane.add(optionLabel);

		JLabel lblName = new JLabel("name");
		lblName.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		lblName.setBounds(27, 67, 69, 20);
		contentPane.add(lblName);

		nameText = new JTextField();
		nameText.setBounds(106, 66, 165, 26);
		nameText.setColumns(10);
		contentPane.add(nameText);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(320, 37, 425, 404);
		contentPane.add(scrollPane);

		btnConfirm = new JButton("confirm");
		btnConfirm.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		btnConfirm.setBounds(86, 221, 131, 29);
		contentPane.add(btnConfirm);

		JButton btnLogout = new JButton("log out \r\n");
		btnLogout.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnLogout.setBounds(20, 522, 91, 23);
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Login frame = new Login();
					frame.setVisible(true);
					dispose();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(btnLogout);

		JButton btnBack = new JButton("back");
		btnBack.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnBack.setBounds(125, 522, 61, 23);
		btnBack.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					back();
				} catch (Exception e1) {
					System.out.print(e1);
				}

			}
		});
		contentPane.add(btnBack);

		JButton btnUpdate = new JButton("update ");
		btnUpdate.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnUpdate.setBounds(715, 522, 93, 23);
		btnUpdate.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					switch (distype) {
					case "staff":
						redraw(staffcontent, distype);
						break;
					case "equip":
						redraw(equipcontent, distype);
						break;
					case "supply":
						redraw(supplycontent, distype);
						break;
					case "expense":
						redraw(expensecontent, distype);
						break;
					}
				} catch (Exception e1) {
					System.out.print(e1);
				}

			}
		});
		contentPane.add(btnUpdate);
	}

	private void redraw(String[] content, String type) {
		if (content.length == 0) {
			String[] nothing = { "No content" };
			JList<?> list = new JList(nothing);
			scrollPane.setViewportView(list);
		} else {
			JList<?> list = new JList<Object>(content);
			list.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					JList<?> list = (JList<?>) e.getSource();
					if (e.getClickCount() == 1) {
						// one-click detected
						currentName = findname(content[list.locationToIndex(e.getPoint())]);
					}

					if (e.getClickCount() == 2) {
						// Double-click detected
						int index = list.locationToIndex(e.getPoint());
						if (type == "staff") {
							try {
								@SuppressWarnings("unused")
								String[] progresslist = ManagementSystem.tcpconnection
										.exec("checkprogress" + "_" + findname(content[index]));
								String progresses = "";
								for (String progress : progresslist) {
									String detail = progress;
									progresses += detail + "\n";
								}
								DisplayTable table = new DisplayTable(progresses);
								table.setVisible(true);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						}
					}
				}
			});

			scrollPane.setViewportView(list);
		}
	}

	private void back() throws IOException {
		ChooseDisplay frame = new ChooseDisplay();
		frame.setVisible(true);
		this.dispose();
	}

	public void addExpense(String reason, double amount) throws Exception {
		ManagementSystem.tcpconnection.exec("addexpense_" + reason + "_" + amount);
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
		String[] a=ManagementSystem.tcpconnection.exec("addstaff_" + name + "_" + role);
		System.out.print(Arrays.toString(a));
		String a1=Arrays.toString(a);
		if (a[0].equals("true")){
			Exceptions frame1 = new Exceptions("create success! name:" + name + " role:" + role);
			frame1.setVisible(true);
		}
		else{
			Exceptions frame1 = new Exceptions("create fails.");
			frame1.setVisible(true);
		}
		
	}

	public void removeStaff(String name) throws Exception {
		ManagementSystem.tcpconnection.exec("removestaff_" + name);
		Exceptions frame1 = new Exceptions("remove success! name:" + name);
		frame1.setVisible(true);
	}

	public void removeEquipment(String name) throws Exception {
		ManagementSystem.tcpconnection.exec("removeequipment_" + name);
		Exceptions frame1 = new Exceptions("remove success! name:" + name);
		frame1.setVisible(true);
	}

	public void removeSupply(String name) throws Exception {
		ManagementSystem.tcpconnection.exec("removesupply_" + name);
		Exceptions frame1 = new Exceptions("remove success! name:" + name);
		frame1.setVisible(true);
	}

	private static String findname(String record) {
		String[] re = record.split(":");
		return re[0];

	}

	private void updateEquip() throws Exception {
		String[] equiplist = ManagementSystem.tcpconnection.exec("checkequipment");
		equipcontent = new String[100];
		for (int i = 0; i < equiplist.length; i++) {
			if (i % 2 == 0) {
				equipcontent[i / 2] = "";
				equipcontent[i / 2] += equiplist[i] + ": ";
			} else {
				equipcontent[i / 2] += equiplist[i];
			}
		}
		redraw(equipcontent, "equip");
	}

	private void updateStaff() throws Exception {
		String[] stafflist = ManagementSystem.tcpconnection.exec("checkstaffs");
		staffcontent = new String[100];
		for (int i = 0; i < stafflist.length; i++) {
			if (i % 2 == 0) {
				staffcontent[i / 2] = "";
				staffcontent[i / 2] += stafflist[i] + ": ";
			} else {
				staffcontent[i / 2] += stafflist[i];
			}
		}
		redraw(staffcontent, "staff");
	}

	private void updateSupply() throws Exception {
		String[] supplylist = ManagementSystem.tcpconnection.exec("checksupplies");
		supplycontent = new String[100];
		for (int i = 0; i < supplylist.length; i++) {
			if (i % 2 == 0) {
				supplycontent[i / 2] = "";
				supplycontent[i / 2] += supplylist[i] + ": ";
			} else {
				supplycontent[i / 2] += supplylist[i];
			}
		}
		redraw(supplycontent, "supply");
	}

	private void updateExpense() throws Exception {
		String[] expenselist = ManagementSystem.tcpconnection.exec("checkexpenses");
		expensecontent = new String[100];
		for (int i = 0; i < expenselist.length; i++) {
			if (i % 2 == 0) {
				expensecontent[i / 2] = "";
				expensecontent[i / 2] += expenselist[i] + ": ";
			} else {
				expensecontent[i / 2] += expenselist[i];
			}
		}
		redraw(expensecontent, "expense");
		String[] h1=ManagementSystem.tcpconnection.exec("getbalance");
		System.out.print(h1[0]);
		lblCurrentBudget.setText("current budget:"+ h1[0]);
	}

	public static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		} catch (NullPointerException e) {
			return false;
		}
		// only got here if we didn't return false
		return true;
	}
}

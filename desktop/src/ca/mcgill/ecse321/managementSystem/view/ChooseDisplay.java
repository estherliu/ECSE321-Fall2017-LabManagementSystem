package ca.mcgill.ecse321.managementSystem.view;

import java.awt.Color;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChooseDisplay extends JFrame {

	private JPanel contentPane;

	public ChooseDisplay() throws IOException {

		setting();

		JButton staff = new JButton("Staff");
		staff.setBackground(new Color(245, 255, 250));
		staff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					chooseDis("staff");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		staff.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 20));
		staff.setBounds(234, 100, 153, 83);
		contentPane.add(staff);

		JButton equip = new JButton("Equipment\r\n");
		equip.setBackground(new Color(245, 255, 250));
		equip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					chooseDis("equip");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		equip.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 20));
		equip.setBounds(496, 100, 153, 83);
		contentPane.add(equip);

		JButton supply = new JButton("Supply");
		supply.setBackground(new Color(245, 255, 250));
		supply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					chooseDis("supply");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		supply.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 20));
		supply.setBounds(234, 252, 153, 83);
		contentPane.add(supply);

		JButton expense = new JButton("Expense");
		expense.setBackground(new Color(245, 255, 250));
		expense.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					chooseDis("expense");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		expense.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 20));
		expense.setBounds(496, 252, 153, 83);
		contentPane.add(expense);

		JButton btnLogOut = new JButton("log out \r\n");
		btnLogOut.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnLogOut.setBounds(10, 518, 93, 23);
		btnLogOut.addActionListener(new ActionListener() {
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
		contentPane.add(btnLogOut);

		JLabel pic2 = new JLabel(new ImageIcon("image/im2.png"));
		pic2.setBounds(0, 0, 830, 564);
		contentPane.add(pic2);
	}

	private void chooseDis(String type) throws Exception {
		Displaying dis = new Displaying(type);
		dis.setVisible(true);
		this.dispose();
	}

	private void setting() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 846, 603);
		contentPane = new JPanel();
		contentPane.setForeground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(230, 245, 255));

		JLabel lblWhatDoYou = new JLabel("Choose the one you want to check");
		lblWhatDoYou.setForeground(new Color(0, 0, 128));
		lblWhatDoYou.setFont(new Font("Tempus Sans ITC", Font.BOLD, 35));
		lblWhatDoYou.setBounds(205, 392, 636, 111);
		contentPane.add(lblWhatDoYou);

		JLabel pic = new JLabel(new ImageIcon("image/im3.png"));
		pic.setBounds(-15, 297, 271, 243);
		contentPane.add(pic);
	}

}

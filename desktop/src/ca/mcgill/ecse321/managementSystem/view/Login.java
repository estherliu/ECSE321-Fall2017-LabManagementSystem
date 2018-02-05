package ca.mcgill.ecse321.managementSystem.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse321.managementSystem.application.ManagementSystem;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField loginName;
	private JPasswordField loginPassword;
	private JTextField regisName;
	private JTextField regisPassword;
	private Image img;

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 */
	public Login() throws IOException {

		setting();

		// useful components
		loginName = new JTextField();
		loginName.setBounds(256, 243, 146, 26);
		contentPane.add(loginName);
		loginName.setColumns(10);

		loginPassword = new JPasswordField();
		loginPassword.setBounds(256, 288, 146, 26);
		contentPane.add(loginPassword);

		JButton btnLogin = new JButton("login");
		btnLogin.setFont(new Font("Trebuchet MS", Font.BOLD, 23));
		btnLogin.setBounds(273, 373, 129, 37);
		contentPane.add(btnLogin);

		regisName = new JTextField();
		regisName.setColumns(10);
		regisName.setBounds(581, 243, 146, 26);
		contentPane.add(regisName);

		regisPassword = new JTextField();
		regisPassword.setColumns(10);
		regisPassword.setBounds(581, 288, 146, 26);
		contentPane.add(regisPassword);

		JButton btnRegister = new JButton("register");
		btnRegister.setFont(new Font("Trebuchet MS", Font.BOLD, 23));
		btnRegister.setBounds(598, 373, 129, 37);
		contentPane.add(btnRegister);


		JLabel pic2 = new JLabel(new ImageIcon("image/im2.png"));
		pic2.setBounds(0, 0, 830, 564);
		contentPane.add(pic2);
		

		
		btnLogin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					login();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		btnRegister.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					register();
				} catch (Exception e1) {

				}
			}
		});

	}

	public void register() throws Exception {
		String username = regisName.getText();
		String password = regisPassword.getText();
		String[] result = ManagementSystem.tcpconnection.exec("register_" + username + "_" + password);
		if (result[0].equals("true")) {
			System.out.println("register success");
			Exceptions frame1 = new Exceptions("register success! name:" + username + " password:" + password);
			frame1.setVisible(true);
		} else {
			System.out.println("register failed");
			Exceptions frame1 = new Exceptions("register fails, username exists.");
			frame1.setVisible(true);
		}

	}

	public void login() throws Exception {
		String username = loginName.getText();
		String password = new String(loginPassword.getPassword());
		String[] result = ManagementSystem.tcpconnection.exec("login_" + username + "_" + password);
		if (result[0].equals("true")) {
			ChooseDisplay frame = new ChooseDisplay();
			System.out.println("login success");
			frame.setVisible(true);
			this.dispose();
		} else {
			System.out.println("login fail");
			Exceptions frame1 = new Exceptions("login fails.");
			frame1.setVisible(true);
		}
	}

	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}

	public void setting() {
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
		
		// static components

		JLabel lblWelcomeTo = new JLabel("Welcome to University Lab Research System ");
		lblWelcomeTo.setBackground(new Color(255, 128, 128));
		lblWelcomeTo.setForeground(new Color(0, 0, 128));
		lblWelcomeTo.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		lblWelcomeTo.setBounds(40, 72, 767, 61);
		contentPane.add(lblWelcomeTo);

		JLabel lblUsername = new JLabel("username");
		lblUsername.setForeground(new Color(0, 0, 128));
		lblUsername.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblUsername.setBounds(157, 236, 99, 35);
		contentPane.add(lblUsername);

		JLabel lblPassword = new JLabel("password");
		lblPassword.setForeground(new Color(0, 0, 128));
		lblPassword.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblPassword.setBounds(157, 281, 86, 35);
		contentPane.add(lblPassword);

		JLabel label = new JLabel("username");
		label.setForeground(new Color(0, 0, 128));
		label.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		label.setBounds(480, 236, 99, 35);
		contentPane.add(label);

		JLabel label_1 = new JLabel("password");
		label_1.setForeground(new Color(0, 0, 128));
		label_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		label_1.setBounds(480, 281, 86, 35);
		contentPane.add(label_1);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(Color.GRAY);
		separator.setBackground(Color.LIGHT_GRAY);
		separator.setBounds(434, 161, 13, 342);
		contentPane.add(separator);

		JLabel lblIfYouHas = new JLabel("If you have an account:");
		lblIfYouHas.setForeground(new Color(0, 0, 128));
		lblIfYouHas.setFont(new Font("Trebuchet MS", Font.ITALIC, 20));
		lblIfYouHas.setBounds(109, 176, 230, 35);
		contentPane.add(lblIfYouHas);

		JLabel lblIfYouDont = new JLabel("If you don't have an account:");
		lblIfYouDont.setForeground(new Color(0, 0, 128));
		lblIfYouDont.setFont(new Font("Trebuchet MS", Font.ITALIC, 20));
		lblIfYouDont.setBounds(457, 176, 299, 35);
		contentPane.add(lblIfYouDont);

	}
}

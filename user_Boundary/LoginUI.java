package user_Boundary;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import faceID_Control.Camera;
import impl_Control.UserDAOImpl;
import servlet_Control.UserOperation;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

/** 
 * This class create the login interface for user
 * @author Zhenghui Wang
 * @version 1.1
 * 
 */
public class LoginUI {
	/**
	 * @param main reference of MainUI instance
	 * @param phoneNum phone number of user
	 * @param loginFrame Face recognition frame
	 */
	MainUI main;
	String phoneNum;  
	JFrame loginFrame = new JFrame("Login");

	// Login Main Panel:
	JPanel loginPanel1 = new JPanel();

	ImageIcon logoImg = new ImageIcon("src/images/logo.png");
	JLabel logoLabel = new JLabel(logoImg);
	JLabel titleLabel = new JLabel("Totoro Ramen Restaurant");

	// Embedded Panel:
	JPanel loginPanel2 = new JPanel();
	JTextField username = new JTextField("Username", 20);
	JTextField password = new JPasswordField("", 20);
	JLabel passwordLabel = new JLabel("Password");

	JLabel tipLabel = new JLabel("username(email, id or phone)");
	JButton submitBtn = new JButton("Log-in");
	JButton registerBtn = new JButton("Not have an account?");
	JButton faceBtn = new JButton("Face recognition");

	UserDAOImpl u = new UserDAOImpl();
	UserOperation oo = new UserOperation();
	
	public LoginUI(MainUI main) {

		this.main = main;

		loginFrame.setBounds(700, 160, 400, 500);
		loginFrame.getLayeredPane().setLayout(null);
		loginFrame.setResizable(false);
		loginFrame.setAlwaysOnTop(true);

		// For Main Panel:
		loginPanel1.setBounds(0, 0, 400, 500);
		loginPanel1.setLayout(null);
		loginPanel1.setFocusable(true);

		logoLabel.setBounds(30, 0, 64, 64);
		titleLabel.setForeground(Color.red);
		titleLabel.setBounds(100, 15, 250, 30);
		titleLabel.setFont(new Font("Times New Roman", 2, 23));

		loginPanel1.add(logoLabel);
		loginPanel1.add(titleLabel);

		// For embedded Panel:
		loginPanel2.setBorder(BorderFactory.createTitledBorder(null, "Login", TitledBorder.LEFT, TitledBorder.TOP,
				new Font("Times New Roman", 2, 23)));
		loginPanel2.setBounds(20, 60, 360, 300);
		loginPanel2.setLayout(null);

		username.setFont(new Font("Times New Roman", 2, 20));
		username.setBounds(60, 50, 250, 30);
		username.setForeground(Color.gray);
		username.setBackground(null);

		password.setFont(new Font("Times New Roman", 2, 20));
		password.setBounds(60, 100, 250, 30);
		password.setForeground(Color.gray);
		password.setBackground(null);
		password.setOpaque(false);

		passwordLabel.setFont(new Font("Times New Roman", 2, 20));
		passwordLabel.setBounds(62, 100, 250, 30);
		passwordLabel.setForeground(Color.gray);

		username.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if (username.getText().equals("")) {
					username.setText("Username");
				}
				username.setForeground(Color.gray);
				username.setBackground(null);
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if (username.getText().equals("Username")) {
					username.setText("");
				}
				username.setForeground(Color.black);
				username.setBackground(Color.white);
			}
		});

		password.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if (password.getText().equals("")) {
					passwordLabel.setVisible(true);
				}
				password.setOpaque(false);
				password.setForeground(Color.gray);
				password.setBackground(null);
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if (passwordLabel.getText().equals("Password")) {
					passwordLabel.setVisible(false);
				}
				password.setOpaque(true);
				password.setForeground(Color.black);
				password.setBackground(Color.white);
			}
		});

		loginPanel2.add(username);
		loginPanel2.add(password);
		loginPanel2.add(passwordLabel);

		tipLabel.setBounds(15, 130, 300, 20);
		tipLabel.setFont(new Font("Times New Roman", 2, 17));
		tipLabel.setForeground(Color.red);

		submitBtn.setBounds(60, 160, 250, 30);
		submitBtn.setFont(new Font("Times New Roman", 2, 20));
		submitBtn.setForeground(Color.white);
		submitBtn.setBackground(Color.DARK_GRAY);

		registerBtn.setBounds(60, 200, 250, 30);
		registerBtn.setFont(new Font("Times New Roman", 2, 20));
		registerBtn.setForeground(Color.white);
		registerBtn.setBackground(Color.DARK_GRAY);

		faceBtn.setBounds(60, 240, 250, 30);
		faceBtn.setFont(new Font("Times New Roman", 2, 20));
		faceBtn.setForeground(Color.white);
		faceBtn.setBackground(Color.DARK_GRAY);

		loginPanel2.add(submitBtn);
		loginPanel2.add(registerBtn);
		loginPanel2.add(faceBtn);
		loginPanel2.add(tipLabel);

		submitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String para = "";

				if (validation()) {
					// interface
					UserOperation uo = new UserOperation();

					try {
						para = uo.login(username.getText(), password.getText());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					System.out.println("para = " + para);
					System.out.println("username: "  + username.getText());
					
					if (para.equals("2")) {
						JOptionPane.showMessageDialog(loginFrame, "Wrong password!", "Message",
								JOptionPane.INFORMATION_MESSAGE);
					} else if (para.equals("3")) {
						JOptionPane.showMessageDialog(loginFrame, "Account doesn't exist!", "Message",
								JOptionPane.INFORMATION_MESSAGE);
					} else {

						main.id = para;
						main.setInvisible();
						UserDAOImpl dao = new UserDAOImpl();
						try {
							main.userInfo = dao.checkInformation(main.id);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						main.setPara(main.userInfo.getfName());
						main.frame.setEnabled(true);
						loginFrame.dispatchEvent(new WindowEvent(loginFrame, WindowEvent.WINDOW_CLOSING));
					} 
				}
			}
		});

		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginFrame.dispatchEvent(new WindowEvent(loginFrame, WindowEvent.WINDOW_CLOSING));
				main.frame.setEnabled(false);
				registerFunction();
			}
		});

		faceBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Camera camera = new Camera(main);
				loginFrame.dispatchEvent(new WindowEvent(loginFrame, WindowEvent.WINDOW_CLOSING));
				
			}
		});

		loginFrame.getLayeredPane().add(loginPanel1, 0);
		loginFrame.getLayeredPane().add(loginPanel2, 0);
		loginFrame.setVisible(true);

		loginFrame.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				System.out.println("oops!");
				main.frame.setEnabled(true);
			}
		});
	}

	/**
	 * This method validates the reasonable username and password, if true, pass; 
	 * @return A boolean value, if true, pass the validation;
	 */
	public boolean validation() {
		if (username.getText().equals("") || username.getText().equals("Username")) {
			username.requestFocus();
			tipLabel.setText("Error: Please enter your username!");
			tipLabel.setVisible(true);
			return false;
		} else if (password.getText().equals("") || password.getText().equals("Password")) {
			password.setText("");
			password.requestFocus();
			tipLabel.setText("Error: Please enter your password!");
			tipLabel.setVisible(true);
			return false;
		}

		tipLabel.setText("");
		return true;
	}

	/** 
	 * This method is used to pass MainUI to another interface
	 */
	public void registerFunction() {
		RegisterUI register = new RegisterUI(this.main);
	}

	/** 
	 * This method is used to get username
	 */
	public String getUsername() {
		return this.username.getText();
	}

}

package user_Boundary;

import javax.swing.*;
import javax.swing.border.TitledBorder;

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
 * This class create the register interface for user
 * @author Zhenghui Wang
 * @version 1.1
 * 
 */
public class RegisterUI {
	
	/**
	 * @param main MainUI object referenced from MainUI class
	 * 
	 */
	
	MainUI main;
	JFrame registerFrame = new JFrame("Register");
	Container contentPane = new Container();
	
	// Register Main Panel: 
	JPanel registerPanel1 = new JPanel();
	
	
	ImageIcon logoImg = new ImageIcon("src/images/logo.png");
	JLabel logoLabel = new JLabel(logoImg);
	JLabel titleLabel = new JLabel("Totoro Ramen Restaurant"); 
	
	UserDAOImpl u = new UserDAOImpl();
	UserOperation oo = new UserOperation();
	
	// Register Embedded Panel: 
	JPanel registerPanel2 = new JPanel();

	JTextField registerField[] = new JTextField[5];
	String text[] = {"First Name", "Last Name", "", "EmailAddress", "PhoneNumber"};
	JLabel tipLabel = new JLabel("Please enter your email or phone number");
	JLabel passwordLabel = new JLabel("Password");
	JButton submitBtn = new JButton("Free Resistration");
	JButton loginBtn = new JButton("Already have an acount!");
	
	public RegisterUI(MainUI main) {
		
		this.main = main;
		
		registerFrame.setBounds(700, 160, 400, 500);
		registerFrame.setContentPane(contentPane);
		registerFrame.getLayeredPane().setLayout(null);
		registerFrame.setResizable(false);
		registerFrame.setAlwaysOnTop(true);    
		
		//For Main Panel:
		registerPanel1.setBounds(0, 0, 400, 500);
		registerPanel1.setLayout(null);
		registerPanel1.setFocusable(true);
		
		logoLabel.setBounds(30, 0, 64, 64);
		titleLabel.setForeground(Color.red);
		titleLabel.setBounds(100, 15, 250, 30);
		titleLabel.setFont(new Font("Times New Roman", 2, 23));
		
		registerPanel1.add(logoLabel);
		registerPanel1.add(titleLabel);

		
		// For embedded Panel: 
		registerPanel2.setBorder(BorderFactory.createTitledBorder(null, "Register", TitledBorder.LEFT,TitledBorder.TOP, new Font("Times New Roman", 2, 23)));
		registerPanel2.setBounds(20, 60, 360, 380);
		registerPanel2.setLayout(null);
		
		passwordLabel.setFont(new Font("Times New Roman", 2, 20));
		passwordLabel.setBounds(62, 140, 250, 30);
		passwordLabel.setForeground(Color.gray);
		passwordLabel.setBackground(null);
		
		for (int i = 0; i<5; i++) {
			final int k = i;
			if (i == 2) {registerField[i] = new JPasswordField(text[i], 20); registerField[i].setOpaque(false);}
			else registerField[i] = new JTextField(text[i], 20);	
			registerField[i].setFont(new Font("Times New Roman", 2, 20));
			registerField[i].setBounds(60, 40+50*i, 250, 30);
			registerField[i].setForeground(Color.gray);
			registerField[i].setBackground(null);
			
			// focus listener design
			registerField[k].addFocusListener(new FocusListener() {
				
				@Override
				public void focusLost(FocusEvent e) {
					// TODO Auto-generated method stub
					if (k != 2) {
						if (registerField[k].getText().equals("")) {
							registerField[k].setText(text[k]);
						}
						registerField[k].setForeground(Color.gray);
						registerField[k].setBackground(null);
					} else {
						if (registerField[k].getText().equals("")) {
							passwordLabel.setVisible(true);
						}
						registerField[k].setOpaque(false);
						registerField[k].setForeground(Color.gray);
						registerField[k].setBackground(null);
					}
				}
				
				@Override
				public void focusGained(FocusEvent e) {
					// TODO Auto-generated method stub
					if (k != 2) {
						if (registerField[k].getText().equals(text[k])) {
							registerField[k].setText("");
						}
						registerField[k].setForeground(Color.black);
						registerField[k].setBackground(Color.white);
					} else {
						if (passwordLabel.getText().equals("Password")) {
							passwordLabel.setVisible(false);
						}
						registerField[k].setOpaque(true);
						registerField[k].setForeground(Color.black);
						registerField[k].setBackground(Color.white);
					}
				}
			});
			registerPanel2.add(registerField[i]);
		}
		
		tipLabel.setBounds(10, 275, 300, 20);
		tipLabel.setFont(new Font("Times New Roman", 2, 17));
		tipLabel.setForeground(Color.red);
        
        submitBtn.setBounds(60, 300, 250, 30);
        submitBtn.setFont(new Font("Times New Roman", 2, 20));
        submitBtn.setForeground(Color.white);
        submitBtn.setBackground(Color.DARK_GRAY);
        
        loginBtn.setBounds(60, 340, 250, 30);
        loginBtn.setFont(new Font("Times New Roman", 2, 20));
        loginBtn.setForeground(Color.white);
        loginBtn.setBackground(Color.DARK_GRAY);
        
        registerPanel2.add(submitBtn);
        registerPanel2.add(loginBtn);
        registerPanel2.add(tipLabel);
        registerPanel2.add(passwordLabel);
        
        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try {
					if (validation()) {
						UserOperation uo = new UserOperation();
						UserDAOImpl udi = new UserDAOImpl();
						String para = "";
						try {
							para = uo.register(registerField[0].getText(), registerField[1].getText(), registerField[2].getText(), 
									registerField[2].getText(), registerField[3].getText(), registerField[4].getText());
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
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
					    registerFrame.dispatchEvent(new WindowEvent(registerFrame,WindowEvent.WINDOW_CLOSING) );
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        
        
        loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				registerFrame.dispatchEvent(new WindowEvent(registerFrame, WindowEvent.WINDOW_CLOSING));
				main.frame.setEnabled(false);
				loginFunction();
			}
		});

        
       
        
        
        registerFrame.getLayeredPane().add(registerPanel1, 0);
        registerFrame.getLayeredPane().add(registerPanel2, 0);
        registerFrame.setVisible(true);
		
        
        registerFrame.addWindowListener(new WindowAdapter() {  
	       
	            public void windowClosing(WindowEvent e) {  
	              
	              main.frame.setEnabled(true);
	         }            
	       });
	}
	
	/**
	 * This method validate if the information input by user are all reasonable
	 * @return a boolean value, if pass the validation, the value is true
	 * @throws IOException
	 */
	public boolean validation() throws IOException {
		
		for (int i = 0; i<4; i++) {
			if (registerField[i].getText().equals("") || registerField[i].getText().equals(text[i]) && i<3) {
				if (i == 2) {
					tipLabel.setText("Error: Please enter your Password!");
					tipLabel.setVisible(true);
					registerField[i].requestFocus(true);
				} else {
					tipLabel.setText("Error: Please enter your " + text[i] + "!");
					tipLabel.setVisible(true);
					registerField[i].requestFocus(true);
				}
				
				return false;
			}
			
			if (i == 3) {
				String pnumber = registerField[4].getText();
				if (registerField[i].getText().equals("") || registerField[i].getText().equals(text[i])) {
					if (registerField[4].getText().equals("") || registerField[4].getText().equals(text[4])) {
						tipLabel.setText("Error: Please enter your Email or Phone number!");
						tipLabel.setVisible(true);
						registerField[i].requestFocus(true);
						return false;
					} else {
						
						if (!phoneValidation(pnumber)) {
							tipLabel.setText("Error: Invalid Phone Number!");
							tipLabel.setVisible(true);
							registerField[4].setText("");
							registerField[4].requestFocus(true);
							return false;
						}
						
						UserDAOImpl ud = new UserDAOImpl();
						if(ud.checkRepeat(registerField[4].getText())) {
							tipLabel.setText("Error: Phone number is repeat!");
							tipLabel.setVisible(true);
							registerField[4].setText("");
							registerField[4].requestFocus(true);
							return false;
						}
						
					}
				} else {
					if (registerField[4].getText().equals("") || registerField[4].getText().equals(text[4])) {
						
					} else {
						if (!phoneValidation(pnumber)) {
							tipLabel.setText("Error: Invalid Phone Number!");
							tipLabel.setVisible(true);
							registerField[4].setText("");
							registerField[4].requestFocus(true);
							return false;
						}
					}
					String email = registerField[i].getText();
					if (email.indexOf("@") == -1 || email.indexOf(".") == -1) {
						tipLabel.setText("Error: Invalid Email Address!");
						tipLabel.setVisible(true);
						registerField[i].setText("");
						registerField[i].requestFocus(true);
						return false;
					} else if (email.indexOf("@") != email.lastIndexOf("@")){
						tipLabel.setText("Error: Invalid Email Address!");
						tipLabel.setVisible(true);
						registerField[i].setText("");
						registerField[i].requestFocus(true);
						return false;
					} else if (email.indexOf("@")> email.lastIndexOf(".")) {
						tipLabel.setText("Error: Invalid Email Address!");
						tipLabel.setVisible(true);
						registerField[i].setText("");
						registerField[i].requestFocus(true);
						return false;
					}
					
					UserDAOImpl ud = new UserDAOImpl();
					
					if(ud.checkRepeat(email)) {
						tipLabel.setText("Error: Repeat Email Address!");
						tipLabel.setVisible(true);
						registerField[i].setText("");
						registerField[i].requestFocus(true);
						return false;
					}
					System.out.println("phone number: "+ registerField[4].getText());
					if(ud.checkRepeat(registerField[4].getText())) {
						tipLabel.setText("Error: Phone number is repeat!");
						tipLabel.setVisible(true);
						registerField[4].setText("");
						registerField[4].requestFocus(true);
						return false;
					}
					
				}
			}
			
		}
		
		tipLabel.setText("");
		return true;
	}
	
	/** 
	 * This method is a submethod for validation method, it is used to check if user's phone number is reasonable
	 * @param number user's phone number input by user
	 * @return a boolean value, if pass the validation, return true
	 */
	public boolean phoneValidation(String number) {
		if (number.length() != 11) {
			tipLabel.setText("Error: Invalid Phone Number!");
			tipLabel.setVisible(true);
			registerField[4].setText("");
			registerField[4].requestFocus(true);
			return false;
		} else {
			for (int i = 0; i<11; i++) {
				if (number.charAt(i)<'0' || number.charAt(i)>'9') {
					tipLabel.setText("Error: Invalid Phone Number!");
					tipLabel.setVisible(true);
					registerField[4].setText("");
					registerField[4].requestFocus(true);
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * This method is used to jump back to MainUI
	 */
	public void loginFunction(){
		LoginUI login = new LoginUI(this.main); 
	}
	
	/**
	 * This method is used to get user's username
	 * @return username
	 */
	public String getUsername() {
		return this.registerField[0].getText();
	}
	
	
}


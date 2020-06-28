package user_Boundary;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;

import impl_Control.ListDAOImpl;
import impl_Control.UserDAOImpl;
import servlet_Control.UserOperation;
import vo_Entity.Ramen;
import vo_Entity.User;

/** 
 * This class create the user personal center interface for user
 * @author Zhenghui Wang
 * @version 1.1
 * 
 */
public class PersonalCenterUI {

	/**
	 * @param id user id, if not login, it is "null"
	 * @param email user's email
	 * @param password user's password
	 * @param phone user's phone number
	 */
	
	String id;
	private String password = "";
	private String email = "";
	private String phone = "";
	UserDAOImpl udi = new UserDAOImpl();
	
	// Main Panel:
	MainUI main;
	JFrame frame;
	JPanel mainPanel = new JPanel();
	JButton returnBtn = new JButton("Return");
	JButton logoutBtn = new JButton("Log-out");
	
	ImageIcon postImg = new ImageIcon("src/images/haibao.jpg");
	JLabel postLabel = new JLabel(postImg);

	// Center Panel
	JPanel centerPanel = new JPanel();
	JButton infoBtn = new JButton("Personal Infomation");
	JButton emailBtn = new JButton("Change Email");
	JButton passwordBtn = new JButton("Change Password");
	JButton phoneBtn = new JButton("Change Phone Number");
	JButton historyBtn = new JButton("History Order(s)");

	// Info Panel:
	JPanel infoPanel = new JPanel();
	JLabel nameLabel = new JLabel();
	JLabel emailLabel = new JLabel();
	JLabel phoneLabel = new JLabel();
	JLabel stampLabel = new JLabel();
	JLabel membershipLabel = new JLabel();
	JLabel registLabel = new JLabel();
	JButton statisticBtn = new JButton("Statistics");
	JButton sendBtn = new JButton("Send to my email per week!");

	// Change Email Panel:
	JPanel emailPanel = new JPanel();
	JLabel oldEmailLabel = new JLabel();
	JTextField emailField = new JTextField();
	JButton emailSubmitBtn = new JButton("Change Email!");
	JLabel emailTipLabel = new JLabel();

	// Change password Panel:
	JPanel passwordPanel = new JPanel();
	JTextField oldPassField = new JPasswordField();
	JTextField passwordField = new JPasswordField();
	JButton passwordSubmitBtn = new JButton("Change Password!");
	JLabel passwordTipLabel = new JLabel();
	JLabel oldLabel = new JLabel("Current Password:");
	JLabel newLabel = new JLabel("New Password:");

	// Change Phone Panel:
	JPanel phonePanel = new JPanel();
	JLabel oldphoneLabel = new JLabel();
	JTextField phoneField = new JTextField();
	JButton phoneSubmitBtn = new JButton("Change Phone Number!");
	JLabel phoneTipLabel = new JLabel();

	// History Panel:
	JPanel embeddedPanel = new JPanel();
	JScrollPane historyPanel = new JScrollPane(embeddedPanel);

	ArrayList<Ramen> list = new ArrayList<Ramen>();
	ListDAOImpl ldi = new ListDAOImpl();
	JButton listBtn[] = new JButton[100];
	JLabel historyLabel = new JLabel("History List");

	// List Panel:
	JPanel listPanel = new JPanel();
	
	JTable table;
	JScrollPane JSP;

	JButton listReturnBtn = new JButton("Return");
	JLabel serialLabel = new JLabel();
	JLabel timeLabel = new JLabel();
	ImageIcon ramenImg = new ImageIcon("src/images/listRamen2.jpg");
	JLabel ramenLabel = new JLabel(ramenImg);
	JLabel soupLabel = new JLabel();
	JLabel noodleLabel = new JLabel();
	JLabel onionLabel = new JLabel();
	JLabel spicinessLabel = new JLabel();
	
	JLabel priceLabel = new JLabel();
	JButton againBtn = new JButton("Another List!");

	public PersonalCenterUI(MainUI main) {

//		// test code
//		frame.setTitle("UIDemo");
//		frame.setBounds(600, 60, 680, 900);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setResizable(false);
//		frame.getLayeredPane().setLayout(null);
//		frame.setLayout(null);
		this.frame = main.frame;
		this.main = main;
		this.id = main.id;
		main.mainPanel.setVisible(false);

		// Get user information:
		UserDAOImpl ud = new UserDAOImpl();
		User u = new User();
		try {
			u = ud.checkInformation(id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		password = u.getPassword();
		email = u.getEmail();
		phone = u.getPhoneNumber();

		// Main Panel Design:
		mainPanel.setLayout(null);
		mainPanel.setBounds(0, 0, 680, 900);
		mainPanel.setOpaque(false);

		
		returnBtn.setBounds(32, 370, 615, 40);
		returnBtn.setFont(new Font("Times New Roman", 2, 20));
		returnBtn.setBackground(Color.gray);

		logoutBtn.setBounds(32, 420, 615, 40);
		logoutBtn.setFont(new Font("Times New Roman", 2, 20));
		logoutBtn.setBackground(Color.gray);

		postLabel.setBounds(20, 500, 640, 229);
		mainPanel.add(postLabel);
		
		mainPanel.add(returnBtn);
		mainPanel.add(logoutBtn);

		// Center Panel Controls:
		centerPanel.setLayout(null);
		centerPanel.setBounds(30, 60, 620, 303);
		centerPanel.setBorder(BorderFactory.createTitledBorder(null, "Personal Center", TitledBorder.LEFT,
				TitledBorder.TOP, new Font("Times New Roman", 2, 25)));

		infoBtn.setBounds(3, 50, 200, 50);
		infoBtn.setBackground(Color.gray);
		infoBtn.setMargin(new Insets(0, 0, 0, 0));
		infoBtn.setFont(new Font("Times New Roman", 2, 18));
		centerPanel.add(infoBtn);

		emailBtn.setBounds(3, 100, 200, 50);
		emailBtn.setBackground(null);
		emailBtn.setMargin(new Insets(0, 0, 0, 0));
		emailBtn.setFont(new Font("Times New Roman", 2, 18));
		centerPanel.add(emailBtn);

		passwordBtn.setBounds(3, 150, 200, 50);
		passwordBtn.setBackground(null);
		passwordBtn.setMargin(new Insets(0, 0, 0, 0));
		passwordBtn.setFont(new Font("Times New Roman", 2, 18));
		centerPanel.add(passwordBtn);

		phoneBtn.setBounds(3, 200, 200, 50);
		phoneBtn.setBackground(null);
		phoneBtn.setMargin(new Insets(0, 0, 0, 0));
		phoneBtn.setFont(new Font("Times New Roman", 2, 18));
		centerPanel.add(phoneBtn);

		historyBtn.setBounds(3, 250, 200, 50);
		historyBtn.setBackground(null);
		historyBtn.setMargin(new Insets(0, 0, 0, 0));
		historyBtn.setFont(new Font("Times New Roman", 2, 18));
		centerPanel.add(historyBtn);

		infoBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				refresh();

				infoPanel.setVisible(true);
				emailPanel.setVisible(false);
				passwordPanel.setVisible(false);
				phonePanel.setVisible(false);
				historyPanel.setVisible(false);
				listPanel.setVisible(false);

				emailBtn.setBackground(null);
				phoneBtn.setBackground(null);
				passwordBtn.setBackground(null);
				infoBtn.setBackground(Color.gray);
				historyBtn.setBackground(null);
				
				listPanel.removeAll();

			}
		});

		emailBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				refresh();

				infoPanel.setVisible(false);
				passwordPanel.setVisible(false);
				emailPanel.setVisible(true);
				phonePanel.setVisible(false);
				historyPanel.setVisible(false);
				listPanel.setVisible(false);

				emailBtn.setBackground(Color.gray);
				phoneBtn.setBackground(null);
				passwordBtn.setBackground(null);
				infoBtn.setBackground(null);
				historyBtn.setBackground(null);
				
				listPanel.removeAll();
			}
		});

		passwordBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				refresh();

				phonePanel.setVisible(false);
				infoPanel.setVisible(false);
				emailPanel.setVisible(false);
				passwordPanel.setVisible(true);
				historyPanel.setVisible(false);
				listPanel.setVisible(false);

				emailBtn.setBackground(null);
				phoneBtn.setBackground(null);
				passwordBtn.setBackground(Color.gray);
				infoBtn.setBackground(null);
				historyBtn.setBackground(null);
				
				listPanel.removeAll();
			}
		});

		phoneBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				refresh();

				phonePanel.setVisible(true);
				infoPanel.setVisible(false);
				emailPanel.setVisible(false);
				passwordPanel.setVisible(false);
				historyPanel.setVisible(false);
				listPanel.setVisible(false);

				emailBtn.setBackground(null);
				phoneBtn.setBackground(Color.gray);
				passwordBtn.setBackground(null);
				infoBtn.setBackground(null);
				historyBtn.setBackground(null);
				
				listPanel.removeAll();
			}
		});

		historyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				refresh();

				phonePanel.setVisible(false);
				infoPanel.setVisible(false);
				emailPanel.setVisible(false);
				passwordPanel.setVisible(false);
				historyPanel.setVisible(true);
				listPanel.setVisible(false);

				emailBtn.setBackground(null);
				phoneBtn.setBackground(null);
				passwordBtn.setBackground(null);
				infoBtn.setBackground(null);
				historyBtn.setBackground(Color.gray);
				
				listPanel.removeAll();
			}
		});

		// Info Panel controls:
		infoPanel.setLayout(null);
		infoPanel.setBounds(233, 110, 415, 250);
		infoPanel.setOpaque(false);
		infoPanel.setBorder(BorderFactory.createLineBorder(Color.gray));

		nameLabel.setText("Name:                            " + u.getlName() + " " + u.getfName());
		nameLabel.setBounds(20, 10, 370, 30);
		nameLabel.setFont(new Font("Times New Roman", 2, 20));
		infoPanel.add(nameLabel);

		membershipLabel.setText("VIP No.:                        " + u.getUserID());
		membershipLabel.setBounds(20, 40, 370, 30);
		membershipLabel.setFont(new Font("Times New Roman", 2, 20));
		infoPanel.add(membershipLabel);

		emailLabel.setText("Email:                           " + u.getEmail());
		emailLabel.setBounds(20, 70, 370, 30);
		emailLabel.setFont(new Font("Times New Roman", 2, 20));
		infoPanel.add(emailLabel);

		phoneLabel.setText("Phone:                          " + u.getPhoneNumber());
		phoneLabel.setBounds(20, 100, 370, 30);
		phoneLabel.setFont(new Font("Times New Roman", 2, 20));
		infoPanel.add(phoneLabel);

		stampLabel.setText("Stamp owned:               " + u.getStamp());
		stampLabel.setBounds(20, 130, 370, 30);
		stampLabel.setFont(new Font("Times New Roman", 2, 20));
		infoPanel.add(stampLabel);

		registLabel.setText("Reg. time:                    " + u.getRegistTime());
		registLabel.setBounds(20, 160, 370, 30);
		registLabel.setFont(new Font("Times New Roman", 2, 20));
		infoPanel.add(registLabel);

		statisticBtn.setBounds(20, 200, 120, 30);
		statisticBtn.setFont(new Font("Times New Roman", 2, 20));
		infoPanel.add(statisticBtn);
		
		
		
		statisticBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListDAOImpl ldi = new ListDAOImpl();
				try {
					ldi.getSalesVolume();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JFrame subframe = new JFrame("Statistics");
				subframe.setBounds(630, 160, 600, 500);
				ImageIcon statisticsImg = new ImageIcon("src/images/sales_of_dishes.jpg");
				JLabel statisticsLabel = new JLabel(statisticsImg);
				statisticsLabel.setBounds(0, 0, 600, 400);
				subframe.add(statisticsLabel);
				subframe.setLayout(null);
				sendBtn.setBounds(50, 410, 480, 30);
				sendBtn.setFont(new Font("Times New Roman", 2, 20));
				subframe.add(sendBtn);
				subframe.setVisible(true);
			}
		});
		
		sendBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(infoPanel, "Submit Successfully!", "Message",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		
		
		// Email Panel controls:
		emailPanel.setLayout(null);
		emailPanel.setBounds(233, 110, 415, 250);
		emailPanel.setOpaque(false);
		emailPanel.setBorder(BorderFactory.createLineBorder(Color.gray));
		emailPanel.setVisible(false);

		oldEmailLabel.setText("Current Email:        " + u.getEmail());
		oldEmailLabel.setBounds(20, 30, 370, 30);
		oldEmailLabel.setFont(new Font("Times New Roman", 2, 20));
		emailPanel.add(oldEmailLabel);

		emailField.setText("New Email:");
		emailField.setFont(new Font("Times New Roman", 2, 20));
		emailField.setBounds(20, 70, 350, 30);
		emailField.setForeground(Color.gray);
		emailField.setBackground(null);
		emailPanel.add(emailField);

		emailSubmitBtn.setBounds(20, 120, 350, 30);
		emailSubmitBtn.setFont(new Font("Times New Roman", 2, 20));
		emailSubmitBtn.setBackground(Color.gray);
		emailPanel.add(emailSubmitBtn);

		emailTipLabel.setBounds(20, 150, 350, 20);
		emailTipLabel.setFont(new Font("Times New Roman", 2, 17));
		emailTipLabel.setForeground(Color.red);
		emailPanel.add(emailTipLabel);

		emailField.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if (emailField.getText().equals("")) {
					emailField.setText("New Email:");
				}
				emailField.setForeground(Color.gray);
				emailField.setBackground(null);
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if (emailField.getText().equals("New Email:")) {
					emailField.setText("");
				}
				emailField.setForeground(Color.black);
				emailField.setBackground(Color.white);
			}
		});

		emailSubmitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					if (emailValidation()) {

						UserOperation uo = new UserOperation();
						try {
							uo.edit(id, 1, emailField.getText());
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				UserDAOImpl ud = new UserDAOImpl();
				User u = new User();
				try {
					u = ud.checkInformation(id);
				} catch (IOException ee) {
					// TODO Auto-generated catch block
					ee.printStackTrace();
				}

				emailField.setText("New Email:");
				oldEmailLabel.setText("Current Email:        " + u.getEmail());
				oldEmailLabel.setVisible(true);

			}
		});

		// Password Panel controls:
		passwordPanel.setLayout(null);
		passwordPanel.setBounds(233, 110, 415, 250);
		passwordPanel.setOpaque(false);
		passwordPanel.setBorder(BorderFactory.createLineBorder(Color.gray));
		passwordPanel.setVisible(false);

		oldPassField.setText("");
		oldPassField.setBounds(20, 30, 350, 30);
		oldPassField.setFont(new Font("Times New Roman", 2, 20));
		oldPassField.setForeground(Color.gray);
		oldPassField.setBackground(null);
		oldPassField.setOpaque(false);
		passwordPanel.add(oldPassField);

		passwordField.setText("");
		passwordField.setFont(new Font("Times New Roman", 2, 20));
		passwordField.setBounds(20, 70, 350, 30);
		passwordField.setForeground(Color.gray);
		passwordField.setBackground(null);
		passwordField.setOpaque(false);
		passwordPanel.add(passwordField);

		passwordSubmitBtn.setBounds(20, 120, 350, 30);
		passwordSubmitBtn.setFont(new Font("Times New Roman", 2, 20));
		passwordSubmitBtn.setBackground(Color.gray);
		passwordPanel.add(passwordSubmitBtn);

		passwordTipLabel.setBounds(20, 150, 350, 20);
		passwordTipLabel.setFont(new Font("Times New Roman", 2, 17));
		passwordTipLabel.setForeground(Color.red);
		passwordPanel.add(passwordTipLabel);

		oldLabel.setBounds(22, 30, 350, 30);
		oldLabel.setFont(new Font("Times New Roman", 2, 20));
		oldLabel.setForeground(Color.gray);
		passwordPanel.add(oldLabel);
		oldLabel.setOpaque(false);

		newLabel.setBounds(22, 70, 350, 30);
		newLabel.setFont(new Font("Times New Roman", 2, 20));
		newLabel.setForeground(Color.gray);
		passwordPanel.add(newLabel);
		newLabel.setOpaque(false);

		oldPassField.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if (oldPassField.getText().equals("")) {
					oldLabel.setText("Current Password:");
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if (oldPassField.getText().equals("")) {
					oldLabel.setText("");
				}
				oldPassField.setForeground(Color.black);
				oldPassField.setBackground(Color.white);
			}
		});

		passwordField.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if (passwordField.getText().equals("")) {
					newLabel.setText("New Password:");
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if (passwordField.getText().equals("")) {
					newLabel.setText("");
				}
				passwordField.setForeground(Color.black);
				passwordField.setBackground(Color.white);
			}
		});

		passwordSubmitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (passwordValidation()) {

					UserOperation uo = new UserOperation();
					try {
						uo.edit(id, 2, passwordField.getText());
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(centerPanel, "Successful!", "Message",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		// Phone Panel controls:
		phonePanel.setLayout(null);
		phonePanel.setBounds(233, 110, 415, 250);
		phonePanel.setOpaque(false);
		phonePanel.setBorder(BorderFactory.createLineBorder(Color.gray));
		phonePanel.setVisible(false);

		oldphoneLabel.setText("Current Number:        " + u.getPhoneNumber());
		oldphoneLabel.setBounds(20, 30, 370, 30);
		oldphoneLabel.setFont(new Font("Times New Roman", 2, 20));
		phonePanel.add(oldphoneLabel);

		phoneField.setText("New Number:");
		phoneField.setFont(new Font("Times New Roman", 2, 20));
		phoneField.setBounds(20, 70, 350, 30);
		phoneField.setForeground(Color.gray);
		phoneField.setBackground(null);
		phonePanel.add(phoneField);

		phoneSubmitBtn.setBounds(20, 120, 350, 30);
		phoneSubmitBtn.setFont(new Font("Times New Roman", 2, 20));
		phoneSubmitBtn.setBackground(Color.gray);
		phonePanel.add(phoneSubmitBtn);

		phoneTipLabel.setBounds(20, 150, 350, 20);
		phoneTipLabel.setFont(new Font("Times New Roman", 2, 17));
		phoneTipLabel.setForeground(Color.red);
		phonePanel.add(phoneTipLabel);

		phoneField.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if (phoneField.getText().equals("")) {
					phoneField.setText("New Number:");
				}
				phoneField.setForeground(Color.gray);
				phoneField.setBackground(null);
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if (phoneField.getText().equals("New Number:")) {
					phoneField.setText("");
				}
				phoneField.setForeground(Color.black);
				phoneField.setBackground(Color.white);
			}
		});

		phoneSubmitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					if (phoneValidation()) {

						UserOperation uo = new UserOperation();
						try {
							uo.edit(id, 3, phoneField.getText());
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(centerPanel, "Successful!", "Message",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				UserDAOImpl ud = new UserDAOImpl();
				User u = new User();
				try {
					u = ud.checkInformation(id);
				} catch (IOException ee) {
					// TODO Auto-generated catch block
					ee.printStackTrace();
				}

				phoneField.setText("New Number:");
				oldphoneLabel.setText("Current Number:        " + u.getPhoneNumber());
				oldphoneLabel.setVisible(true);

			}
		});

		returnBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				setInvisible();
				main.mainBGD.setVisible(true);
				main.mainPanel.setVisible(true);
				main.frame.setVisible(true);
			}
		});
		
		logoutBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				setInvisible();
				main.id = "";
				main.initPara();
				main.loginBtn.setVisible(true);
				main.registerBtn.setVisible(true);
				main.personalBtn.setVisible(false);
				main.logoutBtn.setVisible(false);
				
				main.indexBGD.setVisible(true);
				main.indexPanel.setVisible(true);
			}
		});
		

		// History Panel controls:
		embeddedPanel.setLayout(null);
		embeddedPanel.setBounds(233, 110, 415, 250);
		embeddedPanel.setOpaque(false);
		embeddedPanel.setBorder(BorderFactory.createLineBorder(Color.gray));

		historyLabel.setBounds(150, -2, 110, 35);
		historyLabel.setFont(new Font("Times New Roman", 1, 20));
		embeddedPanel.add(historyLabel);

		try {
			list = ldi.getList(id);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		if (list.size() <= 6)
			embeddedPanel.setPreferredSize(new Dimension(210, 270));
		if (list.size() > 6) {
			embeddedPanel.setPreferredSize(new Dimension(210, 35 * list.size() + 50));
		}

		for (int i = 0; i < list.size(); i++) {
			final int k = i;

			listBtn[i] = new JButton(list.get(i).getTime() + "                           $" + list.get(i).price);
			listBtn[i].setBounds(0, 30 + 35 * i, 385, 35);
			listBtn[i].setFont(new Font("Times New Roman", 2, 18));
			listBtn[i].setForeground(Color.white);
			listBtn[i].setBackground(Color.gray);
			embeddedPanel.add(listBtn[i]);

			listBtn[k].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					configListPanel(k);
				}
			});

		}

		historyPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		historyPanel.setLocation(233, 110);
		historyPanel.setSize(415, 252);
		historyPanel.setVisible(false);

		main.frame.getLayeredPane().add(mainPanel, 0);
		main.frame.getLayeredPane().add(centerPanel, 0);
		main.frame.getLayeredPane().add(infoPanel, 0);
		main.frame.getLayeredPane().add(emailPanel, 0);
		main.frame.getLayeredPane().add(passwordPanel, 0);
		main.frame.getLayeredPane().add(phonePanel, 0);
		main.frame.getLayeredPane().add(historyPanel, 0);


	}

	/**
	 * This method is used to validate if email is reasonable
	 * @return a boolean value, if true, pass the return 
	 * @throws IOException 
	 */
	public boolean emailValidation() throws IOException {

		String email = emailField.getText();
		if (email.equals("") || email.equals("New Email:")) {
			emailTipLabel.setText("Error: Please enter new Email!");
			emailTipLabel.setVisible(true);
			return false;
		} else if (email.indexOf("@") == -1 || email.indexOf(".") == -1) {
			emailTipLabel.setText("Error: Invalid Email Address!");
			emailTipLabel.setVisible(true);
			emailField.setText("");
			emailField.requestFocus(true);
			return false;
		} else if (email.indexOf("@") != email.lastIndexOf("@")) {
			emailTipLabel.setText("Error: Invalid Email Address!");
			emailTipLabel.setVisible(true);
			emailField.setText("");
			emailField.requestFocus(true);
			return false;
		} else if (email.indexOf("@") > email.lastIndexOf(".")) {
			emailTipLabel.setText("Error: Invalid Email Address!");
			emailTipLabel.setVisible(true);
			emailField.setText("");
			emailField.requestFocus(true);
			return false;
		}
		if (udi.checkRepeat(email)) {
			emailTipLabel.setText("Error: Repeat Email Address!");
			emailTipLabel.setVisible(true);
			emailField.setText("");
			emailField.requestFocus(true);
			return false;
		}
		
		emailTipLabel.setText("");
		return true;
	}

	public boolean passwordValidation() {
		if (oldPassField.getText().equals(password)) {
			if (passwordField.getText().equals("")) {
				passwordTipLabel.setText("Error: Please enter new password!");
				passwordTipLabel.setVisible(true);
				return false;
			}
			passwordTipLabel.setText("");
			passwordTipLabel.setVisible(true);
			return true;
		}

		passwordTipLabel.setText("Error: Wrong password!");
		passwordTipLabel.setVisible(true);
		return false;
	}

	public boolean phoneValidation() throws IOException {
		if (phoneField.getText().equals("") || phoneField.getText().equals("New Number:")) {
			phoneTipLabel.setText("Error: Please enter new number!");
			phoneTipLabel.setVisible(true);
			return false;
		}
		// validation
		String number = phoneField.getText();
		if (number.length() != 11) {
			phoneTipLabel.setText("Error: Invalid phone number!");
			phoneTipLabel.setVisible(true);
			return false;
		} else {
			for (int i = 0; i<11; i++) {
				if (number.charAt(i)<'0' || number.charAt(i)>'9') {
					phoneTipLabel.setText("Invalid phone number!");
					phoneTipLabel.setVisible(true);
					return false;
				}
			}
		}
		
		if (udi.checkRepeat(number)) {
			phoneTipLabel.setText("Repeat phone number!");
			phoneTipLabel.setVisible(true);
			return false;
		}
		phoneTipLabel.setText("");
		phoneTipLabel.setVisible(true);
		return true;
	}

	/**
	 * This method is used to refresh the interface
	 */
	public void refresh() {

		// info panel
		UserDAOImpl ud = new UserDAOImpl();
		User u = new User();
		try {
			u = ud.checkInformation(id);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		email = u.getEmail();
		phone = u.getPhoneNumber();
		password = u.getPassword();

		emailLabel.setText("Email:                           " + email);
		phoneLabel.setText("Phone:                          " + phone);

		// refresh tip
		emailTipLabel.setText("");
		phoneTipLabel.setText("");
		passwordTipLabel.setText("");

		// refresh field
		emailField.setText("New Email:");
		oldPassField.setText("");
		passwordField.setText("");
		oldLabel.setText("Current Password:");
		newLabel.setText("New Password:");
		phoneField.setText("New Number:");

	}

//	public static void main(String[] args) {
//		PersonalCenterUI ui = new PersonalCenterUI();
//	}

	/**
	 * This method is used to refresh the interface by set invisibility for all panels
	 */
	public void setInvisible() {
		centerPanel.setVisible(false);
		emailPanel.setVisible(false);
		infoPanel.setVisible(false);
		mainPanel.setVisible(false);
		passwordPanel.setVisible(false);
		phonePanel.setVisible(false);
		historyPanel.setVisible(false);
		listPanel.setVisible(false);
	}

	/**
	 * This method is used to configure list panels
	 * @param k reference different list
	 */
	public void configListPanel(int k) {

		historyPanel.setVisible(false);
		listPanel.setVisible(true);

		listPanel.setLayout(null);
		listPanel.setBounds(233, 110, 415, 250);
		listPanel.setOpaque(false);
		listPanel.setBorder(BorderFactory.createLineBorder(Color.gray));

		
		serialLabel.setText("Serial Number:                              " + list.get(k).getSerialNumer());
		serialLabel.setBounds(10, 5, 380, 20);
		serialLabel.setFont(new Font("Times New Roman", 2, 18));

		timeLabel.setText("Time:                                 " + list.get(k).getTime());
		timeLabel.setBounds(10, 25, 380, 20);
		timeLabel.setFont(new Font("Times New Roman", 2, 18));

		listPanel.add(serialLabel);
		listPanel.add(timeLabel);

		// embedded panel2: 
		JPanel embeddedPanel2 = new JPanel();
		embeddedPanel2.setBounds(10, 50, 380, 84);
		embeddedPanel2.setOpaque(false);
		embeddedPanel2.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		embeddedPanel2.setLayout(null);

		ramenLabel.setBounds(3, 2, 80, 80);

		soupLabel.setText("Soup:                                           " + list.get(k).soup.getType());
		soupLabel.setBounds(100, 0, 270, 20);
		soupLabel.setFont(new Font("Times New Roman", 2, 17));

		noodleLabel.setText("Noodle:                                       " + list.get(k).noodle.getType());
		noodleLabel.setBounds(100, 20, 270, 20);
		noodleLabel.setFont(new Font("Times New Roman", 2, 17));

		onionLabel.setText("Spring onion:                            " + list.get(k).onion.getType());
		onionLabel.setBounds(100, 40, 270, 20);
		onionLabel.setFont(new Font("Times New Roman", 2, 17));

		spicinessLabel.setText("spiciness:                                   " + list.get(k).spiciness);
		spicinessLabel.setBounds(100, 60, 270, 20);
		spicinessLabel.setFont(new Font("Times New Roman", 2, 17));
		
		embeddedPanel2.add(ramenLabel);
		embeddedPanel2.add(soupLabel);
		embeddedPanel2.add(noodleLabel);
		embeddedPanel2.add(onionLabel);
		embeddedPanel2.add(spicinessLabel);
		listPanel.add(embeddedPanel2);
		
		
		//Data processing: 
		int quantity[] = {0, 0, 0, 0};
		double price[] = {0, 0, 0, 0};
		

		if (list.get(k).nori.getQuantity()>0) {
			quantity[0] = list.get(k).nori.getQuantity()-1; 
			price[0] = list.get(k).nori.getPrice() * (list.get(k).nori.getQuantity()-1);
		}
		
		if (list.get(k).boiledEgg.getIsSelected()) {
			quantity[1] = list.get(k).boiledEgg.getQuantity()-1;
			price[1] = list.get(k).boiledEgg.getPrice() * (list.get(k).boiledEgg.getQuantity()-1);
		}

		quantity[2] = list.get(k).bamboo.getQuantity();
		price[2] = list.get(k).bamboo.getPrice() * list.get(k).bamboo.getQuantity();
		
		if (list.get(k).chashu.getIsSelected()) {
			quantity[3] = list.get(k).chashu.getQuantity()-1;
			price[3] = list.get(k).chashu.getPrice();
		}
		

		
		
		String[] columnNames = {"Add-on", "Quantity", "Price"};
		Object[][] rowData = {
				{"Ramen", "1", "$9.9"},
				{"Nori", quantity[0], "$"+ price[0]},
				{"Boiled egg", quantity[1], "$"+ price[1]},
				{"Bamboo shoot", quantity[2], "$"+ price[2]},
				{"Chashu", quantity[3], "$"+ price[3]}
			};
		
		table = new JTable(rowData, columnNames);
		
		JSP = new JScrollPane(table);
		JSP.setVisible(true);
		
		JSP.setBounds(10, 140, 380, 70);
		table.setForeground(Color.BLACK);                   
        table.setFont(new Font("Times New Roman", 2, 17));    
        table.setSelectionForeground(Color.DARK_GRAY);      
        table.setSelectionBackground(Color.LIGHT_GRAY);     
        table.setGridColor(Color.GRAY);                     
        
        table.getTableHeader().setFont(new Font("Times New Roman", 2, 17)); 
        table.getTableHeader().setForeground(Color.RED);               
        table.getTableHeader().setResizingAllowed(false);              
        table.getTableHeader().setReorderingAllowed(false);            
        
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();   
        r.setHorizontalAlignment(JLabel.CENTER);   
        table.setDefaultRenderer(Object.class, r);
        
        table.setEnabled(false);
        table.setRowHeight(20);
		listPanel.add(JSP);
		
		
		listReturnBtn.setBounds(10, 215, 120, 30);
		listReturnBtn.setFont(new Font("Times New Roman", 2, 18));
		listReturnBtn.setBackground(Color.gray);
		listPanel.add(listReturnBtn);
		
		priceLabel.setBounds(160, 215, 100, 30);
		priceLabel.setText("Price: " + list.get(k).price);
		priceLabel.setFont(new Font("Times New Roman", 2, 18));
		listPanel.add(priceLabel);
		
		againBtn.setBounds(280, 215, 120, 30);
		againBtn.setMargin(new Insets(0, 0, 0, 0));
		againBtn.setFont(new Font("Times New Roman", 2, 18));
		againBtn.setBackground(Color.gray);
		listPanel.add(againBtn);

		
		
		// Listener design
		listReturnBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				historyPanel.setVisible(true);
				listPanel.setVisible(false);
				listPanel.removeAll();
				
			}
		});
		
		againBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				setInvisible();
				submitFunction(k);
				
			}
		});
		

		main.frame.getLayeredPane().add(listPanel, 0);

	}
	
	/**
	 * This method send ramen object to payment interface
	 * @param k reference to different ramen from ramen list 
	 */
	public void submitFunction(int k) {
		PaymentUI payment = new PaymentUI(this, list.get(k));
	}

}

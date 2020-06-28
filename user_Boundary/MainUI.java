package user_Boundary;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import impl_Control.UserDAOImpl;
import manager_Boundary.MLogIn;
import vo_Entity.User;
/** 
 * This class initiates the interface for user, it's an original interface
 * @author Zhenghui Wang
 * @version 1.1
 * 
 */

public class MainUI {
	
	/**
	 * @param frame core frame of the software, it will be referenced to another classes... 
	 * @param id It is user's id who login to the system, if not login, it is "null"
	 * @param user It is a reference to an user object
	 */
	
	public JFrame frame = new JFrame();
	Container contentPane = new Container();
	
	JPanel mainPanel = new JPanel();
	//MLogIn lo = new MLogIn();
	// Index panel controls:
	JPanel indexPanel = new JPanel();
	ImageIcon indexImg = new ImageIcon("src/images/background.jpg");
	JLabel indexBGD = new JLabel(indexImg);
	
	ImageIcon tapImage = new ImageIcon("src/images/tap.png");
	JButton tapBtn = new JButton(tapImage);
	JLabel tipLabel = new JLabel("Tap to continue");
	
	// Title Panel Controls: 
	JPanel titlePanel = new JPanel();
	ImageIcon mainLogo = new ImageIcon("src/images/logo.png");
	JLabel mainLogoLabel = new JLabel(mainLogo);
	JLabel mainTitleLabel = new JLabel("Totoro Ramen Restaurant");   
	
	// Main panel controls: 
	ImageIcon mainImg = new ImageIcon("src/images/transparent.png");
	JLabel mainBGD = new JLabel(mainImg);
	
	
	
	JLabel mainTips = new JLabel("Reach free ticket after login");
	JLabel mainDeco1 = new JLabel("|");
	
	JLabel welcomeLabel = new JLabel();
	
	JButton loginBtn = new JButton("login");
	JButton registerBtn = new JButton("register");
	JButton logoutBtn = new JButton("logout");
	JButton personalBtn = new JButton("center");
	
	ImageIcon ramen = new ImageIcon("src/images/ramen.jpg");
	JLabel ramenLabel = new JLabel(ramen);
	
	ImageIcon order = new ImageIcon("src/images/order.png");
	JButton orderBtn = new JButton(order);

	ImageIcon orderlist = new ImageIcon("src/images/orderlist.png");
	JButton orderlistBtn = new JButton(orderlist);

	ImageIcon tickets = new ImageIcon("src/images/tickets.png");
	JButton ticketBtn = new JButton(tickets);

	ImageIcon vip = new ImageIcon("src/images/vip.png");
	JButton vipBtn = new JButton(vip);
	
	JLabel label1 = new JLabel("VIP Code");
	JLabel label2 = new JLabel("My tickets");
	JLabel label3 = new JLabel("My orders");

	JLabel label4 = new JLabel("More Services");

	ImageIcon ticketDemo = new ImageIcon("src/images/ticket.jpg");
	JLabel ticketLabel = new JLabel(ticketDemo);
	
	ImageIcon manageImg = new ImageIcon("src/images/manage.png");
	JButton manageBtn = new JButton(manageImg);
	
	
	//parameter
	public String id = "";
	public User userInfo = new User();
	
	/**
	 * This method set invisibility for the buttons which should not be listed after user login to the system
	 */
	public void setInvisible() {
		this.loginBtn.setVisible(false);
		this.registerBtn.setVisible(false);
		this.logoutBtn.setVisible(true);
		this.personalBtn.setVisible(true);
	}
	
	/**
	 * This method set welcome label based on username
	 * @param username get user first name by search from database(txt file)
	 */
	public void setPara(String username) {
		UserDAOImpl udi = new UserDAOImpl();
		
		this.welcomeLabel.setText("Welcome! " + username);
	}
	
	/**
	 * This method initiates id for user
	 */
	public void initPara() {
		this.welcomeLabel.setText("Welcome! ");
		id = "";
	}
	
	
	/**
	 * This method pass the MainUI object's reference to another class
	 */
	public void thisFunction(){
		LoginUI login = new LoginUI(this); 
	}
	
	/**
	 * This method pass the MainUI object's reference to another class
	 */
	public void orderFunction(){
		RamenUI ramen = new RamenUI(this); 
	}
	
	/**
	 * This method pass the MainUI object's reference to another class
	 */
	public void myOrdersFunction(){
		PersonalCenterUI personal = new PersonalCenterUI(this); 
	}
	
	/**
	 * This method pass the MainUI object's reference to another class
	 */
	public void registerFunction(){
		RegisterUI register = new RegisterUI(this); 
	}
	
	/**
	 * This method pass the MainUI object's reference to another class
	 */
	public void personalFunction(){
		PersonalCenterUI personal = new PersonalCenterUI(this); 
	}
	
	public void manageFunction(){
		MLogIn m = new MLogIn(this);
	}
	

	public MainUI() {
		
		//System.out.println("this is mainUI");
		frame.setTitle("EBU6304-2020 Group 53");    
		frame.setBounds(600, 60, 680, 900);    
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
		//frame.setLayout(null);
		frame.setResizable(true);
		frame.setContentPane(contentPane);
		frame.getLayeredPane().setLayout(null);
		
		
		mainPanel.setBounds(0,0,680,900);
		mainPanel.setLayout(null);
		mainPanel.setOpaque(false);
		indexPanel.setOpaque(false);
		indexPanel.setBounds(0,0,680,900);
        indexPanel.setLayout(null);
        titlePanel.setBounds(0,0,680,900);
        titlePanel.setOpaque(false);
        titlePanel.setLayout(null);
       
        //Here for indexPanel
        indexBGD.setBounds(0, 0, indexImg.getIconWidth(), indexImg.getIconHeight());
        mainBGD.setBounds(0, 0, indexImg.getIconWidth(), indexImg.getIconHeight());
        
        
     	tapBtn.setContentAreaFilled(false);
     	tapBtn.setBounds(280, 650, 64, 64);
   		
     	tipLabel.setForeground(Color.white);
		tipLabel.setFont(new Font("Times New Roman", 2, 23));
		tipLabel.setBounds(235, 705, 200, 60);
		
		indexPanel.add(tipLabel);
		indexPanel.add(tapBtn);
        //mainPanel.setVisible(false);
        
        
        //Here for mainPanel
		mainLogoLabel.setBounds(115, 0, 64, 64);
		
		mainTitleLabel.setForeground(Color.red);
		mainTitleLabel.setFont(new Font("Times New Roman", 2, 30));
		mainTitleLabel.setBounds(180, 0, 320, 60);

		mainTips.setForeground(Color.black);
		mainTips.setFont(new Font("Times New Roman", 2, 18));
		mainTips.setBounds(50, 50, 250, 40);
		
		registerBtn.setForeground(Color.black);
		registerBtn.setFont(new Font("Times New Roman", 2, 18));
		registerBtn.setBounds(465, 50, 70, 40);
		registerBtn.setContentAreaFilled(false);
		registerBtn.setBorder(null);
		
		logoutBtn.setForeground(Color.black);
		logoutBtn.setFont(new Font("Times New Roman", 2, 18));
		logoutBtn.setBounds(465, 50, 70, 40);
		logoutBtn.setContentAreaFilled(false);
		logoutBtn.setBorder(null);
		
		mainDeco1.setForeground(Color.black);
		mainDeco1.setFont(new Font("Times New Roman", 2, 18));
		mainDeco1.setBounds(540, 50, 50, 40);
		
		loginBtn.setForeground(Color.black);
		loginBtn.setFont(new Font("Times New Roman", 2, 18));
		loginBtn.setBounds(550, 50, 50, 40);
		loginBtn.setContentAreaFilled(false);
		loginBtn.setBorder(null);
		
		personalBtn.setForeground(Color.black);
		personalBtn.setFont(new Font("Times New Roman", 2, 18));
		personalBtn.setBounds(550, 50, 50, 40);
		personalBtn.setContentAreaFilled(false);
		personalBtn.setBorder(null);
		
		ramenLabel.setBounds(-3, 85, 680, 341);

		welcomeLabel.setForeground(Color.black);
		welcomeLabel.setText("Welcome! " + id);
		welcomeLabel.setFont(new Font("Times New Roman", 2, 25));
		welcomeLabel.setBounds(20, 420, 200, 50);
		
		
		orderBtn.setContentAreaFilled(false);
		orderBtn.setBounds(0, 470, 680, 65);

		vipBtn.setContentAreaFilled(false);
		vipBtn.setBorder(null);
		vipBtn.setBounds(80, 555, 64, 64);

		ticketBtn.setContentAreaFilled(false);
		ticketBtn.setBorder(null);
		ticketBtn.setBounds(300, 550, 64, 64);

		orderlistBtn.setContentAreaFilled(false);
		orderlistBtn.setBorder(null);
		orderlistBtn.setBounds(520, 550, 64, 64);
		
		
		
		label1.setForeground(Color.black);
		label1.setFont(new Font("Times New Roman", 2, 20));
		label1.setBounds(70, 600, 200, 50);

		label2.setForeground(Color.black);
		label2.setFont(new Font("Times New Roman", 2, 20));
		label2.setBounds(290, 600, 200, 50);

		label3.setForeground(Color.black);
		label3.setFont(new Font("Times New Roman", 2, 20));
		label3.setBounds(510, 600, 200, 50);

		label4.setForeground(Color.black);
		label4.setFont(new Font("Times New Roman", 2, 25));
		label4.setBounds(20, 650, 200, 50);

		ticketLabel.setBounds(30, 710, 300, 100);
		
		
		
		logoutBtn.setVisible(false);
		personalBtn.setVisible(false);
		
		manageBtn.setContentAreaFilled(false);
		//manageBtn.setBorder(null);
		manageBtn.setBounds(630, 820, 32, 32);
		manageBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setEnabled(false);
				manageFunction();
				
			}
		});
        
		
		
        //Container c=getContentPane(); 
		//titlePanel.add(mainLogoLabel);
        titlePanel.add(mainTitleLabel);  
        
        
        mainPanel.add(mainTips);
        mainPanel.add(loginBtn);
        mainPanel.add(logoutBtn);
        mainPanel.add(mainDeco1);
        mainPanel.add(registerBtn);
        mainPanel.add(personalBtn);
        mainPanel.add(ramenLabel);
        mainPanel.add(orderBtn);
        mainPanel.add(welcomeLabel);
        mainPanel.add(vipBtn);
        mainPanel.add(ticketBtn);
        mainPanel.add(orderlistBtn);
        mainPanel.add(label1);
        mainPanel.add(label2);
        mainPanel.add(label3);
        mainPanel.add(label4);
        mainPanel.add(ticketLabel);
        mainPanel.add(manageBtn);
        
        
        
        indexBGD.setVisible(true);
        frame.getLayeredPane().add(indexBGD, -2);
        frame.getLayeredPane().add(mainBGD, -2);
        frame.getLayeredPane().add(titlePanel, -1);
        frame.getLayeredPane().add(indexPanel, 0);
        frame.getLayeredPane().add(mainPanel, 0);
        mainPanel.setVisible(false);
        //mainBGD.setVisible(false);
        frame.setVisible(true);   
        
        
        //listener design
        
     	tapBtn.addActionListener(new ActionListener() {
     		public void actionPerformed(ActionEvent e) {
     			indexPanel.setVisible(false);
     			mainPanel.setVisible(true);
     			indexBGD.setVisible(false);
     			mainBGD.setVisible(true);
     			frame.setVisible(true);
     		}
     	});
     	
     	
        loginBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				//index.setFocusableWindowState(false);
				frame.setEnabled(false);
				thisFunction();

			}
		});
        
        registerBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				//index.setFocusableWindowState(false);
				frame.setEnabled(false);
				registerFunction();

			}
		});
        
        logoutBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				//index.setFocusableWindowState(false);
				initPara();
				loginBtn.setVisible(true);
				registerBtn.setVisible(true);
				personalBtn.setVisible(false);
				logoutBtn.setVisible(false);
			}
		});
        
        orderBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				//index.setFocusableWindowState(false);
				mainPanel.setVisible(false);
				mainBGD.setVisible(false);
				orderFunction();
			}
		});
        
        personalBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				mainPanel.setVisible(false);
				mainBGD.setVisible(false);
				personalFunction();
			}
		});
        
        orderlistBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (!id.equals("")) {
					myOrdersFunction();
				} else {
					JOptionPane.showMessageDialog(frame, "Login please!", "Message", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
        
        vipBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (!id.equals("")) {
					JOptionPane.showMessageDialog(frame, "Your VIP Code: " + getVip(), "Message", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(frame, "Login please!", "Warning", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
        
        ticketBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (!id.equals("")) {
					JOptionPane.showMessageDialog(frame, "You have " + getTicket() + " ticket(s)!", "Message", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(frame, "Login please!", "Warning", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
        
        
	}
	
	/**
	 * This method get user's vip code from database
	 */
	public String getVip() {
		UserDAOImpl ud = new UserDAOImpl();
		User u = new User();
		try {
			//System.out.println(id);
			u = ud.checkInformation(id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u.getUserID();
	}
	
	/**
	 * This method get user's stamp number from database
	 */
	public int getTicket() {
		UserDAOImpl ud = new UserDAOImpl();
		User u = new User();
		try {
			//System.out.println(username);
			u = ud.checkInformation(id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u.getStamp();
	}
	

}

package user_Boundary;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import impl_Control.DishInfoImpl;
import vo_Entity.Ramen;
import vo_Entity.SideDish;

/** 
 * This class create the order interface for user
 * @author Zhenghui Wang
 * @version 1.1
 * 
 */
public class RamenUI {

	/**
	 * @param main MainUI object referenced from MainUI class
	 * @param ramen ramen object 
	 * @param id user's id
	 *
	 */
	
	// Parameters
	MainUI main;
	Ramen ramen = new Ramen();
	String id;
	DecimalFormat df=new DecimalFormat("#.0");
	String available[];
	String price[];
	DishInfoImpl dii = new DishInfoImpl();
	// Index Background
	ImageIcon backImg = new ImageIcon("src/images/post.png");
	JLabel backLabel = new JLabel(backImg);
	
	
	// Main Panel Controls:
	JFrame frame = new JFrame();
	JPanel ramenPanel = new JPanel();

	ImageIcon returnImg = new ImageIcon("src/images/return.png");
	JButton ramenReturnBtn = new JButton(returnImg);

	// Selection Panel Controls:
	JPanel ramenPanel2 = new JPanel();
	JLabel soupText = new JLabel("Soup");
	ImageIcon soupImg = new ImageIcon("src/images/soup.png");
	JLabel soupLabel = new JLabel(soupImg);
	ButtonGroup soupGroup = new ButtonGroup();
	JRadioButton soupBtn[] = new JRadioButton[3];
	String soupName[] = { "Tonkotsu", "Shoyu", "Shio" };

	JLabel noodleText = new JLabel("Noodle");
	ImageIcon noodleImg = new ImageIcon("src/images/noodle.png");
	JLabel noodleLabel = new JLabel(noodleImg);
	ButtonGroup noodleGroup = new ButtonGroup();
	JRadioButton noodleBtn[] = new JRadioButton[3];
	String noodleName[] = {"Soft", "Medium", "Firm"};

	JLabel onionText = new JLabel("Spring onion");
	ImageIcon onionImg = new ImageIcon("src/images/onion.png");
	JLabel onionLabel = new JLabel(onionImg);
	ButtonGroup onionGroup = new ButtonGroup();
	JRadioButton onionBtn[] = new JRadioButton[3];
	String onionName[] = { "No", "A little", "A lot" };

	JLabel spicyText = new JLabel("Spiciness");
	ImageIcon spicyImg = new ImageIcon("src/images/spicy.png");
	JLabel spicyLabel = new JLabel(spicyImg);
	ButtonGroup spicyGroup = new ButtonGroup();
	JRadioButton spicyBtn[] = new JRadioButton[6];
	String spicyName[] = { "0(No)", "1", "2", "3", "4", "5(Max)" };
	
	ImageIcon noriImg = new ImageIcon("src/images/nori.png");
	JLabel noriLabel1 = new JLabel(noriImg);
	JLabel noriText1 = new JLabel("Nori");
	JCheckBox noriBox = new JCheckBox("Add", true);
	
	
	ImageIcon eggImg = new ImageIcon("src/images/egg.png");
	JLabel eggLabel1 = new JLabel(eggImg);
	JLabel eggText1 = new JLabel("Boiled egg");
	JCheckBox eggBox = new JCheckBox("Add", true);
	
	ImageIcon chashuImg = new ImageIcon("src/images/chashu.png");
	JLabel chashuLabel1 = new JLabel(chashuImg);
	JLabel chashuText1 = new JLabel("Chashu");
	JCheckBox chashuBox = new JCheckBox("Add", true);
	
	int spicyLocation[] = { 120, 230, 280, 330, 380, 430 };
	int spicyWidth[] = { 100, 45, 45, 45, 45, 110 };

	// Add Panel Controls:
	JPanel ramenPanel3 = new JPanel();

	JLabel noriLabel = new JLabel("Nori $1");
	JLabel eggLabel = new JLabel("Boiled egg $1");
	JLabel bambooLabel = new JLabel("Bamboo $1");
	JLabel chashuLabel = new JLabel("Chashu $2");
	
	JButton noriDeleteBtn = new JButton("-");
	JLabel noriNumLabel = new JLabel("0");
	JButton noriAddBtn = new JButton("+");
	
	JButton eggDeleteBtn = new JButton("-");
	JLabel eggNumLabel = new JLabel("0");
	JButton eggAddBtn = new JButton("+");
	
	JButton bambooDeleteBtn = new JButton("-");
	JLabel bambooNumLabel = new JLabel("0");
	JButton bambooAddBtn = new JButton("+");
	
	JButton chashuDeleteBtn = new JButton("-");
	JLabel chashuNumLabel = new JLabel("0");
	JButton chashuAddBtn = new JButton("+");
	
	
	
	
	
	String addName[] = { "nori", "boiledEgg", "chashu", "bamboo" };
	String addText[] = { "Nori(+1)", "Boiled egg(+1)", "Chashu(+2)", "Bamboo shoots(+1)" };
	JCheckBox addBox[] = new JCheckBox[4];
	Double ramenPrice = ramen.price;
	JButton ramenSubmitBtn = new JButton("Add");
	JButton resetBtn = new JButton("Reset");
	JLabel ramenPriceLabel = new JLabel(
			"Price:                                                                   " + ramenPrice);

	public RamenUI(MainUI main) {
		this.main = main;
		frame = main.frame;
		id = main.id; 
		
		DishInfoImpl dii = new DishInfoImpl();
		available = dii.readAvail();
		price = dii.readDishPrice();
		
		// main Panel Controls:
		ramenPanel.setLayout(null);
		ramenPanel.setBounds(0, 0, 680, 900);
		ramenPanel.setOpaque(false);

		ramenReturnBtn.setBorderPainted(false);
		ramenReturnBtn.setContentAreaFilled(false);
		ramenReturnBtn.setBounds(10, 15, 32, 32);
		ramenPanel.add(ramenReturnBtn);

		resetBtn.setBounds(50, 730, 280, 40);
//		resetBtn.setBackground(Color.GRAY);
//		resetBtn.setForeground(Color.white);
		resetBtn.setContentAreaFilled(false);
		resetBtn.setFont(new Font("Times New Roman", 2, 22));

		ramenSubmitBtn.setBounds(340, 730, 280, 40);
//		ramenSubmitBtn.setBackground(Color.GRAY);
//		ramenSubmitBtn.setForeground(Color.white);
		ramenSubmitBtn.setContentAreaFilled(false);
		ramenSubmitBtn.setFont(new Font("Times New Roman", 2, 22));
		ramenPriceLabel.setFont(new Font("Times New Roman", 2, 26));

		ramenPriceLabel.setOpaque(false);
		ramenPriceLabel.setBounds(50, 700, 630, 30);

		ramenPanel.add(ramenSubmitBtn);
		ramenPanel.add(resetBtn);
		ramenPanel.add(ramenPriceLabel);

		ramenReturnBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setInvisible();
				main.mainPanel.setVisible(true);
				main.mainBGD.setVisible(true);
			}
		});

		resetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetRamen();
			}
		});

		ramenSubmitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (formValidation()) {
					setInvisible();
//					System.out.println("In ramenSubitBtn listener!");
					setRamen();
					submitFunction();
				}
			}
		});

		// Selection Panel Controls:
		ramenPanel2.setLayout(null);
		ramenPanel2.setBounds(50, 50, 580, 450);
		ramenPanel2.setBorder(BorderFactory.createTitledBorder(null, "Custom options", TitledBorder.LEFT,
				TitledBorder.TOP, new Font("Times New Roman", 2, 26)));

		soupLabel.setBounds(30, 20, 64, 64);
		soupText.setBounds(40, 75, 50, 30);
		soupText.setFont(new Font("Times New Roman", 2, 20));
		for (int i = 0; i < 3; i++) {
			soupBtn[i] = new JRadioButton(soupName[i]);
			soupBtn[i].setBounds(120 + 140 * i, 50, 120, 30);
			soupBtn[i].setFont(new Font("Times New Roman", 2, 25));
			soupBtn[i].setContentAreaFilled(false);
			soupGroup.add(soupBtn[i]);
			ramenPanel2.add(soupBtn[i]);
		}
		
		
		ramenPanel2.add(soupLabel);
		ramenPanel2.add(soupText);

		noodleLabel.setBounds(30, 105, 64, 64);
		noodleText.setBounds(35, 165, 70, 30);
		noodleText.setFont(new Font("Times New Roman", 2, 20));
		for (int i = 0; i < 3; i++) {
			noodleBtn[i] = new JRadioButton(noodleName[i]);
			noodleBtn[i].setBounds(120 + 140 * i, 135, 120, 30);
			noodleBtn[i].setFont(new Font("Times New Roman", 2, 25));
			noodleBtn[i].setContentAreaFilled(false);
			noodleGroup.add(noodleBtn[i]);
			ramenPanel2.add(noodleBtn[i]);
		}
		ramenPanel2.add(noodleLabel);
		ramenPanel2.add(noodleText);

		onionLabel.setBounds(30, 195, 64, 64);
		onionText.setBounds(15, 245, 110, 30);
		onionText.setFont(new Font("Times New Roman", 2, 18));
		for (int i = 0; i < 3; i++) {
			onionBtn[i] = new JRadioButton(onionName[i]);
			onionBtn[i].setBounds(120 + 140 * i, 220, 120, 30);
			onionBtn[i].setFont(new Font("Times New Roman", 2, 25));
			onionBtn[i].setContentAreaFilled(false);
			onionGroup.add(onionBtn[i]);
			ramenPanel2.add(onionBtn[i]);
		}
		
		ramenPanel2.add(onionLabel);
		ramenPanel2.add(onionText);

		spicyLabel.setBounds(30, 275, 64, 64);
		spicyText.setBounds(25, 330, 80, 30);
		spicyText.setFont(new Font("Times New Roman", 2, 20));
		for (int i = 0; i < 6; i++) {
			spicyBtn[i] = new JRadioButton(spicyName[i]);
			spicyBtn[i].setBounds(spicyLocation[i], 295, spicyWidth[i], 30);
			spicyBtn[i].setFont(new Font("Times New Roman", 2, 25));
			spicyBtn[i].setContentAreaFilled(false);
			spicyGroup.add(spicyBtn[i]);
			ramenPanel2.add(spicyBtn[i]);
		}
		ramenPanel2.add(spicyLabel);
		ramenPanel2.add(spicyText);

		noriLabel1.setBounds(30, 355, 64, 64);
		noriText1.setBounds(40, 410, 80, 30);
		noriText1.setFont(new Font("Times New Roman", 2, 22));
		noriBox.setBounds(90, 380, 70, 30);
		noriBox.setFont(new Font("Times New Roman", 2, 26));
		noriBox.setContentAreaFilled(false);
		ramenPanel2.add(noriLabel1);
		ramenPanel2.add(noriText1);
		ramenPanel2.add(noriBox);
		
		
		
		eggLabel1.setBounds(190, 355, 64, 64);
		eggText1.setBounds(180, 410, 90, 30);
		eggText1.setFont(new Font("Times New Roman", 2, 20));
		eggBox.setBounds(250, 380, 70, 30);
		eggBox.setFont(new Font("Times New Roman", 2, 26));
		eggBox.setContentAreaFilled(false);
		ramenPanel2.add(eggBox);
		ramenPanel2.add(eggLabel1);
		ramenPanel2.add(eggText1);
		
		chashuLabel1.setBounds(350, 355, 64, 64);
		chashuText1.setBounds(350, 415, 80, 30);
		chashuText1.setFont(new Font("Times New Roman", 2, 21));
		chashuBox.setBounds(420, 380, 70, 30);
		chashuBox.setFont(new Font("Times New Roman", 2, 26));
		chashuBox.setContentAreaFilled(false);
		ramenPanel2.add(chashuBox);
		ramenPanel2.add(chashuLabel1);
		ramenPanel2.add(chashuText1);
		
		
		
		// Add Panel Controls:
		//ramenPanel3.setVisible(false);
		ramenPanel3.setLayout(null);
		ramenPanel3.setBorder(BorderFactory.createTitledBorder(null, "Add-on(Extra)", TitledBorder.LEFT, TitledBorder.TOP,
				new Font("Times New Roman", 2, 26)));
		ramenPanel3.setBounds(50, 510, 580, 190);

		noriLabel.setBounds(45, 50, 100, 30);
		noriLabel.setText("Nori $" + ramen.nori.getPrice());
		noriLabel.setFont(new Font("Times New Roman", 2, 23));
		
		noriDeleteBtn.setBounds(150, 55, 40, 24);
		noriDeleteBtn.setBackground(Color.gray);
		noriDeleteBtn.setForeground(Color.white);
		noriDeleteBtn.setFont(new Font("Times New Roman", 2, 24));
		noriDeleteBtn.setMargin(new Insets(0, 0, 0, 0));
		
		noriNumLabel.setBounds(200, 55, 20, 20);
		noriNumLabel.setFont(new Font("Times New Roman", 2, 24));
		
		noriAddBtn.setBounds(225, 55, 40, 24);
		noriAddBtn.setBackground(Color.gray);
		noriAddBtn.setForeground(Color.white);
		noriAddBtn.setFont(new Font("Times New Roman", 2, 24));
		noriAddBtn.setMargin(new Insets(0, 0, 0, 0));
		
		ramenPanel3.add(noriLabel);
		ramenPanel3.add(noriDeleteBtn);
		ramenPanel3.add(noriNumLabel);
		ramenPanel3.add(noriAddBtn);
		
		noriDeleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				decreaseQuantity("nori");
			}
		});
		
		noriAddBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				increaseQuantity("nori");
			}
		});
		
		eggLabel.setText("Boiled egg $" + ramen.boiledEgg.getPrice());
		eggLabel.setBounds(280, 50, 150, 30);
		eggLabel.setFont(new Font("Times New Roman", 2, 23));
		
		eggDeleteBtn.setBounds(440, 55, 40, 24);
		eggDeleteBtn.setBackground(Color.gray);
		eggDeleteBtn.setForeground(Color.white);
		eggDeleteBtn.setFont(new Font("Times New Roman", 2, 24));
		eggDeleteBtn.setMargin(new Insets(0, 0, 0, 0));
		
		eggNumLabel.setBounds(490, 55, 20, 20);
		eggNumLabel.setFont(new Font("Times New Roman", 2, 24));
		
		eggAddBtn.setBounds(515, 55, 40, 24);
		eggAddBtn.setBackground(Color.gray);
		eggAddBtn.setForeground(Color.white);
		eggAddBtn.setFont(new Font("Times New Roman", 2, 24));
		eggAddBtn.setMargin(new Insets(0, 0, 0, 0));
		
		ramenPanel3.add(eggLabel);
		ramenPanel3.add(eggDeleteBtn);
		ramenPanel3.add(eggNumLabel);
		ramenPanel3.add(eggAddBtn);
		
		eggDeleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				decreaseQuantity("boiledEgg");
			}
		});
		
		eggAddBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				increaseQuantity("boiledEgg");
			}
		});
		
		chashuLabel.setText("Chashu $" + ramen.chashu.getPrice());
		chashuLabel.setBounds(20, 120, 120, 30);
		chashuLabel.setFont(new Font("Times New Roman", 2, 23));
		
		chashuDeleteBtn.setBounds(150, 125, 40, 24);
		chashuDeleteBtn.setBackground(Color.gray);
		chashuDeleteBtn.setForeground(Color.white);
		chashuDeleteBtn.setFont(new Font("Times New Roman", 2, 24));
		chashuDeleteBtn.setMargin(new Insets(0, 0, 0, 0));
		
		chashuNumLabel.setBounds(200, 125, 20, 20);
		chashuNumLabel.setFont(new Font("Times New Roman", 2, 24));
		
		chashuAddBtn.setBounds(225, 125, 40, 24);
		chashuAddBtn.setBackground(Color.gray);
		chashuAddBtn.setForeground(Color.white);
		chashuAddBtn.setFont(new Font("Times New Roman", 2, 24));
		chashuAddBtn.setMargin(new Insets(0, 0, 0, 0));
		
		ramenPanel3.add(chashuLabel);
		ramenPanel3.add(chashuDeleteBtn);
		ramenPanel3.add(chashuNumLabel);
		ramenPanel3.add(chashuAddBtn);
		
		chashuDeleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				decreaseQuantity("chashu");
			}
		});
		
		chashuAddBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				increaseQuantity("chashu");
			}
		});
		
		bambooLabel.setText("Bamboo $" + ramen.bamboo.getPrice());
		bambooLabel.setBounds(285, 120, 135, 30);
		bambooLabel.setFont(new Font("Times New Roman", 2, 23));
		
		bambooDeleteBtn.setBounds(440, 125, 40, 24);
		bambooDeleteBtn.setBackground(Color.gray);
		bambooDeleteBtn.setForeground(Color.white);
		bambooDeleteBtn.setFont(new Font("Times New Roman", 2, 24));
		bambooDeleteBtn.setMargin(new Insets(0, 0, 0, 0));
		
		bambooNumLabel.setBounds(490, 125, 20, 20);
		bambooNumLabel.setFont(new Font("Times New Roman", 2, 24));
		
		bambooAddBtn.setBounds(515, 125, 40, 24);
		bambooAddBtn.setBackground(Color.gray);
		bambooAddBtn.setForeground(Color.white);
		bambooAddBtn.setFont(new Font("Times New Roman", 2, 24));
		bambooAddBtn.setMargin(new Insets(0, 0, 0, 0));
		
		ramenPanel3.add(bambooLabel);
		ramenPanel3.add(bambooDeleteBtn);
		ramenPanel3.add(bambooNumLabel);
		ramenPanel3.add(bambooAddBtn);
		
		
		{	//设置available信息
			if (available[0].equals("0")) {
				soupBtn[0].setSelected(false);
				soupBtn[0].setEnabled(false);
			}
			if (available[1].equals("0")) {
				soupBtn[1].setSelected(false);
				soupBtn[1].setEnabled(false);
			}
			if (available[2].equals("0")) {
				soupBtn[2].setSelected(false);
				soupBtn[2].setEnabled(false);
			}
			
			if (available[3].equals("0")) {
				noodleBtn[0].setSelected(false);
				noodleBtn[0].setEnabled(false);
			}
			if (available[4].equals("0")) {
				noodleBtn[1].setSelected(false);
				noodleBtn[1].setEnabled(false);
			}
			if (available[5].equals("0")) {
				noodleBtn[2].setSelected(false);
				noodleBtn[2].setEnabled(false);
			}
			
			if (available[6].equals("0")) {
				onionBtn[0].setSelected(false);
				onionBtn[0].setEnabled(false);
			}
			if (available[7].equals("0")) {
				onionBtn[1].setSelected(false);
				onionBtn[1].setEnabled(false);
			}
			if (available[8].equals("0")) {
				onionBtn[2].setSelected(false);
				onionBtn[2].setEnabled(false);
			}
			
			if (available[9].equals("0")) {
				spicyBtn[0].setSelected(false);
				spicyBtn[0].setEnabled(false);
			}
			if (available[10].equals("0")) {
				spicyBtn[1].setSelected(false);
				spicyBtn[1].setEnabled(false);
			}
			if (available[11].equals("0")) {
				spicyBtn[2].setSelected(false);
				spicyBtn[2].setEnabled(false);
			}
			if (available[12].equals("0")) {
				spicyBtn[3].setSelected(false);
				spicyBtn[3].setEnabled(false);
			}
			if (available[13].equals("0")) {
				spicyBtn[4].setSelected(false);
				spicyBtn[4].setEnabled(false);
			}
			if (available[14].equals("0")) {
				spicyBtn[5].setSelected(false);
				spicyBtn[5].setEnabled(false);
			}
			
			if (available[15].equals("0")) {
				noriBox.setSelected(false);
				noriBox.setEnabled(false);
				noriAddBtn.setEnabled(false);
				noriDeleteBtn.setEnabled(false);
			}
			if (available[16].equals("0")) {
				eggBox.setSelected(false);
				eggBox.setEnabled(false);
				eggAddBtn.setEnabled(false);
				eggDeleteBtn.setEnabled(false);
			}
			if (available[17].equals("0")) {
				bambooAddBtn.setEnabled(false);
				bambooDeleteBtn.setEnabled(false);
			}
			if (available[18].equals("0")) {
				chashuBox.setSelected(false);
				chashuBox.setEnabled(false);
				chashuAddBtn.setEnabled(false);
				chashuDeleteBtn.setEnabled(false);
			}
			
			
		}
		
		bambooDeleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				decreaseQuantity("bamboo");
			}
		});
		
		bambooAddBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				increaseQuantity("bamboo");
			}
		});


		ramenPanel2.setVisible(true);
		//ramenPanel3.setVisible(true);
		ramenPanel.setVisible(true);
		
		ramenPanel.setOpaque(false);
		ramenPanel2.setOpaque(false);
		ramenPanel3.setOpaque(false);
		
		backLabel.setBounds(0, 0, 680, 900);
		main.frame.getLayeredPane().add(backLabel, -2);
		main.frame.getLayeredPane().add(ramenPanel2, 0);
		main.frame.getLayeredPane().add(ramenPanel3, 0);
		main.frame.getLayeredPane().add(ramenPanel, 0);

	}

	/**
	 * This method submit the ramen information to payment interface
	 */
	public void submitFunction() {
		PaymentUI order = new PaymentUI(this, ramen);
	}

	/**
	 * This method set invisibility for several panels
	 */
	public void setInvisible() {
		backLabel.setVisible(false);
		ramenPanel.setVisible(false);
		ramenPanel2.setVisible(false);
		ramenPanel3.setVisible(false);
	}

	/**
	 * This method is used to validate the if the information set in form are all reasonable
	 * @return a boolean value, if true, pass the validation
	 */
	public boolean formValidation() {

		for (int i = 0; i < 3; i++) {
			if (soupBtn[i].isSelected()) {
				break;
			}
			if (i == 2) {
				JOptionPane.showMessageDialog(ramenPanel2, "Please choose soup type!", "Warning",
						JOptionPane.WARNING_MESSAGE);
				return false;
			}
		}

		for (int i = 0; i < 3; i++) {
			if (noodleBtn[i].isSelected()) {
				break;
			}
			if (i == 2) {
				JOptionPane.showMessageDialog(ramenPanel2, "Please choose noodle type!", "Warning",
						JOptionPane.WARNING_MESSAGE);
				return false;
			}
		}

		for (int i = 0; i < 3; i++) {
			if (onionBtn[i].isSelected()) {
				break;
			}
			if (i == 2) {
				JOptionPane.showMessageDialog(ramenPanel2, "Please choose onion type!", "Warning",
						JOptionPane.WARNING_MESSAGE);
				return false;
			}
		}

		for (int i = 0; i < 6; i++) {
			if (spicyBtn[i].isSelected()) {
				break;
			}
			if (i == 5) {
				JOptionPane.showMessageDialog(ramenPanel2, "Please choose spiciness!", "Warning",
						JOptionPane.WARNING_MESSAGE);
				return false;
			}
		}

		return true;
	}

	/**
	 * This method is used to set some property of ramen object
	 */
	public void setRamen() {

		for (int i = 0; i < 3; i++) {
			if (soupBtn[i].isSelected()) {
				ramen.soup = new SideDish("soup", soupName[i]);
			}
		}

		for (int i = 0; i < 3; i++) {
			if (noodleBtn[i].isSelected()) {
				ramen.noodle = new SideDish("noodle", noodleName[i]);
			}
		}

		for (int i = 0; i < 3; i++) {
			if (onionBtn[i].isSelected()) {
				ramen.onion = new SideDish("onion", onionName[i]);
			}
		}

		for (int i = 0; i < 6; i++) {
			if (spicyBtn[i].isSelected()) {
				ramen.spiciness = i;
			}
		}
		
		if (noriBox.isSelected()) {
			ramen.nori.setQuantity(ramen.nori.getQuantity()+1);
		}
		
		if (eggBox.isSelected()) {
			ramen.boiledEgg.setQuantity(ramen.boiledEgg.getQuantity()+1);
		}
		if (chashuBox.isSelected()) {
			ramen.chashu.setQuantity(ramen.chashu.getQuantity()+1);
		}
		

	}

	/** 
	 * This method is used to reset all the property of ramen object
	 */
	public void resetRamen() {

		soupGroup.clearSelection();
		noodleGroup.clearSelection();
		onionGroup.clearSelection();
		spicyGroup.clearSelection();
		
		noriBox.setSelected(true);
		eggBox.setSelected(true);
		chashuBox.setSelected(true);
		
		ramen.nori.setQuantity(0);
		ramen.bamboo.setQuantity(0);
		ramen.boiledEgg.setQuantity(0);
		ramen.chashu.setQuantity(0);
		
		noriNumLabel.setText("0");
		eggNumLabel.setText("0");
		bambooNumLabel.setText("0");
		chashuNumLabel.setText("0");
		ramen.price = 9.9;
		ramenPrice = ramen.price;
		ramenPriceLabel
				.setText("Price:                                                                   " + ramenPrice);
	}
	
	/**
	 * This method is used to decrease quantity of each side dish
	 * @param type reference different kinds of side dish
	 */
	public void decreaseQuantity(String type) {
		if (type.equals("nori")) {
			if (ramen.nori.getQuantity() != 0) {
				ramen.nori.setQuantity(ramen.nori.getQuantity()-1);
				ramen.price -= ramen.nori.getPrice();
				noriNumLabel.setText(String.valueOf(ramen.nori.getQuantity()));
			}
		} else if (type.equals("boiledEgg")) {
			if (ramen.boiledEgg.getQuantity() != 0) {
				ramen.boiledEgg.setQuantity(ramen.boiledEgg.getQuantity()-1);
				ramen.price -= ramen.boiledEgg.getPrice();
				eggNumLabel.setText(String.valueOf(ramen.boiledEgg.getQuantity()));
			}
		} else if (type.equals("bamboo")) {
			if (ramen.bamboo.getQuantity() != 0) {
				ramen.bamboo.setQuantity(ramen.bamboo.getQuantity()-1);
				ramen.price -= ramen.bamboo.getPrice();
				bambooNumLabel.setText(String.valueOf(ramen.bamboo.getQuantity()));
			}
		} else {
			if (ramen.chashu.getQuantity() != 0) {
				ramen.chashu.setQuantity(ramen.chashu.getQuantity()-1);
				ramen.price -= ramen.chashu.getPrice();
				chashuNumLabel.setText(String.valueOf(ramen.chashu.getQuantity()));
			}
		}
		
		ramenPriceLabel
		.setText("Price:                                                                   " + df.format(ramen.price));
	
	}
	
	/**
	 * This method is used to increase quantity of each side dish
	 * @param type reference different kinds of side dish
	 */
	public void increaseQuantity(String type) {
		if (type.equals("nori")) {
			if (ramen.nori.getQuantity() < 9) {
				ramen.nori.setQuantity(ramen.nori.getQuantity()+1);
				ramen.price += ramen.nori.getPrice();
				noriNumLabel.setText(String.valueOf(ramen.nori.getQuantity()));
			}
		} else if (type.equals("boiledEgg")) {
			if (ramen.boiledEgg.getQuantity() < 9) {
				ramen.boiledEgg.setQuantity(ramen.boiledEgg.getQuantity()+1);
				ramen.price += ramen.boiledEgg.getPrice();
				eggNumLabel.setText(String.valueOf(ramen.boiledEgg.getQuantity()));
			}
		} else if (type.equals("bamboo")) {
			if (ramen.bamboo.getQuantity() < 9) {
				ramen.bamboo.setQuantity(ramen.bamboo.getQuantity()+1);
				ramen.price += ramen.bamboo.getPrice();
				bambooNumLabel.setText(String.valueOf(ramen.bamboo.getQuantity()));
			}
		} else if (type.equals("chashu")) {
			if (ramen.chashu.getQuantity() < 9) {
				ramen.chashu.setQuantity(ramen.chashu.getQuantity()+1);
				ramen.price += ramen.chashu.getPrice();
				chashuNumLabel.setText(String.valueOf(ramen.chashu.getQuantity()));
			}
		}
		
		ramenPriceLabel.setText("Price:                                                                   " + df.format(ramen.price));
		
	}

}

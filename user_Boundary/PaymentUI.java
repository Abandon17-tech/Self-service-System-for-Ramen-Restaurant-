package user_Boundary;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;

import impl_Control.ListDAOImpl;
import impl_Control.UserDAOImpl;
import servlet_Control.ListOperation;
import vo_Entity.Ramen;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/** 
 * This class create the payment interface for user
 * @author Zhenghui Wang
 * @version 1.1
 * 
 */
public class PaymentUI {

	/**
	 * @param frame core frame of the software, it is referenced from MainUI 
	 * @param main a MainUI object referenced from MainUI
	 * @param serialNum the serial number of an order
	 * @param time current time 
	 * @param id user id
	 */
	
	// test code
	JFrame frame;
	MainUI main;

	// parameter
	String serialNum;
	String time;
	String id = "";
	String diningMethod = "Eat-in";
	String payingMethod = "Cash";
	String[] columnNames = { "Add-on", "Availability", "Quantity", "Price" };

	String diningText[] = { "Eat-in", "Take away" };
	String payingText[] = { "Cash", "QR Code" };

	int diningPara = 0;
	int payingPara = 0;

	// top panel controls
	JPanel paymentPanel1 = new JPanel();

	JButton returnBtn = new JButton("return");
	// JLabel titleLabel = new JLabel("Payment Interface");

	// payment panel controls
	JPanel paymentPanel2 = new JPanel();

	JLabel serialLabel = new JLabel();
	JLabel timeLabel = new JLabel();
	JLabel idLabel = new JLabel();
	ImageIcon ramenImg = new ImageIcon("src/images/ramenDemo.jpg");
	JLabel ramenLabel = new JLabel(ramenImg);
	JLabel soupLabel = new JLabel();
	JLabel noodleLabel = new JLabel();
	JLabel onionLabel = new JLabel();
	JLabel spicinessLabel = new JLabel();

	JPanel embeddedPanel = new JPanel();

	JLabel priceLabel = new JLabel();

	// extra options controls
	JPanel paymentPanel3 = new JPanel();

	JLabel diningLabel = new JLabel("Dining option:");
	JButton diningBtn[] = new JButton[3];
	JLabel payingLabel = new JLabel("Paying option:");
	JButton payingBtn[] = new JButton[3];

	// submit button
	JButton submitBtn = new JButton("Submit");

	@SuppressWarnings("null")
	public PaymentUI(RamenUI ramenUI, Ramen ramen) {
		
		ListOperation lo = new ListOperation();
		serialNum = lo.getSerialNum();
		time = lo.getTime();
		this.main = ramenUI.main;
		id = ramenUI.id;
		initUI(ramen);

		ramenUI.frame.getLayeredPane().add(paymentPanel1, 0);
		ramenUI.frame.getLayeredPane().add(paymentPanel2, 0);
		ramenUI.frame.getLayeredPane().add(embeddedPanel, 0);
		ramenUI.frame.getLayeredPane().add(paymentPanel3, 0);
		ramenUI.frame.setVisible(true);

		// button listener design
		returnBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ramen.price = 9.9;
				paymentPanel1.setVisible(false);
				paymentPanel2.setVisible(false);
				paymentPanel3.setVisible(false);
				embeddedPanel.setVisible(false);
				ramenUI.ramenPanel.setVisible(true);
				ramenUI.ramenPanel2.setVisible(true);
				ramenUI.ramenPanel3.setVisible(true);
			}
		});

	}

	public PaymentUI(PersonalCenterUI personalCenterUI, Ramen ramen) {
		
		ListOperation lo = new ListOperation();
		serialNum = lo.getSerialNum();
		time = lo.getTime();

		this.main = personalCenterUI.main;
		id = personalCenterUI.id;
		initUI(ramen);

		personalCenterUI.frame.getLayeredPane().add(paymentPanel1, 0);
		personalCenterUI.frame.getLayeredPane().add(paymentPanel2, 0);
		personalCenterUI.frame.getLayeredPane().add(embeddedPanel, 0);
		personalCenterUI.frame.getLayeredPane().add(paymentPanel3, 0);
		personalCenterUI.frame.setVisible(true);

		// button listener design
		returnBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ramen.price = 9.9;
				paymentPanel1.setVisible(false);
				paymentPanel2.setVisible(false);
				paymentPanel3.setVisible(false);
				embeddedPanel.setVisible(false);

				personalCenterUI.centerPanel.setVisible(true);
				personalCenterUI.historyPanel.setVisible(true);
				personalCenterUI.mainPanel.setVisible(true);

			}
		});

	}

	public void fillRamen(Ramen ramen) {
		
		

		if (payingPara == 1)
			ramen.setPayingMethod("Cash");
		else
			ramen.setPayingMethod("QR Code");
		if (diningPara == 1)
			ramen.setDiningMethod("Eat-in");
		else
			ramen.setDiningMethod("Take away");
		
		if (!id.equals("")) {
			ramen.setId(id);
			ramen.setSerialNumer(serialNum);
			ramen.setTime(time);
		} else {
			ramen.setId("11111111");
			ramen.setSerialNumer(serialNum);
			ramen.setTime(time);
		}
	}

	/**
	 * This method initiates the payment interface 
	 * @param ramen a ramen object
	 */
	public void initUI(Ramen ramen) {
		
		ListOperation lo = new ListOperation();
		serialNum = lo.getSerialNum();
		time = lo.getTime();
		
		// Data Processing
		double priceText[] = {0, 0, 0, 0};
		int quantityText[] = {0, 0, 0, 0};
		if (ramen.nori.getQuantity()>0) { 
			quantityText[0] = ramen.nori.getQuantity()-1;
			priceText[0] = ramen.nori.getPrice() * (ramen.nori.getQuantity() - 1);
		}
		if (ramen.boiledEgg.getQuantity()>0) {
			quantityText[1] = ramen.boiledEgg.getQuantity()-1;
			priceText[1] = ramen.boiledEgg.getPrice() * (ramen.boiledEgg.getQuantity() - 1);
		}
		quantityText[2] = ramen.bamboo.getQuantity();
		priceText[2] = ramen.bamboo.getQuantity() * ramen.bamboo.getPrice();
		if (ramen.chashu.getQuantity()>0) {
			quantityText[3] = ramen.chashu.getQuantity() - 1;
			priceText[3] = ramen.chashu.getPrice() * (ramen.chashu.getQuantity() - 1);
		}

		String dishAttr[] = { ramen.soup.getType(), ramen.noodle.getType(), ramen.onion.getType(),
				String.valueOf(ramen.spiciness) };

		Object[][] rowData = { { "Nori", ramen.nori.isAvailable(), quantityText[0], "$" + priceText[0] },
				{ "Boiled egg", ramen.boiledEgg.isAvailable(), quantityText[1], "$" + priceText[1] },
				{ "Bamboo shoot", ramen.bamboo.isAvailable(), quantityText[2], "$" + priceText[2] },
				{ "Chashu", ramen.chashu.isAvailable(), quantityText[3], "$" + priceText[3] } };
		JTable table = new JTable(rowData, columnNames);
		JScrollPane JSP = new JScrollPane(table);

		// Code for top panel
		paymentPanel1.setBounds(0, 0, 680, 900);
		paymentPanel1.setOpaque(false);
		paymentPanel1.setLayout(null);

		returnBtn.setBounds(10, 10, 80, 30);

		paymentPanel1.add(returnBtn);

		// Code for payment panel
		paymentPanel2.setBounds(30, 55, 620, 440);
		paymentPanel2.setLayout(null);
		paymentPanel2.setBorder(BorderFactory.createTitledBorder(null, "Payment", TitledBorder.LEFT, TitledBorder.TOP,
				new Font("Times New Roman", 2, 25)));

		serialLabel.setText(
				"Serial Number:                                                                  " + serialNum);
		serialLabel.setBounds(20, 25, 580, 30);
		serialLabel.setFont(new Font("Times New Roman", 2, 20));

		timeLabel.setText("Time:                                                                       " + time);
		timeLabel.setBounds(20, 50, 580, 30);
		timeLabel.setFont(new Font("Times New Roman", 2, 20));

		idLabel.setText("Membership Number:                                                           " + id);
		idLabel.setBounds(20, 75, 580, 30);
		idLabel.setFont(new Font("Times New Roman", 2, 20));

		paymentPanel2.add(serialLabel);
		paymentPanel2.add(timeLabel);
		paymentPanel2.add(idLabel);

		// embedded panel

		embeddedPanel.setBounds(40, 160, 600, 132);
		embeddedPanel.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		embeddedPanel.setLayout(null);

		ramenLabel.setBounds(3, 2, 128, 128);

		soupLabel.setText("Soup:                               " + dishAttr[0]);
		soupLabel.setBounds(160, 0, 460, 30);
		soupLabel.setFont(new Font("Times New Roman", 2, 17));

		noodleLabel.setText("Noodle:                           " + dishAttr[1]);
		noodleLabel.setBounds(160, 30, 460, 30);
		noodleLabel.setFont(new Font("Times New Roman", 2, 17));

		onionLabel.setText("Spring onion:                " + dishAttr[2]);
		onionLabel.setBounds(160, 60, 460, 30);
		onionLabel.setFont(new Font("Times New Roman", 2, 17));

		spicinessLabel.setText("spiciness:                       " + dishAttr[3]);
		spicinessLabel.setBounds(160, 90, 460, 30);
		spicinessLabel.setFont(new Font("Times New Roman", 2, 17));

		embeddedPanel.add(ramenLabel);
		embeddedPanel.add(soupLabel);
		embeddedPanel.add(noodleLabel);
		embeddedPanel.add(onionLabel);
		embeddedPanel.add(spicinessLabel);

		JSP.setBounds(10, 250, 600, 147);
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
		table.setRowHeight(30);
		paymentPanel2.add(JSP);

		priceLabel.setText("Price:    $" + ramen.price);
		priceLabel.setBounds(480, 390, 150, 40);
		priceLabel.setFont(new Font("Times New Roman", 2, 21));
		paymentPanel2.add(priceLabel);

		// Code for extra options
		paymentPanel3.setBounds(30, 500, 620, 120);
		paymentPanel3.setLayout(null);
		paymentPanel3.setBorder(BorderFactory.createTitledBorder(null, "Extra options", TitledBorder.LEFT,
				TitledBorder.TOP, new Font("Times New Roman", 2, 25)));

		diningLabel.setBounds(20, 40, 140, 30);
		diningLabel.setFont(new Font("Times New Roman", 2, 22));
		for (int i = 0; i < 2; i++) {
			diningBtn[i] = new JButton(diningText[i]);
			diningBtn[i].setBounds(200 + 170 * i, 40, 160, 30);
			diningBtn[i].setFont(new Font("Times New Roman", 2, 22));
			paymentPanel3.add(diningBtn[i]);
		}

		payingLabel.setBounds(20, 75, 140, 30);
		payingLabel.setFont(new Font("Times New Roman", 2, 22));
		for (int i = 0; i < 2; i++) {
			payingBtn[i] = new JButton(payingText[i]);
			payingBtn[i].setBounds(200 + 170 * i, 75, 160, 30);
			payingBtn[i].setFont(new Font("Times New Roman", 2, 22));
			paymentPanel3.add(payingBtn[i]);
		}
		// payingBtn[0].setBackground(Color.GRAY);

		paymentPanel3.add(diningLabel);
		paymentPanel3.add(payingLabel);

		// submit button
		submitBtn.setBounds(548, 630, 100, 30);
		paymentPanel1.add(submitBtn);
		
		

		diningBtn[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				diningBtn[0].setBackground(Color.DARK_GRAY);
				diningBtn[0].setForeground(Color.white);
				diningBtn[1].setBackground(null);
				diningBtn[1].setForeground(Color.black);
				diningPara = 1;
			}
		});

		diningBtn[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				diningBtn[1].setBackground(Color.DARK_GRAY);
				diningBtn[1].setForeground(Color.white);
				diningBtn[0].setBackground(null);
				diningBtn[0].setForeground(Color.black);
				diningPara = 2;
			}
		});

		payingBtn[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				payingBtn[0].setBackground(Color.DARK_GRAY);
				payingBtn[0].setForeground(Color.white);
				payingBtn[1].setBackground(null);
				payingBtn[1].setForeground(Color.black);
				payingPara = 1;
			}
		});

		payingBtn[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				payingBtn[1].setBackground(Color.DARK_GRAY);
				payingBtn[1].setForeground(Color.white);
				payingBtn[0].setBackground(null);
				payingBtn[0].setForeground(Color.black);
				payingPara = 2;
			}
		});

		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int bool = 0;
				if (payingPara == 0) {
					System.out.println("Please choose your paying method!");
					JOptionPane.showMessageDialog(paymentPanel1, "Please choose paying method!", "Message",
							JOptionPane.INFORMATION_MESSAGE);
				} else if (diningPara == 0) {
					System.out.println("Please choose your dining method!");
					JOptionPane.showMessageDialog(paymentPanel1, "Please choose dining method!", "Message",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					
						fillRamen(ramen);
						ListDAOImpl ldi = new ListDAOImpl();
						UserDAOImpl ud = new UserDAOImpl();
						try {
							ldi.writeList(ramen);
							if (! id.equals("")) 
								bool = ud.editStamp(id);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					if (bool == 1) {
						JOptionPane.showMessageDialog(paymentPanel1, "This order is free!", "Message",
								JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(paymentPanel1, "Submit Successfully!", "Message",
								JOptionPane.INFORMATION_MESSAGE);
					}
					
					paymentPanel1.setVisible(false);
					paymentPanel2.setVisible(false);
					paymentPanel3.setVisible(false);
					embeddedPanel.setVisible(false);
					
					main.mainBGD.setVisible(true);
					main.mainPanel.setVisible(true);
					
				}
			}
		});

	}

}

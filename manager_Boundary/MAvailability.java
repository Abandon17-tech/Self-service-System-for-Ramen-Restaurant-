package manager_Boundary;
/**
* This class contains the user interface for manager to modify price and availability.
* @author Zhenghui Wang, Liyuan Zhang
*/

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import impl_Control.DishInfoImpl;

public class MAvailability extends JFrame {
	
	JPanel mModifyPnl;
	JLabel[] sortLbl;
	JLabel[] imageLbl;
	JLabel[] nameLbl;
	JRadioButton[] availableRdBtn;
	ButtonGroup[] availableBtnGrp;
	JLabel[] priceLbl;
	JTextField[] priceFld;
	Color myOrange = new Color(224,80,12);
	
	
	
	
	ArrayList<String> dish = new ArrayList<>();	
	DishInfoImpl dii = new DishInfoImpl();
	
	public MAvailability(MLogIn m, JPanel p) {
		
		/**
		 * @param mModifyPnl the JPanel contains all elements for modification
		 * @param sortLbl three JLabels for sort of dishes, including soup, noodles and others
		 * @param imageLbl twelve JLabels for images of dishes
		 * @param nameLbl twelve JLabels for name of dishes
		 * @param availableRdBtn 24 JRadioButtons for available and unavailable of 12 dishes
		 * @param availableBtnGrp 12 ButtonGroup for 12 dishes. Each ButtonGroup contain one "available" RadioButton and one "sold out" RadioButton
		 * @param priceLbl JLabels for fixed price, add-on and free
		 * @param priceFld get modified price
		 */
		
		m.mLogInFrame.setTitle("Modify Menu");
		
		mModifyPnl = new JPanel();
		mModifyPnl.setLayout(null);
		//m.mLogInFrame.add(mModifyPnl);
		m.mLogInFrame.getLayeredPane().add(mModifyPnl, new Integer(0));
		mModifyPnl.setBounds(0, 0, 680, 900);
		
		getDishName();
		String[][] dishname= new String[8][];
		dishname = dii.readOpName();
		
		//sort: noodles, soup, others
		sortLbl = new JLabel[3];
		sortLbl[0] = createLbl(dishname[0][0],Color.red,createFont(3,24),mModifyPnl); 
		sortLbl[1] = createLbl(dishname[1][0],Color.red,createFont(3,24),mModifyPnl); 
		sortLbl[2] = createLbl("Others",Color.red,createFont(3,24),mModifyPnl);
		for(int temp=0; temp<3; temp++) {
			sortLbl[temp].setBounds(30, 204*temp, 600, 68);
		}
		

		imageLbl = new JLabel[12];
		String[] imageURL = new String[12];
		for(int temp=0; temp<12; temp++) {
			imageLbl[temp] = iconLbl("src/manager_Boundary/image/"+temp+".png",mModifyPnl);
		}
		for(int temp=0; temp<3; temp++) {
			imageLbl[temp].setBounds(20+220*temp, 68, 64, 64);
		}
		for(int temp=3; temp<6; temp++) {
			imageLbl[temp].setBounds(-640+220*temp, 272, 64, 64);
		}
		for(int temp=6; temp<9; temp++) {
			imageLbl[temp].setBounds(-1300+220*temp, 476, 64, 64);
		}
		for(int temp=9; temp<12; temp++) {
			imageLbl[temp].setBounds(-1960+220*temp, 660, 64, 64);
		}
		
		
		
		nameLbl = new JLabel[12];
		nameLbl[0] = createLbl(dishname[0][1],myOrange,createFont(1,18),mModifyPnl);
		nameLbl[1] = createLbl(dishname[0][2],myOrange,createFont(1,18),mModifyPnl);
		nameLbl[2] = createLbl(dishname[0][3],myOrange,createFont(1,18),mModifyPnl);
		nameLbl[3] = createLbl(dishname[1][1],myOrange,createFont(1,18),mModifyPnl);
		nameLbl[4] = createLbl(dishname[1][2],myOrange,createFont(1,18),mModifyPnl);
		nameLbl[5] = createLbl(dishname[1][3],myOrange,createFont(1,18),mModifyPnl);
		nameLbl[6] = createLbl(dishname[2][0],myOrange,createFont(1,18),mModifyPnl);//spring onion
		nameLbl[7] = createLbl(dishname[4][0],myOrange,createFont(1,18),mModifyPnl);//nori
		nameLbl[8] = createLbl(dishname[7][0],myOrange,createFont(1,18),mModifyPnl);//chashu
		nameLbl[9] = createLbl(dishname[5][0],myOrange,createFont(1,18),mModifyPnl);//egg
		nameLbl[10] = createLbl(dishname[3][0],myOrange,createFont(1,18),mModifyPnl);//spiciness
		nameLbl[11] = createLbl(dishname[6][0],myOrange,createFont(1,18),mModifyPnl);//bamboo shoots

		for(int temp=0; temp<3; temp++) {
			nameLbl[temp].setBounds(84+220*temp, 48, 136, 20);
		}
		for(int temp=3; temp<6; temp++) {
			nameLbl[temp].setBounds(-576+220*temp, 252, 136, 20);
		}
		for(int temp=6; temp<9; temp++) {
			nameLbl[temp].setBounds(-1236+220*temp, 456, 136, 20);
		}
		for(int temp=9; temp<12; temp++) {
			nameLbl[temp].setBounds(-1896+220*temp, 660, 136, 20);
		}
		
		
		availableRdBtn = new JRadioButton[24];
		availableBtnGrp = new ButtonGroup[12];
		
		int[] previous = new int[12];
		//get previous availability from txt document
		//int[] update = new int[12];
		String[] availableInfo = new String[19];
		availableInfo = dii.readAvail();
		for(int temp3=0; temp3<6; temp3++) {
			previous[temp3] = Integer.parseInt(availableInfo[temp3]);
		}
		previous[6] = Integer.parseInt(availableInfo[8]);//a lot spring onion
		previous[7] = Integer.parseInt(availableInfo[15]);//nori
		previous[8] = Integer.parseInt(availableInfo[18]);//chashu
		previous[9] = Integer.parseInt(availableInfo[16]);//egg
		previous[10] = Integer.parseInt(availableInfo[14]);//spiciness-max
		previous[11] = Integer.parseInt(availableInfo[17]);//bamboo shoots
		/*for(int temp4=0; temp4<12; temp4++) {
			update[temp4]=previous[temp4];
		}*/
		
		//12 radiobuttons for available
		//if available is selected, change unavailable to available
		for(int temp=0; temp<12; temp++) {
			if(previous[temp]==1) {
				availableRdBtn[temp] = new JRadioButton("available",true);
			}else 
				availableRdBtn[temp] = new JRadioButton("available",false);
			availableBtnGrp[temp] = new ButtonGroup();
			mModifyPnl.add(availableRdBtn[temp]);
			availableBtnGrp[temp].add(availableRdBtn[temp]);
			availableRdBtn[temp].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					
					for(int temp1=0;temp1<12;temp1++) {
						if(availableRdBtn[temp1].isSelected()) {
							previous[temp1] = 1;
						}
					}			
				}
			});
		}
		for(int temp=12; temp<24; temp++) {
			if(previous[temp-12]==0) {
				availableRdBtn[temp] = new JRadioButton("sold out", true);
			}else
				availableRdBtn[temp] = new JRadioButton("sold out",false);
			mModifyPnl.add(availableRdBtn[temp]);
			availableBtnGrp[temp-12].add(availableRdBtn[temp]);
			availableRdBtn[temp].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					//String s = "available";
					for(int temp2=12; temp2<24; temp2++){
						if(availableRdBtn[temp2].isSelected()) {
							previous[temp2-12]=0;	
						}
					}
			
				}
			});
		}
		for(int i=0; i<4; i++) {
			for(int j=0; j<3; j++) {
				availableRdBtn[3*i+j].setBounds(84+220*j, 68+204*i, 136, 20);
				availableRdBtn[12+3*i+j].setBounds(84+220*j, 88+204*i, 136, 20);
			}
		}

		
		priceLbl = new JLabel[13];
		for(int temp=0; temp<6; temp++) {
			priceLbl[temp] = createLbl("Fixed-Price",myOrange,createFont(1,13),mModifyPnl);
		}
		priceLbl[6] = createLbl("Free",myOrange,createFont(1,13),mModifyPnl);
		priceLbl[7] = createLbl("Add-On Price:",myOrange,createFont(1,13),mModifyPnl);
		priceLbl[8] = createLbl("Add-On Price:",myOrange,createFont(1,13),mModifyPnl);
		priceLbl[9] = createLbl("Add-On Price:",myOrange,createFont(1,13),mModifyPnl);
		priceLbl[10] = createLbl("Free",myOrange,createFont(1,13),mModifyPnl);
		priceLbl[11] = createLbl("Add-On Price:",myOrange,createFont(1,13),mModifyPnl);

		for(int i=0; i<4; i++) {
			for(int j=0; j<3; j++) {
				priceLbl[3*i+j].setBounds(90+220*j, 108+204*i, 80, 30);
			}
		}
		priceLbl[12] = createLbl("Fixed-Price:",myOrange,createFont(1,13),mModifyPnl);
		priceLbl[12].setBounds(470, 375, 80, 30);
		
		String[] dishprice = new String[5];
		dishprice = dii.readDishPrice();
		priceFld = new JTextField[5];
		priceFld[0] = new JTextField(dishprice[0],10);//fixed
		priceFld[1] = new JTextField(dishprice[1],10);//nori
		priceFld[2] = new JTextField(dishprice[4],10);//chashu
		priceFld[3] = new JTextField(dishprice[2],10);//boiled egg
		priceFld[4] = new JTextField(dishprice[3],10);//bamboo shoots
		for(int temp=0; temp<5; temp++) {
			mModifyPnl.add(priceFld[temp]);
			priceFld[temp].setOpaque(false);
			priceFld[temp].setForeground(myOrange);
		}
		priceFld[0].setBounds(550, 375, 70, 25);
		priceFld[1].setBounds(310, 545, 70, 25);
		priceFld[2].setBounds(530, 545, 70, 25);
		priceFld[3].setBounds(90, 750, 70, 25);
		priceFld[4].setBounds(530, 750, 70, 25);
		
		//return
		ImageIcon returnIcon = new ImageIcon("src/manager_Boundary/image/smallreturn.jpg");
		JButton returnBtn = new JButton(returnIcon);
		mModifyPnl.add(returnBtn);
		returnBtn.setBounds(500,800,100,25);
		returnBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mModifyPnl.setVisible(false);
				p.setVisible(true);
			}
		});
		
		//Save
		ImageIcon saveIcon = new ImageIcon("src/manager_Boundary/image/save.jpg");
		JButton saveBtn = new JButton(saveIcon);
		mModifyPnl.add(saveBtn);
		saveBtn.setBounds(100,800,100,25);
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] money = new String[5];
				for(int i=0; i<5; i++) {
					money[i] = priceFld[i].getText();
				}
				dii.changeprice("Ramen", money[0]);
				dii.changeprice("Extra Nori", money[1]);
				dii.changeprice("Extra boiled egg", money[3]);
				dii.changeprice("Bamboo shoots", money[4]);
				dii.changeprice("Extra Chashu", money[2]);
				
				dii.available(previous[0], 0, 0);//soup Tonkotsu
				dii.available(previous[1], 0, 1);//soup Shoyu
				dii.available(previous[2], 0, 2);//soup Shio
				dii.available(previous[3], 1, 0);//noodle soft
				dii.available(previous[4], 1, 1);//noodle medium
				dii.available(previous[5], 1, 2);//noodle firm
				dii.available(previous[6], 2, 2);//spring onion-a lot
				dii.available(previous[10], 3, 5);//spiciness
				dii.available(previous[7], 4, 0);//nori
				dii.available(previous[9], 5, 0);//egg
				dii.available(previous[11], 6, 0);//bamboo shoots
				dii.available(previous[8], 7, 0);//chashu

				mModifyPnl.setVisible(false);
				p.setVisible(true);
			}
		});
		
	}
	
	public void getDishName() {
				try{
					FileReader fr = new FileReader("src/manager_Boundary/Dishes.txt");
					BufferedReader bf = new BufferedReader(fr);
					String str;
					while((str = bf.readLine())!= null){
						dish.add(str);
					}
					bf.close();
					fr.close();
				}catch(IOException e){
					e.printStackTrace();
				}
				

	}
	
	//Separate read String by : function and return price
	public String getSplit(String str){
			String[] st = str.split(":");
			return st[1];
		}
	//Separate read String by : function and return availability
	public String getSplitAva(String str) {
		String[] st = str.split(":");
		return st[2];
	}

	public JLabel createLbl(String content, Color color, Font font, JPanel panel) {
		JLabel label =new JLabel(content);
		label.setForeground(color);
		label.setFont(font);
		panel.add(label);
		return label;
	}
	public Font createFont(int style, int size) {
		Font font = new Font("Times New Roman",style,size);
		return font;
	}
	public JLabel iconLbl(String address, JPanel panel) {
		ImageIcon icon = new ImageIcon(address);
		JLabel label = new JLabel(icon);
		panel.add(label);
		return label;
	}
	
	
}

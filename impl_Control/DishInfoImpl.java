package impl_Control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


/** 
 * This class is the hub where the dish information is performed
 * @author Dafei Wang
 * @version 1.1
 * 
 */
public class DishInfoImpl {
	
	
	String path = "src/Dishes.txt";
	
	public DishInfoImpl() {
		
	}
	
	/**
	 * This method is used to get each dish or side dish's name
	 * @return dishes' name
	 */
	//Read each option name
	public String[][] readOpName() {
		ArrayList<String> dish = new ArrayList<>();	
		String[][] dishname= new String[8][];
		try{
			FileReader fr = new FileReader(path);
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
		
		dishname[0] = new String[4];
		for(int i=0;i<4;i++) {
			dishname[0][i] = dish.get(6+i).split(":")[1];
		}
		dishname[1] = new String[4];
		for(int i=0;i<4;i++) {
			dishname[1][i] = dish.get(14+i).split(":")[1];
		}
		dishname[2] = new String[4];
		for(int i=0;i<4;i++) {
			dishname[2][i] = dish.get(22+i).split(":")[1];
		}
		dishname[3] = new String[7];
		for(int i=0;i<7;i++) {
			dishname[3][i] = dish.get(29+i).split(":")[1];
		}
		dishname[4] = new String[3];
		for(int i=0;i<3;i++) {
			dishname[4][i] = dish.get(39+i).split(":")[1];
		}
		dishname[5] = new String[3];
		for(int i=0;i<3;i++) {
			dishname[5][i] = dish.get(43+i).split(":")[1];
		}
		dishname[6] = new String[3];
		for(int i=0;i<3;i++) {
			dishname[6][i] = dish.get(47+i).split(":")[1];
		}
		dishname[7] = new String[3];
		for(int i=0;i<3;i++) {
			dishname[7][i] = dish.get(51+i).split(":")[1];
		}

		return dishname;
	}
	
	/**
	 * This method is used to change available information for each side dish
	 * @param a available information
	 * @param i side dish's type
	 * @param j side dish's code
	 */
	//Modify available
	public void  available(int a,int i,int j) {
		String[][] name = readOpName();
		try{
			BufferedReader in=new BufferedReader(new FileReader(path));
        	PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("src/Dishes2.txt")));
        	String line;
        	int judgement = 0;
        	while((line=in.readLine())!=null){
        		int count=0;
        		String[] str=line.split(":");
        		if (judgement == 1) {
        			out.println(line);
        		} else if(str.length>1 && count==0 && name[i][j+1].equals(str[1])) {
        			String[] str1=str[0].split("\\(");
        			String line1=str1[0]+"("+String.valueOf(a)+"):"+str[1];
        			out.println(line1);
        			judgement = 1;
        			count++;
        		}else if(str.length>1 && count==0 && name[i][j+1].equals(str[1])) {
        			String[] str1=str[0].split("\\(");
        			String line1=str1[0]+"("+String.valueOf(a)+"):"+str[1];
        			out.println(line1);
        			judgement = 1;
        			count++;
        		}else if(str.length>1&&count!=0&&name[i][j+1].equals(str[1])) {
        			out.println(line);
        			count--;
        		}else {
        			out.println(line);
        		}
            	
        	}
        	in.close();
        	out.close();
		}catch(IOException e1){
			e1.printStackTrace();
		}

		File file=new File(path);
		file.delete();
		File file2=new File("src/Dishes2.txt");
		file2.renameTo(new File(path));
	}
	
	//Read each dish name
	public String[] readDishName() {
		ArrayList<String> dish = new ArrayList<>();	
		String[] dishname = new String[5];
		try{
			FileReader fr = new FileReader(path);
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
		dishname[0] = dish.get(1).split(":")[1];
		dishname[1] = dish.get(39).split(":")[1];
		dishname[2] = dish.get(43).split(":")[1];
		dishname[3] = dish.get(47).split(":")[1];
		dishname[4] = dish.get(51).split(":")[1];
		return dishname;
	}
	
	/**
	 * This method is used to change side dish's price
	 * @param name side dish's name
	 * @param money new price
	 */
	//Change price
	public void  changeprice(String name,String money) {
		int count=0;
		if(name.equals("Ramen")) {
			count=3;
		}else if(name.equals("Extra Nori")) {
			count=43;
		}else if(name.equals("Extra boiled egg")) {
			count=47;
		}else if(name.equals("Bamboo shoots")) {
			count=51;
		}else if(name.equals("Extra Chashu")) {
			count=55;
		}
		try{
			BufferedReader in=new BufferedReader(new FileReader(path));
        	PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("src/Dishes2.txt")));
        	String line;
        	int countline=1;
        	while((line=in.readLine())!=null){
            	if(countline==count){
            		String[] str=line.split(":");
            		String line1 = str[0]+":"+money;
               		out.println(line1);
            	}else{
                	out.println(line);
           		}
            	countline++;
        	}
        	in.close();
        	out.close();
		}catch(IOException e1){
			e1.printStackTrace();
		}

		File file=new File(path);
		file.delete();
		File file2=new File("src/Dishes2.txt");
		file2.renameTo(new File(path));
	}
	
	public String[] readAvail(){
		
		String[][] avail = new String[8][];
		ArrayList<String> dish = new ArrayList<>();	
		String[] availableInfo = new String[19];
		try{
			FileReader fr = new FileReader(path);
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
		avail[0] = new String[3];
		for(int i=0;i<3;i++) {
			avail[0][i] = String.valueOf(dish.get(7+i).charAt(2));
		}
		
		availableInfo[0] = new String(avail[0][0]);
		availableInfo[1] = new String(avail[0][1]);
		availableInfo[2] = new String(avail[0][2]);
		
		avail[1] = new String[3];
		for(int i=0;i<3;i++) {
			avail[1][i] = String.valueOf(dish.get(15+i).charAt(2));
		}
		
		availableInfo[3] = new String(avail[1][0]);
		availableInfo[4] = new String(avail[1][1]);
		availableInfo[5] = new String(avail[1][2]);
		
		avail[2] = new String[3];
		for(int i=0;i<3;i++) {
			avail[2][i] = String.valueOf(dish.get(23+i).charAt(2));
		}
		
		availableInfo[6] = new String(avail[2][0]);
		availableInfo[7] = new String(avail[2][1]);
		availableInfo[8] = new String(avail[2][2]);
		
		avail[3] = new String[6];
		for(int i=0;i<6;i++) {
			avail[3][i] = String.valueOf(dish.get(30+i).charAt(2));
		}
		
		availableInfo[9] = new String(avail[3][0]);
		availableInfo[10] = new String(avail[3][1]);
		availableInfo[11] = new String(avail[3][2]);
		availableInfo[12] = new String(avail[3][3]);
		availableInfo[13] = new String(avail[3][4]);
		availableInfo[14] = new String(avail[3][5]);
		
		avail[4] = new String[2];
		for(int i=0;i<2;i++) {
			avail[4][i] = String.valueOf(dish.get(40+i).charAt(9));
		}
		avail[5] = new String[2];
		for(int i=0;i<2;i++) {
			avail[5][i] = String.valueOf(dish.get(44+i).charAt(9));
		}
		avail[6] = new String[2];
		for(int i=0;i<2;i++) {
			avail[6][i] = String.valueOf(dish.get(48+i).charAt(9));
		}
		avail[7] = new String[2];
		for(int i=0;i<2;i++) {
			avail[7][i] = String.valueOf(dish.get(52+i).charAt(9));
		}
		
		availableInfo[15] = new String(avail[4][0]);
		availableInfo[16] = new String(avail[5][0]);
		availableInfo[17] = new String(avail[6][0]);
		availableInfo[18] = new String(avail[7][0]);
		
		return availableInfo;
		
	}
	
	/**
	 * This method is used to read each side dishes' price from database
	 * @return all dishes' price
	 */
	public String[] readDishPrice() {
		ArrayList<String> dish = new ArrayList<>();	
		String[] dishprice = new String[5];
		try{
			FileReader fr = new FileReader(path);
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
		dishprice[0] = dish.get(2).split(":")[1];
		dishprice[1] = dish.get(42).split(":")[1];
		dishprice[2] = dish.get(46).split(":")[1];
		dishprice[3] = dish.get(50).split(":")[1];
		dishprice[4] = dish.get(54).split(":")[1];
		return dishprice;
	}
	
	public static void main(String[] args) {
		DishInfoImpl dii = new DishInfoImpl();
		dii.available(0, 5, 0);
		System.out.println("Successful");
	}
	
}

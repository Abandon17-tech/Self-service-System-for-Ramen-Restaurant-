package servlet_Control;

import java.text.SimpleDateFormat;
import java.util.Date;

/** 
 * This class is the hub where the order operation is performed
 * @author Dafei Wang
 * @version 1.1
 * 
 */
public class ListOperation {

	/**
	 * This method is used to generate Serial number for each order
	 * @return serialNumber
	 */
	public String getSerialNum() {
		String serialNum = "";
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        String str=sdf.format(new Date());
		int rand = (int)(1+Math.random()*1000);
        if (rand<10) serialNum = str + "000" + String.valueOf(rand);
        else if (rand<100) serialNum = str + "00" + String.valueOf(rand);
        else if (rand<1000) serialNum = str + "0" + String.valueOf(rand);
		return serialNum;
	}
	
	/**
	 * This method is used to generate current time for order
	 * @return current time
	 */
	public String getTime() {
		
		Date currentTime = new Date(); 
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = formatter.format(currentTime); 
	
		return str;
	}
	
	/** 
	 * This method is used to get all price information for each side dish
	 * @return
	 */
	public Double[] getPrices() {
		Double[] price = new Double[9];
		// ramen, soup, noodle, onion, spicy, nori, egg, bamboo, chashu
		
		
		// Here is extra code;
		for (int i = 0; i<9; i++) {
			price[i] = new Double("k");
		}
		
		return price;
	}
	
	
	public boolean[] getAvailable() {
		boolean[] available = new boolean[20];
		
		// Here is extra code
		
		return available;
	}
	

}

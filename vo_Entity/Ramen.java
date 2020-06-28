package vo_Entity;

import javax.swing.ImageIcon;

import impl_Control.DishInfoImpl;

/** 
 * This class is set for ramen object
 * @author Yanwei Hua
 * @version 1.1
 * 
 */
public class Ramen {
	
	/**
	 * @param serialNumer the serial number of order
	 * @param id user's id
	 * @param time order generated time
	 * @param name ramen's name
	 * @param price ramen's price
	 * @param spiciness ramen's spiciness
	 * @param soup side dish for ramen
	 * @param noodle side dish for ramen
	 * @param onion side dish for ramen
	 * @param nori side dish for ramen
	 * @param boiledegg side dish for ramen
	 * @param chashu side dish for ramen
	 * @param bamboo side dish for ramen
	 */
	private String serialNumer;
	private String id;
	private String time;
	
	String name;
	public double price = 9.9;
	public int spiciness;
	ImageIcon ramenImg;
	public SideDish soup;
	public SideDish noodle;
	public SideDish onion;
	public SideDish nori;
	public SideDish boiledEgg;
	public SideDish chashu;
	public SideDish bamboo;
	
	private String diningMethod;
	private String payingMethod;


	public Ramen() {
		
		soup = new SideDish("soup");
		noodle = new SideDish("noodle");
		onion = new SideDish("onion");
		nori = new SideDish("nori");
		boiledEgg = new SideDish("boiledEgg");
		chashu = new SideDish("chashu");
		bamboo = new SideDish("bamboo");
		name = "Totoro Ramen";
		ramenImg = new ImageIcon("src/images/pic.png");
		
		String[] dishPrice;
		DishInfoImpl dii = new DishInfoImpl();
		dishPrice = dii.readDishPrice();
		
		price = Double.valueOf(dishPrice[0]);
		nori.setPrice(Double.valueOf(dishPrice[1]));
		boiledEgg.setPrice(Double.valueOf(dishPrice[2]));
		bamboo.setPrice(Double.valueOf(dishPrice[3]));
		chashu.setPrice(Double.valueOf(dishPrice[4]));
		
	}


	public String getSerialNumer() {
		return serialNumer;
	}


	public void setSerialNumer(String serialNumer) {
		this.serialNumer = serialNumer;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	public String getDiningMethod() {
		return diningMethod;
	}


	public void setDiningMethod(String diningMethod) {
		this.diningMethod = diningMethod;
	}


	public String getPayingMethod() {
		return payingMethod;
	}


	public void setPayingMethod(String payingMethod) {
		this.payingMethod = payingMethod;
	}
	
	/**
	 * This method is used to add quantity of side dish
	 * @param type reference different kinds of side dish
	 */
	public void addQuantity(String type) {
		if (type.equals("nori")) {
			this.nori.setQuantity(this.nori.getQuantity()+1);
		} else if (type.equals("egg")) {
			this.boiledEgg.setQuantity(this.boiledEgg.getQuantity()+1);
		} else if (type.equals("bamboo")) {
			this.bamboo.setQuantity(this.bamboo.getQuantity()+1);
		} else if (type.equals("chashu")) {
			this.chashu.setQuantity(this.chashu.getQuantity()+1);
		}
	}
	
	/**
	 * This method is used to decrease quantity of side dish
	 * @param type reference different kinds of side dish
	 */
	public void decreaseQuantity(String type) {
		if (type.equals("nori")) {
			this.nori.setQuantity(this.nori.getQuantity()-1);
		} else if (type.equals("egg")) {
			this.boiledEgg.setQuantity(this.boiledEgg.getQuantity()-1);
		} else if (type.equals("bamboo")) {
			this.bamboo.setQuantity(this.bamboo.getQuantity()-1);
		} else if (type.equals("chashu")) {
			this.chashu.setQuantity(this.chashu.getQuantity()-1);
		}
	}
	
}

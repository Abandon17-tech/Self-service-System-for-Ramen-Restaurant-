package vo_Entity;

/** 
 * This class is set for side dishes referenced to ramen object
 * @author Yanwei Hua
 * @version 1.1
 * 
 */

public class SideDish {
	
	/**
	 * @param name side dish's name
	 * @param type side dish's type
	 * @param quantity side dish's quantity
	 * @param price side dish's price
	 * @param available side dish's available information
	 * 
	 */
	private String name;
	private String type;
	private int quantity;
	private double price;
	private boolean available;
	private boolean isSelected;
	
	
	public SideDish(String name, String type) {
		this.name = name;
		this.type = type;

		this.quantity = 1;
		this.available = true;
		this.isSelected = true;
		
	}
	
	public SideDish(String name) {
		this.name = name;
		this.type = null;
		this.quantity = 0;
		
		this.available = true;
		this.isSelected = false;

	}
	
	public SideDish(String name, double price, boolean available) {
		this.available = available;
		this.name = name;
		this.price = price;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	
	public void setPrice(double price) {
		this.price = price;
	}
	public double getPrice() {
		return price;
	}
	
	public void setAvailable(boolean available) {
		this.available = available;
	}
	public boolean isAvailable() {
		return this.available;
	}
	
	public void setIsSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	public boolean getIsSelected() {
		return isSelected;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}

package vo_Entity;

/** 
 * This class is set for ramen object
 * @author Yuejia Li
 * @version 1.1
 * 
 */

public class User {
	
	/**
	 * @param userId user's id
	 * @param fName user's first name
	 * @param lName user's last name
	 * @param password user's password
	 * @param email user's email
	 * @param phoneNumber user's phone number
	 * @param consumption user's consumption
	 * @param registTime user's register time
	 * @param stamp user's stamp number
	 */
	private String userID;
	private String fName;
	private String lName;
	private String password;
	private String email;
	private String phoneNumber;
	private float consumption;
	private String registTime;
	private int stamp;
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public float getConsumption() {
		return consumption;
	}
	public void setConsumption(float consumption) {
		this.consumption = consumption;
	}
	public String getRegistTime() {
		return registTime;
	}
	public void setRegistTime(String registTime) {
		this.registTime = registTime;
	}
	public int getStamp() {
		return stamp;
	}
	public void setStamp(int stamp) {
		this.stamp = stamp;
	}
	
	@Override
	public String toString() {
		return "User [userID=" + userID + ", fName=" + fName + ", lName=" + lName + ", password=" + password
				+ ", email=" + email + ", phoneNumber=" + phoneNumber + ", consumption=" + consumption + ", registTime="
				+ registTime + ", stamp=" + stamp + "]";
	}
}

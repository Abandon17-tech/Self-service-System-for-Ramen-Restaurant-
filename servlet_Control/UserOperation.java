package servlet_Control;

import java.io.IOException;
import java.text.ParseException;

import impl_Control.ImageMail;
import impl_Control.ListDAOImpl;
import impl_Control.UserDAOImpl;
import vo_Entity.User;

/** 
 * This class is the hub where the user operation is performed
 * @author Yuejia Li
 * @version 1.1
 * 
 */
public class UserOperation {	
	public String username;
	public String password;

	/**
	 * This method is used to set login information
	 * @param username user's input username
	 * @param password user's input password
	 * @return user's id
	 * @throws IOException
	 */
	public String login(String username, String password) throws IOException {
		UserDAOImpl ud = new UserDAOImpl();
		ud.getUsers();
		return ud.login(username, password);
	}

	/**
	 * This method is used for user register
	 * @param fname user's input first name
	 * @param lname user's input last name
	 * @param password user's input password
	 * @param again user's input password again 
	 * @param email user's input email
	 * @param pnumber user's input phone number
	 * @return a integer value to judge if the input is valid
	 * @throws IOException
	 * @throws Exception
	 */
	public String register(String fname, String lname, String password, String again, String email, String pnumber) throws IOException, Exception {
		User u = new User();
		UserDAOImpl ud = new UserDAOImpl();

		u.setfName(fname);
		u.setlName(lname);
		u.setEmail(email);
		u.setPassword(password);
		u.setPhoneNumber(pnumber);

		return ud.register(u);
	}

	public void check() throws IOException {
		UserDAOImpl ud = new UserDAOImpl();
		ud.getUsers();
		System.out.print(ud.checkInformation("14840673"));
	}

	/**
	 * This method is used to change user information
	 * @param id user information
	 * @param k reference to different kinds of information
	 * @param str change to the new string
	 * @throws IOException
	 * @throws Exception
	 */
	public void edit(String id, int k, String str) throws IOException, Exception {
		UserDAOImpl ud = new UserDAOImpl();
		User u = new User();
		u = ud.checkInformation(id);
		ud.getUsers();
		
		if (k == 1) ud.editFile(u.getEmail(), str, id);
		else if (k == 2) ud.editFile(u.getPassword(), str, id);
		else if (k == 3) ud.editFile(u.getPhoneNumber(), str, id);
	}

	public void checkSalesVolume(String id) throws Exception {
		ListDAOImpl ldi = new ListDAOImpl();
		ImageMail im = new ImageMail();
		UserDAOImpl ud = new UserDAOImpl();
		
		User u = ud.checkInformation(id);
		
		ldi.getSalesVolume();	
		im.sendDishMail(u);
	}
}

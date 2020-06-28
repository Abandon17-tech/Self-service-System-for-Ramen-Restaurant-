package impl_Control;

import vo_Entity.User;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.*;

import dao_Control.UserDAO;

/** 
 * This class is the hub where the order list information is performed
 * @author Yuejia Li
 * @version 1.1
 * 
 */
public class UserDAOImpl implements UserDAO {
	private String filepath = "src/user.txt";
	// private ArrayList<User> users = new ArrayList<User>();

	/**
	 * This method is used to get all user information from database
	 * @return arraylist which reference all users
	 * @throws IOException
	 */
	// get information of all users from txt
	public ArrayList<User> getUsers() throws IOException {
		ArrayList<User> users = new ArrayList<User>();

		FileReader fReader = new FileReader(filepath);
		BufferedReader bReader = new BufferedReader(fReader);

		String temp = bReader.readLine();

		while (temp != null) {
			User u = new User();
			// System.out.println(temp);
			String seg[] = temp.split(" ");
			u.setUserID(seg[0]);
			u.setfName(seg[1]);
			u.setlName(seg[2]);
			u.setPassword(seg[3]);
			u.setEmail(seg[4]);
			u.setPhoneNumber(seg[5]);
			u.setConsumption(Float.parseFloat(seg[6]));
			u.setRegistTime(seg[7]);
			u.setStamp(Integer.parseInt(seg[8]));
			users.add(u);
			// System.out.println("user email " + u.getEmail());
			temp = bReader.readLine();
		}
		bReader.close();
		fReader.close();

		return users;
	}

	/**
	 * This method is used to validate user's login
	 * @param account user's username
	 * @param password user's password
	 * @return user's id
	 * @throws IOException
	 */
	// login
	public String login(String account, String password) throws IOException {
		ArrayList<User> users = new ArrayList<User>();

		String bool = "";

		UserDAOImpl ud = new UserDAOImpl();
		boolean acc = false;

		users = ud.getUsers();
		for (int i = 0; i < users.size(); i++) {
			if (account.equals(users.get(i).getUserID()) || account.equals(users.get(i).getEmail())
					|| account.equals(users.get(i).getPhoneNumber())) {
				acc = true;
				if (password.equals(users.get(i).getPassword())) {
					System.out.println("Login successful");
					bool = users.get(i).getUserID();
					break;
				} else {
					System.out.println("The password is wrong!");
					bool = "2";
					break;
				}
			}
		}
		if (!acc) {
			System.out.println("The account doesn't exit!");
			bool = "3";
		}

		return bool;
	}

	/**
	 * This method is used to ensure user can register an account
	 * @param user a user object
	 * @return a integer value, validate if input is reasonable
	 * @throws Exception
	 * @throws IOException
	 */
	// register an account
	public String register(User user) throws Exception, IOException {

		String parameter = "1"; // This parameter represents the registration submission result

		FileWriter fw = null;
		String userID = getUUID();
		String fName = user.getfName();
		String lName = user.getlName();
		String password = user.getPassword();
		String email = user.getEmail();
		String phone = user.getPhoneNumber();
		float consumption = (float) 0.0;
		String registTime = getWebTime();
		int stamp = 0;

		ImageMail im = new ImageMail();

		File f = new File(filepath);
		fw = new FileWriter(f, true);

		PrintWriter pw = new PrintWriter(fw);
		pw.print("\n" + userID + " " + fName + " " + lName + " " + password + " " + email + " " + phone + " "
				+ consumption + " " + registTime + " " + stamp);
		pw.flush();

		fw.flush();
		pw.close();
		fw.close();

		user.setUserID(userID);
		user.setConsumption(consumption);
		user.setRegistTime(registTime);

		System.out.print("id " + user.getUserID());
		if (!(user.getEmail().equals("EmailAddress") || user.getEmail().equals("")))
			im.sendRegisterMail(user);

		return user.getUserID();
	}

	/**
	 * Generate a random 8 digits number for user as id
	 * @return user id
	 */
	// get 8-bit id (use for register
	public String getUUID() {
		String id = null;
		UUID uuid = UUID.randomUUID();
		id = uuid.toString();

		id = id.replace("-", "");
		int num = id.hashCode();
		num = num < 0 ? -num : num;
		id = String.valueOf(num).substring(0, 8);
		return id;
	}

	/**
	 * check if email is valid
	 * @param email user's input email
	 * @return a boolean value, which indicates the reasonability of the email
	 */
	// check the type of the e-mail (use for register
	public boolean checkEmail(String email) {
		boolean tag = true;
		final String pattern1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		final Pattern pattern = Pattern.compile(pattern1);
		final Matcher mat = pattern.matcher(email);

		if (!mat.find()) {
			tag = false;
		}
		return tag;
	}

	// check the type of the phone number (use for register
	public boolean checkPhone(String phone) {
		Pattern pat = Pattern.compile("^[1][3578][0-9]{9}$");
		Matcher mat = pat.matcher(phone);
		return mat.find();
	}

	// get time from web (use for register
	public String getWebTime() {
		String webUrl = "http://www.baidu.com";
		String webTime = getNetworkTime(webUrl);

		return webTime;
	}

	// get time from web (use for register
	public String getNetworkTime(String webUrl) {
		try {
			URL url = new URL(webUrl);
			URLConnection conn = url.openConnection();
			conn.connect();
			long dateL = conn.getDate();
			Date date = new Date(dateL);

			SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
			return dateFormat.format(date);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "";
	}
	/**
	 * This methos is used to check user information by id
	 * @param id user id
	 * @return a user object
	 * @throws IOException
	 */
	// look for information
	public User checkInformation(String id) throws IOException {
		ArrayList<User> users = new ArrayList<User>();
		UserDAOImpl ud = new UserDAOImpl();
		User user = new User();

		users = ud.getUsers();

		// System.out.println(users);
		for (int i = 0; i < users.size(); i++) {
			if (id.equals(users.get(i).getUserID()) || id.equals(users.get(i).getPhoneNumber())) {
				user = users.get(i);
				break;
			}
		}
		return user;
	}

	/**
	 * This method id used to ensure user can edit their information
	 * @param target user information property
	 * @param src initial string
	 * @param id new string
	 * @throws IOException
	 */
	// edit information
	public void editFile(String target, String src, String id) throws IOException {
		ArrayList<User> users = new ArrayList<User>();
		BufferedReader br = null;
		PrintWriter pw = null;
		StringBuffer buff = new StringBuffer();
		String line = System.getProperty("line.separator");
		int l = 0;

		UserDAOImpl ud = new UserDAOImpl();

		users = ud.getUsers();

		for (int i = 0; i < users.size(); i++) {
			if (id.equals(users.get(i).getUserID())) {
				l = i;
				break;
			}
		}

		try {
			br = new BufferedReader(new FileReader(filepath));
			int step = 0;
			for (String str = br.readLine(); str != null; str = br.readLine()) {
				if (step == l) {
					if (str.contains(target)) {
						str = str.replaceAll(target, src);
					}
				}
				buff.append(str + line);
				step++;
			}
			pw = new PrintWriter(new FileWriter(filepath), true);
			pw.print(buff);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if (pw != null)
				pw.close();
		}
	}

	/**
	 * This method is used to change user's stamp number
	 * @param id user id
	 * @return a boolean value
	 * @throws IOException
	 */
	public int editStamp(String id) throws IOException {

		int bool = 0;
		String path = "src/user.txt";
		String temp = "";
		String newStr;

		ArrayList<User> users = new ArrayList<User>();
		User user = new User();
		int l = 0;
		UserDAOImpl ud = new UserDAOImpl();

		users = ud.getUsers();
		for (int i = 0; i < users.size(); i++) {
			if (id.equals(users.get(i).getUserID())) {
				user = users.get(i);
				l = i;
				break;
			}
		}

		String userID = user.getUserID();
		String fName = user.getfName();
		String lName = user.getlName();
		String password = user.getPassword();
		String email = user.getEmail();
		String phone = user.getPhoneNumber();
		float consumption = user.getConsumption();
		String registTime = user.getRegistTime();
		int stamp = user.getStamp();

		if (stamp < 10)
			stamp++;
		else {
			stamp -= 10;
			bool = 1;
		}

		newStr = userID + " " + fName + " " + lName + " " + password + " " + email + " " + phone + " " + consumption
				+ " " + registTime + " " + stamp;

		System.out.println("newStr = " + newStr);
		try {
			File file = new File(path);
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			StringBuffer buf = new StringBuffer();

			for (int j = 0; (temp = br.readLine()) != null; j++) {
				if (j == l) {
					buf = buf.append(newStr);
				} else {
					buf = buf.append(temp);
				}
				buf = buf.append(System.getProperty("line.separator"));
			}

			br.close();
			FileOutputStream fos = new FileOutputStream(file);
			PrintWriter pw = new PrintWriter(fos);
			pw.write(buf.toString().toCharArray());
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return bool;
	}
	
	/**
	 * This method is used to check if email or phone number are repeat
	 * @param input user input string
	 * @return a boolean value, if repeat, return true
	 * @throws IOException
	 */
	// check whether email or phone is repeat
	public boolean checkRepeat(String input) throws IOException {
		boolean repeat = false;
		if (input.equals("EmailAddress") || input.equals("PhoneNumber") || input.equals("")) {
			return repeat;
		}

		ArrayList<User> users = new ArrayList<User>();
		UserDAOImpl ud = new UserDAOImpl();

		users = ud.getUsers();
		for (int i = 0; i < users.size(); i++) {
			if (input.equals(users.get(i).getEmail()) || input.equals(users.get(i).getPhoneNumber())) {
				repeat = true;
				break;
			}
		}

		return repeat;
	}

}

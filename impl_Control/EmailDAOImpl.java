package impl_Control;

import java.util.Date;
import java.util.Properties;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import vo_Entity.User;

/** 
 * This class is the hub where email sent is performed
 * @author Dafei Wang
 * @version 1.1
 * 
 */
public class EmailDAOImpl {
	public static String myEmailAccount="2558542942@qq.com";
	public static String myEmailPassword="dagagimzlplzebjc";
	public static String myEmailSMTPHost="smtp.qq.com";
	
	public static void sendAEmail(User user) throws Exception{
		String receiveMailAccount = user.getEmail();
		
		
		Properties properties=new Properties();
		properties.setProperty("mail.transport.protocol", "smtp");
		properties.setProperty("mail.smtp.host", myEmailSMTPHost);
		properties.setProperty("mail.smtp.auth", "true");
		
		
		Session session=Session.getInstance(properties);
		session.setDebug(true);

		
		MimeMessage message=createMimeMessage(session, myEmailAccount, receiveMailAccount, user);
		

		Transport transport=session.getTransport();
		transport.connect(myEmailAccount, myEmailPassword);
		transport.sendMessage(message,message.getAllRecipients());
		transport.close();
		System.out.println("Send successfully!");
	}



	private static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail, User user)throws Exception {
		// TODO Auto-generated method stub
		MimeMessage message=new MimeMessage(session);
		message.setFrom(new InternetAddress(sendMail, "邮件", "UTF-8"));
		message.setRecipient(MimeMessage.RecipientType.TO,new InternetAddress(receiveMail, "Mary", "UTF-8"));
		message.setSubject("Welcome to Totoro Ramen!","UTF-8");
		message.setText("Welcome to register as a member of the Totoro Ramen! Your membership card number is " + user.getUserID() + ":)");
		message.setSentDate(new Date());
		message.saveChanges();
		return message;
	}

}
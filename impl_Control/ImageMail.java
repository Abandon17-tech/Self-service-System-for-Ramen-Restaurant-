package impl_Control;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import vo_Entity.User;

public class ImageMail {
   private String host = "smtp.qq.com";
   private String port = "25";
   private String userName = "2558542942@qq.com";
   private String password = "dagagimzlplzebjc";

   //email for register
   public void sendRegisterMail(User u) throws Exception {
	  String to = u.getEmail();
      Properties pro = System.getProperties();
      pro.put("mail.smtp.host", host);
      pro.put("mail.smtp.port", port);
      pro.put("mail.smtp.auth", "true");

     
      Session sendMailSession = Session.getDefaultInstance(pro,
            new Authenticator()
            {
               @Override
               protected PasswordAuthentication getPasswordAuthentication()
               {
                  return new PasswordAuthentication(userName, password);
               }
            });
    
      Message mailMessage = new MimeMessage(sendMailSession);
    
      mailMessage.setFrom(new InternetAddress(userName));
    
      mailMessage.setRecipient(Message.RecipientType.TO,
            new InternetAddress(to));
    
      mailMessage.setSubject("Welcome to Totoro Ramen!");
    
      mailMessage.setSentDate(new Date());

    
      Multipart mainPart = new MimeMultipart();
    
      BodyPart html = new MimeBodyPart();
  
      html.setContent(
            "<html><body><div>Dear " + u.getlName() + " " + u.getfName() + " : </div><br>"
            + "<div>Welcome to register as a member of the <b>Totoro Ramen</b>! </div><br>"
            + "<div>Your membership card number is " + u.getUserID() + " :) </div><br><br>"
            + "<img src='https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2700084099,2551090374&fm=26&gp=0.jpg'/></body></html>",
            "text/html; charset=utf-8");
      mainPart.addBodyPart(html);
     
      mailMessage.setContent(mainPart);
     
      Transport.send(mailMessage);
      
      System.out.println("Sending successful!");
   }

   //email for sales of dishes
   public void sendDishMail(User u) throws Exception {
	  String to = u.getEmail();
      Properties pro = System.getProperties();
      pro.put("mail.smtp.host", host);
      pro.put("mail.smtp.port", port);
      pro.put("mail.smtp.auth", "true");
      
      //System.out.println(dishes);

    
      Session sendMailSession = Session.getDefaultInstance(pro,
            new Authenticator()
            {
               @Override
               protected PasswordAuthentication getPasswordAuthentication()
               {
                  return new PasswordAuthentication(userName, password);
               }
            });
     
      Message mailMessage = new MimeMessage(sendMailSession);
     
      mailMessage.setFrom(new InternetAddress(userName));
     
      mailMessage.setRecipient(Message.RecipientType.TO,
            new InternetAddress(to));
    
      mailMessage.setSubject("Totoro Ramen's report of sales volume of the past week!");
      
      mailMessage.setSentDate(new Date());

      
      Multipart mainPart = new MimeMultipart("related");
      
      BodyPart html = new MimeBodyPart();
      
      html.setContent(
            "<html><body><div>Dear " + u.getlName() + " " + u.getfName() + " : </div><br>"
            + "<div> This is your report of sales volume of the past week. Thank you for supporting our restaurant!</div><br>"
            + "</body></html>"
            ,"text/html; charset=utf-8");
      
      mainPart.addBodyPart(html);
      
      MimeBodyPart imageBodyPart = new MimeBodyPart();
      DataSource imageds = new FileDataSource("src/sales/sales_of_dishes.jpg");
      imageBodyPart.setDataHandler(new DataHandler(imageds));
      imageBodyPart.setHeader("Content-ID","<attach>");
      imageBodyPart.setHeader("Content-Type", "image/png");
      imageBodyPart.setDisposition(MimeBodyPart.INLINE);
      imageBodyPart.setFileName("sales_of_dishes.png");
      
      mainPart.addBodyPart(imageBodyPart);
      
     
      mailMessage.setContent(mainPart);
     
      Transport.send(mailMessage);
      System.out.println("Sending successful!");
   }
   

   public void sendImageAndAttachmentMail(User u) throws Exception {
	   String to = u.getEmail();
      Properties pro = System.getProperties();
      pro.put("mail.smtp.host", host);
      pro.put("mail.smtp.port", port);
      pro.put("mail.smtp.auth", "true");

      
      Session sendMailSession = Session.getDefaultInstance(pro,
            new Authenticator()
            {
               @Override
               protected PasswordAuthentication getPasswordAuthentication()
               {
                  return new PasswordAuthentication(userName, password);
               }
            });
     
      Message mailMessage = new MimeMessage(sendMailSession);
     
      mailMessage.setFrom(new InternetAddress(userName));
     
      mailMessage.setRecipient(Message.RecipientType.TO,
            new InternetAddress(to));
      
      mailMessage.setSubject("Test Email");
     
      mailMessage.setSentDate(new Date());

     
      MimeBodyPart text = new MimeBodyPart();
      text.setContent("<img src='cid:headImg'>",
            "text/html;charset=UTF-8");

      
      MimeBodyPart image = new MimeBodyPart();
      image.setDataHandler(
            new DataHandler(new FileDataSource("1_jianggujin.jpg")));
      image.setContentID("headImg");

      
      MimeBodyPart attach = new MimeBodyPart();
      DataHandler dh = new DataHandler(new FileDataSource("1_jianggujin.jpg"));
      attach.setDataHandler(dh);
      attach.setFileName(MimeUtility.encodeWord(dh.getName()));

      
      MimeMultipart mp1 = new MimeMultipart();
      mp1.addBodyPart(text);
      mp1.addBodyPart(image);
      mp1.setSubType("related");

      
      MimeBodyPart content = new MimeBodyPart();
      content.setContent(mp1);

      MimeMultipart multipart = new MimeMultipart("mixed");
      multipart.addBodyPart(content);
      multipart.addBodyPart(attach);

      mailMessage.setContent(multipart);
      
      Transport.send(mailMessage);
   }
}


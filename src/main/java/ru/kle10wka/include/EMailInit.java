package ru.kle10wka.include;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EMailInit {
	
	private Session session;
	
	private Properties prop;
	
	private Message emess;
	
	public EMailInit(){
		prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.socketFactory.port", 465);
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.port", 465);
        session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
        	protected PasswordAuthentication getPasswordAuthentication() {
        		return new PasswordAuthentication ("oneguyfromdbkpi@gmail.com", 
            					"the_best_password_in_the_WORLD!");
        	}
        });
	}
	
	public void sendMessage(String emailAddress, String emailText)throws MessagingException{
		emess = new MimeMessage(session);
		emess.setFrom(new InternetAddress("oneguyfromdbkpi@gmail.com"));
    	emess.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailAddress));
    	emess.setSubject("Заселення в гуртожиток");
    	emess.setText(emailText);
    	Transport.send(emess);
	}
}
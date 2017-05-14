import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

//We attach a text file or pdf to send
public class Fileatch{
	
	public void ass() throws AddressException, MessagingException 
	{
		String to="vishanthbharadwaj2295@gmail.com";//username
		String from="vishanthbharadwaj@gmail.com";//username
		final String userName="vishanthbharadwaj@gmail.com";//username
		final String password="**************";//password
		final String host="smtp.gmail.com";
		
		Properties prop=new Properties();
		prop.put("mail.smtp.auth","true");
		prop.put("mail.smtp.starttls.enable","true");
		prop.put("mail.smtp.host",host);
		prop.put("mail.smtp.port","587");
		
		Session s=Session.getInstance(prop,new Authenticator() {
			protected javax.mail.PasswordAuthentication getPasswordAuthentication()
			{
				return new PasswordAuthentication(userName, password);
			}
		});
		
		Message m=new MimeMessage(s);
		m.setFrom(new InternetAddress(from));
		m.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
		m.setSubject("image");
		m.setText("adsdada");
		// Now we have to create a data handler

		Multipart mp=new MimeMultipart();
		BodyPart bp=new MimeBodyPart();
		bp.setText("hello");
		mp.addBodyPart(bp);
		
		//Part 2 Attachment
		
		String filename="/Users/User/Desktop/emb.docx";//path of filename
		bp=new MimeBodyPart();
		mp=new MimeMultipart();
		DataSource ds=new FileDataSource(filename);
		bp.setDataHandler(new DataHandler(ds));
		bp.setFileName(filename);
		mp.addBodyPart(bp);
		m.setContent(mp);
		
		Transport.send(m);
		System.out.println("Sent successfully");
	}
	public static void main(String[] args) throws AddressException, MessagingException {
		for(;;)
		{new Fileatch().ass();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
}
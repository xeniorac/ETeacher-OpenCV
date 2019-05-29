import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;


public class EmailGui extends JFrame{
JButton attach,Send;
JTextField cc;
static JTextField Field;
JTextField subject;
Container con;
static String path;
JTextArea jt;
public EmailGui(int i) {
	// TODO Auto-generated constructor stub
	con=this.getContentPane();
	Send=new JButton("Send");
	cc=new JTextField(20);
	Field=new JTextField(20);
	subject=new JTextField(20);
	jt=new JTextArea(8,20);
	if(subject==null)
	{
		VoiceManager vm=VoiceManager.getInstance();
		Voice v=vm.getVoice("kevin16");
		v.allocate();
		v.speak("Enter The Subject");
	}
	if(jt==null)
	{
		VoiceManager vm=VoiceManager.getInstance();
		Voice v=vm.getVoice("kevin16");
		v.allocate();
		v.speak("Enter The Message");
	}
	Send.addActionListener(new ActionListener() {		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String to=cc.getText();//username
			String from="vishanthbharadwaj@gmail.com";//username
			final String userName="vishanthbharadwaj@gmail.com";//username
			final String password
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
			try
			{m.setFrom(new InternetAddress(from));
			m.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			m.setSubject(subject.getText());
			m.setText(jt.getText());


			Multipart mp=new MimeMultipart();
			BodyPart bp=new MimeBodyPart();
			bp.setText("hello");
			mp.addBodyPart(bp);
			
			String filename=path;//path of filename
			bp=new MimeBodyPart();
			mp=new MimeMultipart();
			DataSource ds=new FileDataSource(filename);
			bp.setDataHandler(new DataHandler(ds));
			bp.setFileName(filename);
			mp.addBodyPart(bp);
			m.setContent(mp);

			Transport.send(m);
			}
			catch(Exception e1)
			{
					Voice v;
					VoiceManager vm=VoiceManager.getInstance();
					v=vm.getVoice("kevin16");
					v.allocate();
					v.speak("No Connection Sir");
					
			}
		}
	});
	attach=new JButton("Attach");
	attach.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		JFileChooser f=new JFileChooser(".");
		int i=f.showOpenDialog(getParent());
		if(i==JFileChooser.APPROVE_OPTION)
		{
			File f1=f.getSelectedFile();
			path=f1.getAbsolutePath();
			Field.setText(path);
		}
		
			
		}
	});
	JPanel p=new JPanel();
	p.add(attach);
	p.add(Send);
	p.add(cc);
	p.add(subject);
	p.add(Field);
	p.add(jt);
	con.add(p);
	this.setVisible(true);
	this.setSize(300,300);
	this.setResizable(true);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
public static void main(String[] args) {
	new EmailGui(0);
}
}

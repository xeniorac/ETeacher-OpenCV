import java.awt.Button;
import java.awt.Container;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;


@SuppressWarnings({ "serial", "unused" })
public class Manager extends JFrame implements ActionListener
{
	JButton piButton;
	JButton chButton,emailButton,notButton;
	Container con;	
	JTextField text;
	JPanel panel;
	public Manager() 
	{
		con=this.getContentPane();
		panel=new JPanel(new GridLayout(5, 5));
		piButton=new JButton("chart");
		chButton=new JButton("chat");
		notButton=new JButton("Notepad");
		emailButton=new JButton("Email");
		text = new JTextField("Select The Above Buttons");
		panel.add(chButton);
		panel.add(piButton);
		panel.add(emailButton);
		panel.add(notButton);
		con.add(panel);
		setVisible(true);
		setSize(300, 300);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		piButton.addActionListener(this);
		chButton.addActionListener(this);
		emailButton.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("chart"))
		{
			new PieChartGui();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			Voice v;
			VoiceManager vm=VoiceManager.getInstance();
			v=vm.getVoice("kevin16");
			v.allocate();
			v.speak("Your Pie Chart Is Ready Sir");
		}
		if(e.getActionCommand().equals("chat"))
		{
			try {
				new ClienChat().go();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			Voice v;
			VoiceManager vm=VoiceManager.getInstance();
			v=vm.getVoice("kevin16");
			v.allocate();
			v.speak("Your Chat Is Ready Sir");
		}
		if(e.getActionCommand().equals("Email"))
		{
			try {

				new EmailGui(0);
				
			} catch (Exception e1) {

				Voice v;
				VoiceManager vm=VoiceManager.getInstance();
				v=vm.getVoice("kevin16");
				v.allocate();
				v.speak("Check Internet Connection");
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		if(e.getActionCommand().equals("Notepad"))
		{
			try {
				Runtime.getRuntime().exec("open -t");
				System.exit(0);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
public static void main(String[] args) {
	new Manager();
}
}

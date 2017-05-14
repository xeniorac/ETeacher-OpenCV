import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;


public class ClienChat extends JFrame implements KeyListener {
	
	JTextField text;
	JTextArea area;
	Socket s;
	DataOutputStream dos;
	DataInputStream dis;
	
	 final String VOICENAME="kevin16";
	  

	
	public ClienChat() throws UnknownHostException, IOException {
		super("chat");
		text=new JTextField();
		area=new JTextArea();
		
		area.setWrapStyleWord(true);
		
		this.getContentPane().add(text,BorderLayout.SOUTH);
		this.getContentPane().add(area);

		this.setVisible(true);
		this.setSize(300,400);
		this.setResizable(false);
		
		text.addKeyListener(this);
		s=new Socket(InetAddress.getLocalHost(),4008); 
		
		dos=new DataOutputStream(s.getOutputStream());
		dis=new DataInputStream(s.getInputStream());
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		new ClienChat().go();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==10)
		{
			String s=text.getText();
			area.append("client:  "+s+"\n");
			text.setText("");
			try {
				dos.writeUTF(s);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			Voice voice;
			VoiceManager vm=VoiceManager.getInstance();
			voice=vm.getVoice(VOICENAME);
			voice.allocate();
			voice.speak("Message Sent sir");
		}
		
	}
	
	public void go() throws IOException
	{
		while(true)
		{
			Voice voice;
			VoiceManager vm=VoiceManager.getInstance();
			voice=vm.getVoice(VOICENAME);
			voice.allocate();
			try {
				voice.speak("Message Recieved Sir");
			} catch (Exception e) {
				// TODO: handle exception
			}
			String s=dis.readUTF();
			voice.speak(s);
			area.append("server:  "+s+"\n");
		}
	}

}

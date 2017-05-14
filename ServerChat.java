import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;


public class ServerChat extends JFrame implements KeyListener{
	
	JTextField text;
	JTextArea area;
	ServerSocket ss;
	Socket s;
	DataOutputStream dos;
	DataInputStream dis;
	
	public ServerChat() throws IOException {
		super("server");
		text=new JTextField();
		area=new JTextArea();
		area.setWrapStyleWord(true);
		this.getContentPane().add(text,BorderLayout.SOUTH);
		this.getContentPane().add(area);
		this.setVisible(true);
		this.setSize(300,400);
		this.setResizable(false);
		text.addKeyListener(this);
		
		ss=new ServerSocket(4008);
		s=ss.accept();
		
		dos=new DataOutputStream(s.getOutputStream());
		dis=new DataInputStream(s.getInputStream());
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==10)
		{
			Voice v ;
			VoiceManager vm=VoiceManager.getInstance();
			v=vm.getVoice("kevin16");
			v.allocate();
			v.speak("message sent sir");
			String s=text.getText();
			area.append("server:  "+s+"\n");
			text.setText("");
			try {
				dos.writeUTF(s);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		
		}
		
	}
	
	public void go() throws IOException
	{
		while(true)
		{
			Voice v;
			VoiceManager vm=VoiceManager.getInstance();
			v=vm.getVoice("kevin16");
			v.speak("message recieved");
			String s=dis.readUTF();
			area.append("clinet:  "+s+"\n");
		}
	}
	
	public static void main(String[] args) throws IOException {
		new ServerChat().go();
	}
	
}
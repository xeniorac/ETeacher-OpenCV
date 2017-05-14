//package pkg2;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Timer;
import java.util.TimerTask;

import javax.media.CannotRealizeException;
import javax.media.NoPlayerException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;


public class ShutDown extends JFrame implements ActionListener{
    JPanel jp;
    JTextField jt;
    JButton jb;
    Container con;
    public ShutDown() {
        // TODO Auto-generated constructor stub
    con=this.getContentPane();
    jp=new JPanel();
    jb=new JButton("shutdown");
    jt=new JTextField(10);
    jp.add(jb);
    jp.add(jt);
    jb.addActionListener(new ActionListener() {
        
        @Override
        public void actionPerformed(ActionEvent arg0) {
            // TODO Auto-generated method stub
        
            {
                String i=(jt.getText().toString());
                int h=Integer.parseInt(i);
                System.out.println(i);
                Timer t=new Timer();
                t.schedule(new TimerTask() {
                    
                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        String name=System.getProperty("os.name");
                        System.out.println(name);
                        if(name.equalsIgnoreCase("Mac OS X"))
                        {
                        	for(;;)
                            {
                        		try {
                            		Thread.sleep(100);
                            	new PlayMusic("/Users/User/Music/JavaTest.wav");
                            } catch (Exception e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }}
                        	
                        }
                    }
                },h*60*1000);
                System.out.println("ShutDown in"+h);
                    }
            
        }
    });
    con.add(jp);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setSize(200, 100);
    this.setVisible(true);
    }
    

@Override
public void actionPerformed(ActionEvent arg0) {
    // TODO Auto-generated method stub
    
}
public static void main(String[] args) {
    new ShutDown();
}
}
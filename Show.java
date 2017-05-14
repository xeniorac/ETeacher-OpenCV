import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.*;
 
public class Show {
    public static void main(String[] args) throws IOException {
    	  Timer t=new Timer();
    	  t.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				  BufferedImage image = null;
				try {
					image = ImageIO.read(new File("/Users/User/Desktop/9.jpg"));
					JLabel label = new JLabel(new ImageIcon(image));
			        JFrame f = new JFrame();
			        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			        f.getContentPane().add(label);
			        f.pack();
			        f.setLocation(200,200);
			        f.setVisible(true);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			        
			}
		},0*60*1000);
      
    }
}
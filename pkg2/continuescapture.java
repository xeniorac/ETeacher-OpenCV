package pkg2;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

import com.googlecode.javacpp.Pointer;

public class continuescapture {
	static int in=0;
public static void main(String[] args) throws HeadlessException, AWTException, IOException {
	Timer t=new Timer();
	Pointer p= new Pointer();
	t.schedule(new TimerTask() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			for(;;)
			{try {
				BufferedImage i=new Robot().createScreenCapture(new Rectangle(java.awt.Toolkit.getDefaultToolkit().getScreenSize()));
				ImageIO.write(i, "bmp",new File("/Users/User/Desktop/bmpp/bottle "+in+".bmp"));
				in++;
				try {
					Thread.sleep(10);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (HeadlessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			}
		}
	}, 1000);
	
}
}

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
import javax.media.CannotRealizeException;
import javax.media.NoPlayerException;

import com.sun.javafx.tk.Toolkit;


public class ScreenShot {
public static void main(String[] args) throws HeadlessException, AWTException, IOException, NoPlayerException, CannotRealizeException {
	 Timer t=new Timer();
     t.schedule(new TimerTask() {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				new PlayMusic("/Users/User/Qt5.2.1/5.2.1/clang_64/examples/quick/demos/maroon/content/audio/catch.wav");
				BufferedImage i=new Robot().createScreenCapture(new Rectangle(java.awt.Toolkit.getDefaultToolkit().getScreenSize()));
				ImageIO.write(i, "png",new File("/Users/User/Desktop/sc.png"));		
			} catch (NoPlayerException | CannotRealizeException
					| IOException | HeadlessException | AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
		}
	}, 15*1000);
	}
}

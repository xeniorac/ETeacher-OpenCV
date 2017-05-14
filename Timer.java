import java.io.IOException;
import java.net.MalformedURLException;
import java.util.TimerTask;

import javax.media.CannotRealizeException;
import javax.media.NoPlayerException;
import javax.swing.JFrame;


public class Timer extends JFrame{
public static void main(String[] args) {
	
	java.util.Timer t=new java.util.Timer();
	t.schedule(new TimerTask() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			{
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					for(;;)
					{ Thread.sleep(100);
						new PlayMusic("/Users/User/Desktop/1.wav");
					
					}} catch (NoPlayerException | CannotRealizeException
						| IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		}
	}, 65*60*1000);
	}
}

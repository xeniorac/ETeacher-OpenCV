package pkgtry;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class Demo4 implements Runnable{

	public void run() {
		// TODO Auto-generated method stub
		try {
			int i=0;
			for(;;)
			{
				Thread.sleep(1000);
				Clip cl=AudioSystem.getClip();
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File("/Users/User/Desktop/javatest1.wav"));
			//System.out.println(++i);
			cl.open(ais);
			cl.start();
			}}
		 catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
public static void main(String[] args) {
}
}

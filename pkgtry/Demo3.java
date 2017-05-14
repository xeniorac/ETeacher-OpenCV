package pkgtry;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
public class Demo3 {
// USE OF SUSPEND...
public static void main(String[] args) throws InterruptedException {
	Thread t2=new Thread(new Demo4());
	t2.start();
	int i =0;
	for(;;)
	{
		System.out.println("hiz");

		Thread.sleep(1000);
		i++;
		System.out.println(i);
		if(i%5==0)
		{
			t2.suspend();
			System.out.println("suspended");
		}
		t2.resume();
	}
}
}

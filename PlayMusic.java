import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.media.CannotRealizeException;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.print.attribute.standard.Media;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.sun.media.MediaPlayer;


public class PlayMusic {
	public PlayMusic() throws NoPlayerException, CannotRealizeException, MalformedURLException, IOException {
		// TODO Auto-generated constructor stub
		File f=new File("/Users/User/Music/JavaTest.wav");
		Player p=Manager.createRealizedPlayer(f.toURI().toURL());
		p.start();
}
	public static void main(String[] args) throws NoPlayerException, CannotRealizeException, MalformedURLException, IOException {
		new PlayMusic();
	}
}

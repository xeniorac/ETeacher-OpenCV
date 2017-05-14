import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import com.iSpeech.SpeechResult;
import com.iSpeech.iSpeechRecognizer;
import com.iSpeech.iSpeechRecognizer.SpeechRecognizerEvent;

public class iSpeechASR implements SpeechRecognizerEvent
{
	public iSpeechASR()
	{
		
	}
	public  void runFile(String api, boolean production)
	{
		try 
		{
			iSpeechRecognizer iSpeech = new iSpeechRecognizer(api, production);
			iSpeech.setFreeForm(3);
			SpeechResult result = iSpeech.startFileRecognize("audio/x-wav", new File("tts.wav"), this);
			//iSpeech.setLanguage("es-ES");
			System.out.println("Result = "+ result.Text + " "+result.Confidence);
			
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	@Override
	public void stateChanged(int event, int freeFormValue,Exception lastException) {
		
		if(event == SpeechRecognizerEvent.RECORDING_COMMITTED)
			System.out.println("event="+event + " freeform = "+ freeFormValue +" ");
		
	}

}

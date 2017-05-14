/*import java.applet.Applet;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.media.CannotRealizeException;
import javax.media.NoPlayerException;
import javax.swing.JLabel;

import com.googlecode.javacpp.*;
import com.googlecode.javacv.FrameGrabber;
import com.googlecode.javacv.cpp.opencv_objdetect;
import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;
import static com.googlecode.javacv.cpp.opencv_objdetect.*;
import static com.googlecode.javacv.cpp.opencv_highgui.*;
import org.omg.CORBA.PUBLIC_MEMBER;

import com.sun.speech.freetts.*;
public class Face extends Applet implements Runnable {

    private CvHaarClassifierCascade classifier;
    private CvMemStorage storage;
    private FrameGrabber grabber;
    private IplImage grabbedImage, grayImage,myimage;
    private CvSeq faces;
    private boolean stop;
    static CvRect r;
    private Exception exception = null;
    public	int flag;
    @Override public void init() {
    	JLabel j=new JLabel("J.D.S");
    	try {
			new PlayMusic();
		} catch (NoPlayerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (CannotRealizeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
            // Load the classifier file from Java resources.
            String classiferName = "haarcascade_frontalface_alt.xml";
            File classifierFile = Loader.extractResource(classiferName, null, null, null);
            if (classifierFile == null || classifierFile.length() <= 0) {
                throw new IOException("Could not extract \"" + classiferName + "\" from Java resources.");
            }
            // Preload the opencv_objdetect module to work around a known bug.
            Loader.load(opencv_objdetect.class);
            classifier = new CvHaarClassifierCascade(cvLoad(classifierFile.getAbsolutePath()));
            classifierFile.delete();
            if (classifier.isNull()) {
                throw new IOException("Could not load the classifier file.");
            }
            storage = CvMemStorage.create();
            grabber = FrameGrabber.createDefault(0);
            grabber.setImageWidth(getWidth());
            grabber.setImageHeight(getHeight());
            grabbedImage = grayImage = null;
            faces = null;
        } catch (Exception e) {
            if (exception == null) {
                exception = e;
                repaint();
            }
        }
    }

    @Override public void start() {
        try {
            stop = false;
            new Thread(this).start();
        } catch (Exception e) {
            if (exception == null) {
                exception = e;
                repaint();
            }
        }
    }

    public void run() {
        try {
            grabber.start();
            while (!stop) {
                grabbedImage = grabber.grab();
                if (grayImage == null) {
                    grayImage = IplImage.create(grabbedImage.width(), grabbedImage.height(), IPL_DEPTH_8U, 1);
                }
                if (faces == null) {
                    cvClearMemStorage(storage);
                    cvCvtColor(grabbedImage, grayImage, CV_BGR2GRAY);
                    faces = cvHaarDetectObjects(grayImage, classifier, storage, 1.1, 3, CV_HAAR_DO_CANNY_PRUNING);
                    repaint();
                }
            }
            grabbedImage = grayImage = null; 
            grabber.stop();
        } catch (Exception e) {
            if (exception == null) {
                exception = e;
                repaint();
            }
        }
        
    }
    @Override public void update(Graphics g) {
        paint(g);
    }
    public void add(int x) throws UnknownHostException, IOException
    {
               
    	  if((x==114|x==115|x==116|x==117|x==118|x==119|x==120|x==121) && flag==0)
          {
    		 
    		 
    		  final String VOICENAME="kevin16";
    		  
    		  		Voice voice;
    		  		VoiceManager vm=VoiceManager.getInstance();
    		  		voice=vm.getVoice(VOICENAME);
    		  		voice.allocate();
    		  		voice.speak("Hi.Vishanth");
    		  		flag=1;
    		   //	Fileatch f=new Fileatch();
    		  ///	try {
			 	//f.send("", "", "");
				//} catch (MessagingException e) {
					// TODO Auto-generated catch block
				//	voice.speak("no connection sire");
				//	e.printStackTrace();
			//	}
    		  	new Manager();
          }
    }
    void add2(int x)
    {
    //	if((x==114|x==115|x==116|x==117|x==118|x==119|x==120|x==121)&&flag==0)
    	//{try {
		//	new ClienChat().go();
		//} catch (IOException e) {
		//	e.printStackTrace();
		//	}
    	//}
    }
    @Override public void paint(Graphics g) {
		while (grabbedImage != null && flag==0) {
            BufferedImage image = grabbedImage.getBufferedImage(2.2/grabber.getGamma());
            Graphics2D g2 = image.createGraphics();
            if (faces != null) {
                g2.setColor(Color.WHITE);
                g2.setStroke(new BasicStroke(2));
                int total = faces.total();
                for (int i = 0; i < total && flag==0; i++) {
                    r = new CvRect(cvGetSeqElem(faces, i));
                    g2.drawRect(r.x(), r.y(), r.width(), r.height());
                   // System.out.println(String.valueOf(r.x())+"	 "+String.valueOf(r.y())+"	 "+String.valueOf(r.height())+"	 "+String.valueOf(r.width()));
               //     System.out.println(Nose.getNo());
                    int x=r.height()-r.width()+r.x()+r.y();
                    System.out.println(x);
						try {
							add(x);
						//	add2(x);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
                }
                faces = null;
            }
            g.drawImage(image, 0, 0, null);        
            break;
        }
        if (exception != null) {
            int y = 0, h = g.getFontMetrics().getHeight();
            g.drawString(exception.toString(), 5, y += h);
            for (StackTraceElement e : exception.getStackTrace()) {
                g.drawString("        at " + e.toString(), 5, y += h);
            }
        }
    }
    @Override public void stop() {
        stop = true;
    }

    @Override public void destroy() {
        try {
            grabber.release();
        } catch (Exception e) {
            if (exception == null) {
                exception = e;
                repaint();
            }
        }
    }
}*/
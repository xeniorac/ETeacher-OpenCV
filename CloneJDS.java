import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;
import static com.googlecode.javacv.cpp.opencv_objdetect.*;
// This is a cool shit woo hoo!!
import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.media.CannotRealizeException;
import javax.media.NoPlayerException;

import com.googlecode.javacpp.Loader;
import com.googlecode.javacv.CanvasFrame;
import com.googlecode.javacv.cpp.opencv_core.CvScalar;
import com.googlecode.javacv.cpp.opencv_highgui;
import com.googlecode.javacv.cpp.opencv_highgui.CvCapture;
import com.googlecode.javacv.cpp.opencv_objdetect;
import com.googlecode.javacv.cpp.opencv_objdetect.CvHaarClassifierCascade;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class CloneJDS {
	static int n;
	static int e;
	static int f,le,re;
	private static int flag;
  public static void main(String[] args) throws Exception {
	 // returns a screen shot in 15s in a file called sc.png for testing and verification.......
	  new ScreenShot();
	 /* Timer t=new Timer();
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
		}, 15*1000);*/

   // new PlayMusic("/Users/User/Music/JavaTest.wav");
    // Load object detection
    Loader.load(opencv_objdetect.class);

    // Construct classifiers from xml.
    CvHaarClassifierCascade faceClassifier = loadHaarClassifier(
        "/Users/User/Desktop/Eclipse/eclipse/Eclipse.app/Contents/MacOS/opencv/TryImageCapture/src/facebyme.xml");
    CvHaarClassifierCascade penClassifier  = loadHaarClassifier("/Users/User/Desktop/Eclipse/eclipse/Eclipse.app/Contents/MacOS/opencv/TryImageCapture/src/facebyme.xml");
    CvHaarClassifierCascade noseClassifier = loadHaarClassifier(
        "/Users/User/Desktop/Eclipse/eclipse/Eclipse.app/Contents/MacOS/opencv/TryImageCapture/src/facebyme.xml");
    CvHaarClassifierCascade eyesClassifier = loadHaarClassifier("/Users/User/Desktop/Eclipse/eclipse/Eclipse.app/Contents/MacOS/opencv/TryImageCapture/src/facebyme.xml");
    CvHaarClassifierCascade leftearClassifier = loadHaarClassifier("/Users/User/Desktop/Eclipse/eclipse/Eclipse.app/Contents/MacOS/opencv/TryImageCapture/src/facebyme.xml");
    CvHaarClassifierCascade rightearClassifier = loadHaarClassifier("/Users/User/Desktop/Eclipse/eclipse/Eclipse.app/Contents/MacOS/opencv/TryImageCapture/src/facebyme.xml");
    // Grab the default video device. This work for both built-win 
    // and usb webcams.
    CvCapture capture = opencv_highgui.cvCreateCameraCapture(0);

    // Set the capture resolution 480x320 gives decent quality 
    // and the lower resolution will make our real-time video 
    // processing a little faster.    
    opencv_highgui.cvSetCaptureProperty(capture,
        opencv_highgui.CV_CAP_PROP_FRAME_HEIGHT, 240); // cvCaptureProperty is used to provide the parameters to capture the video frame parameters are (cvcapture object,int height/width, int size)
    opencv_highgui.cvSetCaptureProperty(capture,
        opencv_highgui.CV_CAP_PROP_FRAME_WIDTH, 320);

    // Construct a JavaCV Image that matches the properties of the 
    // captured imaged.    
    IplImage grabbedImage = opencv_highgui.cvQueryFrame(capture);
    IplImage mirrorImage = grabbedImage.clone();
    IplImage grayImage = IplImage.create(mirrorImage.width(),
        mirrorImage.height(), IPL_DEPTH_8U, 1);
    // OpenCV's C++ roots means we need to allocate memory
    // to use as working storage for object detection.
    CvMemStorage faceStorage = CvMemStorage.create();
    CvMemStorage handStorage = CvMemStorage.create();
    CvMemStorage eyeStorage = CvMemStorage.create();
    CvMemStorage rightearstorage=CvMemStorage.create();
    CvMemStorage leftearstorage=CvMemStorage.create();
    CvMemStorage penstorage= CvMemStorage.create();
    //Create a frame to echo to.
    CanvasFrame frame = new CanvasFrame("J.D.S", 1);

    // Keep looping while our frame is visible and we're getting 
    // images from the webcam
    while (frame.isVisible()
        && (grabbedImage = opencv_highgui.cvQueryFrame(capture)) 
          != null && flag==0) {
     //   m2(f,n,e,le,re);
      // Clear out storage 
      cvClearMemStorage(faceStorage);
      cvClearMemStorage(handStorage);
      cvClearMemStorage(eyeStorage);
      cvClearMemStorage(leftearstorage);
      cvClearMemStorage(rightearstorage);
      cvClearMemStorage(penstorage);
      // Flip the image because a mirror image looks more natural 
      cvFlip(grabbedImage, mirrorImage, 1);
      // Create a black and white image - best for face detection
      // according to OpenCV sample.
      cvCvtColor(mirrorImage, grayImage, CV_BGR2GRAY); //input image, output image , conversion..... got it bro!!

      // Find faces in grayImage and mark with green 
      // rectangles on mirrorImage.
      findAndMarkObjects(faceClassifier, faceStorage,
          CvScalar.GREEN, grayImage, grayImage,1);
      findAndMarkObjects(penClassifier, penstorage, CvScalar.GRAY, mirrorImage, grayImage, 6);
      // Find hands in mirrorImage and mark with green
      // rectangles on mirrorImage
      findAndMarkObjects(noseClassifier, handStorage,
          CvScalar.BLUE, mirrorImage,grayImage,2);
      findAndMarkObjects(eyesClassifier, handStorage,
              CvScalar.RED, mirrorImage,grayImage,3);
      findAndMarkObjects(rightearClassifier, rightearstorage,
              CvScalar.YELLOW, mirrorImage,grayImage,4);
      findAndMarkObjects(leftearClassifier,leftearstorage,
              CvScalar.YELLOW, mirrorImage,grayImage,5);
      // display mirrorImage on frame
      frame.showImage(grayImage);
      m2(f,n,e,0,0);
     
   
    }

   // display captured image on frame
    frame.dispose();
    opencv_highgui.cvReleaseCapture(capture);
  }

  /**
   * JDS Theory:->
   * Find objects matching the supplied Haar classifier.
   * 
   *  classifier The Haar classifier for the object we're looking for.
   *  storage In-memory storage to use for computations
   *  color Colur of the marker used to make objects found.
   *  inImage Input image that we're searching.
   *  outImage Output image that we're going to mark and display.
   */
  private static void findAndMarkObjects(
      CvHaarClassifierCascade classifier, 
      CvMemStorage storage, 
      CvScalar colour,
      IplImage inImage,
      IplImage outImage,int k) {
    
    CvSeq faces = cvHaarDetectObjects(inImage, classifier,
        storage, 1.1, 3, CV_HAAR_DO_CANNY_PRUNING);
    int totalFaces = faces.total();
    for (int i = 0; i < totalFaces; i++) {
      CvRect r = new CvRect(cvGetSeqElem(faces, i));
      int x = r.x(), y = r.y(), w = r.width(), h = r.height();
      int dd=x-y+w+h;
     System.out.println(dd);
      // method(dd,k);
      cvRectangle(outImage, cvPoint(x, y), cvPoint(x + w, y + h),
          colour, 1, CV_AA, 0);
    }
  }
 
  private static CvHaarClassifierCascade loadHaarClassifier(
      String classifierName) {
    
    CvHaarClassifierCascade classifier = new CvHaarClassifierCascade(
        cvLoad(classifierName));
    if (classifier.isNull()) {
      System.err.println("Error loading classifier file \"" + classifier
          + "\".");
      System.exit(1);
    }
    
    return classifier;
  }
  private static void method(int x,int i)
  {
	  if(i==1)
	  {
		  f=x;
	  }
	  else if(i==2)
	  {
		  n=x;
	  }
	  else if(i==3)
	  {
		  e=x;
	  }
	  else if(i==4)
	  {
		  le=x;
	  }
	  else if(i==5)
	  {
		  re=x;
	  }
  }
  private static void m2(int x,int y,int z,int a,int b) {
	// TODO Auto-generated method stub
	  System.out.println(x+"->face  "+y+"->nose  "+z+"-> eyes  "+a+"->leftear  "+b+"->rightear");
	  System.out.println("full"+(x-y+z));
	  if(x!=0 || y!=0 || z!=0)
	  {  flag=1;	  	
	  	VoiceManager vm=VoiceManager.getInstance();
	  	Voice v=vm.getVoice("kevin16");
	  	v.allocate();
	  	v.setVolume(1000);
	  	v.speak("U will get this stuff in S.P");
	  	v.deallocate();
	  	//new Manager();
	  }
  }
}
// THIS IS A CLONE OF THE MAIN PROGRAM IN GREYSCALE MODEL WHICH IS USED FOR TESTING PURPOSE.
//THANKYOU...........
//PROGRAMMED BY VISHANTH BHARADWAJ ..
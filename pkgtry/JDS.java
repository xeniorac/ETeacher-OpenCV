package pkgtry;
import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;
import static com.googlecode.javacv.cpp.opencv_objdetect.*;

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
public class JDS {
	
	static int n;
	static int e;
	static int f,le,re;
	private static int flag;
  public static void main(String[] args) throws Exception {
	 // returns a screen shot in 15s in a file called sc.png for testing and verification.....
    // Load object detection
	  
    Loader.load(opencv_objdetect.class);
    
    // Construct classifiers from xml.
    CvHaarClassifierCascade faceClassifier = loadHaarClassifier(
        "/Users/User/Desktop/Eclipse/eclipse/Eclipse.app/Contents/MacOS/opencv/TryImageCapture/src/CarPlate.xml");
   
    // Grab the default video device. This work for both built-win 
    // and usb webcams.
    CvCapture capture = opencv_highgui.cvCreateCameraCapture(0);

    // Set the capture resolution 320x240 gives decent quality 
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
   
    //Create a frame to echo to.
    CanvasFrame frame = new CanvasFrame("J.D.S", 1);

    // Keep looping while our frame is visible and we're getting 
    // images from the webcam
  //  Thread t2=new Thread(new Demo4(),"demo4");
  //	t2.start();
    while (frame.isVisible()
        && (grabbedImage = opencv_highgui.cvQueryFrame(capture)) 
          != null && flag==0) {
        //m2(f,n,e,le,re);
      // Clear out storage 
      cvClearMemStorage(faceStorage);
      
      // Flip the image because a mirror image looks more natural 
      cvFlip(grabbedImage, mirrorImage, 1);
      // Create a black and white image - best for face detection
      // according to OpenCV sample.
      cvCvtColor(mirrorImage, grayImage, CV_BGR2GRAY);

      // Find faces in grayImage and mark with green 
      // rectangles on mirrorImage.
      findAndMarkObjects(faceClassifier, faceStorage,
          CvScalar.GREEN, grayImage, mirrorImage);
      // display mirrorImage on frame
      frame.showImage(mirrorImage);  
      
    }

   // display captured image on frame
    frame.dispose();
    opencv_highgui.cvReleaseCapture(capture);
  }
 
  private static void m2(int f2) throws InterruptedException {
	// TODO Auto-generated method stub
	 
	  /*{t.suspend();}
	  Thread.sleep(2000);
	  System.out.println("suspend");
	  {
		  t.resume();
	  }
	  System.out.println(t.getName());*/
}

private static void findAndMarkObjects(
      CvHaarClassifierCascade classifier, 
      CvMemStorage storage, 
      CvScalar colour,
      IplImage inImage,
      IplImage outImage) throws InterruptedException {
    
    CvSeq faces = cvHaarDetectObjects(inImage, classifier,
        storage, 1.1, 3, CV_HAAR_DO_CANNY_PRUNING);
    int totalFaces = faces.total();
    for (int i = 0; i < totalFaces; i++) {
      CvRect r = new CvRect(cvGetSeqElem(faces, i));
      int x = r.x(), y = r.y(), w = r.width(), h = r.height();
      int dd = x+y+w+h;
	if(totalFaces==0)
      {dd=0;}
      System.out.println(dd);
      m2(dd);
     
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
}
  
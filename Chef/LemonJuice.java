package Chef;
//PROJECT -> COOKBOOK.
//AUTHOR ->XENIORAC PROJECT START DATE -> 24/8/2014-> 8:30:00 AM
import static com.googlecode.javacv.cpp.opencv_core.CV_AA;
import static com.googlecode.javacv.cpp.opencv_core.IPL_DEPTH_8U;
import static com.googlecode.javacv.cpp.opencv_core.cvClearMemStorage;
import static com.googlecode.javacv.cpp.opencv_core.cvFlip;
import static com.googlecode.javacv.cpp.opencv_core.cvGetSeqElem;
import static com.googlecode.javacv.cpp.opencv_core.cvLoad;
import static com.googlecode.javacv.cpp.opencv_core.cvPoint;
import static com.googlecode.javacv.cpp.opencv_core.cvRectangle;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_BGR2GRAY;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvCvtColor;
import static com.googlecode.javacv.cpp.opencv_objdetect.CV_HAAR_DO_CANNY_PRUNING;
import static com.googlecode.javacv.cpp.opencv_objdetect.cvHaarDetectObjects;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.googlecode.javacv.CanvasFrame;
import com.googlecode.javacv.cpp.opencv_highgui;
import com.googlecode.javacv.cpp.opencv_core.CvMemStorage;
import com.googlecode.javacv.cpp.opencv_core.CvRect;
import com.googlecode.javacv.cpp.opencv_core.CvScalar;
import com.googlecode.javacv.cpp.opencv_core.CvSeq;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import com.googlecode.javacv.cpp.opencv_highgui.CvCapture;
import com.googlecode.javacv.cpp.opencv_legacy.Cv3dTrackerTrackedObject;
import com.googlecode.javacv.cpp.opencv_objdetect.CvHaarClassifierCascade;
import com.sun.org.apache.xerces.internal.util.URI;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

			  public class LemonJuice {
				static int flag=0;
			  static VoiceManager vm=VoiceManager.getInstance();
			  	static Voice v=vm.getVoice("kevin16");
			  	static int sx;
			  	static int sy;
			  	static int sz;
			  	public LemonJuice() {
					// TODO Auto-generated constructor stub
			  		try {
						m1();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			  	}
			  	 static void m1() throws InterruptedException {
			  		// TODO Auto-generated constructor stub
			  		 CvHaarClassifierCascade faceClassifier = loadHaarClassifier(
			  			        "/Users/User/Desktop/Eclipse/eclipse/Eclipse.app/Contents/MacOS/opencv/TryImageCapture/src/haarcascade_frontalface_alt.xml");
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
			  			   			    //Create a frame to echo to.
			  			    CanvasFrame frame = new CanvasFrame("CHEF", 1);

			  			    // Keep looping while our frame is visible and we're getting 
			  			    // images from the webcam
			  			    while (frame.isVisible()
			  			        && (grabbedImage = opencv_highgui.cvQueryFrame(capture)) 
			  			          != null && flag==0) {
			  			     //   m2(f,n,e,le,re);
			  			      // Clear out storage 
			  			      cvClearMemStorage(faceStorage);
			  			      
			  			      // Flip the image because a mirror image looks more natural 
			  			      cvFlip(grabbedImage, mirrorImage, 1);
			  			      // Create a black and white image - best for face detection
			  			      // according to OpenCV sample.
			  			      cvCvtColor(mirrorImage, grayImage, CV_BGR2GRAY); //input image, output image , conversion..... got it bro!!

			  			      // Find faces in grayImage and mark with green 
			  			      // rectangles on mirrorImage.
			  			      findAndMarkObjects(faceClassifier, faceStorage,
			  			          CvScalar.GREEN, grayImage, grayImage,1);
			  			      
			  			      // rectangles on mirrorImage
			  			      // display mirrorImage on frame
			  			      frame.showImage(mirrorImage);
			  			     // break;
			  			      if(sx!=0||sy!=0||sz!=0)
			  			      {method1(sx,sy,sz);}
			  			      else
			  			      {
			  			   
			  			    	  {
			  			    		  v.allocate();
			  			    		  v.speak("U have an incomplete task sir");
			  			    		  Thread.sleep(100);
			  			    	  }
			  			      }
			  			      //m2(f,n,e);
			  			    }
			  			    
			  			   // display captured image on frame
			  			    frame.dispose();
			  			    opencv_highgui.cvReleaseCapture(capture);
					  		
					  }
			  
			  			  /**
			  			   *LEMONJUICE Theory:->
			  			   * Find objects matching the supplied Haar classifier.
			  			   * 
			  			   *  classifier The Haar classifier for the object we're looking for.
			  			   *  storage In-memory storage to use for computations
			  			   *  color Color of the marker used to make objects found.
			  			   *  inImage Input image that we're searching.
			  			   *  outImage Output image that we're going to mark and display.
			  			   */
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
			  			      sx=x;sy=y;sz=w-h;
			  			      System.out.println(sx+"->"+sy+"->");
			  			      cvRectangle(outImage, cvPoint(x, y), cvPoint(x + w, y + h),
			  			          colour, 1, CV_AA, 0);
			  			    
			  			    }
			  			  }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			  			  private static CvHaarClassifierCascade loadHaarClassifier(String classifierName) 
			  {    
				    CvHaarClassifierCascade classifier = new CvHaarClassifierCascade(
				        cvLoad(classifierName));
				    if (classifier.isNull()) {
				      System.err.println("Error loading classifier file \"" + classifier
				          + "\".");
				      System.exit(1);
				    }
					return classifier;
			  }
			  private static void method1(int xx,int yy,int zz) throws InterruptedException {
				// TODO Auto-generated method stub
				  if(xx!=0||yy!=0||zz!=0)
				  {v.allocate();
				  v.speak("so let us start");
				  v.speak("First cut the lemon as shown in the figure");
				  show("/Users/User/Desktop/Eclipse/eclipse/Eclipse.app/Contents/MacOS/opencv/TryImageCapture/src/lemon.jpeg",500,500);
				  flag=1;
				  Thread.sleep(1000);
				  
				  m2();}
			}
			  private static void m2() {
				// TODO Auto-generated method stub
				  sx=0;sy=0;sz=0;
				  flag=0;
				  CvHaarClassifierCascade faceClassifier = loadHaarClassifier(
		  			        "/Users/User/Desktop/Eclipse/eclipse/Eclipse.app/Contents/MacOS/opencv/TryImageCapture/src/haarcascade_frontalface_alt.xml");
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
		  			   			    //Create a frame to echo to.
		  			    CanvasFrame frame = new CanvasFrame("CHEF", 1);

		  			    // Keep looping while our frame is visible and we're getting 
		  			    // images from the webcam
		  			    while (frame.isVisible()
		  			        && (grabbedImage = opencv_highgui.cvQueryFrame(capture)) 
		  			          != null && flag==0){
		  			     //   m2(f,n,e,le,re);
		  			      // Clear out storage 
		  			      cvClearMemStorage(faceStorage);
		  			      
		  			      // Flip the image because a mirror image looks more natural 
		  			      cvFlip(grabbedImage, mirrorImage, 1);
		  			      // Create a black and white image - best for face detection
		  			      // according to OpenCV sample.
		  			      cvCvtColor(mirrorImage, grayImage, CV_BGR2GRAY); //input image, output image , conversion..... got it bro!!
		  			      // Find faces in grayImage and mark with green 
		  			      // rectangles on mirrorImage.
		  			      findAndMarkObjects(faceClassifier, faceStorage,
		  			          CvScalar.GREEN, mirrorImage, mirrorImage,1);
		  			      if(sy!=0 || sx!=0 || sz!=0)
		  			      { method2(sx,sy,sz); }
		  			      else
		  			      {
		  			    	    v.speak("U have an incomplete task left sir");
		  			    	    try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
		  			      }
		  			      // rectangles on mirrorImage
		  			      // display mirrorImage on frame
		  			      frame.showImage(mirrorImage);
		  			    }
		  			    
		  			   // display captured image on frame
		  			    frame.dispose();
		  			    opencv_highgui.cvReleaseCapture(capture);
				  						
			}
			  static void method2(int i,int j,int k)
			  {
				  v.allocate();
				  v.setStyle("kevin8");
				  v.speak("Now for one lemon take the quantity of sugar salt  shown");
				  System.out.println("method2");
				  show("/Users/User/Desktop/Eclipse/eclipse/Eclipse.app/Contents/MacOS/opencv/TryImageCapture/src/sugarsalt.jpeg",500,500);
				//  show("/Users/User/Desktop/Eclipse/eclipse/Eclipse.app/Contents/MacOS/opencv/TryImageCapture/src/glass_of_water.jpg",500,500);
				  try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
				  flag=1;
				  m25();
			  }
			  private static void m25()
			  {
				  sx=0;sy=0;sz=0;
				   flag=0;
					  CvHaarClassifierCascade faceClassifier = loadHaarClassifier(
			  			        "/Users/User/Desktop/Eclipse/eclipse/Eclipse.app/Contents/MacOS/opencv/TryImageCapture/src/haarcascade_frontalface_alt.xml");
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
			  			   			    //Create a frame to echo to.
			  			    CanvasFrame frame = new CanvasFrame("CHEF", 1);

			  			    // Keep looping while our frame is visible and we're getting 
			  			    // images from the webcam
			  			    while (frame.isVisible()
			  			        && (grabbedImage = opencv_highgui.cvQueryFrame(capture)) 
			  			          != null && flag==0){
			  			     //   m2(f,n,e,le,re);
			  			      // Clear out storage 
			  			      cvClearMemStorage(faceStorage);
			  			      
			  			      // Flip the image because a mirror image looks more natural 
			  			      cvFlip(grabbedImage, mirrorImage, 1);
			  			      // Create a black and white image - best for face detection
			  			      // according to OpenCV sample.
			  			      cvCvtColor(mirrorImage, grayImage, CV_BGR2GRAY); //input image, output image , conversion..... got it bro!!
			  			      // Find faces in grayImage and mark with green 
			  			      // rectangles on mirrorImage.
			  			      findAndMarkObjects(faceClassifier, faceStorage,
			  			          CvScalar.GREEN, mirrorImage, mirrorImage,1);
			  			      if(sx!=0 && sy!=0 || sz!=0)
			  			      {method25(sx,sy,sz);}
			  			      else
			  			      {
			  			    	  v.speak("u have incomplete task left sir");
			  			      }
			  			      // rectangles on mirrorImage
			  			      // display mirrorImage on frame
			  			      frame.showImage(mirrorImage);
			  			    }
			  			    
			  			   // display captured image on frame
			  			    frame.dispose();
			  			    opencv_highgui.cvReleaseCapture(capture); 
			  }
			  private static void method25(int i, int j , int k) {
				// TODO Auto-generated method stub
				  v.allocate();
				  v.speak("Now for one lemon take the quantity of water as shown");
				  System.out.println("method2");
				  //show("/Users/User/Desktop/Eclipse/eclipse/Eclipse.app/Contents/MacOS/opencv/TryImageCapture/src/sugarsalt.jpeg",500,500);
				   show("/Users/User/Desktop/Eclipse/eclipse/Eclipse.app/Contents/MacOS/opencv/TryImageCapture/src/glass_of_water.jpg",500,500);
				  try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
				  flag=1;
				  m3();
			}
			   private static void m3() {
					  sx=0;sy=0;sz=0;
				   flag=0;
					  CvHaarClassifierCascade faceClassifier = loadHaarClassifier(
			  			        "/Users/User/Desktop/Eclipse/eclipse/Eclipse.app/Contents/MacOS/opencv/TryImageCapture/src/haarcascade_frontalface_alt.xml");
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
			  			   			    //Create a frame to echo to.
			  			    CanvasFrame frame = new CanvasFrame("CHEF", 1);

			  			    // Keep looping while our frame is visible and we're getting 
			  			    // images from the webcam
			  			    while (frame.isVisible()
			  			        && (grabbedImage = opencv_highgui.cvQueryFrame(capture)) 
			  			          != null && flag==0){
			  			     //   m2(f,n,e,le,re);
			  			      // Clear out storage 
			  			      cvClearMemStorage(faceStorage);
			  			      
			  			      // Flip the image because a mirror image looks more natural 
			  			      cvFlip(grabbedImage, mirrorImage, 1);
			  			      // Create a black and white image - best for face detection
			  			      // according to OpenCV sample.
			  			      cvCvtColor(mirrorImage, grayImage, CV_BGR2GRAY); //input image, output image , conversion..... got it bro!!
			  			      // Find faces in grayImage and mark with green 
			  			      // rectangles on mirrorImage.
			  			      findAndMarkObjects(faceClassifier, faceStorage,
			  			          CvScalar.GREEN, mirrorImage, mirrorImage,1);
			  			      if(sx!=0 && sy!=0 || sz!=0)
			  			      {method3(sx,sy,sz);}
			  			      else
			  			      {
			  			    	  v.speak("u have incomplete task left sir");
			  			      }
			  			      // rectangles on mirrorImage
			  			      // display mirrorImage on frame
			  			      frame.showImage(mirrorImage);
			  			    }
			  			    
			  			   // display captured image on frame
			  			    frame.dispose();
			  			    opencv_highgui.cvReleaseCapture(capture);
					  						

			}
			private static void method3(int i, int j, int k) {
				v.allocate();
				System.out.println("method3");
				v.speak("Stir it nicely from my marsk to 25 seconds");
				v.speak("it should look like this .");
				show("/Users/User/Desktop/Eclipse/eclipse/Eclipse.app/Contents/MacOS/opencv/TryImageCapture/src/lemon_juice.jpg",1300,1600);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				v.setVolume(100);
				v.speak("Congrats U Just finished your dish have fun");
				flag=1;
				System.exit(0);
			}

			static void show(String s,int i,int j)
			  {
				  
				  try {
					BufferedImage ima=ImageIO.read(new File(s));
					JLabel l=new JLabel(new ImageIcon(ima));
					JFrame f1=new JFrame("Like This");
					f1.getContentPane().add(l);
					f1.pack();
					f1.setVisible(true);
					f1.setSize(i, j);
					f1.setResizable(true);
				
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
			 
			  
			public static void main(String[] args) throws InterruptedException {
				new LemonJuice();
			}
	}
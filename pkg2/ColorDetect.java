package pkg2;



import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_highgui.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;

import com.googlecode.javacv.CanvasFrame;
import com.googlecode.javacv.cpp.opencv_highgui;
//non-static imports
import com.googlecode.javacv.cpp.opencv_core.CvScalar;
import com.googlecode.javacv.cpp.opencv_core.IplImage;

public class ColorDetect {
     //color range of red like color
	static CvScalar min = cvScalar(255, 255, 255, 0);//BGR-A
    static CvScalar max= cvScalar(255, 255, 255, 0);//BGR-A
    public static void main(String[] args) {
        //read image
    	CvCapture cam=cvCreateCameraCapture(CV_CAP_ANY);
        IplImage orgImg = cvQueryFrame(cam);
        IplImage hsvimg=cvCreateImage(cvGetSize(orgImg), 8, 3);
        cvCvtColor(orgImg, hsvimg, CV_BGR2HSV);
        CanvasFrame can1=new CanvasFrame("First",1);
        CanvasFrame can2=new CanvasFrame("Second",1);
        IplImage imgThreshold = cvCreateImage(cvGetSize(orgImg), 8, 1);
      
      //  cvSaveImage("/Users/User/Desktop/threshold.jpg", imgThreshold);
        while(can1.isVisible() && (orgImg=cvQueryFrame(cam))!=null)
        {
        	  cvInRangeS(orgImg, min, max, imgThreshold);
              cvSmooth(imgThreshold, imgThreshold, CV_MEDIAN, 13);
              can1.showImage(orgImg);
              can2.showImage(imgThreshold);
        try {
				Thread.sleep(0);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        can1.dispose();
        can2.dispose();
        opencv_highgui.cvReleaseCapture(cam);

    }
}
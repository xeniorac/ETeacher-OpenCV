package pkg2;

import static com.googlecode.javacv.cpp.opencv_highgui.cvCreateFileCapture;
import static com.googlecode.javacv.cpp.opencv_highgui.cvQueryFrame;

import com.googlecode.javacv.CanvasFrame;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import com.googlecode.javacv.cpp.opencv_highgui.CvCapture;

public class video {
public video(String s) {
	CvCapture cap=cvCreateFileCapture(s);
	IplImage img;
	CanvasFrame canvas = new CanvasFrame("video",1);
	for(;;)
	{
		img=cvQueryFrame(cap);
		canvas.showImage(img);
	}
}
}

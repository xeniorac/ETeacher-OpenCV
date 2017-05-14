import java.awt.Frame;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
//Source Code
//Some algorithms are with me and we can make it dynamic
//THANK YOU.....
import com.orsoncharts.plot.PiePlot3D;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
public class PieChart {
public PieChart() {
	// TODO Auto-generated constructor stub
	DefaultPieDataset piechart=new DefaultPieDataset();
	piechart.setValue("First", new Integer(20));
	piechart.setValue("four", new Integer(30));
	piechart.setValue("Second", new Integer(33));
	piechart.setValue("Third", new Integer(44));
	JFreeChart jfree=ChartFactory.createPieChart3D("Pie Chart",piechart,true, true,true);
	org.jfree.chart.plot.PiePlot3D p=(org.jfree.chart.plot.PiePlot3D) jfree.getPlot();
	ChartFrame ch=new ChartFrame("Test", jfree);
	ch.setVisible(true);
	ch.setSize(300, 300);
	 
	DefaultCategoryDataset bargraph=new DefaultCategoryDataset();
	 final String VOICENAME="kevin16";
	  
		Voice voice;
		VoiceManager vm=VoiceManager.getInstance();
		voice=vm.getVoice(VOICENAME);
		voice.allocate();
	bargraph.setValue(309600,"Outstandin","93-94");
	bargraph.setValue(-4580559, "-Outstandin","00-01");
	
	JFreeChart jfree2=ChartFactory.createBarChart3D("Demo", "DEmop","Feasd",bargraph, PlotOrientation.VERTICAL, false, true,false);
	ChartFrame chbar=new ChartFrame("Test2",jfree2);
	chbar.setVisible(true);
	chbar.setSize(300, 300);
//	voice.speak("309600");
//	voice.speak("4580554");
//	voice.speak("1398869");
//	voice.speak("2217526");
//	voice.speak("27169726");
//	voice.speak("4442723");
//	voice.speak("-8918893");
//	voice.speak("1008509");
//	voice.speak("2513673");
//	voice.speak("7805242");
//	voice.speak("52653550");
//	voice.speak("89178273");
//	voice.speak("184359452");

	//try {
	//	ChartUtilities.saveChartAsJPEG(new File("yoyo.jpg"),jfree2, 500,500);
//	} catch (IOException e) {
		// TODO Auto-generated catch block
	//	e.printStackTrace();
	//}
	//ch.setIconImage(image);
	XYSeries s=new XYSeries("HI");
	s.add(1, 3);
	s.add(1, 3);
	s.add(3,4);
	s.add(4,2);	
	XYSeriesCollection sc=new XYSeriesCollection(s);
	//sc.addSeries(s);
	JFreeChart jfree3=ChartFactory.createXYLineChart("Demo","x","y",sc,PlotOrientation.VERTICAL, true,true,false);
	ChartFrame cf=new ChartFrame("Demo",jfree3);
	cf.setVisible(true);
	cf.setSize(300, 300);
}
public static void main(String[] args) {
	PieChart p=new PieChart();
	}
}

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;


public class PieChartGui extends JFrame{
	JTextField jt1,jt2,jt3,jt4;
	JTextField js1,js2,js3,js4;
	JButton pieButton,barButton,xyButton,Speak;
	JPanel p;
	Container con;
	public PieChartGui() {
		con=this.getContentPane();
		p=new JPanel();
		// TODO Auto-generated constructor stub
		jt1=new JTextField(8);
		js1=new JTextField(10);

		jt2=new JTextField(8);
		js2=new JTextField(10);

		jt3=new JTextField(8);
		js3=new JTextField(10);

		jt4=new JTextField(8);
		js4=new JTextField(10);
		
		pieButton=new JButton("PieChart");
		barButton=new JButton("BarChart");
		xyButton=new JButton("XYChart");
		Speak=new JButton("Hear");
		Speak.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				VoiceManager vm=VoiceManager.getInstance();
				Voice v=vm.getVoice("kevin16");
				v.allocate();
				v.speak(jt1.getText().toString());
				v.speak(js1.getText().toString());
				v.speak(jt2.getText().toString());
				v.speak(js2.getText().toString());
				v.speak(jt3.getText().toString());
				v.speak(js3.getText().toString());
				v.speak(jt4.getText().toString());
				v.speak(js4.getText().toString());		
			}
		});
		pieButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int i1=Integer.parseInt(jt1.getText());
				int i2=Integer.parseInt(jt2.getText());
				int i3=Integer.parseInt(jt3.getText());
				int i4=Integer.parseInt(jt4.getText());
				String s1=js1.getText();
				String s2=js2.getText();
				String s3=js3.getText();
				String s4=js4.getText();
				DefaultPieDataset piechart=new DefaultPieDataset();
				piechart.setValue(s1, new Integer(i1));
				piechart.setValue(s2, new Integer(i2));
				piechart.setValue(s3, new Integer(i3));
				piechart.setValue(s4, new Integer(i4));
				JFreeChart jfree=ChartFactory.createPieChart3D("Pie Chart",piechart,true, true,true);
				org.jfree.chart.plot.PiePlot3D p=(org.jfree.chart.plot.PiePlot3D) jfree.getPlot();
				ChartFrame ch=new ChartFrame("Test", jfree);
				ch.setVisible(true);
				ch.setSize(300, 300);
			}
		});
		xyButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				XYSeries s=new XYSeries("HI");
				int i1=Integer.parseInt(jt1.getText());
				int i2=Integer.parseInt(jt2.getText());
				int i3=Integer.parseInt(jt3.getText());
				int i4=Integer.parseInt(jt4.getText());
				int s1=Integer.parseInt(js1.getText());
				int s2=Integer.parseInt(js2.getText());
				int s3=Integer.parseInt(js3.getText());
				int s4=Integer.parseInt(js4.getText());
				s.add(i1, s1);
				s.add(i2, s2);
				s.add(i3,s3);
				s.add(i4,s4);	
				XYSeriesCollection sc=new XYSeriesCollection(s);
				//sc.addSeries(s);
				JFreeChart jfree3=ChartFactory.createXYLineChart("XyXhart","x","y",sc,PlotOrientation.VERTICAL, true,true,false);
				ChartFrame cf=new ChartFrame("Demo",jfree3);
				cf.setVisible(true);
				cf.setSize(300, 300);

			}
		});
		barButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int i1=Integer.parseInt(jt1.getText());
				int i2=Integer.parseInt(jt2.getText());
				int i3=Integer.parseInt(jt3.getText());
				int i4=Integer.parseInt(jt4.getText());
				String s1=js1.getText();
				String s2=js2.getText();
				String s3=js3.getText();
				String s4=js4.getText();

				DefaultCategoryDataset bargraph=new DefaultCategoryDataset();				  
				bargraph.setValue(i1,s1,String.valueOf(i1));
				bargraph.setValue(i2,s2,String.valueOf(i2));
				bargraph.setValue(i3,s3,String.valueOf(i3));
				bargraph.setValue(i4,s4,String.valueOf(i4));

				JFreeChart jfree2=ChartFactory.createBarChart3D("Demo", "X","Y",bargraph, PlotOrientation.VERTICAL, false, true,false);
				ChartFrame chbar=new ChartFrame("Test2",jfree2);
				chbar.setVisible(true);
				chbar.setSize(300, 300);				
			}
		});
		p.add(jt1);
		p.add(jt2);
		p.add(jt3);
		p.add(jt4);
		p.add(js1);
		p.add(js2);
		p.add(js3);
		p.add(js4);
		p.add(pieButton);
		p.add(barButton);
		p.add(xyButton);
		p.add(Speak);
		con.add(p);
		this.setVisible(true);
		this.setResizable(true);
		this.setSize(300, 300);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new PieChartGui();
	}
}

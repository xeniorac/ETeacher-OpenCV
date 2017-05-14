import java.awt.Color;
import java.io.File;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;



public class Random {
public static void main(String[] args) throws IOException {
	  DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	    dataset.setValue(6.4, "LR", "LemonJuice");
	    dataset.setValue(8, "LP", "LemonJuice");
	    dataset.setValue(7, "LR", "Trigonometry");
	    dataset.setValue(9.2, "LP", "Trigonometry");
	    dataset.setValue(5.7, "LR", "Human-Body");
	    dataset.setValue(8.3, "LP", "Human-Body");
	    dataset.setValue(6.2, "LR", "WorldWar1");
	    dataset.setValue(9.2, "LP", "WorldWar1");
	    dataset.setValue(6.8, "LR", "Geometry");
	    dataset.setValue(9.1, "LP", "Geometry");
	    dataset.setValue(5.6, "LR", "GeographySoil");
	    dataset.setValue(7.2, "LP", "GeographySoil");
	    // Profit1, Profit2 represent the row keys
	    // Jane, Tom, Jill, etc. represent the column keys
	    JFreeChart chart = ChartFactory.createBarChart3D( "Regular vs Proposed Method",
	    "SUBJECTS", "Average Questions Answered", dataset, PlotOrientation.VERTICAL, true, true, false );
	       // Adjust the colour of the title
	    CategoryPlot p = chart.getCategoryPlot();  // Get the Plot object for a bar graph   
	    ChartFrame cf=new ChartFrame("Demo",chart);
		cf.setVisible(true);
		cf.setSize(300, 300);
        ChartUtilities.saveChartAsJPEG(new File("/Users/User/Desktop/aa.jpg"), chart, 800, 900);


}
}

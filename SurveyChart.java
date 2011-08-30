import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.text.AttributedString;
import java.text.DecimalFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

public class SurveyChart {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFreeChart chart = createChart(createDatasetG());
		try {
			ChartUtilities.saveChartAsJPEG(new File("/Users/SPC/Desktop/G.jpg"), chart, 400, 400);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Finished.");

	}

	// public static String title =
	// "B.) Effectiveness of overall SOPAC Division communication practices";
	// public static String title =
	// "C.) Staff level of personal job satisfaction";

	// public static String title =
	// "D.) Staff view of quality of leadership/programme management";
	// public static String title =
	// "E.) Staff awareness of the bigger SPC/SOPAC picture following integration";
	// public static String title =
	// "F.) Awareness of other SPC policies & practices";
	public static String title = "G.) Impression of the SPC system in place to address staff grievances";

	private static PieDataset createDatasetB() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("Excellent", new Double(13.7254902000));
		dataset.setValue("Good", new Double(38.2352941200));
		dataset.setValue("Fair", new Double(28.7581699300));
		dataset.setValue("Poor", new Double(10.4575163400));
		dataset.setValue("Not Good", new Double(8.8235294100));
		return dataset;
	}

	private static PieDataset createDatasetC() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("Excellent", new Double(27.2727272700));
		dataset.setValue("Good", new Double(40.9982174700));
		dataset.setValue("Fair", new Double(14.0819964300));
		dataset.setValue("Poor", new Double(6.7736185400));
		dataset.setValue("Not Good", new Double(10.8734402900));
		return dataset;
	}

	private static PieDataset createDatasetD() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("Excellent", new Double(21.0084033600));
		dataset.setValue("Good", new Double(42.0168067200));
		dataset.setValue("Fair", new Double(16.2464986000));
		dataset.setValue("Poor", new Double(10.3641456600));
		dataset.setValue("Not Good", new Double(10.3641456600));
		return dataset;
	}

	private static PieDataset createDatasetE() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("Excellent", new Double(14.3288084500));
		dataset.setValue("Good", new Double(40.5731523400));
		dataset.setValue("Fair", new Double(24.7360482700));
		dataset.setValue("Poor", new Double(9.0497737600));
		dataset.setValue("Not Good", new Double(11.3122171900));
		return dataset;
	}

	private static PieDataset createDatasetF() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("Excellent", new Double(9.8039215700));
		dataset.setValue("Good", new Double(21.5686274500));
		dataset.setValue("Fair", new Double(21.5686274500));
		dataset.setValue("Poor", new Double(15.6862745100));
		dataset.setValue("Not Good", new Double(31.3725490200));
		return dataset;
	}

	private static PieDataset createDatasetG() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("Excellent", new Double(22.8758169900));
		dataset.setValue("Good", new Double(35.9477124200));
		dataset.setValue("Fair", new Double(17.6470588200));
		dataset.setValue("Poor", new Double(13.7254902000));
		dataset.setValue("Not Good", new Double(9.8039215700));
		return dataset;
	}

	private static JFreeChart createChart(PieDataset dataset) {

		JFreeChart chart = ChartFactory.createPieChart3D(title,
		dataset, // data
		true, // include legend
		true, false);

		PiePlot3D plot = (PiePlot3D) chart.getPlot();
		plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
		plot.setNoDataMessage("No data available");
		plot.setCircular(true);
		plot.setLabelGap(0.1);
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public String generateSectionLabel(PieDataset d, Comparable key) {
				DecimalFormat df = new DecimalFormat("#.##");
				return key.toString() + "\r\n " + df.format(d.getValue(key)) + "%";
			}
			
			@Override
			public AttributedString generateAttributedSectionLabel(PieDataset arg0, Comparable arg1) {
				return null;
			}
		});
		return chart;

	}

}

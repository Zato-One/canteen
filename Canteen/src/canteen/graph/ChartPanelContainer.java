package canteen.graph;

import java.awt.Color;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import canteen.others.IncomingPersons;
import canteen.result.Result;

public class ChartPanelContainer{
	ArrayList<Result> results;
	ArrayList<Double> waitingTimes;
	IncomingPersons incomingPersons;
	final ChartPanel chartPanel;

	public ChartPanelContainer(final String title, ArrayList<Result> results,
			ArrayList<Double> waitingTimes,
			IncomingPersons incomingPersons) throws Exception {
		this(title, results, waitingTimes, incomingPersons, "X", "Y"); 
	}
	
	public ChartPanelContainer(final String title, ArrayList<Result> results,
			ArrayList<Double> waitingTimes,
			IncomingPersons incomingPersons,
			String xAxisName, String yAxisName) throws Exception {
		this.results = results;
		this.waitingTimes = waitingTimes;
		this.incomingPersons = incomingPersons;

		if (results.size() != waitingTimes.size()) {
			throw new Exception("Results and WaitingTimes have got different sizes");
		}

		final XYDataset dataset = createDataset();
		final JFreeChart chart = createChart(dataset, title, xAxisName, yAxisName);
		
		chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
	}
	
	public ChartPanel getChartPanel() {
		return chartPanel;
	}
	
	private XYDataset createDataset() {
		// incoming persons
		XYSeries incomingPerson = new XYSeries("příchozí osoby");
		int i = 0;
		for (Result result : results) {
			incomingPerson.add((double) result.getTime(), (double) incomingPersons.getNumberOfPersons(i));
			i++;
		}

		// foodQueue
		XYSeries foodQueue = new XYSeries("fronta na jídlo");
		for (Result result : results) {
			foodQueue.add((double) result.getTime(), (double) result.getNumberOfPeopleInFoodQueue());
		}

		// tableQueue
		XYSeries tableQueue = new XYSeries("fronta na sednutí");
		for (Result result : results) {
			tableQueue.add((double) result.getTime(), (double) result.getNumberOfPeopleInTableQueue());
		}

		// eating
		XYSeries eating = new XYSeries("osob za stolem");
		for (Result result : results) {
			eating.add((double) result.getTime(), (double) result.getNumberOfPeopleEating());
		}

		// waitingTime
		XYSeries waitingTime = new XYSeries("doba čekání na výdej jídla");
		i = 0;
		for (Result result : results) {
			waitingTime.add((double) result.getTime(), (double) waitingTimes.get(i));
			i++;
		}
		
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(incomingPerson);
		dataset.addSeries(foodQueue);
		dataset.addSeries(tableQueue);
		dataset.addSeries(eating);
		dataset.addSeries(waitingTime);
		
		return dataset;
	}

	private JFreeChart createChart(final XYDataset dataset,
			String title, String xAxisName, String yAxisName) {

		// create the chart...
		final JFreeChart chart = ChartFactory.createXYLineChart(title, // chart
																						// title
				xAxisName, // x axis label
				yAxisName, // y axis label
				dataset, // data
				PlotOrientation.VERTICAL, true, // include legend
				true, // tooltips
				false // urls
				);

		// NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
		chart.setBackgroundPaint(Color.white);

		// final StandardLegend legend = (StandardLegend) chart.getLegend();
		// legend.setDisplaySeriesShapes(true);

		// get a reference to the plot for further customisation...
		final XYPlot plot = chart.getXYPlot();
		plot.setBackgroundPaint(Color.white);
		
		plot.setDomainGridlinePaint(Color.lightGray);
		plot.setRangeGridlinePaint(Color.lightGray);

		final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		// renderer.setSeriesLinesVisible(1, false);
		renderer.setSeriesShapesVisible(0, false);
		renderer.setSeriesShapesVisible(1, false);
		renderer.setSeriesShapesVisible(2, false);
		renderer.setSeriesShapesVisible(3, false);
		renderer.setSeriesShapesVisible(4, false);
		plot.setRenderer(renderer);

		// change the auto tick unit selection to integer units only...
		final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		// OPTIONAL CUSTOMISATION COMPLETED.

		return chart;

	}
}

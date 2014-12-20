package canteen.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import canteen.CanteenSimulation;
import canteen.graph.ChartPanelContainer;
import canteen.others.ParametersManager;
import canteen.result.Result;

@SuppressWarnings("serial")
public class MainWindow extends JFrame implements ActionListener {
	private static final Dimension SIMULATION_PANEL_SIZE = new Dimension(600, 500);
    private static final Dimension CONFIG_PANEL_SIZE = new Dimension(250, 500);
    private static final Dimension RUN_BUTTON_SIZE = new Dimension(150, 50);
    private JPanel controlPanel;
    private JPanel configurationPanel;
    private JTabbedPane tabs;
    private JPanel graphPanel;
    private JPanel simulationPanel;
    private SliderPanel sliderPanelEatMean;
	private SliderPanel sliderPanelEatStD;
	private SliderPanel sliderPanelFoodWaitMean;
	private SliderPanel sliderPanelFoodWaitStD;
	private SliderPanel sliderPanelCapacity;
	private SliderPanel sliderPanelStepCount;
	//private SliderPanel sliderPanelInterval;
	private CanteenSimulation simulation;
	private ArrayList<Result> results;
	//private int actualStepNumber = 0;
	private SliderPanel timeSlider;
	
	public MainWindow() {
    	initComponents();
    	initConfigurationPanel();
    	
    	this.setTitle("Simulace stravovacích systémů vysokých škol");
		this.pack();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
    
    private void initSimulation() {
    	timeSlider.setMaximum(sliderPanelStepCount.getValue());
    	
    	// define simulation parameters, each method is decribed in JavaDocs
		ParametersManager parametersManager = new ParametersManager();
		parametersManager.setIncomingPersonsDataFileName("real_incoming_persons_data_per_minute.txt");
		//ParametersManager.enableDebug();
		// no initialization these values will lead to use default values - which are based on real data  
		parametersManager.setMeanForEating(sliderPanelEatMean.getValue());
		parametersManager.setStdForEating(sliderPanelEatStD.getValue());
		parametersManager.setMeanForFoodQueue(sliderPanelFoodWaitMean.getValue());
		parametersManager.setStdForFoodQueue(sliderPanelFoodWaitStD.getValue());
		parametersManager.setCanteenCapacity((int) sliderPanelCapacity.getValue());
		parametersManager.setSimulationTime((int) sliderPanelStepCount.getValue());
		
		// create simulation
		try {
			simulation = new CanteenSimulation(parametersManager);
			// run simulation
			simulation.run();
			
			results = simulation.getResults();
			simulationPanel = new SimulationPanel(results);
			((SimulationPanel) simulationPanel).initSimulationPanel();
			
			//java.awt.Component graphComponent =  tabs.getComponent(1);
			tabs.removeAll();
			tabs.add("Simulace", simulationPanel);
			
			ArrayList<Double> waitingTimes = simulation.getWaitingTimes();
			
			final ChartPanelContainer chartFrame = new ChartPanelContainer(
				"", results,waitingTimes,simulation.getIncomingPersons(),
				"interval času", "počet lidí");
			
			//tabs.add("Graf", graphComponent);
			tabs.add("Graf", chartFrame.getChartPanel());
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		if (results != null) {
			((SimulationPanel) simulationPanel).updateRoundPanels(
				timeSlider.getIntegerValue() - 1);
		}
    }
    
    private void initConfigurationPanel() {
    	sliderPanelEatMean = new SliderPanel(
    			"Průměrná doba jezení (min):", 5, 25, 180, false);
    	sliderPanelEatStD = new SliderPanel(
    			"Směrodatná odchylka jezení:", 0.1f, 5f, 10f, true);
    	sliderPanelFoodWaitMean = new SliderPanel(
    			"Průměrný počet obsloužených lidí za minutu:", 0, 13, 60, true);
    	sliderPanelFoodWaitStD = new SliderPanel(
    			"Směrodatná odchylka obsloužených lidí:", 0.1f, 1f, 10f, true);
    	sliderPanelCapacity = new SliderPanel(
    			"Kapacita jídelny:", 4, 320, 1000, false);
    	sliderPanelStepCount = new SliderPanel(
    			"Počet kroků:", 10, 360, 1000, false);
    	/*sliderPanelInterval = new SliderPanel(
    			"Doba mezi intervaly (min):", 1, 5, 10, false);*/
    	
    	JButton runButton = new JButton("Spustit");
    	runButton.setActionCommand("confirm");
		runButton.addActionListener(this);
		
    	GroupLayout sliderLayout = new GroupLayout(configurationPanel);
        configurationPanel.setLayout(sliderLayout);
        sliderLayout.setAutoCreateGaps(true);
        sliderLayout.setAutoCreateContainerGaps(true);
        
        sliderLayout.setHorizontalGroup(
    		sliderLayout.createParallelGroup()
    		.addComponent(sliderPanelEatMean, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    		.addComponent(sliderPanelEatStD, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    		.addComponent(sliderPanelFoodWaitMean, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    		.addComponent(sliderPanelFoodWaitStD, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    		.addComponent(sliderPanelCapacity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    		.addComponent(sliderPanelStepCount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    		//.addComponent(sliderPanelInterval, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        	.addGroup(sliderLayout.createSequentialGroup()
	        	.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	    		.addComponent(runButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, RUN_BUTTON_SIZE.width)
	        	.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
			)
    	);
        sliderLayout.setVerticalGroup(
        	sliderLayout.createSequentialGroup()
        	.addComponent(sliderPanelEatMean, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        	.addComponent(sliderPanelEatStD, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        	.addComponent(sliderPanelFoodWaitMean, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        	.addComponent(sliderPanelFoodWaitStD, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        	.addComponent(sliderPanelCapacity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        	.addComponent(sliderPanelStepCount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        	//.addComponent(sliderPanelInterval, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        	.addGroup(sliderLayout.createSequentialGroup()
	        	.addComponent(runButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        	)
		); 
	}

	private void initComponents() {
    	configurationPanel = new javax.swing.JPanel();
        tabs = new JTabbedPane();
        simulationPanel = new JPanel();
        graphPanel = new JPanel();
        tabs.addTab("Simulace", simulationPanel);
        tabs.addTab("Graf", graphPanel);
		
        controlPanel = new javax.swing.JPanel();
        
        configurationPanel.setBorder(new javax.swing.border.TitledBorder("Nastavení"));
        //tabs.setBorder(new javax.swing.border.LineBorder(Color.black));
        controlPanel.setBorder(new javax.swing.border.LineBorder(Color.gray));
        
        JButton stepFirst = new JButton("První krok");
        JButton stepPrevious = new JButton("Předchozí krok");
        JButton stepNext = new JButton("Další krok");
        JButton stepLast = new JButton("Poslední krok");
        
        stepFirst.setActionCommand("first");
		stepFirst.addActionListener(this);
		stepPrevious.setActionCommand("previous");
		stepPrevious.addActionListener(this);
		stepNext.setActionCommand("next");
		stepNext.addActionListener(this);
		stepLast.setActionCommand("last");
		stepLast.addActionListener(this);
		
		timeSlider = new SliderPanel("Výběr kroku: ", 1, 1,
				1, false);
		
		ChangeListener listener = new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
            	doTimeSliderAction(e);
            }
        };
        
        timeSlider.addChangeListener(listener);
		
		GroupLayout controlLayout = new GroupLayout(controlPanel);
        controlPanel.setLayout(controlLayout);
        controlLayout.setAutoCreateGaps(true);
        controlLayout.setAutoCreateContainerGaps(true);
        
        controlLayout.setHorizontalGroup(
        	controlLayout.createParallelGroup()
        	.addComponent(timeSlider)
        	.addGroup(controlLayout.createSequentialGroup()
    			.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    			.addComponent(stepFirst)
        		.addComponent(stepPrevious)
        		.addComponent(stepNext)
        		.addComponent(stepLast)
        		.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        	)
        );
        controlLayout.setVerticalGroup(
        	controlLayout.createSequentialGroup()
        	.addComponent(timeSlider)
        	.addGroup(controlLayout.createParallelGroup()
    			.addComponent(stepFirst)
        		.addComponent(stepPrevious)
        		.addComponent(stepNext)
        		.addComponent(stepLast)
        	)
        );
        
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(
    		layout.createSequentialGroup()
    		.addGroup(layout.createParallelGroup()
	    		.addComponent(tabs, GroupLayout.DEFAULT_SIZE,
	    				SIMULATION_PANEL_SIZE.width, GroupLayout.DEFAULT_SIZE)
	    		.addComponent(controlPanel)
    		)
    		.addComponent(configurationPanel, GroupLayout.DEFAULT_SIZE,
    				CONFIG_PANEL_SIZE.width, CONFIG_PANEL_SIZE.width + 200)
		);
        layout.setVerticalGroup(
        	layout.createSequentialGroup()
    		.addGroup(layout.createParallelGroup()
	    		.addGroup(layout.createSequentialGroup()
		    		.addComponent(tabs, GroupLayout.DEFAULT_SIZE,
		    				SIMULATION_PANEL_SIZE.height, Short.MAX_VALUE)
		    		.addComponent(controlPanel, GroupLayout.PREFERRED_SIZE,
		    				GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    		)
	    		.addComponent(configurationPanel, GroupLayout.DEFAULT_SIZE,
	    				GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    		)
		);
    }
	
	public static void main(String[] args) {
		new MainWindow();
	}

	private void doTimeSliderAction(ChangeEvent e) {
		if (results != null) {
			((SimulationPanel) simulationPanel).updateRoundPanels(
				timeSlider.getIntegerValue() - 1);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "confirm":
			initSimulation();
			this.repaint();
			break;
		case "first":
			if (results != null) {
				timeSlider.setValue(1);
				((SimulationPanel) simulationPanel).updateRoundPanels(
						timeSlider.getIntegerValue() - 1);
			}
			break;
		case "previous":
			if (results != null
					&& timeSlider.getIntegerValue() > 1) {
				timeSlider.setValue(timeSlider.getIntegerValue() - 1);
				((SimulationPanel) simulationPanel).updateRoundPanels(
						timeSlider.getIntegerValue() - 1);
			}
			break;
		case "next":
			if (results != null
					&& timeSlider.getIntegerValue() < results.size()) {
				timeSlider.setValue(timeSlider.getIntegerValue() + 1);
				((SimulationPanel) simulationPanel).updateRoundPanels(
						timeSlider.getIntegerValue() - 1);
			}
			break;
		case "last":
			if (results != null) {
				timeSlider.setValue(results.size());
				((SimulationPanel) simulationPanel).updateRoundPanels(
						timeSlider.getIntegerValue() - 1);
			}
			break;
		default:
			break;
		}
	}
}

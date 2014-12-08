package canteen.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import canteen.CanteenSimulation;
import canteen.others.ParametersManager;

@SuppressWarnings("serial")
public class MainWindow extends JFrame implements ActionListener {
	private JPanel buttonPanel;
    private JPanel configurationPanel;
    private JPanel simulationPanel;
    private static final Dimension SIMULATION_PANEL_SIZE = new Dimension(600, 500);
    private static final Dimension CONFIG_PANEL_SIZE = new Dimension(250, 500);
    private static final Dimension RUN_BUTTON_SIZE = new Dimension(150, 50);
    private SliderPanel sliderPanelEatMean;
	private SliderPanel sliderPanelEatStD;
	private SliderPanel sliderPanelFoodWaitMean;
	private SliderPanel sliderPanelFoodWaitStD;
	private SliderPanel sliderPanelCapacity;
	private SliderPanel sliderPanelStepCount;
	//private SliderPanel sliderPanelInterval;
	private RoundValuePanel roundPanel;
	
    public MainWindow() {
    	initComponents();
    	initConfigurationPanel();
    	
    	this.setTitle("Nastavení");
		this.pack();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
    
    private void runSimulation() {
    	// define simulation parameters, each method is decribed in JavaDocs
		ParametersManager parametersManager = new ParametersManager();
		parametersManager.setIncomingPersonsDataFileName("real_incoming_persons_data.txt");
		ParametersManager.enableDebug();
		// no initialization these values will lead to use default values - which are based on real data  
		parametersManager.setMeanForEating(sliderPanelEatMean.getValue());
		parametersManager.setStdForEating(sliderPanelEatStD.getValue());
		parametersManager.setMeanForFoodQueue(sliderPanelFoodWaitMean.getValue());
		parametersManager.setStdForFoodQueue(sliderPanelFoodWaitStD.getValue());
		parametersManager.setCanteenCapacity((int) sliderPanelCapacity.getValue());
		parametersManager.setSimulationTime((int) sliderPanelStepCount.getValue());
		
		// create simulation
		CanteenSimulation simulation;
		try {
			simulation = new CanteenSimulation(parametersManager);
			// run simulation
			simulation.run();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }
    
    private void initConfigurationPanel() {
    	sliderPanelEatMean = new SliderPanel(
    			"Průměrná doba jezení (min):", 5, 25, 180, false);
    	sliderPanelEatStD = new SliderPanel(
    			"Směrodatná odchylka jezení:", 0.1f, 5f, 10f, true);
    	sliderPanelFoodWaitMean = new SliderPanel(
    			"Průměrná doba vydání jídla (min):", 0, 5, 60, true);
    	sliderPanelFoodWaitStD = new SliderPanel(
    			"Směrodatná odchylka vydání jídla:", 0.1f, 1f, 10f, true);
    	sliderPanelCapacity = new SliderPanel(
    			"Kapacita jídelny:", 4, 300, 1000, false);
    	sliderPanelStepCount = new SliderPanel(
    			"Počet kroků:", 10, 73, 1000, false);
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
        simulationPanel = new javax.swing.JPanel();
        buttonPanel = new javax.swing.JPanel();
        
        configurationPanel.setBorder(new javax.swing.border.TitledBorder("Configuration"));
        simulationPanel.setBorder(new javax.swing.border.LineBorder(Color.black));
        buttonPanel.setBorder(new javax.swing.border.LineBorder(Color.gray));
        
        buttonPanel.add(new JButton("First step"));
        buttonPanel.add(new JButton("Previous step"));
        buttonPanel.add(new JButton("Next step"));
        buttonPanel.add(new JButton("Last step"));
        
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(
    		layout.createSequentialGroup()
    		.addGroup(layout.createParallelGroup()
	    		.addComponent(simulationPanel, GroupLayout.DEFAULT_SIZE,
	    				SIMULATION_PANEL_SIZE.width, GroupLayout.DEFAULT_SIZE)
	    		.addComponent(buttonPanel)
    		)
    		.addComponent(configurationPanel, GroupLayout.DEFAULT_SIZE,
    				CONFIG_PANEL_SIZE.width, CONFIG_PANEL_SIZE.width + 200)
		);
        layout.setVerticalGroup(
        	layout.createSequentialGroup()
    		.addGroup(layout.createParallelGroup()
	    		.addGroup(layout.createSequentialGroup()
		    		.addComponent(simulationPanel, GroupLayout.DEFAULT_SIZE,
		    				SIMULATION_PANEL_SIZE.height, Short.MAX_VALUE)
		    		.addComponent(buttonPanel, GroupLayout.PREFERRED_SIZE,
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

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "confirm":
			runSimulation();
			break;
		default:
			break;
		}
	}
}

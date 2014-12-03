package canteen.gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ConfigurationWindow extends JFrame {
	private JPanel buttonPanel;
    private JPanel configurationPanel;
    private JPanel simulationPanel;
    private static final Dimension SIMULATION_PANEL_SIZE = new Dimension(600, 500);
    private static final Dimension CONFIG_PANEL_SIZE = new Dimension(250, 500);
	
    public ConfigurationWindow() {
    	initComponents();
    	initConfigurationPanel();
		this.setTitle("Nastavení");
		this.pack();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
    }
    
    private void initConfigurationPanel() {
    	SliderPanel sliderPanelEatMean = new SliderPanel(
    			"Prùmìrná doba jezení (min):", 5, 25, 180, false);
    	SliderPanel sliderPanelEatStD = new SliderPanel(
    			"Smìrodatná odchylka jezení:", 0.1f, 5f, 10f, true);
    	SliderPanel sliderPanelFoodWaitMean = new SliderPanel(
    			"Prùmìrná doba vydání jídla (min):", 0, 5, 60, true);
    	SliderPanel sliderPanelFoodWaitStD = new SliderPanel(
    			"Smìrodatná odchylka vydání jídla:", 0.1f, 1f, 10f, true);
    	SliderPanel sliderPanelCapacity = new SliderPanel(
    			"Kapacita jídelny:", 4, 300, 1000, false);
    	SliderPanel sliderPanelStepCount = new SliderPanel(
    			"Poèet krokù:", 10, 73, 1000, true);
    	SliderPanel sliderPanelInterval = new SliderPanel(
    			"Doba mezi intervaly mìøení (min):", 1, 5, 10, true);
    	
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
    		.addComponent(sliderPanelInterval, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    	);
        sliderLayout.setVerticalGroup(
        	sliderLayout.createSequentialGroup()
        	.addComponent(sliderPanelEatMean, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        	.addComponent(sliderPanelEatStD, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        	.addComponent(sliderPanelFoodWaitMean, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        	.addComponent(sliderPanelFoodWaitStD, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        	.addComponent(sliderPanelCapacity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        	.addComponent(sliderPanelStepCount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        	.addComponent(sliderPanelInterval, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
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
		new ConfigurationWindow();
	}
}

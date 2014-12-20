package canteen.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import canteen.result.Result;

@SuppressWarnings("serial")
public class SimulationPanel extends JPanel {
	private static final Dimension CANTEEN_IMAGE_SIZE = new Dimension(480, 380);
	private RoundValuePanel roundPanelEating;
	private RoundValuePanel roundPanelFinished;
	private RoundValuePanel roundPanelInFoodQueue;
	private RoundValuePanel roundPanelInTableQueue;
	private ArrayList<Result> results;
	private BufferedImage image;
	private JLabel timeLabel;
	
	public SimulationPanel(ArrayList<Result> results) {
		this.results = results;
		
		try {
			image = ImageIO.read(getClass().getClassLoader()
					.getResourceAsStream("canteen_plan.png"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void initSimulationPanel() {
		if (results == null) return;
		int maxPeopleEating = Integer.MIN_VALUE;
		int maxPeopleFinished = Integer.MIN_VALUE;
		int maxPeopleInFoodQueue = Integer.MIN_VALUE;
		int maxPeopleInTableQueue = Integer.MIN_VALUE;
		int actualPeopleEating;
		int actualPeopleFinished;
		int actualPeopleInFoodQueue;
		int actualPeopleInTableQueue;
		
		this.removeAll();
		
		for (int i = 0; i < results.size(); i++) {
			actualPeopleEating		 = results.get(i).getNumberOfPeopleEating();
			actualPeopleFinished	 = results.get(i).getNumberOfPeopleFinished();
			actualPeopleInFoodQueue	 = results.get(i).getNumberOfPeopleInFoodQueue();
			actualPeopleInTableQueue = results.get(i).getNumberOfPeopleInTableQueue();
			maxPeopleEating =
					(actualPeopleEating > maxPeopleEating) ?
							actualPeopleEating : maxPeopleEating;
			maxPeopleFinished =
					(actualPeopleFinished > maxPeopleFinished) ?
							actualPeopleFinished : maxPeopleFinished;
			maxPeopleInFoodQueue =
					(actualPeopleInFoodQueue > maxPeopleInFoodQueue) ?
							actualPeopleInFoodQueue : maxPeopleInFoodQueue;
			maxPeopleInTableQueue =
					(actualPeopleInTableQueue > maxPeopleInTableQueue) ?
							actualPeopleInTableQueue : maxPeopleInTableQueue;
		}
		
		roundPanelEating = new RoundValuePanel(0,
				results.get(0).getNumberOfPeopleEating(), maxPeopleEating);
		roundPanelFinished = new RoundValuePanel(0,
				results.get(0).getNumberOfPeopleFinished(), maxPeopleFinished);
		roundPanelInFoodQueue = new RoundValuePanel(0,
				results.get(0).getNumberOfPeopleInFoodQueue(), maxPeopleInFoodQueue);
		roundPanelInTableQueue = new RoundValuePanel(0,
				results.get(0).getNumberOfPeopleInTableQueue(), maxPeopleInTableQueue);
		
		this.setLayout(null);
		
		roundPanelInFoodQueue.setBounds(320, 170, 500, 500);
		roundPanelInTableQueue.setBounds(110, 40, 500, 500);
		roundPanelEating.setBounds(112, 179, 500, 500);
		roundPanelFinished.setBounds(40, 370, 500, 500);
		
		this.add(roundPanelInFoodQueue);
		this.add(roundPanelInTableQueue);
		this.add(roundPanelEating);
		this.add(roundPanelFinished);
		
		JLabel foodQueueLabel = new JLabel("fronta na jídlo");
		JLabel tableQueueLabel = new JLabel("fronta na sednutí");
		JLabel eatingLabel = new JLabel("lidé za stolem");
		JLabel finishedLabel = new JLabel("najezených lidí celkem");
		timeLabel = new JLabel("Čas: ");
		timeLabel.setFont(new Font(timeLabel.getFont().getName(),
				timeLabel.getFont().getStyle(), 30));
		
		foodQueueLabel.setBounds(340, 260, 100, 20);
		tableQueueLabel.setBounds(70, 30, 100, 20);
		eatingLabel.setBounds(33, 219, 100, 20);
		finishedLabel.setBounds(145, 410, 140, 20);
		timeLabel.setBounds(430, 400, 400, 100);
		timeLabel.setText("Čas: " + results.get(0).getFormattedTime());
		
		this.add(foodQueueLabel);
		this.add(tableQueueLabel);
		this.add(eatingLabel);
		this.add(finishedLabel);
		this.add(timeLabel);
	}
	
	public void updateRoundPanels(int actualStepNumber) {
		roundPanelEating.setValue(
				results.get(actualStepNumber).getNumberOfPeopleEating());
		roundPanelFinished.setValue(
				results.get(actualStepNumber).getNumberOfPeopleFinished());
		roundPanelInFoodQueue.setValue(
				results.get(actualStepNumber).getNumberOfPeopleInFoodQueue());
		roundPanelInTableQueue.setValue(
				results.get(actualStepNumber).getNumberOfPeopleInTableQueue());
		timeLabel.setText("Čas: " + results.get(actualStepNumber).getFormattedTime());
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 20, 20,
				CANTEEN_IMAGE_SIZE.width,
				CANTEEN_IMAGE_SIZE.height, null);
    }
}

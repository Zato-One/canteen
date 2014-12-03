package canteen.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SimulationWindow extends JPanel {
	private static final int WINDOW_WIDTH = 400;
	private static final int WINDOW_HEIGHT = 400;
	
	public SimulationWindow() {
		this.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
	}
	
	protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        drawLine(g2);
    }
	
	private void drawLine(Graphics2D g2) {
		g2.setStroke(new BasicStroke(3));
        g2.setColor(Color.GRAY);
        g2.drawLine(30, 30, 170, 170);
	}
	
	public static void main(String[] args) {
		new ConfigurationWindow();
	}
}

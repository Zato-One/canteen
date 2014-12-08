package canteen.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RoundValuePanel extends JPanel {
	private final int WINDOW_WIDTH;
	private int minValue;
	private int actualValue;
	private int maxValue;
	
	public RoundValuePanel(int minValue, int initValue, int maxValue) {
		this(minValue, initValue, maxValue, 100);
	}
	
	public RoundValuePanel(int minValue, int initValue, int maxValue,
			int panelSize) {
		this.minValue = (minValue > maxValue) ? maxValue : minValue;
		this.actualValue = (initValue > maxValue) ? maxValue : initValue;
		this.maxValue = maxValue;
		this.WINDOW_WIDTH = panelSize;
		this.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_WIDTH));
		this.setOpaque(false);
		this.repaint();
	}
	
	protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        drawLine(g2);
    }
	
	public void setValue(int value) {
		this.actualValue = (value > maxValue) ? maxValue : value;
		if (this.actualValue < minValue)
			this.actualValue = minValue;
		this.repaint();
	}
	
	private void drawLine(Graphics2D g2) {
		int circleSize = (int) (((float) WINDOW_WIDTH / 2)
        		* (((float) (actualValue - minValue)
        		/ (float) (maxValue - minValue)) + 1));  
		if (circleSize < WINDOW_WIDTH / 2)
			circleSize = WINDOW_WIDTH / 2;
        
        int offset = (WINDOW_WIDTH - circleSize) / 2;
        
        g2.setStroke(new java.awt.BasicStroke(3));
        g2.setColor(getRelevantColor());
        
        g2.fillOval(offset + 2, offset + 2, circleSize - 4, circleSize - 4);
        //g2.setColor(getABitDarkerColor(getRelevantColor(minValue, actualValue, maxValue)));
        g2.setColor(getABitDarkerColor(getRelevantColor()));
        g2.drawOval(offset + 2, offset + 2, circleSize - 4, circleSize - 4);
        
        g2.setColor(Color.black);
        
        g2.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 16));
        
        String actualValueText = String.valueOf(actualValue);
        int stringWidth = (int)
        		g2.getFontMetrics().getStringBounds(
        				actualValueText, g2).getWidth();
        int stringHeight = (int)
        		g2.getFontMetrics().getStringBounds(
        				actualValueText, g2).getHeight();
        
        
        //g2.drawString(String.valueOf(actualValue), 0, 0);
        g2.drawString(actualValueText,
        		WINDOW_WIDTH / 2 - stringWidth / 2,
        		(WINDOW_WIDTH + stringHeight / 2) / 2);
	}
	
	private Color getABitDarkerColor(Color relevantColor) {
		return getABitDarkerColor(relevantColor, 30);
	}

	private Color getABitDarkerColor(Color relevantColor,
			int change) {
		int rColor = relevantColor.getRed() - change;
		int gColor = relevantColor.getGreen() - change;
		int bColor = relevantColor.getBlue() - change;
		
		rColor = (rColor < 0) ? 0 : rColor;
		gColor = (gColor < 0) ? 0 : gColor;
		bColor = (bColor < 0) ? 0 : bColor;
		
		return new Color(rColor, gColor, bColor);
	}

	private Color getRelevantColor() {
		int shade;
		
		shade = 255 - (int) (((float) (actualValue - minValue) / (float) (maxValue - minValue)) * 255f);
		
		return new Color(255, shade, 0);
	}
}

package canteen.gui;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class SliderPanel extends JPanel {
	//private static final Dimension LABEL_SIZE = new Dimension(300, 25);
	//private static final Dimension SLIDER_SIZE = new Dimension(100, 30);
	//private static final Dimension SLIDER_PANEL_SIZE = new Dimension(400, 40);
	
	private JLabel label;
	private JSlider slider;
	private JTextField textField;
	private boolean realValues = true;
	
	// constructor with default charColumns (textBox size)
	public SliderPanel(String labelText, float minValue, float initValue,
			float maxValue, boolean realValues) {
		this(labelText, minValue, initValue, maxValue, 4, realValues);
	}
	
	// constructor with given charColumns (textBox size)
	public SliderPanel(String labelText, float minValue, float initValue,
			float maxValue, int charColumns, boolean realValues) {
		ChangeListener listener = new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
            	doSlidersAction(e);
            }
        };
        
        this.realValues = realValues;
        
        label = createLabel(labelText);
        slider = createSlider(minValue, maxValue, initValue, listener);
        if (realValues)
			textField = createTextField(charColumns, String.valueOf(initValue));
		else
			textField = createTextField(charColumns, String.valueOf((int) initValue));
		
		GroupLayout sliderLayout = new GroupLayout(this);
        this.setLayout(sliderLayout);
        sliderLayout.setAutoCreateGaps(true);
        sliderLayout.setAutoCreateContainerGaps(true);
        
        sliderLayout.setHorizontalGroup(
    		sliderLayout.createParallelGroup()
    		.addComponent(label)
    		.addGroup(sliderLayout.createSequentialGroup()
	    		.addComponent(slider, GroupLayout.DEFAULT_SIZE,
	    				GroupLayout.DEFAULT_SIZE, 6000)
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(textField, GroupLayout.PREFERRED_SIZE,
						GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
			)
    	);
        sliderLayout.setVerticalGroup(
        	sliderLayout.createSequentialGroup()
        	.addComponent(label)
        	.addGroup(sliderLayout.createParallelGroup()
            	.addComponent(slider)
    			.addComponent(textField)
			)
		);
	}
	
	public JLabel createLabel(String name) {
		return new JLabel(name);
	}
	
	public JSlider createSlider(float minValue, float maxValue, float initValue,
			ChangeListener listener) {
		JSlider slider;

		if (realValues) {
			slider = new JSlider((int) (minValue * 100),
					(int) (maxValue * 100),
					(int) (initValue * 100));
		} else {
			slider = new JSlider((int) minValue,
					(int) maxValue,
					(int) initValue);
		}
		slider.addChangeListener(listener);
		
		return slider;
	}
	
	public JTextField createTextField(int charColumns, String initText) {
		JTextField textField = new JTextField(charColumns);
		textField.setText(initText);
		textField.setEditable(false);
		
		textField.setHorizontalAlignment(JTextField.CENTER);
		
		return textField;
	}	
	
	private void doSlidersAction(ChangeEvent e) {
		String textBoxString;
		
		if (realValues) {
			textBoxString =
				String.valueOf(((float) ((JSlider) e.getSource()).getValue()) / 100);
			textField.setText(textBoxString);
		} else {
			textBoxString =
					String.valueOf(((JSlider) e.getSource()).getValue());
			textField.setText(textBoxString);
		}
		textBoxString = null;
	}
	
	public void addChangeListener(ChangeListener listener) {
		slider.addChangeListener(listener);
	}
	
	public float getFloatValue() {
		return Float.valueOf(textField.getText());
	}
	
	public int getIntegerValue() {
		return Integer.valueOf(textField.getText());
	}
	
	public float getValue() {
		return getFloatValue();
	}
	
	public void setValue(float value) {
		if (realValues)
			slider.setValue((int) (value * 100));
		else
			slider.setValue((int) value);
	}
	
	public void setMaximum(float maximum) {
		if (realValues)
			slider.setMaximum((int) (maximum * 100));
		else
			slider.setMaximum((int) maximum);
	}
}

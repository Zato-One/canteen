package canteen.others;

import java.util.ArrayList;
import java.util.Collections;

public class CalculateAvegare {
	public static double calculate(ArrayList<Integer> values) {

		Collections.sort(values);
		
		/*
		// Calculate median (middle number)
		double median = 0;
		double pos1 = Math.floor((values.size() - 1.0) / 2.0);
		double pos2 = Math.ceil((values.size() - 1.0) / 2.0);
		if (pos1 == pos2) {
			median = values.get((int) pos1);
		} else {
			median = (values.get((int) pos1) + values.get((int) pos2)) / 2.0;
		}
		*/
		int sum = 0;
		int i = 0;
		for(int value:values){
			if(value != 0){
				sum = sum + value;
				i++;
			}
		}
		
		double average = 0;
		if(i != 0) {
			average = (double)sum / (double)i; 
		}
		
		return average;

	}
}

package canteen.others;

import java.util.ArrayList;

public class CalculateAvegare {
	public static double calculate(ArrayList<Integer> values) {

		int sum = 0;
		int i = 0;
		for(int value:values){
				sum = sum + value;
				i++;
		}
		
		double average = 0;
		if(i != 0) {
			average = (double)sum / (double)i; 
		}
		
		return average;

	}
}

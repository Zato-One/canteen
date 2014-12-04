package canteen.others;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class IncomingPersons {

	ArrayList<Integer> values = new ArrayList<>();

	public IncomingPersons(String filename) throws Exception {
		try {
			loadFromFile(filename);
			if (ParametersManager.isDebug()) {
				System.out.println("Loaded " + values.size() + " records from "
						+ filename);
			}
		} catch (Exception e) {
			throw new Exception(
					"Unable to load incoming persons data from file: "
							+ filename);
		}
	}
	
	
	public ArrayList<Integer> getIncomingPersons(){
		return values;
	}
	
	
	public int getSumOfAllIncomingPersons(){
		int sum = 0;
		for(int i : values) {
			sum = sum + i;
		}
		return sum;
	}
	
	public int getNumberOfPersons(int possitionInArray){
		if (possitionInArray < values.size()) {
			return values.get(possitionInArray);
		} else {
			return 0;
		}
	}

	private void loadFromFile(String filename) throws Exception {
		Scanner scanner = new Scanner(new File(filename));
		int i = 0;
		while (scanner.hasNextInt()) {
			values.add(scanner.nextInt());
		}
	}
}

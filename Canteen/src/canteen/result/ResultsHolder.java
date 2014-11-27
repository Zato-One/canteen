package canteen.result;

import canteen.person.PersonHolder;
import canteen.person.PersonState;
import canteen.result.Result;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;

public class ResultsHolder {

	ArrayList<Result> results = new ArrayList<>();

	public void addResult(int time, PersonHolder personHolder) {
		Result result = new Result(time,
				personHolder.getNumberOfPersonsInState(PersonState.foodQueue),
				personHolder.getNumberOfPersonsInState(PersonState.tableQueue),
				personHolder.getNumberOfPersonsInState(PersonState.eating),
				personHolder.getNumberOfPersonsInState(PersonState.finished));
		results.add(result);
	}

	public ArrayList<Result> getAllResults() {
		return results;
	}

	public void writeResult(int pos) {
		Result result = results.get(pos);
		if (result != null) {
			System.out.println("Time: " + result.getFormattedTime());
			System.out.println("FoodQueue: "
					+ result.getNumberOfPeopleInFoodQueue());
			System.out.println("TableQueue: "
					+ result.getNumberOfPeopleInTableQueue());
			System.out.println("Eating: " + result.getNumberOfPeopleEating());
			System.out.println("Finished: "
					+ result.getNumberOfPeopleFinished());
			System.out.println("---");
		}
	}

	public void writeAll() {
		for (int i = 0; i < results.size(); i++) {
			writeResult(i);
		}
	}

	public void saveToCSV(String filename, String divider) throws Exception {
		PrintWriter writer = new PrintWriter(filename, "UTF-8");
		for (Result result : results) {
			writer.println(result.getTime() + divider
					+ result.getFormattedTime() + divider
					+ result.getNumberOfPeopleInFoodQueue() + divider
					+ result.getNumberOfPeopleInTableQueue() + divider
					+ result.getNumberOfPeopleEating() + divider
					+ result.getNumberOfPeopleFinished());
		}
		writer.close();
	}

}

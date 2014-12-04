package canteen.result;

import java.io.PrintWriter;
import java.util.ArrayList;

import canteen.others.IncomingPersons;
import canteen.person.PersonHolder;
import canteen.person.PersonState;

public class ResultsHolder {

	ArrayList<Result> results = new ArrayList<>();
	ArrayList<Double> waitingTimes = new ArrayList<>();

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
	
	public ArrayList<Double> getAllWaitingTimes(){
		return waitingTimes;
	}
	
	public void setWaitingTimes(ArrayList<Double> newWatitngTimes){
		waitingTimes = newWatitngTimes;
		for(int i = 0;i<waitingTimes.size();i++){
			if(waitingTimes.get(i) < 0.0){
				waitingTimes.set(i, (double)-1);
			}
		}
	}
	
	public void writeAllWaitingTimes() {
		for (Double time : waitingTimes) {
			System.out.println(time);
		}
	}
	
	
	public int getSumOfAllPersons(){
		int sum = 0;
		if(results.size() > 0) {
			Result result = results.get(results.size()-1);
		    sum = result.getNumberOfPeopleEating() + result.getNumberOfPeopleFinished() + result.getNumberOfPeopleInFoodQueue() + result.getNumberOfPeopleInTableQueue();
		}
		return sum;
	}

	public void writeResult(int pos) {
		Result result = results.get(pos);
		if (result != null) {
			System.out.println("Time: " + result.getTime());
			System.out.println("Real time: " + result.getFormattedTime());
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

	public void saveToCSV(String filename, String divider,IncomingPersons incomingPersons) throws Exception {
		PrintWriter writer = new PrintWriter(filename, "UTF-8");
		int i = 0;
		for (Result result : results) {
			writer.println(
					result.getTime() + divider
					+ result.getFormattedTime() + divider
					+ incomingPersons.getNumberOfPersons(i) + divider
					+ result.getNumberOfPeopleInFoodQueue() + divider
					+ result.getNumberOfPeopleInTableQueue() + divider
					+ result.getNumberOfPeopleEating() + divider
					+ result.getNumberOfPeopleFinished() + divider
					+ waitingTimes.get(i));
			i++;
		}
		writer.close();
	}

}

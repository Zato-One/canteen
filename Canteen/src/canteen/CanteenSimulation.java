package canteen;

import canteen.moveTo.MoveToTableQueue;
import canteen.moveTo.MoveToEating;
import canteen.moveTo.MoveToFinish;
import canteen.person.Person;
import canteen.person.PersonHolder;
import canteen.person.PersonState;
import canteen.result.Result;
import canteen.result.ResultsHolder;

import java.io.IOException;
import java.util.ArrayList;

import others.CalculateAvegare;
import others.IncomingPersons;
import others.ParametersManager;

public class CanteenSimulation {

	int currentTime = 0;
	PersonHolder personHolder;
	ResultsHolder resultHolder;
	IncomingPersons incomingPersons;
	ParametersManager parametersManager;

	public CanteenSimulation(ParametersManager parametersManager)
			throws Exception {
		if (parametersManager != null) {
			this.personHolder = new PersonHolder();
			this.resultHolder = new ResultsHolder();
			this.incomingPersons = new IncomingPersons(
					parametersManager.getIncomingPersonsfilename());
			this.parametersManager = parametersManager;
			if (ParametersManager.isDebug()) {
				parametersManager.writeValues();
			}
		} else {
			throw new Exception("ParametersManager is not valid");
		}
	}

	public void run() throws Exception {
		try {
		int i = 0;
		for (currentTime = 0; currentTime < (parametersManager
				.getSimulationTime() * parametersManager.getSimulationStep()); currentTime += parametersManager
				.getSimulationStep()) {
			// save result
			resultHolder.addResult(currentTime, personHolder);
			if (ParametersManager.isDebug()) {
				resultHolder.writeResult(i);
			}

			// add people
			personHolder.addPerson(currentTime, incomingPersons
					.getNumberOfPerson(currentTime, parametersManager));
			// move from food queue to table queue
			MoveToTableQueue.move(
					personHolder.getPersonsInState(PersonState.foodQueue),
					parametersManager);
			// move from table queue to eating state
			MoveToEating.move(
					personHolder.getPersonsInState(PersonState.tableQueue),
					personHolder.getNumberOfPersonsInState(PersonState.eating),
					currentTime, parametersManager);
			// move from eating state to finish state
			MoveToFinish.move(
					personHolder.getPersonsInState(PersonState.eating),
					currentTime, parametersManager);

			i++;
		}
		
		calculateWaitingTime();
		
		if(ParametersManager.isDebug()){
			System.out.println("Total persons incoming: "+incomingPersons.getSumOfAllIncomingPersons());
			System.out.println("Total persons in last step: "+resultHolder.getSumOfAllPersons());
			System.out.println("Avegare waiting time: ");
			resultHolder.writeAllWaitingTimes();
			
		}
		
		}
		catch (Exception e){
			throw new Exception("Something goes wrong during the simulation.");
		}
		
		

	}

	
	public ArrayList<Result> getResults() throws Exception {
		ArrayList<Result> results = resultHolder.getAllResults();
		if (results.size() > 0) {
			return results;
		} else {
			throw new Exception(
					"Simulation dont have any results. Did you call run() before getResults()?");
		}
	}


	private double howLongTakeToReachEatingStateAtTime(int atTime) {
        ArrayList<Person> personsWithTime = personHolder.getPersonsWithArrivalTime(atTime);
        ArrayList<Integer> times = new ArrayList<>();
        
        for(Person person : personsWithTime){
        	if (person.getDispatchTime() != 0) {
        		Integer diff = person.getDispatchTime() - person.getArrivalTime();
        		times.add(diff);
        	}
        }
        
        return CalculateAvegare.calculate(times);
        
    }
	
	
	private void calculateWaitingTime(){
		ArrayList<Double> waitingTimes = new ArrayList<>();
		for (int i = 0; i < (parametersManager.getSimulationTime() * parametersManager.getSimulationStep()); i = i + parametersManager.getSimulationStep()) {
			waitingTimes.add(howLongTakeToReachEatingStateAtTime(i));
		}
		resultHolder.setWaitingTimes(waitingTimes);
	}

	public ArrayList<Double> getWaitingTimes() throws Exception {
		ArrayList<Double> results = resultHolder.getAllWaitingTimes();
		if (results.size() > 0) {
			return results;
		} else {
			throw new Exception(
					"Simulation dont have any results. Did you call run() before getWaitingTimes()?");
		}
		
	}


	public void saveToCSV(String filename, String divider) throws Exception {
		try {
			resultHolder.saveToCSV(filename, divider,incomingPersons);
		} catch (Exception e) {
			throw new Exception("Unable to create CSV file");
		}
	}
	
	public void plotGraph() throws Exception{
		try {
			Runtime.getRuntime().exec("gnuplot plot.gnu");
		}
		catch (Exception e){
			throw new Exception("Unable to plot graph - need gnuplot!");
		}
	}

}

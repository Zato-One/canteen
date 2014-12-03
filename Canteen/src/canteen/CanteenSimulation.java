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

	public void run() {
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
		
		if(ParametersManager.isDebug()){
			System.out.println("Total persons incoming: "+incomingPersons.getSumOfAllIncomingPersons());
			System.out.println("Total persons in last step: "+resultHolder.getSumOfAllPersons());
			
		}

	}

	public void writeDebugResults() {
		resultHolder.writeAll();
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

	public double howLongTakeToReachEatingStateAtTime(int atTime) {
        ArrayList<Person> personsWithTime = personHolder.getPersonsWithArrivalTime(atTime);
        int i = 0;
        int sum = 0;
        for(Person person : personsWithTime){
        	if (person.getDispatchTime() != 0) {
        		int diff = Math.abs(person.getDispatchTime() - person.getArrivalTime());
        		System.out.println("----");
        		System.out.println(atTime+": Diff "+diff);
        		System.out.println(atTime+": Arrival "+person.getArrivalTime());
        		System.out.println(atTime+": Dispatch "+person.getDispatchTime());
                sum = sum + diff;
                i++;
        	}
        	else {
        		// simulation needs more time
        		i = 1;
        		sum = -1;
        		break;
        	}
        	
        }
        
        // no person at this time
        double mean = -2;
        if (i != 0) {
            mean = (double) sum / (double) i;
        }
        return mean;
        
    }

	public ArrayList<Double> getAllWaitingTimes() {
		ArrayList<Double> waitingTimes = new ArrayList<>();
		for (int i = 0; i < (parametersManager.getSimulationTime() * parametersManager.getSimulationStep()); i = i + parametersManager.getSimulationStep()) {
			waitingTimes.add(howLongTakeToReachEatingStateAtTime(i));
		}

		return waitingTimes;
	}

	public void writeAllWaitingTimes() {
		for (Double time : getAllWaitingTimes()) {
			System.out.println(time);
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

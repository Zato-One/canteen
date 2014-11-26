package canteen;

import canteen.moveTo.MoveToTableQueue;
import canteen.moveTo.MoveToEating;
import canteen.moveTo.MoveToFinish;
import canteen.person.Person;
import canteen.person.PersonHolder;
import canteen.person.PersonState;
import canteen.result.Result;
import canteen.result.ResultsHolder;

import java.util.ArrayList;

public class CanteenSimulation {

    int currentTime = 0;
    PersonHolder personHolder;
    ResultsHolder resultHolder;
    IncomingPersons incomingPersons;
    ParametersManager parametersManager;

    public CanteenSimulation(ParametersManager parametersManager) throws Exception {
        if (parametersManager != null) {
            this.personHolder = new PersonHolder();
            this.resultHolder = new ResultsHolder();
            this.incomingPersons = new IncomingPersons(parametersManager.getIncomingPersonsfilename());
            this.parametersManager = parametersManager;
        } else {
            throw new Exception("ParametersManager is not valid");
        }
    }

    public void run() {
        for (currentTime = 0; currentTime < parametersManager.getSimulationTime(); currentTime++) {
            // save result
            resultHolder.addResult(currentTime, personHolder);

            // add people
            personHolder.addPerson(currentTime, incomingPersons.getNumberOfPerson(currentTime));
            // move from food queue to table queue
            MoveToTableQueue.move(personHolder.getPersonsInState(PersonState.foodQueue), parametersManager);
            // move from table queue to eating state
            MoveToEating.move(personHolder.getPersonsInState(PersonState.tableQueue), personHolder.getNumberOfPersonsInState(PersonState.eating), currentTime, parametersManager);
            // move from eating state to finish state
            MoveToFinish.move(personHolder.getPersonsInState(PersonState.eating), parametersManager);
            
        }

    }

    public void writeResults() {
        resultHolder.writeAll();
    }
    
    public ArrayList<Result> getResults() throws Exception{
    	ArrayList<Result> results = resultHolder.getAllResults(); 
    	if(results.size() > 0) {
    		return results;
    	}
    	else {
    		throw new Exception("Simulation dont have any results. Did you call run() before getResults()?");
    	}
    }

    public double howLongTakeToReachEatingStateAtTime(int atTime) {
        ArrayList<Person> personsWithTime = personHolder.getPersonsWithArrivalTime(atTime);
        int i = 0;
        int sum = 0;
        Boolean hasRun = false;
        for (Person person : personsWithTime) {
            hasRun = true;
            if (person.getDispatchTime() != 0) {
                int diff = person.getDispatchTime() - person.getArrivalTime();
                sum = sum + diff;
                i++;
            } else {
                sum = -1;
                i = 1;
                break;
            }
        }
        double mean = -2;
        if (hasRun) {
            mean = (double) sum / (double) i;
        }
        return mean;
    }

    public ArrayList<Double> getAllWaitingTimes() {
        ArrayList<Double> waitingTimes = new ArrayList<>();
        for (int i = 0; i < parametersManager.getSimulationTime(); i++) {
            waitingTimes.add(howLongTakeToReachEatingStateAtTime(i));
        }

        return waitingTimes;
    }

    public void saveToCSV(String filename, String divider) throws Exception {
        try {
            resultHolder.saveToCSV(filename, divider);
        } catch (Exception e) {
            throw new Exception("Unable to create CSV file");
        }
    }

}

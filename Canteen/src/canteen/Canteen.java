package canteen;

import java.util.ArrayList;

import canteen.result.Result;

public class Canteen {

	public static void main(String[] args) {
		try {
			// define simulation parameters, each method is decribed in JavaDocs
			ParametersManager parametersManager = new ParametersManager();
			parametersManager.setIncomingPersonsDataFileName("real_incoming_persons_data.txt");
			ParametersManager.enableDebug();
			// no initialization these values will lead to use default values - which are based on real data  
			//parametersManager.setMeanForEating(10.0);
			//parametersManager.setStdForEating(0.5);
			//parametersManager.setMeanForFoodQueue(5.0);
			//parametersManager.setStdForFoodQueue(0.5);
			//parametersManager.setCanteenCapacity(300);
			//parametersManager.setSimulationTime(40);
			//parametersManager.setSimulationStep(5);
			

			// create simulation
			CanteenSimulation simulation = new CanteenSimulation(
					parametersManager);
			// run simulation
			simulation.run();
			
			// save results to CSV
			simulation.saveToCSV("results.csv", ",");
			
			// plot graph to file
			// simulation.plotGraph();

			//simulation.writeAllWaitingTimes();
			
			// how to get simulation results
			ArrayList<Result> results = simulation.getResults();
			for (Result result : results) {
				// do what you want to do
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}

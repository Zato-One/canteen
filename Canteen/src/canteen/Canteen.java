package canteen;

import java.util.ArrayList;

import canteen.result.Result;

public class Canteen {

	public static void main(String[] args) {
		try {
			// define simulation parameters, each method is decribed in JavaDocs
			ParametersManager parametersManager = new ParametersManager();
			parametersManager.setSimulationTime(40);
			parametersManager
					.setIncomingPersonsDataFileName("incoming_persons_data.txt");
			parametersManager.setCanteenCapacity(100);
			parametersManager.setMeanForFoodQueue(5.0);
			parametersManager.setStdForFoodQueue(0.5);
			parametersManager.setMeanForEating(10.0);
			parametersManager.setStdForEating(0.5);

			// create simulation
			CanteenSimulation simulation = new CanteenSimulation(
					parametersManager);
			// run simulation
			simulation.run();
			// write results to console
			simulation.writeResults();
			// save results to CSV
			simulation.saveToCSV("results.csv", ",");

			
			// how to get simulation results
			ArrayList<Result> results = simulation.getResults();
			for (Result result : results) {
				// just do what you want to do!
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}

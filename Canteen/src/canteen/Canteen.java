package canteen;

import java.util.ArrayList;

import canteen.result.Result;

public class Canteen {

	public static void main(String[] args) {
		try {
			// define simulation parameters, each method is decribed in JavaDocs
			ParametersManager parametersManager = new ParametersManager();
			parametersManager.setIncomingPersonsDataFileName("real_incoming_persons_data.txt");
			parametersManager.setMeanForFoodQueue(5.0);
			parametersManager.setStdForFoodQueue(0.5);
			parametersManager.setSimulationStep(5);
			
			// no initialization these values will lead to use default values - which are based on real data  
			//parametersManager.setMeanForEating(10.0);
			//parametersManager.setStdForEating(0.5);
			//parametersManager.setCanteenCapacity(100);
			//parametersManager.setSimulationTime(40);
			

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
			System.out.println("Results size: "+results.size());
			for (Result result : results) {
				
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}

package canteen;

import java.util.ArrayList;

import canteen.result.Result;

public class Canteen {

    public static void main(String[] args) {
        try {
            // define simulation parameters, each method is decribed in JavaDocs
            ParametersManager parametersManager = new ParametersManager();
            parametersManager.setSimulationTime(40);
            parametersManager.setIncomingPersonsDataFileName("incoming_persons_data.txt");
            parametersManager.setCanteenCapacity(100);
            parametersManager.setMeanForFoodQueue(5.0);
            parametersManager.setStdForFoodQueue(0.5);
            parametersManager.setMeanForEating(2.0);
            parametersManager.setStdForEating(0.5);

            // create simulation
            CanteenSimulation simulation = new CanteenSimulation(parametersManager);
            // run simulation
            simulation.run();
            // write results to console
            simulation.writeResults();
            // save results to CSV
            simulation.saveToCSV("results.csv", ",");


            
            //ArrayList<Result> results = simulation.getResults();
            //results.get(1).get

            // How long take to reach eating state at given time
            // -1 means not enough simulation time for compute, -2 means no data to compute from
            // System.out.println("Time 0: " + simulation.howLongTakeToReachEatingStateAtTime(0));
            
            // Print all waiting times
            // -1 means not enough simulation time for compute, -2 means no data to compute from
            // ArrayList<Double> waitingTimes = simulation.getAllWaitingTimes();
            // for (Double time : waitingTimes) {
            //     System.out.println(time + "");
            // }

        } catch (Exception e) {
            System.out.println(e.getCause().toString());
        }
    }

}

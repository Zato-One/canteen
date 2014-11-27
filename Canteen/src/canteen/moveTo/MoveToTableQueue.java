package canteen.moveTo;

import canteen.ParametersManager;
import canteen.person.PersonState;
import canteen.person.Person;
import java.util.ArrayList;
import java.util.Random;

public class MoveToTableQueue {

    public static void move(ArrayList<Person> personsInFoodQueue,ParametersManager parametersManager) {
        int numberToMove = calculateNumberOfPersonsToMoveToNextState(parametersManager);
        int i = 0;
        for(Person person : personsInFoodQueue){
            if(i < numberToMove) {
                person.setPersonState(PersonState.tableQueue);
                i++;
            }
            else{
                break;
            }
        }
        if(ParametersManager.isDebug()){
        	System.out.println("From FOODQUEUE to TABLEQUEUE: "+i);
        }
    }

    public static int calculateNumberOfPersonsToMoveToNextState(ParametersManager parametersManager) {
        Random rng = new Random();
        double numberOfPersonsToMove = parametersManager.getMoveToTableQueueGaussianMean() 
                                       + parametersManager.getMoveToTableQueueGaussianStd() * rng.nextGaussian();
        return (int)Math.round(numberOfPersonsToMove);
    }

}

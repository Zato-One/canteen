package canteen.moveTo;

import canteen.others.ParametersManager;
import canteen.person.PersonState;
import canteen.person.Person;

import java.util.ArrayList;
import java.util.Random;

public class MoveToFinish {

    public static void move(ArrayList<Person> personsEating,int currentTime, ParametersManager parametersManager) {
    	int i = 0;
        for(Person person : personsEating){
            int eatingTime = currentTime - person.getDispatchTime();
            if(eatingTime >= calculateChanceToMove(parametersManager)) {
            	person.setPersonState(PersonState.finished);
            	i++;
            }
        }
        if(ParametersManager.isDebug()){
        	System.out.println("From EATING to FINISH: "+i);
        }
       
    }

    public static int calculateChanceToMove(ParametersManager parametersManager) {
        Random rng = new Random();
        double numberOfPersonsToMove = parametersManager.getMoveToFinishGaussianMean() 
                                       + parametersManager.getMoveToFinishGaussianStd() * rng.nextGaussian();
        return (int)Math.round(numberOfPersonsToMove);
    }

}

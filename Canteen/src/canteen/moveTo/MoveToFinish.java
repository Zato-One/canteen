package canteen.moveTo;

import canteen.ParametersManager;
import canteen.person.PersonState;
import canteen.person.Person;
import java.util.ArrayList;
import java.util.Random;

public class MoveToFinish {

    public static void move(ArrayList<Person> personsEating,int currentTime, ParametersManager parametersManager) {
        for(Person person : personsEating){
            int eatingTime = currentTime - person.getDispatchTime();
            if(eatingTime >= calculateChanceToMove(parametersManager)) {
            	person.setPersonState(PersonState.finished);
            }
        }
    }

    public static int calculateChanceToMove(ParametersManager parametersManager) {
        Random rng = new Random();
        double numberOfPersonsToMove = parametersManager.getMoveToFinishGaussianMean() 
                                       + parametersManager.getMoveToFinishGaussianStd() * rng.nextGaussian();
        return (int)Math.round(numberOfPersonsToMove);
    }

}

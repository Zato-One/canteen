package canteen.moveTo;

import canteen.ParametersManager;
import canteen.person.PersonState;
import canteen.person.Person;
import java.util.ArrayList;
import java.util.Random;

public class MoveToFinish {

    public static void move(ArrayList<Person> personsEating,ParametersManager parametersManager) {
        int numberToMove = calculateNumberOfPersonsToMoveToNextState(parametersManager);
        int i = 0;
        for(Person person : personsEating){
            if(i < numberToMove) {
                person.setPersonState(PersonState.finished);
                i++;
            }
            else{
                break;
            }
        }
    }

    public static int calculateNumberOfPersonsToMoveToNextState(ParametersManager parametersManager) {
        Random rng = new Random();
        double numberOfPersonsToMove = parametersManager.getMoveToFinishGaussianMean() 
                                       + parametersManager.getMoveToFinishGaussianStd() * rng.nextGaussian();
        return (int)Math.round(numberOfPersonsToMove);
    }

}

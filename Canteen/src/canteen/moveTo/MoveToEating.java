package canteen.moveTo;

import canteen.ParametersManager;
import canteen.person.Person;
import canteen.person.PersonState;
import java.util.ArrayList;
import java.util.Random;

public class MoveToEating {

    public static void move(ArrayList<Person> personsInTableQueue, int numberOfPersonsEating,int currentTime,ParametersManager parametersManager) {
        int diff = parametersManager.getCanteenCapacity() - numberOfPersonsEating;
        int i = 0;
        for (Person person : personsInTableQueue) {
            if (i < diff) {
                person.setPersonState(PersonState.eating);
                person.setDispatchTime(currentTime);
                i++;
            } else {
                break;
            }
        }
    }

}

package canteen.moveTo;

import java.util.ArrayList;

import canteen.others.ParametersManager;
import canteen.person.Person;
import canteen.person.PersonState;

public class MoveToEating {

    public static void move(ArrayList<Person> personsInTableQueue, int numberOfPersonsEating,int currentTime,ParametersManager parametersManager) {
        int diff = Math.abs(parametersManager.getCanteenCapacity() - numberOfPersonsEating);
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
        if(ParametersManager.isDebug()){
        	System.out.println("From TABLEQUEUE to EATING: "+i);
        }
    }

}

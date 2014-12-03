package canteen.person;

import java.util.ArrayList;

import others.ParametersManager;


public class PersonHolder {
    ArrayList<Person> persons = new ArrayList<>();
    
    public void addPerson(int currentTime,int numberOfPersons){
    	if(ParametersManager.isDebug()){
    		System.out.println("Added "+numberOfPersons+" to FOODQUEUE at "+currentTime+".");
    	}
        for(int i=0;i<numberOfPersons;i++){
            persons.add(new Person(currentTime));
        }
    }
    
    public ArrayList<Person> getPersonsInState(PersonState personState){
        ArrayList<Person> personsInState = new ArrayList<>();
        
        for(Person person : persons){
            if(person.getPersonState() == personState){
                personsInState.add(person);
            }
        }
        
        return personsInState;
    }
    
    public int getNumberOfPersonsInState(PersonState personState){
        return getPersonsInState(personState).size();
    }
    
    public ArrayList<Person> getPersonsWithArrivalTime(int time){
        ArrayList<Person> personsWithTime = new ArrayList<>();
        for(Person person : persons){
            if(person.getArrivalTime() == time){
                personsWithTime.add(person);
            }
        }
        return personsWithTime;
    }
    
}
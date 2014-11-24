package canteen.person;

public class Person {
    public int timeOfArrival = 0;
    public int timeOfDispatch = 0;
    public PersonState personState = PersonState.foodQueue;

    public Person(int initTime) {
        timeOfArrival = initTime;
    }
    
    public PersonState getPersonState(){
        return personState;
    }
    
    public void setPersonState(PersonState newPersonState){
        personState = newPersonState;
    }
    
    public void setDispatchTime(int dispatchTime){
        timeOfDispatch = dispatchTime;
    }
    
    public int getArrivalTime(){
        return  timeOfArrival;
    }
    
    public int getDispatchTime(){
        return timeOfDispatch;
    }
    

    
}

package canteen.result;

public class Result {
    private int time = 0;
    private int foodQueue = 0;
    private int tableQueue = 0;
    private int eating = 0;
    private int finished = 0;

    public Result(int time, int foodQueue, int tableQueue, int eating, int finished) {
        this.time = time;
        this.foodQueue = foodQueue;
        this.tableQueue = tableQueue;
        this.eating = eating;
        this.finished = finished;
    }
    
    public int getTime(){
        return time;
    }
    
    public int getNumberOfPeopleInFoodQueue(){
        return foodQueue;
    }
    
    public int getNumberOfPeopleInTableQueue(){
        return tableQueue;
    }
    
    public int getNumberOfPeopleEating(){
        return eating;
    }
    
    public int getNumberOfPeopleFinished(){
        return finished;
    }
    
    
}

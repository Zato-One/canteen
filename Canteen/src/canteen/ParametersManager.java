package canteen;

public class ParametersManager {

	private String incomingPersonsfilename = "incoming_persons_data.txt";
	private int canteenCapacity = 320;
	private int simulationTime = 73;
	private double moveToFinishGaussianStd = 3.2042;
	private double moveToFinishGaussianMean = 10.667;
	private double moveToTableQueueGaussianStd = 2;
	private double moveToTableQueueGaussianMean = 8;
	private int simulationStep = 5;

	/**
	 * Set filename of data set with incoming persons measurement. File have to
	 * be formated as INT\nINT\nINT
	 * 
	 * @param filename
	 *            Name of the file
	 */

	public void setIncomingPersonsDataFileName(String filename) {
		incomingPersonsfilename = filename;
	}

	/**
	 * Set duration of simulation in minutes
	 * 
	 * @param time
	 *            Duration time
	 */
	public void setSimulationTime(int time) {
		simulationTime = time;
	}

	/**
	 * Set canteen capacity
	 * 
	 * @param capacity
	 *            Total number of seats in canteen
	 */
	public void setCanteenCapacity(int capacity) {
		canteenCapacity = capacity;
	}

	/**
	 * Set standart deviation value for gaussian ranodom generator in food
	 * queue.
	 * 
	 * @param std
	 *            Value of standart deviation (only positive value)
	 */
	public void setStdForFoodQueue(double std) {
		moveToTableQueueGaussianStd = std;
	}

	/**
	 * Set mean value for gaussian ranodom generator in food queue.
	 * 
	 * @param mean
	 *            Value of mean (where the peak will be)
	 */
	public void setMeanForFoodQueue(double mean) {
		moveToTableQueueGaussianMean = mean;
	}

	/**
	 * Set standart deviation value for gaussian ranodom generator in eating
	 * state queue.
	 * 
	 * @param std
	 *            Value of standart deviation (only positive value)
	 */
	public void setStdForEating(double std) {
		moveToFinishGaussianStd = std;
	}

	/**
	 * Set mean value for gaussian ranodom generator in eating state.
	 * 
	 * @param mean
	 *            Value of mean (where the peak will be)
	 */
	public void setMeanForEating(double mean) {
		moveToFinishGaussianMean = mean;
	}

	/**
	 * Set simulation step time
	 * 
	 * @param step
	 *           Value of step in minutes 
	 */
	public void setSimulationStep(int step) {
		simulationStep = step;
	}

	public String getIncomingPersonsfilename() {
		return incomingPersonsfilename;
	}

	public int getCanteenCapacity() {
		return canteenCapacity;
	}

	public int getSimulationTime() {
		return simulationTime;
	}
	
	public int getSimulationStep(){
		return simulationStep;
	}

	public double getMoveToFinishGaussianMean() {
		return moveToFinishGaussianMean;
	}

	public double getMoveToFinishGaussianStd() {
		return moveToFinishGaussianStd;
	}

	public double getMoveToTableQueueGaussianMean() {
		return moveToTableQueueGaussianMean;
	}

	public double getMoveToTableQueueGaussianStd() {
		return moveToTableQueueGaussianStd;
	}

}

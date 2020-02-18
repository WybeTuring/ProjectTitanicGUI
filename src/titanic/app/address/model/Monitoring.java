package titanic.app.address.model;
import java.util.ArrayList;

/**
 * This class holds information about all observatories.
 * Has methods that return the observatory with the largest average galamsey colour value,
 * Largest galamsey colour value ever recorded and 
 * List of all galamsey recorded at a colour value greater than a given number.
 * @author Hephzibah Emereole
 */
	public class Monitoring {
		ArrayList <Observatory> observatoryList = new ArrayList<Observatory>();
		
		/**
		 * default constructor
		 */
		public Monitoring() {
			
		}
		
		/**
		 * This is an overloaded constructor		
		 * @param Observe is an arraylist containing list of observatories and
		 * information about all observatories.
		 */
		public Monitoring(ArrayList<Observatory>  Observe) {
			 this.observatoryList= Observe;
		}
		
		
		/**
		 * Gets the list of observatories
		 * @return the observatoryList
		 */
		public ArrayList<Observatory> getObservatoryList() {
			return observatoryList;
		}
		
		/**
		 * sets the observatory list to a desired list
		 * @param observatoryList the observatoryList to set
		 */
		public void setObservatoryList(ArrayList<Observatory> observatoryList) {
			this.observatoryList = observatoryList;
		}
		
		/**
		 * This method identifies the observatory with largest average colour value
		 * @returns the Observatory object with the largest average colour value
		 */
		public Observatory largestAverageObservatory() {
			Observatory newObserv = null;
			double averageCol = 0;
			for (int i=0; i< observatoryList.size(); i++) {
				if (averageCol < observatoryList.get(i).averageGalamColourValue()) {
					averageCol = observatoryList.get(i).averageGalamColourValue();
					newObserv = observatoryList.get(i);
				}
			}
			return newObserv;
			}
				
				
	    /**
	     * The method returns the largest galamsey colour value ever recorded
	     * @return an integer as the largest colour value
	     */
		public int largeColValue() {
			int colVal =0;
			for(Observatory e:observatoryList) {
				for(Galamsey g:e.getListGalamObserv()) {
					if(colVal < g.getColourValue()) {
						colVal = g.getColourValue();
					}
				}
			}
			return colVal;
		}
		
		
		/**
		 * Takes an arbitrary number as input
		 * Provides all galamsey events recorded with colour value greater than the arbitrary number
		 * @param number, an arbitary number provided by the user
		 * @returns the list of galamsey objects. 
		 */
		
		public ArrayList<Galamsey> allGalamseyObjectsGreaterThan(double number){
			ArrayList<Galamsey> allObserv = new ArrayList<Galamsey>();
			for (Observatory e: this.observatoryList) {
				for (Galamsey g: e.getListGalamObserv()) {
					if (g.getColourValue() > number)
						allObserv.add(g);	
					}
				}	
			return allObserv;
			}
		
		/***
		 * Adds a new observatory to an already existing list of observatories. 
		 * @param newObservatory
		 * @return a boolean to indicate whether or not the addition was successful
		 */
		public boolean addNewObservatory(Observatory newObservatory) {
			for(Observatory o:observatoryList) {
				if(o.equals(newObservatory)) {
					return false;
				}
			}
			observatoryList.add(newObservatory);
			return true;
		}
		
}
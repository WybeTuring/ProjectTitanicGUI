package titanic.app.address.model;
/**
 * Creation of Observatory class
 * @version: 1.0.1
 * @author: Kweku Andoh Yamoah(71712022)
 */

import java.util.ArrayList;
import java.util.Comparator;


/**
 * Observatory class with fields, methods and constructor to store and retrieve
 * the name of the observatory, the name of the country in which it is located, the year in which
 * galamsey observations started, the area covered by the observatory (in square kilometers) and a list of
 * galamsey events that it has recorded.
 *
 * It also returns;
 * The largest galamsey colour value recorded by the observatory.
 * The average galamsey colour value recorded at the observatory.
 * A list of all galamsey recorded at the observatory with a colour value greater than a
 * given/arbitrary number.
 * 
 * 
 */

public class Observatory {
    private String nameofObservatory;
    private String countryName;
    private int startingYear;
    private double areaCovered;
    ArrayList<Galamsey> listGalamObserv = new ArrayList<> ();

    /**
     * Default Constructor
     */
    public Observatory() {
    }

    /**
     * Overloaded Constructor
     * @param nameofObservatory, A string that holds the name of the observatory String.
     * @param countryName,  A string that holds the country name.
     * @param yearSiteGalam, An integer representing the year in which the observatory was started.
     * @param listGalamObserv, An ArrayList that holds the cases of identified cases of Galamsey cases.
     */
    public Observatory(String nameofObservatory, String countryName, int yearSiteGalam, double area, ArrayList<Galamsey> listGalamObserv) {
        this.nameofObservatory = nameofObservatory;
        this.countryName = countryName;
        this.startingYear = yearSiteGalam;
        this.areaCovered = area;
        this.listGalamObserv = listGalamObserv;
    }

    /**
     * A third constructor that takes into account the fact that most observatories will be created before the list
     * of Galamsey events they register is established.
     * @param nameofObservatory A string that holds the name of the observatory String.
     * @param countryName, A string that holds the country name.
     * @param yearSiteGalam, An integer representing the year in which the observatory was started.
     * @param area An ArrayList that holds the cases of identified cases of Galamsey cases.
     */
    public Observatory(String nameofObservatory, String countryName, int yearSiteGalam, double area) {
        this.nameofObservatory = nameofObservatory;
        this.countryName = countryName;
        this.startingYear = yearSiteGalam;
        this.areaCovered = area;

    }
 
    /**
     * This constructor allows for observatories to be created when only their names are known. The advantage of doing this
     * is evident when users try to insert Galamsey events, and cite observatories that does not exist at the time.
     * @param nameofObservatory
     */
    public Observatory(String nameofObservatory) {
    	this.nameofObservatory = nameofObservatory;
        this.countryName = "LOREM IPSUM";
        this.startingYear = 1791;
        this.areaCovered = 0.0;
    }
    
    /**
	 * @return Gets the name of the observatory.
	 */
	public String getNameofObservatory() {
		return nameofObservatory;
	}

	/**
	 * Sets the name of the observatory to a given value. 
	 * @param nameofObservatory The name of the observatory we will like to set.
	 */
	public void setNameofObservatory(String nameofObservatory) {
		this.nameofObservatory = nameofObservatory;
	}

	/**
	 * @return The country in which the given observatory operates. 
	 */
	public String getCountryName() {
		return countryName;
	}

	/**
	 * Sets the country of operation for a given observatory.
	 * @param  countryName The name of the desired country. 
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	/**
	 * @return Gets the year in which the obsevatory in question began work. 
	 */
	public int getStartingYear() {
		return startingYear;
	}

	/**
	 * Sets the year in which the observatory started work. 
	 * @param startingYear, a integer that represents the year we will like to set. 
	 */
	public void setStartingYear(int startingYear) {
		this.startingYear = startingYear;
	}

	/**
	 * @return Gets the area covered by the observatory. 
	 */
	public double getAreaCovered() {
		return areaCovered;
	}

	/**
	 * @param areaCovered the area covered by an observatory to a given value.
	 */
	public void setAreaCovered(double areaCovered) {
		this.areaCovered = areaCovered;
	}

	/**
	 * This method gets the list of galamsey events registered by a given observatory. 
	 * @return An ArrayList of Galamsey events. 
	 */
	public ArrayList<Galamsey> getListGalamObserv() {
		return listGalamObserv;
	}

	/**
	 * This method sets the list of Galamsey cases observed to a particular, pre-defined list.
	 * @param listGalamObserv The ArrayList we will like to define as the list of observed Galamsey objects.
	 */
	public void setListGalamObserv(ArrayList<Galamsey> listGalamObserv) {
		this.listGalamObserv = listGalamObserv;
	}

	/**
	 * Overrides the default hashCode method.
     *
     * @return
     */
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(areaCovered);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((countryName == null) ? 0 : countryName.hashCode());
		result = prime * result + ((listGalamObserv == null) ? 0 : listGalamObserv.hashCode());
		result = prime * result + ((nameofObservatory == null) ? 0 : nameofObservatory.hashCode());
		result = prime * result + startingYear;
		return result;
	}

    /**
     * Overrides the default equals class from the Object class to enable the comparison of objects.
     */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Observatory other = (Observatory) obj;
		if (Double.doubleToLongBits(areaCovered) != Double.doubleToLongBits(other.areaCovered))
			return false;
		if (countryName == null) {
			if (other.countryName != null)
				return false;
		} else if (!countryName.equals(other.countryName))
			return false;
		if (listGalamObserv == null) {
			if (other.listGalamObserv != null)
				return false;
		} else if (!listGalamObserv.equals(other.listGalamObserv))
			return false;
		if (nameofObservatory == null) {
			if (other.nameofObservatory != null)
				return false;
		} else if (!nameofObservatory.equals(other.nameofObservatory))
			return false;
		if (startingYear != other.startingYear)
			return false;
		return true;
	}
   
    /**
     * Overrides the toString() method of the Object class to enable convenient printing of objects of type observatory.
     */
    @Override
    public String toString() {
        return "Observatory{" +
                "nameofObservatory='" + nameofObservatory + '\'' +
                ", countryName='" + countryName + '\'' +
                ", yearSiteGalam='" + startingYear + '\'' +
                ", listGalamObserv=" + listGalamObserv +
                '}';
    }
    

	/**
     *Helper method.
     *This method helps other methods to be able to count how many galamsey objects of their type have been identified by the 
     *observatory. 
     * @param value An integer value that will be used as a reference in counting and identifying the objects.
     * @return Returns the integer count of a particular coulour value.
     */
    private int returnCount(int value){
        int count = 0;
        for (Galamsey galamsey : listGalamObserv) {
            if (galamsey.getColourValue () == value) {
                count++;
            }
        }

        return count;
    }

    /**
     * This method helps us to count the number of cases that were identified with Green colours.
     * @return The integer count of the green cases identified.
     */
    public int countofGalamGreen(){
        return  returnCount (1);

    }

    /**
     *This method helps us to count the number of cases that were identified with Yellow colours.
     * @return The integer count of the yellow cases identified.
     
     */
    public int countofGalamYellow(){
        return  returnCount (2);

    }

    /**
     *This method helps us to count the number of cases that were identified with Brown colours.
     * @return The integer count of the brown cases identified.
     
     */
    public int countofGalamBrown(){
        return  returnCount (3);
    }

    /**
     * A personal Comparator to return the maximum colour value
     * between two galamsey objects.
     */
    public class MyComparator implements Comparator<Galamsey> {
        public int compare(Galamsey o1, Galamsey o2){
            return Math.max (o1.getColourValue (),o2.getColourValue ());
        }
    }

    /**
     * This method enables us to return the largest value recorded by an observatory.
     * @return Returns and integer value that represents the largest value recorded by a given observatory.
     */
    public int largestGalamValueRec(){
        listGalamObserv.sort (new MyComparator ());
        return listGalamObserv.get (0).getColourValue ();
    }

    /**
     * This method enables the calculation of the average colour value recorded by a given observatory.
     * @return A double that represents the average.
     */
    public double averageGalamColourValue() {
        double sum = 0;
        int count = 0;
        for (Galamsey galamsey : listGalamObserv) {
            count++;
            sum += galamsey.getColourValue ();
        }
        return  sum / count;
    }

    /**
     * This method enables us to return a list of Galamsey objects that have colour values greater than an arbitrary number.
     * @param arbitraryNumber An arbitrary integer.
     * @return An ArrayList of Galamsey objects.
     */
    public ArrayList<Galamsey> eventsGreaterThanNo(int arbitraryNumber ) {
        ArrayList<Galamsey> listofEvents = new ArrayList<> ();
        if (arbitraryNumber <= 0) {
            return this.listGalamObserv;
        	} 
        else {
            int i = 0;
            while (i < listofEvents.size()) {
                if (listGalamObserv.get (i).getColourValue () > arbitraryNumber) {
                    listofEvents.add (listGalamObserv.get (i));
                }
                i++;
            }
        }
        return listofEvents;
    }
    
    /**
     * This method allows us to add a given galamsey object to an already existing observatory class by adding the galamsey object to the 
     * attribute that holds the list of galamsey objects.
     * @param obs An object of type Galamsey.
     * @return a boolean that is false if the Galamsey object was already in the list, and true if it was newly added.
     */
    public boolean addGalamseyEvent(Galamsey obs) {
    	
    	if(listGalamObserv != null) {
    	for(Galamsey g:listGalamObserv) {
    		if(g.equals(obs)) {
    			return false;
    		}
    	}
    	this.listGalamObserv.add(obs);
    	return true;
    	}
    	else {
    		listGalamObserv.add(obs);
        	return true;
    	}
    }

}

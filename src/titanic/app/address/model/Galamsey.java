package titanic.app.address.model;
/**
 * This class stores information about identified cases of galamsey.
 * It uses the Position to define a position in which the Galamsey was identified.
 * Used to store and retrieve information about the vegetation colour, colour value,position and year.
 * @author wybeturing
 * @version 1.0
 *
 */

public class Galamsey {
	private String vegetationColour;
	private int colourValue;
	private Position position;
	private int year;
	
	/**
	 * Default constructor
	 */
	public Galamsey() {
		
	}
	/**
	 * Overloaded Constructor
	 * Refers the parameters to the instance variables above 
	 * @param vegcol Specifies the colour of the vegetation. Can be green, yellow or brown. 
	 * @param col Specifies the colour value of the vegetation, colour values are pre-fixed. 
	 * @param pos Specifies the position at which the galamsey was identified. 
	 * @param year
	 */
	public Galamsey(String vegcol, int col, Position pos, int year) {
		this.vegetationColour = vegcol;
		this.colourValue = col;
		this.position = pos;
		this.year = year;
	}

	/**
	 * Gets the colour of the vegetation
	 * @return A string representing the vegetation colour; can either be green, brown or yellow. 
	 */
	public String getVegetationColour() {
		return vegetationColour;
	}

	/**
	 * Sets the vegetation colour to a desired colour. 
	 * @param vegetationColour The colour of the vegetation. 
	 */
	public void setVegetationColour(String vegetationColour) {
		this.vegetationColour = vegetationColour;
	}

	/**
	 * Gets the colour value of the vegetation; these values are pre-fixed and can either be 1, 2 or 3. 
	 * @return An integer value representing the colour. 
	 */
	public int getColourValue() {
		return colourValue;
	}

	/**
	 * Sets the colour value to a particular value. 
	 * @param colourValue The desired colour value number (1, 2, or 3)
	 */
	public void setColourValue(int colourValue) {
		this.colourValue = colourValue;
	}
	
	/**
	 * Gets the position at which an act of Galamsey was identified. 
	 * @return An object of type Position. 
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * Sets the position of the Galamsey observation to a desired position. 
	 * @param position The new position we are setting the identified act to
	 */
	public void setPosition(Position position) {
		this.position = position;
	}

	/**
	 * Gets the year during which the Galamsey act was observed. 
	 * @return An integer representing the year in which the Galamsey was observed. 
	 */
	public int getYear() {
		return year;
	}

	/**
	 * This sets the year to a new required year. 
	 * @param year The new year in which we want to register the Galamsey object. 
	 */
	public void setYear(int year) {
		this.year = year;
	}
	
	/**
	 * Overrides the default hashcode method
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + colourValue;
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		result = prime * result + ((vegetationColour == null) ? 0 : vegetationColour.hashCode());
		result = prime * result + year;
		return result;
	}
	
	/**
	 * This method checks for the equality of Galamsey objects
	 * @return A boolean that indicates whether or not the objects are equal
	 * @param An object of type Object
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Galamsey other = (Galamsey) obj;
		if (colourValue != other.colourValue)
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (vegetationColour == null) {
			if (other.vegetationColour != null)
				return false;
		} else if (!vegetationColour.equals(other.vegetationColour))
			return false;
		if (year != other.year)
			return false;
		return true;
	}
	
	/**
	 * This method returns a string that can be printed on the screen to represent a Galamsey object.
	 * @return Returns a string that will often be printed to the console. 
	 */
	public String toString() {
		return ("Vegetation Colour: " + this.vegetationColour + "  Colour Value: "
				+ this.colourValue + "  Position|| (Latitude: " + this.position.getLatitude() + "  Longitude: " + this.position.getLongitude()
				+ " Year: " + this.year);
	}
	
}

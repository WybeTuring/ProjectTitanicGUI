package dbconnection;

import java.sql.*;
import java.time.Year;
/**
 * This is a database connection class,
 * which connects to a local instance of a mySQL.
 * The database connects to the GUI for the Terra Observatory.
 * The database allows a user to register a new galamsey event, register new
 * observatories and view some defined statistical information defined by the team.
 */
public class DatabaseConnection {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/TerraDb?verifyServerCertificate=false&useSSL=true&serverTimezone=UTC";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "root";

    //Connection tools
    Connection conn;
    Statement createStatement;
    ResultSet resultSet;

    /**
     * An overloaded  constructor for the connection class
     * The constructor registers and establishes a database connection
     */
    public DatabaseConnection() {

        try {
            //Registering a connection
            Class.forName ("com.mysql.cj.jdbc.Driver");
            //Opening a database connection
            conn = DriverManager.getConnection (DB_URL, USER, PASS);
            

        } catch (ClassNotFoundException e) {
            e.printStackTrace ();
        } catch (SQLException e) {
          
        }

    }

    /**
     * A query method which registers a galamsey event inputed
     * from the Terra GUI into the Terra database
     *
     * @param observeNameInput The name of the observatory which recorded the manual event
     * @param vegColourInput The vegetation colour recorded which is a string
     * @param colourValueInput The colour value associated with a valid vegetation colour
     * @param latInput The latitude position associated with the event
     * @param longiInput The longitude position associated with the event
     * @param eventYearInput The year which the event was recorded
     * @return a bollean value to give an indication if the operation succeeded
     */
    public Boolean registerGalamseyEvents(String observeNameInput, String vegColourInput, String colourValueInput, Float latInput, Float longiInput, int eventYearInput) {
        try {
            

            PreparedStatement ps = conn.prepareStatement("INSERT INTO GALAMSEY (observeName,vegColour,colourValue,lat,longi, eventYear) VALUES(?,?,?,?,?,?)");
            ps.setString (1,observeNameInput);
            ps.setString(2,vegColourInput);
            ps.setString(3,colourValueInput);
            ps.setFloat (4,latInput);
            ps.setFloat (5,longiInput);
            ps.setString (6, String.valueOf (eventYearInput));
            switch (ps.executeUpdate ()) {
            }
            return true;
        } catch (SQLException e) {
            
            return false;
        }
    }

    /**
     * A query method which registers a new observatory inputted
     * from the Terra GUI into the Terra database
     *
     * @param nameInput The name of the new observatory
     * @param countryInput The country where the observatory is located
     * @param startYearInput The year the observatory started
     * @param areaInput The area the observatory covers
     * @return
     */
    public Boolean registerObservatories(String nameInput,String countryInput,int startYearInput,int areaInput) {
        try {
            
            PreparedStatement ps = conn.prepareStatement("INSERT INTO OBSERVATORY (name,country,startYear,area) VALUES(?,?,?,?)");
            ps.setString (1,nameInput);
            ps.setString(2,countryInput);
            ps.setInt(3,startYearInput);
            ps.setInt (4,areaInput);
            switch (ps.executeUpdate ()) {
            }
            return true;
        }
        catch (SQLException e) {
         
            return false;
        }
    }

    /**
     * A query which returns the largest colour value ever
     * recorded by the Galamsey table
     * @return An integer indicating the value, which is never greater than 3
     */
    public Integer largestGalamseyEvent() {
        try {
           
            createStatement = conn.createStatement ();
            String sql;
            sql = "SELECT MAX(colourValue) FROM  GALAMSEY";
            resultSet = createStatement.executeQuery (sql);

            // Extract data from result set
            while (resultSet.next ()) {
                //Retrieve by column name
                int colourValue = resultSet.getInt ("Max(colourValue)");
                //System.out.println (colourValue);

                return colourValue;
            }

        } catch (SQLException e) {
            
        }
        return -1; //query failed
    }

    /**
     * A query method which returns all galamsey events
     * greater than the given input. An number greater than 3 returns no events
     * @param number the number to request events greater than
     * @return the return type is a ResultSet which is used by the Terra GUI to present statistical information
     */
    public ResultSet ArbitaryGalamseyEvent(int number) {
    	ResultSet result = null;
        try {
            
            String sql;
            PreparedStatement ps = conn.prepareStatement( "select observeName,vegColour,colourValue,lat,longi,eventYear " +
                                                                  "FROM GALAMSEY WHERE colourValue > ?");
            ps.setString (1, String.valueOf (number));
            resultSet = ps.executeQuery ();

            // Extract data from result set
            while (resultSet.next ()) {
                //Retrieve by column name
                String observeName = resultSet.getString ("observeName");
                String vegColour = resultSet.getString ("vegColour");
                int colValue = resultSet.getInt ("colourValue");
                String latitude = resultSet.getString ("lat");
                String longitude = resultSet.getString ("longi");
                String eventYear = resultSet.getString ("eventYear");

                String results = observeName + "\t" + vegColour + "\t" + colValue + "\t" + latitude + "\t" + longitude +"\t" + eventYear;

                System.out.println (results + "\n");
            }

        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }


    /**
     * A method which queries the Terra database
     * and displays to the Terra GUI the list of registered observatories
     *
     * @return the return type is a ResultSet which is used by the Terra GUI to present statistical information
     */
    public void listObservatories () {
        try {
            System.out.println ("Listing Observatories...");
            createStatement = conn.createStatement ();
            String sql;
            sql = "SELECT name,country,startYear,area " +
                    "FROM OBSERVATORY;";
            resultSet = createStatement.executeQuery (sql);

            // Extract data from result set
            while (resultSet.next ()) {
                //Retrieve by column name
                String name = resultSet.getString ("name");
                String country = resultSet.getString ("country");
                int year = resultSet.getInt ("startYear");
                int area = resultSet.getInt ("area");


                String results = name + "\t" + country + "\t" + year + "\t" + area;

                System.out.println (results + "\n");

            }

        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }

    /**
     * A method which queries the Terra database
     * and displays to the Terra GUI the list of registered galamsey events
     *
     * @return the return type is a ResultSet which is used by the Terra GUI to present statistical information
     */
    public void listGalamsey () {
        try {
            System.out.println ("Listing Galamsey...");
            String sql;
            PreparedStatement ps = conn.prepareStatement( "select observeName,vegColour,colourValue,lat,longi,eventYear " +
                                                                  "FROM GALAMSEY");
            resultSet = ps.executeQuery ();

            // Extract data from result set
            while (resultSet.next ()) {
                //Retrieve by column name
                String observeName = resultSet.getString ("observeName");
                String vegColour = resultSet.getString ("vegColour");
                int colValue = resultSet.getInt ("colourValue");
                String latitude = resultSet.getString ("lat");
                String longitude = resultSet.getString ("longi");
                String eventYear = resultSet.getString ("eventYear");

                String results = observeName + "\t" + vegColour + "\t" + colValue + "\t" + latitude + "\t" + longitude +"\t" + eventYear;

                System.out.println (results + "\n");
            }

        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }
}

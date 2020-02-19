package dbconnection;

import java.sql.*;
import java.time.Year;
import java.util.Set;

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

    public DatabaseConnection() {

        try {
            //Registering a connection
            Class.forName ("com.mysql.cj.jdbc.Driver");
            //Opening a database connection
            conn = DriverManager.getConnection (DB_URL, USER, PASS);

        } catch (ClassNotFoundException e) {
           
        } catch (SQLException e) {
           
        }

    }

    public Boolean registerGalamseyEvents(String observeNameInput, String vegColourInput, int colourValueInput, Double latInput, Double longiInput, int eventYearInput) {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO GALAMSEY (observeName,vegColour,colourValue,lat,longi, eventYear) VALUES(?,?,?,?,?,?)");
            ps.setString (1,observeNameInput);
            ps.setString(2,vegColourInput);
            ps.setInt(3,colourValueInput);
            ps.setDouble (4,latInput);
            ps.setDouble (5,longiInput);
            ps.setInt(6, eventYearInput);
            ps.executeUpdate();
            return true;
        } 
        catch (SQLException e) {
        	e.printStackTrace();
            return false;
        }
    }

    public Boolean registerObservatories(String nameInput,String countryInput,int startYearInput,float areaInput) {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO OBSERVATORY (name,country,startYear,area) VALUES(?,?,?,?)");
            ps.setString (1,nameInput);
            ps.setString(2,countryInput);
            ps.setInt(3,startYearInput);
            ps.setFloat(4,areaInput);
            switch (ps.executeUpdate ()) {
            }
            return true;
        }
        catch (SQLException e) {
            return false;
        }
    }

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

    public ResultSet ArbitaryGalamseyEvent(int number) {
    	ResultSet resultSet = null;
        try {
            
            String sql;
            PreparedStatement ps = conn.prepareStatement( "select observeName,vegColour,colourValue,lat,longi, eventYear " +
                    "FROM GALAMSEY WHERE colourValue > ?");
            ps.setString (1, String.valueOf (number));
            resultSet = ps.executeQuery ();

        } catch (SQLException e) {
          
        }
        return resultSet;
    }



    public ResultSet listObservatories () {
    	ResultSet resultSet = null;
        try {
       
            createStatement = conn.createStatement ();
            String sql;
            sql = "SELECT name,country,startYear,area " +
                    "FROM OBSERVATORY;";
            resultSet = createStatement.executeQuery (sql);

        } catch (SQLException e) {
           
        }
        return resultSet;
    }

    public ResultSet listGalamsey () {
    	ResultSet resultSet = null;
        try {
            PreparedStatement ps = conn.prepareStatement( "select observeName,vegColour,colourValue,lat,longi,eventYear " +
                                                                  "FROM GALAMSEY");
            resultSet = ps.executeQuery ();

        } catch (SQLException e) {
            
        }
       return resultSet;
    }

}

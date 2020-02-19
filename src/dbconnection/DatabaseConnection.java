package dbconnection;

import java.sql.*;
import java.time.Year;

public class DatabaseConnection {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/TerraDb?verifyServerCertificate=false&useSSL=true&serverTimezone=UTC";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "admin";

    //Connection tools
    Connection conn;
    Statement createStatement;
    ResultSet resultSet;

    public DatabaseConnection() {

        try {
            //Registering a connection
            Class.forName ("com.mysql.cj.jdbc.Driver");
            System.out.println ("Connecting to a selected database illegal mining database");
            //Opening a database connection
            conn = DriverManager.getConnection (DB_URL, USER, PASS);
            System.out.println ("Connected database successfully...");

        } catch (ClassNotFoundException e) {
            e.printStackTrace ();
        } catch (SQLException e) {
            e.printStackTrace ();
        }

    }

    public Boolean registerGalamseyEvents(String observeNameInput, String vegColourInput, String colourValueInput, Float latInput, Float longiInput, int eventYearInput) {
        try {
            System.out.println ("Inserting records into the galamsey table.");

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
            System.out.println ("Data insertion failed, check input failed");
            e.printStackTrace ();
            return false;
        }
    }

    public Boolean registerObservatories(String nameInput,String countryInput,int startYearInput,int areaInput) {
        try {
            System.out.println ("Inserting records into the observatories table.");
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
            System.out.println ("Data insertion failed, check input failed");
            e.printStackTrace ();
            return false;
        }
    }

    public Integer largestGalamseyEvent() {
        try {
            System.out.println ("Creating statement...");
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
            e.printStackTrace ();
        }
        return -1; //query failed
    }

    public void ArbitaryGalamseyEvent(int number) {
        try {
            System.out.println ("Creating statement...");
            String sql;
            PreparedStatement ps = conn.prepareStatement( "select observeName,vegColour,colourValue,lat,longi, eventYear " +
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



    public void listObservatories () {
        try {
            System.out.println ("Creating statement...");
            createStatement = conn.createStatement ();
            String sql;
            sql = "SELECT name,country,startYear,area " +
                    "FROM OBSERVATORY;";
            resultSet = createStatement.executeQuery (sql);

            // Extract data from result set
            while (resultSet.next ()) {
                //Retrieve by column name
                String name = resultSet.getString ("name");
                String country= resultSet.getString ("country");
                int year = resultSet.getInt ("startYear");
                int area = resultSet.getInt ("area");


                String results = name + "\t" + country + "\t" + year + "\t" + area;

                System.out.println (results + "\n");

            }

        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }
    public  static  void main(String args []){
        DatabaseConnection kay = new DatabaseConnection ();
        kay.listObservatories ();
    }

}

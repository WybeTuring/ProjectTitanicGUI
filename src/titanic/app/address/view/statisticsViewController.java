package titanic.app.address.view;

/**
 * This class controls the statistics view pane, and makes sure that the switching of the pages is well synchronized.
 * @author wybeturing
 * @version 1.1
 */
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbconnection.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import titanic.app.address.MainApp;


public class statisticsViewController {

    @FXML
    private TextField arbitraryNumberField;

    @FXML
    private Button arbitraryValueButton;

    @FXML
    private Button largestGalamseyRecorded;

    @FXML
    private Button totalNumberOfObservatories;

    @FXML
    private Button totalNumberOfGalamseyRecorded;

    @FXML
    private Label largestGalamseyLabel;

    @FXML
    private Label registeredObservatoryLabel;

    @FXML
    private Label registeredGalamseyLabel;
    
    @FXML
    private TextArea resultsTestArea;

    @FXML
    private Button statisticsBackButton;
    
    private DatabaseConnection dataBase = new DatabaseConnection();
    
private MainApp mainApp;
    
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
    /**
	 * Checks if a string is a number and using that to display a warning.
	 * @param str
	 * @return
	 */
    private boolean isNumeric(String str) {
		  return str.matches("-?\\d+(\\.\\d+)?"); 
		}
    
    
    /**
     * An event method that deals with the back button
     * @param event Which in this case is a mouse click event.
     * @throws IOException 
     */
    public void chooseViewAction(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        String buttonId = button.getId();
     
        if(buttonId.equals("statisticsBackButton")) {
        	FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/sample.fxml"));
            AnchorPane sample = (AnchorPane) loader.load();
            
        	BorderPane rootL = mainApp.getRootLayout();
        	rootL.setCenter(sample);
        	
        	sampleController controller = loader.getController();
            controller.setMainApp(mainApp);
        }
        
    }
    
    /**
     * This method reacts to the methods that are called when the statistics view pane is being used. 
     * @param event
     */
    public void newButtonClick(MouseEvent event) {
    	Button button = (Button) event.getSource();
        String buttonId = button.getId();
        
        // If the event is coming from the arbitrary value button
        if(buttonId.equals("arbitraryValueButton")) {
        	String numT = arbitraryNumberField.getText();
        	if(!isNumeric(numT)) {
        		Alert alert = new Alert(AlertType.NONE);
    			alert.setAlertType(AlertType.ERROR);
    			alert.setContentText("Please enter a number");
    			alert.showAndWait();
    			arbitraryNumberField.setText("");
        	}
        	else {
        		resultsTestArea.setText("");
        		ResultSet resultSet = dataBase.ArbitaryGalamseyEvent(Integer.parseInt(arbitraryNumberField.getText()));
            	  // Extract data from result set
                try {
    				while (resultSet.next ()) {
    				    //Retrieve by column name
    				    String observeName = resultSet.getString ("observeName");
    				    String vegColour = resultSet.getString ("vegColour");
    				    int colValue = resultSet.getInt ("colourValue");
    				    String latitude = resultSet.getString ("lat");
    				    String longitude = resultSet.getString ("longi");
    				    String eventYear = resultSet.getString ("eventYear");

    				    String results = observeName + "\t\t" + vegColour + "\t\t" + colValue + "\t\t" + latitude + "\t\t" + longitude +"\t\t" + eventYear;
    				    resultsTestArea.appendText(results + "\n");
    				}
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
        	}
        }
        
        else if(buttonId.equals("largestGalamseyRecorded")) {
        	resultsTestArea.setText("");
        	Integer temp = dataBase.largestGalamseyEvent();
        	switch(temp) {
        	  case 1:
        		  resultsTestArea.appendText("The largest colour value recorded was " + temp + " and stands for Green Vegetation");
        		  break;
        	  case 2:
        		  resultsTestArea.appendText("The largest colour value recorded was " + temp + " and stands for Yellow Vegetation");
        		  break;
        	  case 3:
        		  resultsTestArea.appendText("The largest colour value recorded was " + temp + " and stands for Brown Vegetation");
        		  break;
        	  default:
        		  resultsTestArea.appendText("Sorry the querry failed, there may be no data in the database yet");
        	}

        }
        
        else if(buttonId.equals("totalNumberOfObservatories")) {
        	resultsTestArea.setText("");
        	ResultSet resultSet = dataBase.listObservatories();
        	String result = String.format("%-40s" + "\t" + "%-20s" + "\t" + "%-6s" + "\t" + "%-6s", "Observatory Name", "Country", "Year", "Area");
        	resultsTestArea.appendText(result + "\n\n"); 
        	try {
				while (resultSet.next ()) {
				     //Retrieve by column name
				     String name = resultSet.getString ("name");
				     String country= resultSet.getString ("country");
				     int year = resultSet.getInt ("startYear");
				     int area = resultSet.getInt ("area");
				     String results2 = String.format("%-40s" + "\t" + "%-20s" + "\t" + "%-6d" + "\t" + "%-6d", name, country, year, area);
				     resultsTestArea.appendText(results2 + "\n");

				 }
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
        }
        
        else if(buttonId.equals("totalNumberOfGalamseyRecorded")) {
        	resultsTestArea.setText("");
        	ResultSet resultSet = dataBase.listGalamsey();
        	String result = String.format("%-40s" + "\t" + "%-20s" + "\t" + "%-14s" + "\t" + "%-10s" + 
        	"\t" + "%-10s" + "\t" + "%-6s", 
        	"Observatory Name", "Vegetation Colour", "Colour Value", "Latitude", "Longitude", "Year");
        	resultsTestArea.appendText(result + "\n\n");
        	 try {
				while (resultSet.next ()) {
				     //Retrieve by column name
				     String observeName = resultSet.getString ("observeName");
				     String vegColour = resultSet.getString ("vegColour");
				     int colValue = resultSet.getInt ("colourValue");
				     String latitude = resultSet.getString ("lat");
				     String longitude = resultSet.getString ("longi");
				     String eventYear = resultSet.getString ("eventYear");

				     String results = observeName + "\t\t" + vegColour + "\t\t" + colValue + "\t\t" + latitude + "\t\t" + longitude +"\t\t" + eventYear;
				     String result1 = String.format("%-40s" + "\t" + "%-20s" + "\t" + "%-14d" + "\t" +
				     "%-10s" + "\t" + "%-10s" + "\t" + "%-6s", 
				     observeName, vegColour, colValue, latitude,longitude, eventYear);
				     resultsTestArea.appendText(result1 + "\n");
				     
				 }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
    }

}


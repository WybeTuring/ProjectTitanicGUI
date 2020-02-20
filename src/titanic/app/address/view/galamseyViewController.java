package titanic.app.address.view;

/**
 * This controller controls the galamseyView.fxml and makes sure that page rendering is perfect. 
 * @author wybeturing 
 * @version 1.0
 * 
 */

import java.io.IOException;
import java.sql.*;

import dbconnection.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import titanic.app.address.MainApp;
import titanic.app.address.model.Galamsey;
import titanic.app.address.model.Observatory;
import titanic.app.address.model.Position;

/**
 * This class gets connections with all the fxml elements in the galamseyView.fxml and enables us to make changes and listen for events.
 * @author wybeturing
 *
 */
public class galamseyViewController {
    
    @FXML
    private TextField observatoryNameField;
    
    @FXML
    private CheckBox observatoryCheckbox;

    @FXML
    private TextField vegetationColourField;
    
    @FXML
    private CheckBox vegetationCheckbox;

    @FXML
    private TextField colourValueField;
    
    @FXML
    private CheckBox colourCheckbox;

    @FXML
    private TextField latitudeField;
    
    @FXML
    private CheckBox latitudeCheckbox;

    @FXML
    private Button galamseyRegisterButton;

    @FXML
    private TextField longitudeField;
    
    @FXML 
    private TextField yearField;
    
    @FXML
    private CheckBox yearCheckbox;
    
    @FXML
    private CheckBox longitudeCheckbox;

    @FXML
    private Button galamseyBackButton;
    
    private MainApp mainApp;
    
    private DatabaseConnection dataBase = new DatabaseConnection();
    
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
    /**
     * Listens to events that will most often deal with the back button.
     * @param event
     * @throws IOException
     */
    public void chooseViewAction(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        String buttonId = button.getId();
     
        if(buttonId.equals("galamseyBackButton")) {
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
	 * Checks if a string is a number and using that to display a warning.
	 * @param str
	 * @return
	 */
    private boolean isNumeric(String str) {
		  return str.matches("-?\\d+(\\.\\d+)?"); 
		}
    
    /**
     * This method deals with the registration to the database.
     * @param event Which in this case is a mouse click event.
     */
    public void newButtonClick(MouseEvent event) {
    	
    	String observatoryName = observatoryNameField.getText();
    	String  vegetationColour = vegetationColourField.getText();
    	String colourValue = colourValueField.getText();
    	String latitude = latitudeField.getText();
    	String longitude = longitudeField.getText();
    	String year = yearField.getText();
    	
    	
    	
    	// Validating the user input from the register Galamsey page
    	
    	if(observatoryName.equals("") || year.equals("") || vegetationColour.equals("") || colourValue.equals("") || latitude.equals("") || longitude.equals("")) {
    		Alert alert = new Alert(AlertType.NONE);
			alert.setAlertType(AlertType.ERROR);
			alert.setContentText("Please enter all fields.");
			alert.showAndWait();
			if(!observatoryName.equals("")) {
				observatoryCheckbox.setSelected(true);
			}
			if(!vegetationColour.equals("") && (vegetationColour.toLowerCase().equals("yellow") || vegetationColour.toLowerCase().equals("green") || vegetationColour.toLowerCase().equals("brown") )) {
				vegetationCheckbox.setSelected(true);
			}
			else {
				Alert alert1 = new Alert(AlertType.NONE);
				alert1.setAlertType(AlertType.ERROR);
				alert1.setContentText("Vegetation colours can only be green, yellow and brown.");
				alert1.showAndWait();
				vegetationColourField.setText("");
			}
			if(!colourValue.equals("") && (colourValue != "1" && colourValue != "2" && colourValue != "3") ){
				Alert alert1 = new Alert(AlertType.NONE);
				alert1.setAlertType(AlertType.ERROR);
				alert1.setContentText("Colour Values can only be 1, 2, or 3");
				alert1.showAndWait();
				colourValueField.setText("");
			}
			else {
				colourCheckbox.setSelected(true);
			}
    	}
    	else if(!(vegetationColour.toLowerCase().equals("yellow") || vegetationColour.toLowerCase().equals("green") || vegetationColour.toLowerCase().equals("brown") )) {
    		Alert alert1 = new Alert(AlertType.NONE);
			alert1.setAlertType(AlertType.ERROR);
			alert1.setContentText("Vegetation colours can only be green, yellow and brown.");
			alert1.showAndWait();
			vegetationColourField.setText("");
    	}
    	else if(Double.parseDouble(colourValue) != 1 && Double.parseDouble(colourValue) != 2 && Double.parseDouble(colourValue) != 3) {
    		Alert alert1 = new Alert(AlertType.NONE);
			alert1.setAlertType(AlertType.ERROR);
			alert1.setContentText("Colour Values can only be 1, 2, or 3");
			alert1.showAndWait();
			colourValueField.setText("");
    	}
    	else if(!isNumeric(latitude) || !isNumeric(longitude) ) {
    		Alert alert1 = new Alert(AlertType.NONE);
			alert1.setAlertType(AlertType.ERROR);
			alert1.setContentText("Latitude and Longitude must be numbers");
			alert1.showAndWait();
			latitudeField.setText("");
			longitudeField.setText("");
    	}
    	else if(!isNumeric(year)) {
    		Alert alert1 = new Alert(AlertType.NONE);
			alert1.setAlertType(AlertType.ERROR);
			alert1.setContentText("Years must be numbers.");
			alert1.showAndWait();
			latitudeField.setText("");
    	}
    	// The block in which everything has been validated
    	else {
    		colourCheckbox.setSelected(true);
    		observatoryCheckbox.setSelected(true);
    		vegetationCheckbox.setSelected(true);
    		longitudeCheckbox.setSelected(true);
    		latitudeCheckbox.setSelected(true);
    		yearCheckbox.setSelected(true);
    		Position pos = new Position(Double.parseDouble(latitude), Double.parseDouble(longitude));
    		Galamsey obs = new Galamsey(vegetationColour, Integer.parseInt(colourValue), pos, Integer.parseInt(year));
    		dataBase.registerGalamseyEvents(observatoryName, obs.getVegetationColour(), obs.getColourValue(), obs.getPosition().getLatitude(), obs.getPosition().getLongitude(), obs.getYear());
    		
    		// Process the values obtained
    		Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setContentText("Successfully registered");
			alert.showAndWait();
    		
    		// Clear the fields
    		vegetationColourField.setText("");
    		latitudeField.setText("");
			longitudeField.setText("");
			colourValueField.setText("");
			observatoryNameField.setText("");
			yearField.setText("");
    		
    	}
    	
    	
    }
	


}

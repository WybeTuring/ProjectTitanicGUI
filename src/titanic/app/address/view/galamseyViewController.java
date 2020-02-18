package titanic.app.address.view;


import java.io.IOException;

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
    private CheckBox longitudeCheckbox;

    @FXML
    private Button galamseyBackButton;
    
    private MainApp mainApp;
    
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
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
    
    public void newButtonClick(MouseEvent event) {
    	
    	String observatoryName = observatoryNameField.getText();
    	String  vegetationColour = vegetationColourField.getText();
    	String colourValue = colourValueField.getText();
    	String latitude = latitudeField.getText();
    	String longitude = longitudeField.getText();
    	
    	
    	
    	// Validating the user input from the register Galamsey page
    	
    	if(observatoryName.equals("") || vegetationColour.equals("") || colourValue.equals("") || latitude.equals("") || longitude.equals("")) {
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
			if(!colourValue.equals("") && (colourValue != "1" || colourValue != "2" || colourValue != "3") ){
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
    	else if(!(Double.parseDouble(colourValue) == 1 || colourValue == "2" || colourValue == "3")) {
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
    	// The block in which everything has been validated
    	else {
    		colourCheckbox.setSelected(true);
    		observatoryCheckbox.setSelected(true);
    		vegetationCheckbox.setSelected(true);
    		longitudeCheckbox.setSelected(true);
    		latitudeCheckbox.setSelected(true);
    		
    		// Process the values obtained
    		
    		
    		// Clear the fields
    		vegetationColourField.setText("");
    		latitudeField.setText("");
			longitudeField.setText("");
			colourValueField.setText("");
			observatoryNameField.setText("");
    		
    	}
    }

}

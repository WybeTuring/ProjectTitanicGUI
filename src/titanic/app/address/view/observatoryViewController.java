package titanic.app.address.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import titanic.app.address.MainApp;
import titanic.app.address.model.Observatory;

import java.sql.*;

import dbconnection.DatabaseConnection;

/**
 * 
 * @author wybeturing, Kweku Yamoah
 *
 */
public class observatoryViewController {

    @FXML
    private TextField observatoryNameField;

    @FXML
    private TextField countryNameField;

    @FXML
    private TextField yearStartedField;

    @FXML
    private TextField squareAreaField;

    @FXML
    private Button registerButton;
    
    @FXML
    private CheckBox nameCheckbox;
    
    @FXML
    private CheckBox countryCheckbox;
    
    @FXML
    private CheckBox yearCheckbox;
    
    @FXML
    private CheckBox areaCheckbox;
    
    private DatabaseConnection dataBase = new DatabaseConnection();

    @FXML
    private Button backButton;
    
    private MainApp mainApp;
    
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
	@FXML
    public void chooseViewAction(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        String buttonId = button.getId();
        
        if(buttonId.equals("backButton")) {
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
	
	@FXML
	private void newButtonClick(MouseEvent event){
		
		String observatoryName = observatoryNameField.getText();
		String countryName = countryNameField.getText();
		String yearStarted = yearStartedField.getText();
		String squareArea = squareAreaField.getText();
		
		// Validating the user input.
		if(observatoryName.equals("") || countryName.equals("") || yearStarted.equals("") || squareArea.equals("")) {
			Alert alert = new Alert(AlertType.NONE);
			alert.setAlertType(AlertType.ERROR);
			alert.setContentText("Please enter all fields.");
			alert.showAndWait();
			if(!observatoryName.equals("")) {
				nameCheckbox.setSelected(true);
			}
			if(!countryName.equals("")) {
				countryCheckbox.setSelected(true);
			}
			if(!yearStarted.equals("") && isNumeric(yearStarted) ) {
				yearCheckbox.setSelected(true);
			}
			if(!yearStarted.equals("") && !isNumeric(yearStarted) ) {
				yearStartedField.setText("");
			}
			if(!squareArea.equals("") && isNumeric(squareArea)) {
				areaCheckbox.setSelected(true);
			}
			if(!squareArea.equals("") && !isNumeric(squareArea)) {
				squareAreaField.setText("");
			}
		}
		else if(!isNumeric(yearStarted) || !isNumeric(squareArea)) {
			Alert alert = new Alert(AlertType.NONE);
			alert.setAlertType(AlertType.ERROR);
			alert.setContentText("Year & Square Area must be numbers.");
			alert.showAndWait();
			yearStartedField.setText("");
			squareAreaField.setText("");
		}
		else if(yearStarted.length() != 4){
			Alert alert = new Alert(AlertType.NONE);
			alert.setAlertType(AlertType.ERROR);
			alert.setContentText("A year is 4 digits long.");
			alert.showAndWait();
			yearStartedField.setText("");
		}
		else {
			nameCheckbox.setSelected(true);
			countryCheckbox.setSelected(true);
			yearCheckbox.setSelected(true);
			areaCheckbox.setSelected(true);
			
			// Displaying the information telling the user that the data has been recieved and written to the database
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setContentText("Successfully registered");
			alert.showAndWait();
			
			// Creating the database connection and using it to insert the data that the user has entered.
			Observatory obs = new Observatory(observatoryName, countryName, Integer.parseInt(yearStarted), Double.parseDouble(squareArea));
			
			dataBase.registerObservatories(obs.getNameofObservatory(), obs.getCountryName(), obs.getStartingYear(), (float)obs.getAreaCovered());
			
			// Setting all textfields back to nothing.
			observatoryNameField.setText("");
			countryNameField.setText("");
			yearStartedField.setText("");
			squareAreaField.setText("");
			
			
		}
		
	   
	}



	
}


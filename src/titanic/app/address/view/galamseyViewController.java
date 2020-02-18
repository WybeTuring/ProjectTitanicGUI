package titanic.app.address.view;


import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
    
    public void newButtonClick(MouseEvent event) {
    	
    	String observatoryName = observatoryNameField.getText();
    	String  vegetationColour = vegetationColourField.getText();
    	String colourValue = colourValueField.getText();
    	String latitude = latitudeField.getText();
    	String longitude = longitudeField.getText();
    }

}

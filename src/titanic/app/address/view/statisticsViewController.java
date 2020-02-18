package titanic.app.address.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import titanic.app.address.MainApp;

public class statisticsViewController {

    @FXML
    private TextField arbitraryNumberField;

    @FXML
    private TextField largestGalamseyField;

    @FXML
    private TextField registeredObservatoriesField;

    @FXML
    private TextField registeredGalamseyField;

    @FXML
    private Button arbitraryValueButton;

    @FXML
    private Button largestGalamseyRecorded;

    @FXML
    private Button totalNumberOfObservatories;

    @FXML
    private Button totalNumberOfGalamseyRecorded;

    @FXML
    private Button statisticsBackButton;
    
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

}


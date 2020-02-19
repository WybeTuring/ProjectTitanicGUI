package titanic.app.address;

/**
 * This class contains the main method that starts our application and confirms that the controllers and the elements in the stage are being 
 * switched correctly .
 */
import java.io.IOException;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import titanic.app.address.model.Monitoring;
import titanic.app.address.view.sampleController;

/**
 * The main class.
 * @author wybeturing
 *
 */
public class MainApp extends Application{

    private Stage primaryStage;
    private BorderPane rootLayout;
    
    public MainApp() {
    	
    }
    /**
  		Starts the application life cycle and intializes the stage and the first scene. 
     */
	public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setResizable(false);
        this.primaryStage.setTitle("The Terra Observatory");

        initRootLayout();
        showHomeview();
    }
    
    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/root.fxml"));
            rootLayout = (BorderPane) loader.load();
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showHomeview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/sample.fxml"));
            AnchorPane sample = (AnchorPane) loader.load();
            
            // Set person overview into the centre of root layout.
            rootLayout.setCenter(sample);

            // Give the controller access to the main app.
            sampleController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public BorderPane getRootLayout() {
    	return rootLayout;
    }
    
    
    public static void main(String[] args) {
        launch(args);
     }
     
}

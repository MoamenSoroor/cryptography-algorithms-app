package app;

import app.coreUI.Controller;
import app.coreUI.Model;
import app.coreUI.StageWindow;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage stage) {
		try {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("View.fxml"));
			VBox root = loader.load();
			Controller<VBox> controller = loader.getController();
			Model<VBox> model = new ViewModel();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("view.css").toExternalForm());
			stage.setScene(scene);
			stage.sizeToScene();
			StageWindow<VBox> stageWindow = new StageWindow<>("View.fxml", root, controller , model , scene, stage);
			controller.setStageWindow(stageWindow);
			model.setStageWindow(stageWindow);
			controller.linkModel();
			stage.setTitle("Encryption_App_v4.1_by_Moamen_Soroor");
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

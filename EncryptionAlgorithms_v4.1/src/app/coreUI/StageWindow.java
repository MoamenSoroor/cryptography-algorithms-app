package app.coreUI;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StageWindow<T extends Parent> {

	private String fxmlLocation;
	
	private T root;
	
	private Controller<T> controller;
	
	private Model<T> model;

	private Scene scene;
	
	private Stage stage;
	
	
	
	
	public StageWindow() {
		// TODO Auto-generated constructor stub
	}
	
	

	public StageWindow(String fxmlLocation, T root, Controller<T> controller , Model<T> model, Scene scene, Stage stage) {
		super();
		this.fxmlLocation = fxmlLocation;
		this.root = root;
		this.controller = controller;
		this.model = model;
		this.scene = scene;
		this.stage = stage;
	}



	public String getFxmlLocation() {
		return fxmlLocation;
	}

	public T getRoot() {
		return root;
	}

	public Controller<T> getController() {
		return controller;
	}

	public Scene getScene() {
		return scene;
	}

	public Stage getStage() {
		return stage;
	}

	public Model<T> getModel() {
		return model;
	}
	
	
	
	

}

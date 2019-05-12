package app.coreUI;

import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public abstract class Controller <T extends Parent> implements Initializable {

	protected StageWindow<T> stageWindow;
		
	public Controller() {
		// TODO Auto-generated constructor stub
	}

	public void setStageWindow(StageWindow<T> stageWindow) {
		this.stageWindow = stageWindow;
	}

	StageWindow<T> getStageWindow() {
		return stageWindow;
	}

	public String getFxmlLocation() {
		return stageWindow.getFxmlLocation();
	}

	public T getRoot() {
		return stageWindow.getRoot();
	}

	public Scene getScene() {
		return stageWindow.getScene();
	}

	public Stage getStage() {
		return stageWindow.getStage();
	}

	public void close() {
		stageWindow.getStage().close();
	}
	
	public abstract void linkModel();
	
	

}

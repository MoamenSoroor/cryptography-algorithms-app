package app.coreUI;

import javafx.scene.Parent;

public class Model <T extends Parent>{

	protected StageWindow<T> stageWindow;

	public Model() {
		// TODO Auto-generated constructor stub
	}

	StageWindow<T> getStageWindow() {
		return stageWindow;
	}

	public void setStageWindow(StageWindow<T> stageWindow) {
		this.stageWindow = stageWindow;
	}

	

}

package app;

import app.algorithms.AlgorithmSet;
import app.core.Algorithm;
import app.core.AlgorithmList;
import app.core.Reporting;
import app.coreUI.Model;
import javafx.scene.layout.VBox;


public class ViewModel extends Model<VBox> {

	private AlgorithmList list;
	
	private Algorithm selectedAlgorithm;
			
	public ViewModel() {
		
		list = new AlgorithmList();
		AlgorithmSet.loadAlgorithms(list);		

	}

	public Algorithm getAlgorithm(String name) {
		return list.getAlgorithm(name);
	}

	public boolean isAlgorithmNameExist(String str) {
		return list.isAlgorithmExist(str);
	}

	public String[] getNames() {
		return list.getAlgorithmsNames();
	}

	public int size() {
		return list.size();
	}

	public Algorithm getSelectedAlgorithm() {
		return selectedAlgorithm;
	}
	
	public void selectAlgorithm(String name) {
		selectedAlgorithm = getAlgorithm(name);
		selectedAlgorithm.setReport(getReporting());
	}

	public AlgorithmList getList() {
		return list;
	}

	public Reporting getReporting() {
		return (Reporting) stageWindow.getController();
	}
	
	
	

	
	
	
	
	

	
	

}

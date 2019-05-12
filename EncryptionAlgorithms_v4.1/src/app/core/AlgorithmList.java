package app.core;

import java.util.ArrayList;

public class AlgorithmList extends ArrayList<Algorithm> {
	
	private static final long serialVersionUID = 1L;

	
	
	public AlgorithmList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Algorithm getAlgorithm(String name) {
		for(Algorithm a : this) {
			if(a.getAlgorithmName().equalsIgnoreCase(name))
				return a;
		}
		return null;
	}
	
	public boolean isAlgorithmExist(String str) {
		
		for(Algorithm algo : this) {
			if(algo.getAlgorithmName().equals(str))
				return true;
		}
		return false;
	}

	public String[] getAlgorithmsNames() {
		if(this.isEmpty())
			return null;
		String [] names = new String[this.size()];
		for(int i = 0 ; i< this.size() ; i++) {
			names[i] = this.get(i).getAlgorithmName();
		}
		return names;
	}

	
	
	
	
	
	

}

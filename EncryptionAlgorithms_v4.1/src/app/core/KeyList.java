package app.core;

import java.util.ArrayList;
import java.util.List;

import app.algorithms.DES_Algorithm;

public class KeyList {
	
	private List<String> keys = new ArrayList<>();
	
	public KeyList() {
		// TODO Auto-generated constructor stub
	}

	public boolean add(String arg0) {
		return keys.add(arg0);
	}

	public String get(int arg0) {
		return keys.get(arg0);
	}

	public String remove(int index) {
		return keys.remove(index);
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("KeyList :---------------------------------------" + System.lineSeparator());
		for(int i = 0 ; i < DES_Algorithm.ROUNDS_NUMBER ; i++) {
			builder.append("Key_" + (i + 1) + " = " + BinaryFormatter.toFormattedString_8(keys.get(i))  + System.lineSeparator() );
		}
		builder.append("end --------------------------------------------");
		return builder.toString();

	}
	

}

package app.algorithms;

import app.core.AlgorithmList;

public class AlgorithmSet {
	
	public static void loadAlgorithms(AlgorithmList list) {
		list.add(new CaeserAlgorithm());
		list.add(new PlayFairAlgorithm());
		//list.add(new FiestelAlgorithm());
		list.add(new DES_Algorithm());
		list.add(new RC4_Algorithm());
		list.add(new RSA_Algorithm());




	}

}

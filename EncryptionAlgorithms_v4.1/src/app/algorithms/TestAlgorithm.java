package app.algorithms;

import app.core.Algorithm;

public class TestAlgorithm extends Algorithm{

	public TestAlgorithm() {
		setAlgorithmName("Test Algorithm");
	}

	@Override
	public String encrypt(String key, String plainText) {
		// TODO Auto-generated method stub
		return "test algorithm encrypted Text";
	}

	@Override
	public String decrypt(String key, String cipherText) {
		// TODO Auto-generated method stub
		return "test algorithm decrypted Text";
	}
	
	

}

package app.algorithms;

import app.core.Algorithm;
import app.core.BinaryFormatter;
import app.core.BinaryOperations;
import app.core.KeyList;

public class FiestelAlgorithm extends Algorithm{

	public final static int ROUNDS_NUMBER = 16;
	private final KeyList keyList = new KeyList();
	
	public FiestelAlgorithm() {
		setAlgorithmName("Feistel Algorithm");
	}

	@Override
	public String encrypt(String key, String plainText) {
		
		String ciphertext = BinaryFormatter.toOrdinaryString(plainText);
		writeLog("PlaintextLength = " + ciphertext.length());
		writeLog("Plaintext = " + BinaryFormatter.toFormattedString_4(ciphertext));

		for(int i = 0 ; i < ROUNDS_NUMBER ; i++) {
			writeLog("");
			writeLog("RoundNumber = " + (i + 1));
			keyList.add(BinaryOperations.generateRandom32());
			
			writeLog("Key_" + (i + 1) + " = " + BinaryFormatter.toFormattedString_4(keyList.get(i)));
			writeLog("KeyLength = " + keyList.get(i).length());
			
			ciphertext = roundCipher(ciphertext, keyList.get(i));
			
			writeLog("CiphertextLength = " + ciphertext.length());
			writeLog("Ciphertext = " + BinaryFormatter.toFormattedString_4(ciphertext));
		}
		ciphertext = swap(ciphertext);
		writeLog("SwappedCipherText = " + BinaryFormatter.toFormattedString_4(ciphertext));
		return ciphertext;
	}

	@Override
	public String decrypt(String key, String cipherText) {
		String ciphertext = BinaryFormatter.toOrdinaryString(cipherText);
		for(int i = 0 ; i < ROUNDS_NUMBER ; i++) {
			ciphertext = roundCipher(ciphertext, keyList.get(ROUNDS_NUMBER - i - 1));
		}
		return swap(ciphertext);
	}
	
	private String roundCipher(String text , String key) {
		String right = getRight(text);
		String left = getLeft(text);
		
		String result = function(right , key);
		String newRight = binaryXOR(result , left);
		
		return right.concat(newRight);
	}
	
	private String permute(int [] array , String text) {
		StringBuilder builder = new StringBuilder();
		for(int i = 0 ; i < array.length ; i++) {
			builder.append(text.charAt(array[i]));
		}
		return builder.toString();
	}
	
	private String binaryXOR(String text1, String text2) {
		StringBuilder builder = new StringBuilder();
		for(int i = 0 ; i < text1.length() ; i++) {
			if(text1.charAt(i) == text2.charAt(i))
				builder.append('0');
			else
				builder.append('1');
		}
		return builder.toString();
	}
	
	private String binaryAND(String text1, String text2) {
		StringBuilder builder = new StringBuilder();
		for(int i = 0 ; i < text1.length() ; i++) {
			char ch1 = text1.charAt(i);
			char ch2 = text2.charAt(i);
			if(ch1 == '1')
				if(ch2 == '1')
					builder.append('1');
				else
					builder.append('0');
			else
				builder.append('0');
			
		}
		return builder.toString();
	}
	
	private String binaryOR(String text1, String text2) {
		StringBuilder builder = new StringBuilder();
		for(int i = 0 ; i < text1.length() ; i++) {
			char ch1 = text1.charAt(i);
			char ch2 = text2.charAt(i);
			if(ch1 == '0')
				if(ch2 == '0')
					builder.append('0');
				else
					builder.append('1');
			else
				builder.append('1');
			
		}
		return builder.toString();
	}
	
	private String binaryNOT(String text1) {
		StringBuilder builder = new StringBuilder();
		for(int i = 0 ; i < text1.length() ; i++) {
			builder.append(text1.charAt(i) == '0' ? '1' : '0');
		}
		return builder.toString();
	}
	

	private String function(String right, String key) {
		return binaryOR(right, key);
	}

	private String getLeft(String text) {
		return text.substring(0, text.length() / 2 );
	}
	
	
	private String getRight(String text) {
		return text.substring(text.length() / 2);

	}
	
	private String swap(String text) {
		return getRight(text) + getLeft(text);
	}
	
	
	
	
	
	
	
	
	
	
}

package app.algorithms;

import app.core.Algorithm;

public class RC4_Algorithm extends Algorithm {
	
	public final static int KEY_LENGTH = 256;
	int [] s = new int[KEY_LENGTH];
	int [] k = new int[KEY_LENGTH];

	public RC4_Algorithm() {
		setAlgorithmName("RC4_Algorithm");
	}
	
	@Override
	public String encrypt(String key, String plainText) {
		try {
			if(detectEncryptionExceptions(key , plainText))
				throw new Exception();
			writeLog("Selected Algorithm: " + algorithmName);
			writeLog("Encryption Started...");
			
			// encryption
			String ciphertext = getCipher(plainText, key);
			
			writeLog("Encryption ended Successfully");
			return ciphertext;
		}
		catch (Exception e) {
			writeLog(" Encryption Failed");
			return "";
		}
		
	}


	@Override
	public String decrypt(String key, String cipherText) {
		try {
			if(detectEncryptionExceptions(key , cipherText))
				throw new Exception();
			writeLog("Selected Algorithm: " + algorithmName);
			writeLog("Decryption Started...");
			
			// encryption
			String ciphertext = getCipher(cipherText, key);
			
			writeLog("Decryption ended Successfully");
			return ciphertext;
		}
		catch (Exception e) {
			writeLog(" Decryption Failed");
			return "";
		}
	}

	private boolean detectEncryptionExceptions(String key, String plainText) {
		boolean result = false;
		
		if(key.isEmpty() || plainText.isEmpty()) {
			writeError(" Exception: key or plaintext is Empty");
			result = true;
		}
		
		return result;
	}

	private void initKey(String key) {
		int [] enteredKey = toArrayOfInt(key);
		
		for(int i = 0 ; i < KEY_LENGTH ; i++) {
			s[i] = i;
			k[i] = enteredKey[ i % enteredKey.length];
		}
		for(int i = 0 , j = 0 ; i < KEY_LENGTH ; i++) {
			j = (j + s[i] + k[i]) % KEY_LENGTH;
			swapS(i , j);
		}
		
		writeLog(arrayToString(enteredKey, "Entered Key "));
		writeLog("");
		writeLog(arrayToString(k, "k "));
		writeLog("");
		writeLog(arrayToString(s, "S "));

	}
	
	private String getCipher(String plainText , String key) {
		initKey(key);
		
		int [] plainInts = toArrayOfInt(plainText);
		int [] cipherInts = new int [plainInts.length];
		int i = 0 , j = 0;
		for(int counter = 0 ; counter < plainInts.length ; counter++) {
			i = counter % KEY_LENGTH;
			j = (j + s[i]) % KEY_LENGTH;
			swapS(i , j);
			int intKey = s[(s[i] + s[j])% KEY_LENGTH];
			cipherInts[counter] = intKey ^ plainInts[counter];
		}
		
		return toText(cipherInts);
	}
	
	private boolean swapS(int index1 , int index2) {
		int temp = s[index1];
		s[index1] = s[index2];
		s[index2] = temp;
		return true;
	}
	
/*	public static void main(String[] args) {
		System.out.println(arrayToString(toArrayOfInt("abc")));
		System.out.println(toText(toArrayOfInt("abc")));
		
	}*/

}

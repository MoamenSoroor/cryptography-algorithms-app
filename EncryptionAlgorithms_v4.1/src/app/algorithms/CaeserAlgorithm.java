package app.algorithms;

import app.core.Algorithm;
import app.core.CharCoder;

public class CaeserAlgorithm extends Algorithm {

	public CaeserAlgorithm() {
		super();
		setAlgorithmName("Caeser Algorithm");
	}

	@Override
	public String encrypt(String key, String plainText) {
		try {
			String myPlaintext = CharCoder.processToEncryption(plainText);

			if(detectEncryptionExceptions(key , plainText))
				throw new Exception();
			report.writeLog("Selected Algorithm: "+ algorithmName);
			report.writeLog("Plaintext Key: "+ key);
			report.writeLog("Encryption Started...");
			int myKey = Integer.parseInt(key);

			StringBuilder ciphertext = new StringBuilder();
			for(int i = 0 ; i< myPlaintext.length() ; i++) {
				char plainChar = myPlaintext.charAt(i);
				int plainValue = CharCoder.getCustomValue(plainChar);

				int cipherValue = (plainValue + myKey)  % (CharCoder.MAX_VALUE + 1);
				cipherValue = updateCaeserNegativeValue(cipherValue);

				char ciperChar = CharCoder.getUnicodeValue(cipherValue);

				ciphertext.append(ciperChar);
			}
			report.writeLog("Encryption ended Successfully");
			return CharCoder.processToView(ciphertext.toString());
		}
		catch(Exception ex) {
			report.writeLog("Encryption Failed");
			return "";
		}
		
	}

	@Override
	public String decrypt(String key, String cipherText) {
		try {
			String myCiphertext = CharCoder.processToEncryption(cipherText);

			if(detectDecryptionExceptions(key , cipherText))
				throw new Exception();
			report.writeLog("");
			report.writeLog("Selected Algorithm: "+ algorithmName);
			report.writeLog("cipherText Key: "+ key);
			report.writeLog("Decryption Started...");
			
			int myKey = Integer.parseInt(key);

			StringBuilder plaintext = new StringBuilder();
			for(int i = 0 ; i< myCiphertext.length() ; i++) {
				char cipherChar = myCiphertext.charAt(i);

				int cipherValue = CharCoder.getCustomValue(cipherChar);
				
				int plainValue = (cipherValue - myKey)  % (CharCoder.MAX_VALUE + 1);
				plainValue = updateCaeserNegativeValue(plainValue);
				char ciperChar = CharCoder.getUnicodeValue(plainValue);
				plaintext.append(ciperChar);
			}
			report.writeLog("Decryption ended Successfully");
			return CharCoder.processToView(plaintext.toString());
		}
		catch(Exception ex) {
			report.writeLog("Decryption Failed");
			report.writeLog(""+ ex.toString());

			return "";
		}
	}

	private boolean detectEncryptionExceptions(String key, String plainText) {
		boolean result = false;
		if(key.isEmpty() || plainText.isEmpty()) {
			report.writeError("Exception: key or Ciphertext is Empty");
			result = true;
		}
		if(!isCaeserKeyAccepted(key)) {
			report.writeError("Exception: Ceaser Key is not accepted");
			result = true;
		}
		
		if(!CharCoder.isTextSupported(plainText)) {
			report.writeError("Exception: Ciphertext has unsupported Characters");
			result = true;
		}
		
		return result;
	}
	
	private boolean detectDecryptionExceptions(String key, String Ciphertext) {
		boolean result = false;
		if(key.isEmpty() || Ciphertext.isEmpty()) {
			report.writeError("Exception: key or Ciphertext is Empty");
			result = true;
		}
		if(!isCaeserKeyAccepted(key)) {
			report.writeError("Exception: Ceaser Key is not accepted");
			result = true;
		}
		
		if(!CharCoder.isTextSupported(Ciphertext)) {
			report.writeError("Exception: Ciphertext has unsupported Characters");
			result = true;
		}
		
		return result;
		
	}
	
	private boolean isCaeserKeyAccepted(String key) {
		String key2;
		char first = key.charAt(0);
		if(first == '+' || first == '-')
			key2 = key.substring(1, key.length());
		else
			key2 = key;
		for(char ch : key2.toCharArray()) {
			if(ch < '0' && ch > '9')
				return false;
				
		}
		try {
			Integer.parseInt(key2);
		}catch(Exception ex) {
			return false;
		}
		return true;
		
	}

	private int updateCaeserNegativeValue(int value) {

		return value < 0 ? CharCoder.MAX_VALUE + 1 + value : value ;
	}
	
	
	
	
}

package app.algorithms;

import app.core.Algorithm;
import app.core.CharCoder;

public class PlayFairAlgorithm extends Algorithm {

	/*
	 * encryption:
	 * 1- convert i/j to i or j in the entered key
	 * 2- remove repetition on key
	 * 3- build matrix [5][5] by key and squence of alphabet a-z without repeting any character 
	 * 		and i/j be in one cell and it must be the same as i/j in key
	 * 4- make method getMatrixLocation(plainChar) that returns row , cloumn of a character
	 * 5- make method getCipherChar(plainChar) that returns cipher character 
	 * 		typically like playfair algorithm
	 * 6- make method processPlainText(plaintext) that process plaintext, makes the following
	 * 		- convert i/j to i or j 
	 * 		- add X between two identical characters in sequence
	 * 		- if length of plaintext is odd, add X at the end of plaintext
	 * 7- make method getCipherText(processedPlaintext) that returns ciphertext, 
	 * 		takes processed plaintext, uses getCipherChar(plainChar) 
	 * 8- print ciphertext to UI
	 * 
	 * decryption:
	 * 		- use the encryption mechanism but make getPlainChar(cipherChar) 
	 * 		and make it works instead of getCipherChar(plainChar)
	 * 
	 */
	
/*	private final char startCapitalChar = 'A';
	private final char startSmallChar = 'a';
	private char selectedSmallOrCapitalChar = 'a';
	private char selectedIorJ = 'i';
	private char unknownChar = 'x';
	private char unknownChar2 = 'y';
*/
	
	public PlayFairAlgorithm() {
		setAlgorithmName("PlayFair Algorithm");
	}

	@Override
	public String encrypt(String key, String plainText) {
		try {
			if(detectEncryptionExceptions(key , plainText))
				throw new Exception();
			report.writeLog(" Selected Algorithm: " + algorithmName);
			report.writeLog(" Plaintext Key: " + key);
			report.writeLog(" Encryption Started...");
			String ciphertext = getCiphertext(plainText, key);
			report.writeLog(" Encryption ended Successfully");
			return ciphertext;
		}
		catch (Exception e) {
			report.writeLog(" Encryption Failed");
			return "";
		}
		
	}

	@Override
	public String decrypt(String key, String ciphertext) {
		try {
			if(detectDecryptionExceptions(key , ciphertext))
				throw new Exception();
			report.writeLog("Selected Algorithm: " + algorithmName);
			report.writeLog("Plaintext Key: " + key);
			report.writeLog("Decryption Started...");
			String plainText = getPlainttext(ciphertext, key);
			report.writeLog("Decryption ended Successfully");
			return plainText;
		}
		catch (Exception e) {
			report.writeLog("Decryption Failed");
			return "";
		}

	}
	
	private boolean detectEncryptionExceptions(String key, String plainText) {
		boolean result = false;
		if(key.isEmpty() || plainText.isEmpty()) {
			report.writeError(" Exception: key or plaintext is Empty");
			result = true;
		}
		if(!isKeyAccepted(key)) {
			report.writeError(" Exception: Invalid Key");
			result = true;
		}
		if(!isTextAccepted(plainText)) {
			report.writeError(" Exception: Invalid Plaintext");
			result = true;
		}
		if(key.length() > 25)
			writeLog("NOTE: Key length > 25, it will be cutted to 25");
		
		return result;
	}

	private boolean detectDecryptionExceptions(String key, String plainText) {
		boolean result = false;
		if(key.isEmpty() || plainText.isEmpty()) {
			report.writeError(" Exception: key or ciphertext is Empty");
			result = true;
		}
		if(!isKeyAccepted(key)) {
			report.writeError(" Exception: Invalid Key");
			result = true;
		}
		if(!isTextAccepted(plainText)) {
			report.writeError(" Exception: Invalid Plaintext");
			result = true;
		}
		if(plainText.length() % 2 != 0) {
			
		}
		if(isRepetitionInPairsExists(plainText)) {
			
		}
		if(key.length() > 25)
			writeLog("NOTE: Key length > 25, it will be cutted to 25");
		
		return result;
	}

	private boolean isRepetitionInPairsExists(String plainText) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean isTextAccepted(String plainText) {
		for(char ch : plainText.toCharArray()) {
			if((ch < 'a' || ch > 'z') && (ch < 'A' || ch > 'Z'))
				return false;
		}
		return true;
	}

	private boolean isKeyAccepted(String key) {
		for(char ch : key.toCharArray()) {
			if((ch < 'a' || ch > 'z') && (ch < 'A' || ch > 'Z'))
				return false;
		}
		return true;
	}

	private String convertTextToSmall(String text) {
		
		StringBuilder builder = new StringBuilder();
		for(char ch : text.toCharArray()) {
			if(ch >= 'A' && ch <= 'Z') { 
				int loc = 'a' + ch - 'A';
				builder.append((char)loc);
			}
			else
				builder.append(ch);
		}
		
		return builder.toString();
		
	}
	
	private String convertTextToCapital(String text) {
		
		StringBuilder builder = new StringBuilder();
		for(char ch : text.toCharArray()) {
			if(ch >= 'a' && ch <= 'z') { 
				int loc = 'A' + ch - 'a';
				builder.append((char)loc);
			}
			else
				builder.append(ch);
		}
		
		return builder.toString();
		
	}
	
	private String convertToIChar(String text) {
		StringBuilder builder = new StringBuilder();
		for(char ch : text.toCharArray()) {
			if(ch == 'j')
				builder.append('i');
			else
				builder.append(ch);

		}
		return builder.toString();
	}
	
	private String convertToJChar(String text) {
		StringBuilder builder = new StringBuilder();
		for(char ch : text.toCharArray()) {
			if(ch == 'i')
				builder.append('j');
			else
				builder.append(ch);
		}
		return builder.toString();

	}
	
	private String appendXIfOddlength(String text) {
		if(text.length() % 2 == 1) {
			if(text.charAt(text.length() - 1) == 'x')
				return text + "y";
			else
				return text + "x";
		}
		else
			return text;
			
	}
	
	private String insertXBetween(String text) {
		StringBuilder builder = new StringBuilder();
		int i = 0;
		while( i < text.length()) {
			char ch = text.charAt(i);
			
			if( i + 1 == text.length()) {
				// last caracter has no character after it, and i + 1 makes NullPointerException
				builder.append(ch);
				i++;
			}
			else {
				char ch2 = text.charAt(i + 1);
				if(ch == ch2) {
					if(ch == 'x') {
						// ...xxnd...
						builder.append(ch);
						builder.append('y');
						i++;
					}
					else {
						// ...ffnd...
						builder.append(ch);
						builder.append('x');
						i++;
					}
					
				}
				else {
					// ...mnts...
					builder.append(ch);
					builder.append(ch2);
					i += 2;	
				}
			}
			
			

		}
		return builder.toString();
	}
	
	private String generateSequenceChars(String key) {
		StringBuilder builder = new StringBuilder();
		int length = 26;
		for(int i = 0 ; i < length ; i++) {
			char ch = (char)('a' + i);
			boolean exists = false;
			for(char keychar : key.toCharArray()) {
				if(keychar == ch) {
					exists = true;
					break;
				}
			}
			if(!exists && ch != 'j') {
				builder.append(ch);
			}
			
		}
		return builder.toString();

	}
	
	private String cutOverKeyLength(String key) {
		String result;
		if(key.length() > 25) {
			result = key.substring(0, 24);
			writeLog("NOTE: key is too length, it will be cutted to 25 chars");
			writeLog("		Entered Key = " + key);
			writeLog("		Cutted Key = " + result);

		}
		else
			result = key;
		return result;
				
	}

	private String addSpacesBetweenEveryTwo(String text) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < text.length(); i+=2) {
			builder.append(text.charAt(i));
			builder.append(text.charAt(i + 1));
			builder.append(" ");
		}
		return builder.toString();
	}
	
	private String removeRepetations(String key) {
		StringBuilder builder = new StringBuilder();
		builder.append(key.charAt(0));
		int length = key.length();
		for (int i = 1; i < length ; i++) {
			char ch = key.charAt(i);
			boolean isRepeated = false;
			for(char ch2 : builder.toString().toCharArray()) {
				if(ch2 == ch) {
					isRepeated = true;
					break;
				}
			}
			if(!isRepeated)
				builder.append(ch);
		}
		return builder.toString();
	}

	private char[][] Buildmatrix(String key) {
		String matText = key + generateSequenceChars(key);
		//System.out.println(matText + " " + matText.length());
		if(matText.length() != 25)
			return null;
		char[][] mat = new char [5][5];
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[i].length; j++) {
				mat[i][j] = matText.charAt(5 * i + j);
			}
		}
		return mat;
		
	}
	
	private int [] getLocationInMatrix(char [][] mat, char ch){
		int [] location = new int [2];
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[i].length; j++) {
				if(mat[i][j] == ch) {
					location[0] = i;
					location[1] = j;
					return location;
				}
			}
				
		}
		return null;
		
	}
	
	private String getStringOfMatrix(char [][] mat) {
		StringBuilder builder = new  StringBuilder();
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[i].length; j++) {
				builder.append(mat[i][j]);
				builder.append("\t");
			}
			builder.append(System.lineSeparator());
		}
		return builder.toString();
	}
	
	private char[] getCipherChar(char [][] mat , char[] pairPlainChar){
		char [] pairChars = new char [2];
		int [] loc1 = getLocationInMatrix(mat, pairPlainChar[0]);
		int [] loc2 = getLocationInMatrix(mat, pairPlainChar[1]);
		
		int i1 = loc1[0], j1 = loc1[1] ;
		int i2 = loc2[0]  , j2 = loc2[1];
		if(i1 != i2 && j1 != j2) {
			pairChars[0] = mat[i1][j2];
			pairChars[1] = mat[i2][j1];
		}
		else if(j1 == j2) {
			/// equation new_i = (old_i + 1) % 5 
			pairChars[0] = mat[(i1 + 1)%5][j1];
			pairChars[1] = mat[(i2 + 1)%5][j2];

		}
		else {
			pairChars[0] = mat[i1][(j1 + 1)%5];
			pairChars[1] = mat[i2][(j2 + 1)%5];

		}
		return pairChars;
	}

	private String getCiphertext(String plaintext , String key) {
		key = cutOverKeyLength(key);
		key = convertTextToSmall(key);
		key = convertToIChar(key);
		key = removeRepetations(key);
		char [][] matrix = Buildmatrix(key);
		writeLog("PlayFair Matrix: " + System.lineSeparator() + getStringOfMatrix(matrix));
		plaintext = convertTextToSmall(plaintext);
		plaintext = convertToIChar(plaintext);
		plaintext = insertXBetween(plaintext);
		plaintext = appendXIfOddlength(plaintext);
		writeLog("Plaintext:   "+ addSpacesBetweenEveryTwo(plaintext));

		StringBuilder builder = new StringBuilder();
		for(int i = 0 ; i < plaintext.length() ; i+=2) {
			char [] pair = getCipherChar(matrix, new char[] {plaintext.charAt(i) , plaintext.charAt(i + 1)} );
			builder.append(pair[0]);
			builder.append(pair[1]);
		}
		writeLog("Ciphertext: "+ addSpacesBetweenEveryTwo(builder.toString()));
		return builder.toString();
	}

	private char[] getPlainChar(char [][] mat , char[] pairPlainChar){
		char [] pairChars = new char [2];
		int [] loc1 = getLocationInMatrix(mat, pairPlainChar[0]);
		int [] loc2 = getLocationInMatrix(mat, pairPlainChar[1]);

		int i1 = loc1[0], j1 = loc1[1] ;
		int i2 = loc2[0]  , j2 = loc2[1];
		 
		if(i1 != i2 && j1 != j2) {
			pairChars[0] = mat[i1][j2];
			pairChars[1] = mat[i2][j1];
		}
		else if(j1 == j2) {
			/// equation new_i = (old_i - 1 + 5) % 5
			// we add 5 for negative value;
			pairChars[0] = mat[(i1 + 4) % 5][j1];
			pairChars[1] = mat[(i2 + 4) % 5][j2];

		}
		else {
			pairChars[0] = mat[i1][(j1 + 4) % 5];
			pairChars[1] = mat[i2][(j2 + 4) % 5];

		}
		return pairChars;
	}

	private String getPlainttext(String plaintext , String key) {
		key = cutOverKeyLength(key);
		key = convertToIChar(key);
		key = removeRepetations(key);
		key = convertTextToSmall(key);
		char [][] matrix = Buildmatrix(key);
		writeLog("PlayFair Matrix: " + System.lineSeparator() + getStringOfMatrix(matrix));
		writeLog("Ciphertext: " + System.lineSeparator() + addSpacesBetweenEveryTwo(plaintext));

		StringBuilder builder = new StringBuilder();
		for(int i = 0 ; i < plaintext.length() ; i+=2) {
			char [] pairPlain = new char[] {plaintext.charAt(i) , plaintext.charAt(i + 1)};
			char [] pairCipher = getPlainChar(matrix, pairPlain);
			builder.append(pairCipher[0]);
			builder.append(pairCipher[1]);

		}
		writeLog("Plaintext:   "+ addSpacesBetweenEveryTwo(builder.toString()));
		return builder.toString();
	}

	
	
	
}

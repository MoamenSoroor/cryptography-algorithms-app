package app.algorithms;

import app.core.Algorithm;
import app.core.BinaryFormatter;
import app.core.BinaryOperations;
import app.core.KeyList;

public class DES_Algorithm extends Algorithm {
	
	public static final String DES_ALGORITHM = "DES_Algorithm";
	
	public static final int BLOCK_ENGTH = 64;
		
	public DES_Algorithm() {
		setAlgorithmName(DES_ALGORITHM);
//		System.out.println("expansionDBOX =" + expasionDBox.length);
//		System.out.println("straightDBox =" + straightDBox.length);
//		System.out.println("initialPermutaion =" + initialPermutaion.length);
//		System.out.println("finalPermutaion =" + finalPermutaion.length);
//		System.out.println("keyInitPermutaion =" + keyInitPermutaion.length);
//		System.out.println("keyCompression =" + keyCompression.length);
//		System.out.println("numberOfLeftRotation =" + numberOfLeftRotation.length);
//		System.out.println("sBoxes =" + sBoxes.length);


	}
	
	// permute 32 bit to 48 bit in function before sboxes
	private final int[] expasionDBox = new int[] { 32, 1, 2, 3, 4, 5, 4, 5, 6, 7, 8, 9, 8, 9, 10, 11, 12, 13, 12, 13,
			14, 15, 16, 17, 16, 17, 18, 19, 20, 21, 20, 21, 22, 23, 24, 25, 24, 25, 26, 27, 28, 29, 28, 29, 31, 31, 32,
			1 };

	// permute 32 bit to 32 bit in function after sboxes
	private final int[] straightDBox = new int[] { 16, 7, 20, 21, 29, 12, 28, 17, 1, 15, 23, 26, 5, 18, 31, 10, 2, 8,
			24, 14, 32, 27, 3, 9, 19, 13, 30, 6, 22, 11, 4, 25 };

	// permute 64 bit before encryption
	private final int[] initialPermutaion = new int[] { 58, 50, 42, 34, 26, 18, 10, 2, 60, 52, 44, 36, 28, 20, 12, 4,
			62, 54, 46, 38, 30, 22, 14, 6, 64, 56, 48, 40, 32, 24, 16, 8, 57, 49, 41, 33, 25, 17, 9, 1, 59, 51, 43, 35,
			27, 19, 11, 3, 61, 53, 45, 37, 29, 21, 13, 5, 63, 55, 47, 39, 31, 23, 15, 7 };

	// permute 64 bit after encryption (reverse of initial permutation)
	private final int[] finalPermutaion = new int[] { 40, 8, 48, 16, 56, 24, 64, 32, 39, 7, 47, 15, 55, 23, 63, 31, 38,
			6, 46, 14, 54, 22, 62, 30, 37, 5, 45, 13, 53, 21, 61, 29, 36, 4, 44, 12, 52, 20, 60, 28, 35, 3, 43, 11, 51,
			19, 59, 27, 34, 2, 42, 10, 50, 18, 58, 26, 33, 1, 41, 9, 49, 17, 57, 25 };

	private final int[][][] sBoxes = {
			// sbox 1
			{ 		{ 14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7 },
					{ 0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8 },
					{ 4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0 },
					{ 15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13 }, },
			// sbox 2
			{ 		{ 15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10 },
					{ 3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5 },
					{ 0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15 },
					{ 13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9 }, },
			// sbox 3
			{ 		{ 10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8 },
					{ 13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1 },
					{ 13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7 },
					{ 1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12 }, },
			// sbox 4
			{ 		{ 7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15 },
					{ 13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9 },
					{ 10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4 },
					{ 3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14 }, },
			// sbox 5
			{ 		{ 2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9 },
					{ 14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6 },
					{ 4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14 },
					{ 11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3 }, },
			// sbox 6
			{ 		{ 12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11 },
					{ 10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8 },
					{ 9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6 },
					{ 4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13 }, },
			// sbox 7

			{ 		{ 4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1 },
					{ 13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6 },
					{ 1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2 },
					{ 6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12 }, },
			// sbox 8
			{ 		{ 13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7 },
					{ 1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2 },
					{ 7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8 },
					{ 2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11 }, } };

	// key parity drop and permutaion form 64 bit to 56 bit
	private final int[] keyInitPermutaion = new int[] { 
			57, 49, 41, 33, 25, 17, 9, 1, 58, 50, 42, 34, 26, 18, 10, 2, 59,
			51, 43, 35, 27, 19, 11, 3, 60, 52, 44, 36, 63, 55, 47, 39, 31, 23, 
			15, 7, 62, 54, 46, 38, 30, 22, 14, 6, 61, 53, 45, 37, 29, 21, 13, 5, 28, 20, 12, 4

	};

	// compression of 56 bit key before each round encryption
	private final int[] keyCompression = new int[] { 14, 17, 11, 24, 1, 5, 3, 28, 15, 6, 21, 10, 23, 19, 12, 4, 26, 8,
			16, 07, 27, 20, 13, 2, 41, 52, 31, 37, 47, 55, 30, 40, 51, 45, 33, 48, 44, 49, 39, 56, 34, 53, 46, 42, 50,
			36, 29, 32 };

	
	private final int[] numberOfLeftRotation = new int[] { 1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1 };

	public final static int ROUNDS_NUMBER = 16;
	
	Padding plainPadding = new Padding();
	Padding keyPadding = new Padding();
	
	
	@Override
	public String encrypt(String key, String plainText) {

		try {
			if(detectEncryptionExceptions(key , plainText))
				throw new Exception();
			writeLog("Selected Algorithm: " + algorithmName);
			writeLog("Encryption Started...");
			plainPadding = new Padding();
			keyPadding = new Padding();
			String [] plainBlocks = toStringBlocks(plainPadding.addPadding(plainText));
			String [] cipherBlocks = new String [plainBlocks.length];
			String myKey = keyPadding.addPadding(key);
			writeLog("plain padding chars: " + plainPadding.getPaddingchars());
			writeLog("plain padding length: " + plainPadding.getPaddingLength());
			writeLog("plain with padding : " + concatString(plainBlocks));

			writeLog("key padding chars: " + keyPadding.getPaddingchars());
			writeLog("key padding length: " + keyPadding.getPaddingLength());
			writeLog("key with padding : " + myKey);
			
			for (int i = 0; i < cipherBlocks.length; i++) {
				cipherBlocks[i] = toNormalString(getCiphertext(toBinaryString(plainBlocks[i]),toBinaryString(myKey)));
			}
			
			// encryption
			writeLog("Encryption ended Successfully");
			return concatString(cipherBlocks);
		}
		catch (Exception e) {
			writeLog(" Encryption Failed");
			return "";
		}
		
		
	}

	private boolean detectEncryptionExceptions(String key, String plainText) {
		boolean result = false;
		if(key.isEmpty() || plainText.isEmpty()) {
			writeError(" Exception: key or plaintext is Empty");
			result = true;
		}
		
		if(key.length() > 4 )
			writeLog("Exception: Invalid key Length, insert key with 64 bit length");
		return result;
	}

	private boolean isTextAccepted(String plainText) {
		return plainText.matches("[01]*");
	}

	private boolean isKeyAccepted(String key) {
		return key.matches("[01]*");
	}

	private String getCiphertext(String plainText, String key) {
		writeLog("one block Encryption");
		String ciphertext = BinaryFormatter.toOrdinaryString(plainText);
		key = BinaryFormatter.toOrdinaryString(key);

		writeLog("Plaintext = " + BinaryFormatter.toFormattedString_4(ciphertext));
		writeLog("PlaintextLength = " + ciphertext.length());
		writeLog("EnteredKey = " + BinaryFormatter.toFormattedString_4(key));
		writeLog("KeyLength = " + key.length());
		
		ciphertext = permute(initialPermutaion, ciphertext);
		key = permute(keyInitPermutaion, key);
		
		KeyList keyList = new KeyList();
		
		for (int i = 0; i < ROUNDS_NUMBER; i++) {
			
			writeLog("");
			writeLog("RoundNumber = " + (i + 1));
			
			// key round key generation
			key = generateRoundKey(i, key);
			keyList.add(key);
			writeLog("Key_56_Bit =" + (i + 1) + " = " + BinaryFormatter.toFormattedString_4(key));
			writeLog("KeyLength = " + key.length());
			
			// compression key
			String subKey = permute(keyCompression, key);
			
			writeLog("Key_48_BitOfRound_" + (i + 1) + " = " + BinaryFormatter.toFormattedString_4(subKey));
			writeLog("KeyLength = " + subKey.length());

			// one round cipherment
			ciphertext = roundCipher(ciphertext, subKey);

			writeLog("CiphertextOfRound_" + (i + 1) + " = " +BinaryFormatter.toFormattedString_4(ciphertext));
			writeLog("CiphertextLength = " + ciphertext.length());

		}
		///////
		ciphertext = swap(ciphertext);
		ciphertext = permute(finalPermutaion, ciphertext);
		writeLog("EncryptedPlaintext = " + BinaryFormatter.toFormattedString_4(ciphertext));
		return ciphertext;
	}

	@Override
	public String decrypt(String key, String ciphertext) {
		try {
			if(detectDecryptionExceptions(key , ciphertext))
				throw new Exception();
			writeLog("Selected Algorithm: " + algorithmName);
			writeLog("Decryption Started...");
			String [] cipherBlocks = toStringBlocks(ciphertext);
			String [] plainBlocks = new String [cipherBlocks.length];
			String myKey = keyPadding.addPadding(key);
			writeLog("key padding chars: " + keyPadding.getPaddingchars());
			writeLog("key padding length: " + keyPadding.getPaddingLength());
			writeLog("key with padding : " + myKey);
			for (int i = 0; i < plainBlocks.length; i++) {
				plainBlocks[i] = toNormalString(getPlainttext(toBinaryString(cipherBlocks[i]),toBinaryString(myKey)));
			}
			
			// decryption
			writeLog("Decryption ended Successfully");
			writeLog("plain: " + concatString(plainBlocks));
			return plainPadding.removePadding(concatString(plainBlocks));
			
		}
		catch (Exception e) {
			writeLog("Decryption Failed");
			return "";
		}
		
		

		
	}

	// encryption
	private String getPlainttext(String ciphertext, String key) {
		String plaintext = BinaryFormatter.toOrdinaryString(ciphertext);
		key = BinaryFormatter.toOrdinaryString(key);
		
		writeLog("ciphertextLength = " + plaintext.length());
		writeLog("EnteredCiphertext = " + BinaryFormatter.toFormattedString_4(plaintext));
		writeLog("EnteredKey = " + BinaryFormatter.toFormattedString_4(key));
		writeLog("KeyLength = " + key.length());
		
		plaintext = permute(initialPermutaion, plaintext);
		key = permute(keyInitPermutaion, key);
		// all keys generation
		KeyList keyList = generateAllRoundsKeys(key);
		
		for (int i = 0; i < ROUNDS_NUMBER; i++) {
			
			writeLog("");
			writeLog("RoundNumber = " + (i + 1));
			
			writeLog("Key_56_Bit =" + (i + 1) + " = " + BinaryFormatter.toFormattedString_4(key));
			writeLog("KeyLength = " + key.length());
			
			// compression key form 56 to 48
			String subKey = permute(keyCompression, keyList.get(ROUNDS_NUMBER - i - 1));
			
			writeLog("Key_48_BitOfRound_" + (i + 1) + " = " + BinaryFormatter.toFormattedString_4(subKey));
			writeLog("KeyLength = " + subKey.length());

			// one round cipherment
			plaintext = roundCipher(plaintext, subKey);

			writeLog("PlaintextOfRound_" + (i + 1) + " = " +BinaryFormatter.toFormattedString_4(plaintext));
			writeLog("PlaintextLength = " + plaintext.length());

		}
		plaintext = swap(plaintext);
		plaintext = permute(finalPermutaion, plaintext);
		writeLog("ResultPlaintext = " + BinaryFormatter.toFormattedString_4(plaintext));
		return plaintext;
	}

	private boolean detectDecryptionExceptions(String key, String ciphertext) {
		boolean result = false;
		if(key.isEmpty() || ciphertext.isEmpty()) {
			writeError(" Exception: key or ciphertext is Empty");
			result = true;
		}
		
		if(key.length() > 4 )
			writeLog("Exception: Invalid key Length, insert key with 64 bit length");
		
		return result;
		
		
		
	}

	private KeyList generateAllRoundsKeys(String key) {
		KeyList list = new KeyList();
		String roundKey = key;
		for(int i = 0; i < ROUNDS_NUMBER ; i++) {
			roundKey = generateRoundKey(i , roundKey);
			list.add(roundKey);
			
		}
		return list;
	}

	private String roundCipher(String text, String key) {
		String right = getRight(text);
		String left = getLeft(text);

		String result = function(right, key);
		String newRight = BinaryOperations.binaryXOR(result, left);
		// newLeft = right  then concate newLeft with result of function
		return right.concat(newRight);
	}

	private String permute(int[] array, String text) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			builder.append(text.charAt(array[i] - 1));
		}
		return builder.toString();
	}

	private String permuteSBox(int[][] sbox, String text) {
		String row = "" + text.charAt(0) + text.charAt(5);
		String col =  text.substring(1, 5);

		int intRow = getRow(row);
		int intCol = getColumn(col);
		return getBinary(sbox[intRow][intCol]);
	}
	
	private String getBinary(int value) {
		switch(value) {
		case 0 : return "0000";
		case 1 : return "0001";
		case 2 : return "0010";
		case 3 : return "0011";
		case 4 : return "0100";
		case 5 : return "0101";
		case 6 : return "0110";
		case 7 : return "0111";
		case 8 : return "1000";
		case 9 : return "1001";
		case 10: return "1010";
		case 11: return "1011";
		case 12: return "1100";
		case 13: return "1101";
		case 14: return "1110";
		case 15: return "1111";
		default: return "1111";
		}
	}
	
	private int getColumn(String col) {
		switch(col) {
		case "0000": return 0;
		case "0001": return 1;
		case "0010": return 2;
		case "0011": return 3;
		case "0100": return 4;
		case "0101": return 5;
		case "0110": return 6;
		case "0111": return 7;
		case "1000": return 8;
		case "1001": return 9;
		case "1010": return 10;
		case "1011": return 11;
		case "1100": return 12;
		case "1101": return 13;
		case "1110": return 14;
		case "1111": return 15;
		default: return -1;
		}
	}
	
	private int getRow(String row) {
		switch(row) {
		case "00": return 0;
		case "01": return 1;
		case "10": return 2;
		case "11": return 3;
		
		default: return -1;
		}
	}

	private String function(String right, String key) {
		// suppose that right 32 bit and key is 48 bit
		right = permute(expasionDBox, right);
		// now key length = right length = 48 bit
		right = BinaryOperations.binaryXOR(right, key);
		
		// sboxes
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < right.length(); i += 6) {
			String block = right.substring(i, i + 6);
			
			// takes 6 bit and return 4 bit each time
			block = permuteSBox(sBoxes[i/6], block);
			
			builder.append(block);

		}

		return permute(straightDBox, builder.toString());
	}

	private String getLeft(String text) {
		return text.substring(0, text.length() / 2);
	}

	private String getRight(String text) {
		return text.substring(text.length() / 2);

	}

	private String swap(String text) {
		return getRight(text) + getLeft(text);
	}
	
	private String generateRoundKey(int roundIndex, String key) {
		String keyLeft = getLeft(key);
		String keyRight = getRight(key);
		keyLeft = leftRotation(numberOfLeftRotation[roundIndex], keyLeft);
		keyRight = leftRotation(numberOfLeftRotation[roundIndex], keyRight);
		return keyLeft + keyRight;
	}
	
	private String leftRotation(int times , String value) {
		return value.substring(times) + value.substring(0, times);
	}
	
	private String [] toStringBlocks (String text) {
		int blocksNumber = (text.length() / 4);
		
		String [] blocks = new String [blocksNumber];
		for(int i = 0 ; i < blocksNumber ; i++) {
			blocks[i] = text.substring(i*4 , i*4 + 4);
		}
		
		if(text.length() % 4 != 0 )
			blocks[blocksNumber - 1] = text.substring((blocksNumber - 1) * 4  , text.length());

		return blocks;
	}
	
	public String concatString(String [] blocks) {
		StringBuilder builder = new StringBuilder();
		for(int i = 0 ; i < blocks.length ; i++) {
			builder.append(blocks[i]);
		}
		return builder.toString();
	}
	
	public String toNormalString(String binary) {
		StringBuilder builder = new StringBuilder();
		if(binary.length() % 16 != 0)
			throw new RuntimeException();
		for(int i = 0  ; i < binary.length() ; i+= 16) {
			builder.append((char)Integer.parseInt(binary.substring(i, i + 16), 2));
		}
		return builder.toString();
	}
	
		
	private String toBinaryString(String text) {
		//System.out.println("text: " + text);
		StringBuilder builder = new StringBuilder();
		for(char ch : text.toCharArray()) {
			builder.append(String.format("%16s", Integer.toBinaryString((int) ch)).replace(' ' , '0'));
		}
		//System.out.println("binary: " + builder.toString());
		return builder.toString();
	}
	
	public class Padding{
		
		private int paddingLength = 0;
		
		private String paddingchars;
		
		
		
		public int getPaddingLength() {
			return paddingLength;
		}

		public String getPaddingchars() {
			return paddingchars;
		}

		public Padding() {
			// TODO Auto-generated constructor stub
		}
		
		private String [] to64BitBlockWithPadding(String text) {
			if(isPaddingNeeded(text))
				text = addPadding(text);
			
			int blocksNumber = (text.length() / 4);
			String [] blocks = new String [blocksNumber];
			
			for(int i = 0 ; i < blocksNumber ; i += 4) {
				blocks[i] = text.substring(i , i + 4);
			}
			
			return blocks;
		}
		
		public String concatStringWithRemovePadding(String [] blocks) {
			StringBuilder builder = new StringBuilder();
			for(int i = 0 ; i < blocks.length - 1 ; i++) {
				builder.append(blocks[i]);
			}
			builder.append(blocks[blocks.length - 1].substring(0, 4 - paddingLength ));
			return builder.toString();
		}
		
		/// NOTE : text parameter should be normal string not binary string
		private boolean isPaddingNeeded(String text) {
			return text.length() % 4 != 0;
		}
		
	 	/// works with padding length field 0-3
		private String addPadding(String text) {
			if(isPaddingNeeded(text)) {
				paddingLength = 4 - text.length() % 4;
				String padding = createPadding();
				//System.out.println("padding: " + padding);
				text += padding;
				//System.out.println("text: " + text);

			}
			return text;
		}
		
		public String createPadding() {
			return paddingchars = repeate("a" , paddingLength);
		}
		
		public String createPadding(int length) {
			return paddingchars = repeate("a" , length);
		}
		
	 	/// works with padding length field 0-3
		private String removePadding(String text) {
			return text.substring(0, text.length() - paddingLength);
		}
		
		private String repeate(String s , int rep) {
			StringBuilder builder = new StringBuilder();
			for(int i = 0 ; i < rep ; i++) {
				builder.append(s);
			}
			return builder.toString();
		}
		
		private String repeate(char ch , int rep) {
			StringBuilder builder = new StringBuilder();
			for(int i = 0 ; i < rep ; i++) {
				builder.append(ch);
			}
			return builder.toString();
		}
		
		
	}
	
/*	public static void main(String[] args) {
		DES_Algorithm a = new DES_Algorithm();
		DES_Algorithm.Padding padding = a.new Padding();
		
		System.out.println(padding.addPadding("moame"));
		System.out.println(padding.removePadding(padding.addPadding("moame")));
	}
	*/
	
//	public static void main(String[] args) {
//		int [] per = {3,4,2,1,6,5};
//		String str = "Moamen";
//		System.out.println(str);
//		System.out.println(BinaryOperations.permute(per, str));
//	}
//	
	
	
	
	
	
	
	
	
	
	

}

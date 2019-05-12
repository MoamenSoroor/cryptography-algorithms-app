package app.core;

public class BinaryOperations {

	public static String generateRandomBinaryBits(int size) {
		StringBuilder builder = new StringBuilder();
		for(int i = 0 ; i < size ; i++) {
			builder.append((int)(Math.random() * 2));
		}
		
		return builder.toString();
		
	}
	
	public static String generateRandom64() {
		return generateRandomBinaryBits(64);
	}
	
	public static String generateRandom32() {
		return generateRandomBinaryBits(32);
	}
	
	public static String addOneBitPadding(String text) {
		int times = text.length() % 64;
		if(times == 0)
			return text;
		StringBuilder builder = new StringBuilder(text);
		if(text.charAt(text.length() - 1) == '0') {
			builder.append('1');
			builder.append(repeate('0', times));
		}
		else if(text.charAt(text.length() - 1) == '1') {
			builder.append('0');
			builder.append(repeate('1', times));
		}
		
		return builder.toString();
	}
	
	public static int numberOf64Blocks(String text) {
		return text.length() / 64 + (text.length() % 64) == 0 ? 0 : 1;
	}
	
	public static int numberToComplete64Block(String text) {
		return text.length() / 64 + (text.length() % 64) == 0 ? 0 : 1;
	}
	
	public static String repeate(char ch , int times) {
		StringBuilder builder = new StringBuilder();
		for(int i = 0 ;i<times ; i++) {
			builder.append(ch);
		}
		return builder.toString();
	}
	public static String repeate(String str , int times) {
		StringBuilder builder = new StringBuilder();
		for(int i = 0 ; i < times ; i++) {
			builder.append(str);
		}
		return builder.toString();
	}
	
	public static String permute(int [] array , String text) {
		StringBuilder builder = new StringBuilder();
		for(int i = 0 ; i < array.length ; i++) {
			builder.append(text.charAt(array[i] - 1));
		}
		return builder.toString();
	}
	
	public static  String binaryXOR(String text1, String text2) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < text1.length(); i++) {
			if (text1.charAt(i) == text2.charAt(i))
				builder.append('0');
			else
				builder.append('1');
		}
		return builder.toString();
	}

	public static  String binaryAND(String text1, String text2) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < text1.length(); i++) {
			char ch1 = text1.charAt(i);
			char ch2 = text2.charAt(i);
			if (ch1 == '1')
				if (ch2 == '1')
					builder.append('1');
				else
					builder.append('0');
			else
				builder.append('0');

		}
		return builder.toString();
	}

	public static  String binaryOR(String text1, String text2) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < text1.length(); i++) {
			char ch1 = text1.charAt(i);
			char ch2 = text2.charAt(i);
			if (ch1 == '0')
				if (ch2 == '0')
					builder.append('0');
				else
					builder.append('1');
			else
				builder.append('1');

		}
		return builder.toString();
	}

	public static  String binaryNOT(String text1) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < text1.length(); i++) {
			builder.append(text1.charAt(i) == '0' ? '1' : '0');
		}
		return builder.toString();
	}
	
	
//	public static void main(String[] args) {
//		// test
//		println("10101010101"); 
//		println("11011100100");
//		println(binaryXOR("10101010101", "11011100100"));
//	}
//	
//	public static void println(String str) {
//		System.out.println(str);
//	}
	
	
	
	
	
	
	
	

}

package app.core;

public class BinaryFormatter {

	private char separator = '_';
	
	private int blockLength = 4;
	
	public BinaryFormatter() {
		super();
	}

	public BinaryFormatter(char separator, int blockLength) {
		super();
		this.separator = separator;
		this.blockLength = blockLength;
	}
	
	public BinaryFormatter(char separator) {
		super();
		this.separator = separator;
		this.blockLength = 4;
	}
	
	public BinaryFormatter(int length) {
		super();
		this.separator = '_';
		this.blockLength = length;
	}
	
	
	public String format(String text) {
		return toFormattedString(text, this.separator, blockLength);
	}
	
	public String unformat(String text) {
		return toOrdinaryString(text);
	}
	
	
	public static  String toFormattedString(String text , char separator , int blockSize) {
		StringBuilder builder = new StringBuilder();
		int counter = 0;
		int length = text.length();
		for(int i = 0 ; i < length ; i++) {
			counter++;
			builder.append(text.charAt(i));
			if(counter == blockSize && i < length - 1) {
				builder.append(separator);
				counter = 0;
			}
		}
		return builder.toString();
	}
	
	public static  String toFormattedString4(String text , char separator) {
		return toFormattedString(text, separator, 4);
	}
	
	public static  String toFormattedString6(String text , char separator) {
		return toFormattedString(text, separator, 6);
	}
	
	public static  String toFormattedString8(String text , char separator) {
		return toFormattedString(text, separator, 8);
	}
	
	public static  String toFormattedString_(String text , int blockLength) {
		return toFormattedString(text, '_', blockLength);
	}
	
	public static  String toFormattedString_4(String text) {
		return toFormattedString(text, '_', 4);
	}
	
	public static  String toFormattedString_6(String text) {
		return toFormattedString(text, '_', 6);
	}
	
	public static  String toFormattedString_8(String text) {
		return toFormattedString(text, '_', 8);
	}
	
	public static boolean isBinaryChar(char ch) {
		return ch == '0' || ch == '1';
	}
	
	public static String toOrdinaryString(String formattedString) {
		StringBuilder builder = new StringBuilder();
		for(char ch : formattedString.toCharArray()) {
			if(ch == '0' || ch == '1')
				builder.append(ch);
		}
		return builder.toString();
	}
	
//	public static void main(String[] args) {
//		System.out.println(toOrdinaryString("1111_0000_1111_0000"));
//		System.out.println(toFormattedString("1111000011110000" , '_' , 4));
//
//	}
	
	

}

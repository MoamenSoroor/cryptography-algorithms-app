package app.core;

public abstract class Algorithm implements Encryption{
		
	protected String algorithmName = "None";
	
	protected Reporting report;
		
	public Algorithm() {
		super();
		
	}
	
	public Algorithm(String name) {
		super();
		algorithmName = name;
		
	}
	
	public String getAlgorithmName() {
		// TODO Auto-generated method stub
		return algorithmName;
	}
	
	public void setAlgorithmName(String algorithmName) {
		this.algorithmName = algorithmName;
	}
	

	public void writeLog(String line) {
		report.writeLog(line);
	}

	public void writeError(String line) {
		report.writeError(line);
	}
	
	public abstract String encrypt(String key, String plainText);

	public abstract String decrypt(String key, String cipherText);

	public void setReport(Reporting reporting) {
		this.report = reporting;
		
	}
	
	public static boolean isCharacterPrintable(char ch) {
		return true;
	}
	

	
	public static String processTextToView(String text) {
		StringBuilder builder = new StringBuilder();
		
		
		
		return builder.toString();
	}
	
	public static String processTextFromView(String text) {
		StringBuilder builder = new StringBuilder();
		
		
		
		return builder.toString();
	}
	
	public static String toUnicode(char ch) {
		return String.format("\\u%04x",(int) ch );
	}
	
	public static char toCharacter(String unicode) {
		//if(unicode.matches("\\u[0-9A-Fa-f]{4}")) {
		return unicode.charAt(0);
		//}
		//else throw new RuntimeException("cannot encode unicode value: "+ unicode);
	}
	
	
	public static int [] toTwoByte(char ch) {
		String binarychar = String.format("%16s", Integer.toBinaryString( (int)ch )).replace(' ', '0');
		int one = Integer.parseInt(binarychar.substring(0, 8),2);
		int two = Integer.parseInt(binarychar.substring(8, 16),2);
		return new int [] {one , two};
		
	}
	
	public static int [] toArrayOfInt(String text) {
		int [] arr = new int [text.length() * 2];
		int index = 0;
		for(char ch : text.toCharArray()) {
			String binarychar = String.format("%16s", Integer.toBinaryString( (int)ch )).replace(' ', '0');
			int one = Integer.parseInt(binarychar.substring(0, 8),2);
			int two = Integer.parseInt(binarychar.substring(8, 16),2);
			arr[index++] = one;
			arr[index++] = two;
		}
		return arr;
	}
	
	public static String toText(int [] ints) {
		StringBuilder builder = new StringBuilder();
		if(ints.length % 2 != 0)
			throw new RuntimeException();
		
		for(int i = 0 ; i < ints.length ; i+=2) {
			String binaryone = String.format("%8s", Integer.toBinaryString( ints[i] )).replace(' ', '0');
			String binarytwo = String.format("%8s", Integer.toBinaryString( ints[i + 1] )).replace(' ', '0');
			
			String binarych = binaryone + binarytwo;
			builder.append((char)Integer.parseInt(binarych, 2));
		}
		return builder.toString();
	}
	
	public static String arrayToString(int [] ints) {
		StringBuilder builder = new StringBuilder();
		builder.append("Int Array:[");
		for( int i : ints)
			builder.append(" " + i);
		builder.append(" ]");
		return builder.toString();
	}
	
	public static String arrayToString(int [] ints , String title) {
		StringBuilder builder = new StringBuilder();
		builder.append(title + ":[");
		for( int i : ints)
			builder.append(" " + i);
		builder.append(" ]");
		return builder.toString();
	}
	
	
	

}

package app.core;

public class CharCoder {

	// 0 - 9 ------- numbers
	// 10 - 35 ----- small charcters
	// 36 - 61 ----- capital characters
	// 62 whitespace  !
	// 63 tab \t      @
	// 64 newLine     #   \r \n \u2028 \u2029 \u0085
	// 65   plus char +
	// 66    minus char -
	

	// total number of characters = 67
	public static int MAX_VALUE = 66;


	public static char getUnicodeValue(int customValue) {
		if (isNumber(customValue))
			return (char) ('0' + customValue);
		else if (isSmallLetter(customValue))
			return (char) ('a' + customValue - 10);
		else if (isCapitalLetter(customValue))
			return (char) ('A' + customValue - 36);
		else if (isSpace(customValue))
			return ' ';
		else if (isTab(customValue))
			return '\t';
		else if (isNewLine(customValue))
			return '\n';
		else if (isPlus(customValue))
			return '+';
		else if (isMinus(customValue))
			return '-';
		else
			throw new NullPointerException();
	}

	public static int getCustomValue(char unicodeValue) {
		if (isNumber(unicodeValue))
			return (char) (unicodeValue - '0');
		else if (isSmallLetter(unicodeValue))
			return (char) (unicodeValue - 'a' + 10);
		else if (isCapitalLetter(unicodeValue))
			return (char) (unicodeValue - 'A' + 36);
		else if (isSpace(unicodeValue))
			return 62;
		else if (isTab(unicodeValue))
			return 63;
		else if (isNewLine(unicodeValue))
			return 64;
		else if (isPlus(unicodeValue))
			return 65;
		else if (isMinus(unicodeValue))
			return 66;
		else
			throw new NullPointerException();
	}

	public static boolean isCharSupported(char unicodeValue) {
		if (isNumber(unicodeValue))
			return true;
		else if (isSmallLetter(unicodeValue))
			return true;
		else if (isCapitalLetter(unicodeValue))
			return true;
		else if (isSpace(unicodeValue))
			return true;
		else if (isTab(unicodeValue))
			return true;
		else if (isNewLine(unicodeValue))
			return true;
		else if (isPlus(unicodeValue))
			return true;
		else if (isMinus(unicodeValue))
			return true;
		else
			return false;

	}

	public static boolean isTextSupported(String unicodeText) {
		for (char ch : unicodeText.toCharArray()) {
			if (!isCharSupported(ch))
				return false;
		}
		return true;
	}

	public static boolean isNumber(int customValue) {
		return customValue >= 0 && customValue <= 9 ? true : false;
	}

	public static boolean isNumber(char unicodeValue) {
		return unicodeValue >= '0' && unicodeValue <= '9' ? true : false;
	}

	public static boolean isCapitalLetter(int customValue) {
		return customValue >= 36 && customValue <= 61 ? true : false;
	}

	public static boolean isCapitalLetter(char unicodeValue) {
		return unicodeValue >= 'A' && unicodeValue <= 'Z' ? true : false;
	}

	public static boolean isSmallLetter(int customValue) {
		return customValue >= 10 && customValue <= 35 ? true : false;
	}

	public static boolean isSmallLetter(char unicodeValue) {
		return unicodeValue >= 'a' && unicodeValue <= 'z' ? true : false;
	}

	public static boolean isNewLine(int customValue) {
		return customValue == 64 ? true : false;
	}

	public static boolean isNewLine(char unicodeValue) {
		return unicodeValue == '\n' || unicodeValue == '\r' || unicodeValue == '\u2028' || unicodeValue == '\u2029'
				|| unicodeValue == '\u0085' ? true : false;
	}

	public static boolean isSpace(int customValue) {
		return customValue == 62 ? true : false;
	}

	public static boolean isSpace(char unicodeValue) {
		return unicodeValue == ' ' ? true : false;
	}

	public static boolean isTab(int customValue) {
		return customValue == 63 ? true : false;
	}

	public static boolean isTab(char unicodeValue) {
		return unicodeValue == '\t' ? true : false;
	}
	
	public static boolean isPlus(int customValue) {
		return customValue == 65 ? true : false;
	}

	public static boolean isPlus(char unicodeValue) {
		return unicodeValue == '+' ? true : false;
	}
	
	public static boolean isMinus(int customValue) {
		return customValue == 66 ? true : false;
	}

	public static boolean isMinus(char unicodeValue) {
		return unicodeValue == '-' ? true : false;
	}
	

	public static String processToView(String text) {
		// 64 newLine \r \n \u2028 \u2029 \u0085
		
		String newLine = System.lineSeparator();
		return text.replace("\r\n", newLine).replace("\n\r", newLine)
				.replace("\r", newLine).replace("\n", newLine)
				.replace("\u2028", newLine).replace("\u2029", newLine)
				.replace("\u0085", newLine);
	}
	
	

	public static String processToEncryption(String text) {
		// 64 newLine \r \n \u2028 \u2029 \u0085
		return text.replace("\r\n", "\n").replace("\n\r", "\n")
				.replace("\r", "\n").replace('\u2028', '\n')
				.replace('\u2029', '\n').replace('\u0085', '\n');

	}
	

}

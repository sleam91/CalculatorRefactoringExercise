package utils;

import java.util.OptionalDouble;

public class Utility {

	public static OptionalDouble getDouble(String str) {
		try {
			return OptionalDouble.of(Double.valueOf(str));
		} catch (NumberFormatException | NullPointerException e) {
			return OptionalDouble.empty();
		}
	}

	public static void holdErrorMessage() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private static final String Digits = "(\\p{Digit}+)";
	private static final String HexDigits = "(\\p{XDigit}+)";
	private static final String Exp = "[eE][+-]?" + Digits;
	private static final String fpRegex = ("[\\x00-\\x20]*" + "[+-]?(" + "NaN|" + "Infinity|" + "(((" + Digits
			+ "(\\.)?(" + Digits + "?)(" + Exp + ")?)|" + "(\\.(" + Digits + ")(" + Exp + ")?)|" + "((" + "(0[xX]"
			+ HexDigits + "(\\.)?)|" + "(0[xX]" + HexDigits + "?(\\.)" + HexDigits + ")" + ")[pP][+-]?" + Digits + "))"
			+ "[fFdD]?))" + "[\\x00-\\x20]*");

	public static boolean isNumericRegex(String str) {
		if (str.matches(fpRegex)) {
			return true;
		}
		return false;
	}

	public static boolean isNumericNoRegex(String str) {
		if (str == null) {
			return false;
		}
		char[] data = str.toCharArray();
		if (data.length <= 0) {
			return false;
		}
		int index = 0;
		if ((data[0] == '-' || data[0] == '+') && data.length > 1) {
			index = 1;
		}
		int decimalPoints = 0;
		for (; index < data.length; index++) {
			if (decimalPoints > 1)
				return false;
			if (data[index] == '.') {
				decimalPoints++;
				continue;
			}

			if (!Character.isDigit(data[index]))
				return false;
		}
		return true;
	}

}

package in.fssa.onlyhomefood.util;

import in.fssa.onlyhomefood.exception.ValidationException;

public class StringUtil {
	
	public static void rejectIfInvaildString(String input, String inputName) throws ValidationException {

		if (input == null || "".equals(input.trim())) {
			throw new ValidationException(inputName.concat(" cannot be null or empty"));
		}
	}
	
	public static boolean isValid(String input) {
		if (input == null || "".equals(input.trim())) {
			return false;
		}
		return true;
	}

	public static boolean isInValid(String input) {
		if (input == null || "".equals(input.trim())) {
			return true;
		}
		return false;
	}

}

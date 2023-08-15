package in.fssa.onlyhomefood.util;

import in.fssa.onlyhomefood.exception.ValidationException;

public class IntUtil {
	
	public static void rejectIfInvalidId(int id, String inputName) throws ValidationException {
		if(id <= 0) {
			throw new ValidationException(inputName.concat(" cannot be zero or below zero"));
		}
	}

//	Patterns
	public static void rejectIfInvalidNumber(long number, String inputNumber) throws ValidationException {
		if (number < 6000000001l || number > 9999999999l) {
			throw new ValidationException(inputNumber.concat(" must start between 6 - 9 and have 10 digits"));
		}
	}
	
	public static void rejectIfInvalidQuantity(int quantity, String inputQuanity) throws ValidationException {
		if (quantity < 1 || quantity > 1000) {
			throw new ValidationException(inputQuanity.concat(" must be below 1000"));
		}
	}
	
}

package in.fssa.onlyhomefood.util;

import in.fssa.onlyhomefood.exception.ValidationException;

public class IntUtil {
	/**
	 * 
	 * @param id
	 * @param inputName
	 * @throws ValidationException
	 */
	public static void rejectIfInvalidId(int id, String inputName) throws ValidationException {
		if (id <= 0) {
			throw new ValidationException(inputName.concat(" cannot be zero or below zero"));
		}
	}
	/**
	 * 
	 * @param size
	 * @throws ValidationException
	 */
	public static void rejectIfInvalidSize(int input, String inputName) throws ValidationException {
		if (input < 0) {
			throw new ValidationException(inputName.concat(" cannot be negative"));
		}
		else if(input > 10) {
			throw new ValidationException(inputName.concat(" limit is exceeded"));
		}
	}

	/**
	 * 
	 * @param number
	 * @param inputNumber
	 * @throws ValidationException
	 */
//	Patterns
	public static void rejectIfInvalidNumber(long number, String inputNumber) throws ValidationException {
		if (number < 6000000001l || number > 9999999999l) {
			throw new ValidationException(inputNumber.concat(" must start between 6 - 9 and have total of 10 digits"));
		}
	}

	/**
	 * 
	 * @param range
	 * @param inputRange
	 * @throws ValidationException
	 */
	public static void rejectIfInvalidRange(int range, String inputRange) throws ValidationException {
		if (range < 1 || range > 1000) {
			throw new ValidationException(inputRange.concat(" range between 1 and 1000"));
		}
	}

}

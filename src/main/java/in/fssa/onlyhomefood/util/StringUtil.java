package in.fssa.onlyhomefood.util;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import in.fssa.onlyhomefood.exception.ValidationException;

public class StringUtil {
	/**
	 * 
	 * @param input
	 * @param inputName
	 * @throws ValidationException
	 */
	public static void rejectIfInvalidString(String input, String inputName) throws ValidationException {
		if (input == null || "".equals(input.trim())) {
			throw new ValidationException(inputName.concat(" cannot be null or empty"));
		}
	}

	/**
	 * 
	 * @param input
	 * @return
	 */
	public static boolean isValid(String input) {
		if (input == null || "".equals(input.trim())) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param input
	 * @return
	 */
	public static boolean isInValid(String input) {
		if (input == null || "".equals(input.trim())) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param name
	 * @throws ValidationException
	 */
//	Patterns
	public static void rejectIfInvalidName(String name, String inputName) throws ValidationException {

		String regex = "^[a-zA-Z]+(?:[' -][a-zA-Z]+){0,29}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(name);
		if (!matcher.matches()) {
			throw new ValidationException(inputName.concat(" must contain only alphabets with minimum 3 letters can have characters like(',-) with a single space and followed by letters"));
		}
	}
	
	/**
	 * 
	 * @param quantityType
	 * @throws ValidationException
	 */
	public static void rejectIfInvalidQuantityType(String quantityType, String inputQuantityType) throws ValidationException {

		String regex = "^[a-zA-Z]+(?:[' -][a-zA-Z]+){0,29}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(quantityType);
		if (!matcher.matches()) {
			throw new ValidationException(inputQuantityType.concat(" must contain only alphabets with minimum 2 letters can have characters like(',-) with a single space and followed by letters"));
		}
	}

	/**
	 * 
	 * @param email
	 * @throws ValidationException
	 */
	public static void rejectIfInvalidEmail(String email) throws ValidationException {

		String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@([a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		if (!matcher.matches()) {
			throw new ValidationException("Invalid Email Id");
		}
	}

	/**
	 * 
	 * @param password
	 * @throws ValidationException
	 */
	public static void rejectIfInvalidPassword(String password) throws ValidationException {

		String regex = "^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z]).*$";
		Pattern patter = Pattern.compile(regex);
		Matcher matcher = patter.matcher(password);
		if (!matcher.matches()) {
			throw new ValidationException("Password does not match the requested pattern");
		}
	}

	/**
	 * 
	 * @param address
	 * @throws ValidationException
	 */
	public static void rejectIfInvalidAddress(String address) throws ValidationException {

		String regex = "[a-zA-Z0-9.,-/()]+(?:[ -][a-zA-Z0-9.,-/()]+)*";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(address);
		if (!matcher.matches()) {
			throw new ValidationException("Invalid Address Pattern");
		}
	}

}

package in.fssa.onlyhomefood.validator;

import in.fssa.onlyhomefood.dao.UserDAO;
import in.fssa.onlyhomefood.exception.PersistanceException;
import in.fssa.onlyhomefood.exception.ValidationException;
import in.fssa.onlyhomefood.model.User;
import in.fssa.onlyhomefood.util.IntUtil;
import in.fssa.onlyhomefood.util.StringUtil;

public class UserValidator {
	/**
	 * 
	 * @param user
	 * @throws ValidationException
	 */
	public static void validate(User user) throws ValidationException {

		if (user == null) {
			throw new ValidationException("User cannot be null");
		}

		StringUtil.rejectIfInvalidString(user.getEmail(), "Email");
		StringUtil.rejectIfInvalidString(user.getName(), "Name");
		StringUtil.rejectIfInvalidString(user.getPassword(), "Password");
		IntUtil.rejectIfInvalidNumber(user.getMobNumber(), "Mobile Number");
		
		
		StringUtil.rejectIfInvalidName(user.getName());
		StringUtil.rejectIfInvalidEmail(user.getEmail());
		StringUtil.rejectIfInvalidPassword(user.getPassword());
	}
	/**
	 * 
	 * @param id
	 * @throws ValidationException
	 */
	public static void isIdValid(int id) throws ValidationException {
		
		try {
			IntUtil.rejectIfInvalidId(id, "User Id");
			UserDAO userDao = new UserDAO();
			userDao.checkIdExists(id);
		} catch (PersistanceException e) {
			throw new ValidationException(e.getMessage());
		}
		
	}

}

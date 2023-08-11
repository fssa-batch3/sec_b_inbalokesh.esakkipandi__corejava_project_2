package in.fssa.onlyhomefood.validator;

import in.fssa.onlyhomefood.dao.UserDAO;
import in.fssa.onlyhomefood.exception.PersistanceException;
import in.fssa.onlyhomefood.exception.ValidationException;
import in.fssa.onlyhomefood.model.User;
import in.fssa.onlyhomefood.util.StringUtil;

public class UserValidator {

	public static void validate(User user) throws ValidationException {

		if (user == null) {
			throw new ValidationException("Invalid User Input");
		}

		StringUtil.rejectIfInvaildString(user.getEmail(), "Email");
		StringUtil.rejectIfInvaildString(user.getName(), "Name");
		StringUtil.rejectIfInvaildString(user.getPassword(), "Password");
		StringUtil.rejectIfInvalidNumber(user.getMobNumber(), "Mobile Number");

		StringUtil.rejectIfInvalidEmail(user.getEmail());
		StringUtil.rejectIfInvalidPassword(user.getPassword());
	}

	public static void isIdValid(int id) throws ValidationException {
		
		try {
			StringUtil.rejectIfInvalidId(id, "User Id");
			UserDAO userDao = new UserDAO();
			userDao.checkIdExists(id);
		} catch (PersistanceException e) {
//			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		}
		
	}

}

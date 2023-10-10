package in.fssa.onlyhomefood.service;

import java.security.NoSuchAlgorithmException;
import java.util.Set;

import in.fssa.onlyhomefood.dao.UserDAO;
import in.fssa.onlyhomefood.exception.PersistenceException;
import in.fssa.onlyhomefood.exception.ServiceException;
import in.fssa.onlyhomefood.exception.ValidationException;
import in.fssa.onlyhomefood.model.User;
import in.fssa.onlyhomefood.util.IntUtil;
import in.fssa.onlyhomefood.util.PasswordUtil;
import in.fssa.onlyhomefood.util.StringUtil;
import in.fssa.onlyhomefood.validator.UserValidator;

public class UserService {
	/**
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public Set<User> getAllUsers() throws ServiceException {
		UserDAO userDAO = new UserDAO();
		Set<User> userList = null;
		try {
			userList = userDAO.findAll();

		} catch (PersistenceException e) {
			throw new ServiceException(e.getMessage());
		}
		return userList;
	}

	/**
	 * 
	 * @param userId
	 * @return
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public User findUserById(int userId) throws ValidationException, ServiceException {

		User user = null;
		try {
			UserValidator.isIdValid(userId);
			UserDAO userDAO = new UserDAO();
			user = userDAO.findById(userId);

		} catch (PersistenceException e) {
			throw new ServiceException(e.getMessage());
		}
		return user;
	}

	/**
	 * 
	 * @param userPhoneNumber
	 * @return
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public User findUserByPhoneNumber(long userPhoneNumber) throws ValidationException, ServiceException {

		User user = null;
		try {
			IntUtil.rejectIfInvalidNumber(userPhoneNumber, "Phone Number");
			UserDAO userDAO = new UserDAO();
			user = userDAO.findByPhoneNumber(userPhoneNumber);

		} catch (PersistenceException e) {
			throw new ServiceException(e.getMessage());
		}
		return user;
	}

	/**
	 * 
	 * @param newUser
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public void createNewUser(User newUser) throws ValidationException, ServiceException {

		try {
			UserValidator.validate(newUser);
			newUser.setPassword(PasswordUtil.encryptPassword(newUser.getPassword()));
			UserDAO userDAO = new UserDAO();
			userDAO.create(newUser);
		} catch (PersistenceException | NoSuchAlgorithmException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * 
	 * @param id
	 * @param updateUser
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public void updateUser(int id, User updateUser) throws ValidationException, ServiceException {

		try {
			UserValidator.isIdValid(id);
			UserValidator.validate(updateUser);
			UserDAO newUserDAO = new UserDAO();
			newUserDAO.update(id, updateUser);

		} catch (PersistenceException e) {
			throw new ServiceException("Failed to " + e.getMessage());
		}
	}

	/**
	 * 
	 * @param id
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public void deleteUser(int id) throws ValidationException, ServiceException {

		try {
			UserValidator.isIdValid(id);
			UserDAO newUserDAO = new UserDAO();
			newUserDAO.delete(id);
		} catch (PersistenceException e) {
			throw new ServiceException("Failed to " + e.getMessage());
		}
	}

	public void loginUser(long number, String password) throws ValidationException, ServiceException {

		try {

			IntUtil.rejectIfInvalidNumber(number, "Mobile Number");
			StringUtil.rejectIfInvalidString(password, "Password");
			StringUtil.rejectIfInvalidPassword(password);

			UserDAO newUserDAO = new UserDAO();

			User user = newUserDAO.findByPhoneNumber(number);

			if (user == null) {
				throw new ServiceException("Invalid Login Credentials");
			}

			String hashPassword = user.getPassword();

			int firstIndex = hashPassword.indexOf('$'); // Find the index of the first "$"
			int secondIndex = hashPassword.indexOf('$', firstIndex + 1); // Find the index of the second "$" starting
																			// from the position after the first one

			if (firstIndex != -1 && secondIndex != -1) {

				String salt = hashPassword.substring(firstIndex + 1, secondIndex); // Extract the substring between the
																					// two "$" characters

				password = PasswordUtil.checkPass(password, salt);

				String genPass = "$" + salt + "$" + password;

				newUserDAO.checkUserCredentials(number, genPass);

			} else {
				System.out.println("The input string does not contain the expected format.");
			}

		} catch (PersistenceException e) {
			throw new ServiceException(e.getMessage());
		}

	}
}

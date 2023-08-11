package in.fssa.onlyhomefood.service;

import in.fssa.onlyhomefood.dao.UserDAO;
import in.fssa.onlyhomefood.exception.PersistanceException;
import in.fssa.onlyhomefood.exception.ServiceException;
import in.fssa.onlyhomefood.exception.ValidationException;
import in.fssa.onlyhomefood.model.User;
import in.fssa.onlyhomefood.validator.UserValidator;

public class UserService {

	public void create(User newUser) throws ValidationException, ServiceException {

		try {
			UserValidator.validate(newUser);
			UserDAO userDao = new UserDAO();
			userDao.create(newUser);
		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(int id, User updateUser) throws ValidationException, ServiceException {

		try {
			UserValidator.validate(updateUser);
			UserValidator.isIdValid(id);
			UserDAO newUserDao = new UserDAO();
			newUserDao.update(id, updateUser);
			
		} catch (PersistanceException e) {
			e.printStackTrace();
			throw new ServiceException("Failed to " + e.getMessage());
		}
	}

	public void delete(int id) throws ValidationException, ServiceException {

		try {
			UserValidator.isIdValid(id);
			UserDAO newUserDao = new UserDAO();
			newUserDao.delete(id);
		} catch (PersistanceException e) {
			e.printStackTrace();
			throw new ServiceException("Failed to " + e.getMessage());
		}
	}
}

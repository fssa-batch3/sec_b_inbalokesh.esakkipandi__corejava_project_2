package in.fssa.onlyhomefood.service;

import java.util.Set;

import in.fssa.onlyhomefood.dao.UserDAO;
import in.fssa.onlyhomefood.exception.PersistenceException;
import in.fssa.onlyhomefood.exception.ServiceException;
import in.fssa.onlyhomefood.exception.ValidationException;
import in.fssa.onlyhomefood.model.User;
import in.fssa.onlyhomefood.validator.UserValidator;

public class UserService {
	/**
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public Set<User> getAll() throws ServiceException {
		UserDAO userDao = new UserDAO();
		Set<User> userList = null;
		try {
			userList = userDao.findAll();
			
		} catch (PersistenceException e) {
			System.out.println(e);
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
	public User findById(int userId) throws ValidationException, ServiceException {
		
		User user = null;
		try {
			UserValidator.isIdValid(userId);
			UserDAO userDao = new UserDAO();
			user = userDao.findById(userId);
			
		} catch (PersistenceException e) {
			e.printStackTrace();
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
	public void create(User newUser) throws ValidationException, ServiceException {

		try {
			UserValidator.validate(newUser);
			UserDAO userDao = new UserDAO();
			userDao.create(newUser);
		} catch (PersistenceException e) {
			System.out.println(e);
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
	public void update(int id, User updateUser) throws ValidationException, ServiceException {

		try {
			UserValidator.validate(updateUser);
			UserValidator.isIdValid(id);
			UserDAO newUserDao = new UserDAO();
			newUserDao.update(id, updateUser);
			
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException("Failed to " + e.getMessage());
		}
	}
	/**
	 * 
	 * @param id
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public void delete(int id) throws ValidationException, ServiceException {

		try {
			UserValidator.isIdValid(id);
			UserDAO newUserDao = new UserDAO();
			newUserDao.delete(id);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException("Failed to " + e.getMessage());
		}
	}
}

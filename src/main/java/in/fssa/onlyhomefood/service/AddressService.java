package in.fssa.onlyhomefood.service;

import java.util.List;

import in.fssa.onlyhomefood.dao.AddressDAO;
import in.fssa.onlyhomefood.exception.PersistenceException;
import in.fssa.onlyhomefood.exception.ServiceException;
import in.fssa.onlyhomefood.exception.ValidationException;
import in.fssa.onlyhomefood.model.Address;
import in.fssa.onlyhomefood.util.IntUtil;
import in.fssa.onlyhomefood.validator.AddressValidator;

public class AddressService {
	AddressDAO addressDAO = new AddressDAO();

	public List<Address> getAllUserAddress(int userid) throws ServiceException, ValidationException {
		List<Address> addressList = null;
		try {
			IntUtil.rejectIfInvalidId(userid, "User Id");
			addressList = addressDAO.findAllById(userid);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return addressList;
	}

	public void createNewAddress(Address newAddress) throws ServiceException, ValidationException {

		AddressValidator.validate(newAddress);
		AddressValidator.checkAddressToCreate(newAddress);

	}

	public void updadeAddress(int addressId, Address editAddress) throws ServiceException, ValidationException {

		int existAddressid;
		Address existAddress = null;
		AddressValidator.validate(editAddress);
		IntUtil.rejectIfInvalidId(addressId, "Address Id");

		try {
			existAddress = addressDAO.findById(addressId);
			AddressValidator.validate(existAddress);
			if (existAddress == null) {
				throw new ServiceException("Address not found");
			}
			existAddressid = addressDAO.findAddressExist(editAddress);
			if (existAddressid == 0) {
				editAddress.setDefaultStatus(existAddress.getDefaultStatus());
				System.out.println(existAddress.getDefaultStatus());
				System.out.println(editAddress.getDefaultStatus());
				addressDAO.create(editAddress);
				addressDAO.update(addressId, false, false);
			} else if (existAddress.getActive() == true) {
				throw new ServiceException("Address already exist");
			} else {
				editAddress.setDefaultStatus(existAddress.getDefaultStatus());
				addressDAO.update(addressId, false, false);
				addressDAO.update(existAddressid, editAddress.getDefaultStatus(), true);
			}

		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	public void deleteAddress(int addressId) throws ValidationException, ServiceException {

		IntUtil.rejectIfInvalidId(addressId, "Address Id");
		try {
			Address address = addressDAO.findById(addressId);
			if (address == null) {
				throw new ServiceException("Address not found");
			}
			addressDAO.delete(addressId);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	public void setAsDefault(int addressId, int userId) throws ValidationException, ServiceException {

		UserService userService = new UserService();

		IntUtil.rejectIfInvalidId(addressId, "Address Id");
		IntUtil.rejectIfInvalidId(userId, "User Id");

		try {
			userService.findUserById(userId);
			Address address = addressDAO.findById(addressId);

			if (address == null) {
				throw new ServiceException("Address not found");

			}
			if (address.getActive() == false) {
				throw new ServiceException("This address has been deleted");
			}

			int id = addressDAO.findDefaultAddress(userId);
			IntUtil.rejectIfInvalidId(id, "Id");
			if (id == addressId) {
				throw new ServiceException("This address is already in the default state");
			}

			addressDAO.update(id, false, true);
			addressDAO.update(addressId, true, true);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());

		}

	}

}

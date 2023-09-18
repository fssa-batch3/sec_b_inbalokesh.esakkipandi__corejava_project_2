package in.fssa.onlyhomefood.validator;

import java.util.List;

import in.fssa.onlyhomefood.dao.AddressDAO;
import in.fssa.onlyhomefood.dao.UserDAO;
import in.fssa.onlyhomefood.exception.PersistenceException;
import in.fssa.onlyhomefood.exception.ValidationException;
import in.fssa.onlyhomefood.model.Address;
import in.fssa.onlyhomefood.util.IntUtil;
import in.fssa.onlyhomefood.util.StringUtil;

public class AddressValidator {

	public static void validate(Address address) throws ValidationException {

		if (address == null) {
			throw new ValidationException("Address cannot be null");
		}

		StringUtil.rejectIfInvalidString(address.getName(), "Name");
		StringUtil.rejectIfInvalidString(address.getLocation(), "Location");
		StringUtil.rejectIfInvalidString(address.getStreetName(), "Street Name Or House Number");
		StringUtil.rejectIfInvalidString(address.getTownName(), "Town Name");
		StringUtil.rejectIfInvalidString(address.getCity(), "City");
		StringUtil.rejectIfInvalidString(address.getState(), "State");
		IntUtil.rejectIfInvalidId(address.getUserId(), "User Id");
		IntUtil.rejectIfInvalidNumber(address.getPhoneNumber(), "Phone Number");

		StringUtil.rejectIfInvalidAddress(address.getLocation());
		StringUtil.rejectIfInvalidAddress(address.getStreetName());
		StringUtil.rejectIfInvalidAddress(address.getTownName());
		StringUtil.rejectIfInvalidName(address.getCity(), "City");
		StringUtil.rejectIfInvalidName(address.getName(), "Name");

		if (!"TN".equals(address.getState())) {
			throw new ValidationException("This state is not available");
		}

		rejectIfInvalidPincode(address.getPinCode());
	}

//	Pattern for Pincode
	public static void rejectIfInvalidPincode(int pincode) throws ValidationException {
		if (pincode < 600001 || pincode > 643253) {
			throw new ValidationException("Invalid Pin code!! we are currently only available in Tamil Nadu");
		}
	}

	public static void checkAddressToCreate(Address address) throws ValidationException {
		AddressDAO addressDAO = new AddressDAO();
		UserDAO userDAO = new UserDAO();
		List<Address> userAddressList = null;

		try {
			validate(address); // Validating the address
			userDAO.checkIdExists(address.getUserId()); // Check Id is present
			userAddressList = addressDAO.findAllById(address.getUserId());

			int size = userAddressList.size(); // Getting the size of the usersAddress

			int addressExistId = addressDAO.findAddressExist(address);
			IntUtil.rejectIfInvalidSize(size); // Size should not be negative and also above the the limit 10

			if (addressExistId == 0) { // Checking whether the address already exists for the user
				if (size == 0) { // Checking the user in creating address for the first time
					address.setDefaultStatus(true); // If the address is created for the first time it is set to be
													// default address
					addressDAO.create(address); // Address created sucessfully
				} else { // else he creates two or more address
					if (address.getDefaultStatus() == true) { // Checks if the default status is true
						int addressId = addressDAO.findDefaultAddress(address.getUserId()); // Gets the address Id of
																							// the user address which is
																							// already in the default
																							// true
						addressDAO.update(addressId, false, true); // Changes the default status to false for the above
																	// address Id
						addressDAO.create(address); // Creates a new Address with the default status true
					} else { // else default status is not true
						addressDAO.create(address); // Creates the address normally
					}

				}
			} else {
				Address ExistingAddress = addressDAO.findById(addressExistId); // Getting the existing address
				validate(ExistingAddress); // validates the address
				if (ExistingAddress.getActive()) { // Checks whether the found address is active or not
					System.out.println(3);
					throw new ValidationException("This address already exist in your book"); // If active it throws an
																								// exception
				} else {
					if (address.getDefaultStatus() == true) { // Checks if the default status is true
						int addressId = addressDAO.findDefaultAddress(address.getUserId()); // Gets the address Id of
																							// the user address which is
																							// already in the default
																							// true
						addressDAO.update(addressId, false, true); // Changes the default status to false for the above
																	// address Id
						addressDAO.update(ExistingAddress.getId(), true, true); // Changes the Existing address as a new
																				// Address with its default status true
					} else { // else if the default status is false
						addressDAO.update(ExistingAddress.getId(), address.getDefaultStatus(), true); // Changes the
																										// Existing
																										// address with
																										// the default
																										// status false
					}
				}

			}
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		}
	}
	

}

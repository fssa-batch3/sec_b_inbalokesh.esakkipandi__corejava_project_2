package in.fssa.onlyhomefood.validator;

import in.fssa.onlyhomefood.dao.AddressDAO;
import in.fssa.onlyhomefood.dao.UserDAO;
import in.fssa.onlyhomefood.exception.PersistenceException;
import in.fssa.onlyhomefood.exception.ValidationException;
import in.fssa.onlyhomefood.model.Address;
import in.fssa.onlyhomefood.model.Order;
import in.fssa.onlyhomefood.util.IntUtil;

public class OrderValidator {
	/**
	 * 
	 * @param orderQuantity
	 * @param inp
	 * @throws ValidationException
	 */
	public static void rejectIfInvalidQuanityOrder(int orderQuantity, String inp) throws ValidationException {
		if (orderQuantity < 1 || orderQuantity > 10) {
			throw new ValidationException(inp.concat(" must be below 10"));
		}
	}

	/**
	 * 
	 * @param order
	 * @throws ValidationException
	 */
	public static void validate(Order order) throws ValidationException {

		if (order == null) {
			throw new ValidationException("Order cannot be null");
		}

//		Validations
		IntUtil.rejectIfInvalidId(order.getCreatedBy(), "User Id");
		IntUtil.rejectIfInvalidId(order.getTotalPrice(), "Price");
		IntUtil.rejectIfInvalidId(order.getDeliveryAddressId(), "Address Id");
	}

	/**
	 * 
	 * @param order
	 * @throws ValidationException
	 */
	public static void checkIdExist(Order order) throws ValidationException {
		try {
			UserDAO userDAO = new UserDAO();
			userDAO.checkIdExists(order.getCreatedBy());
			AddressDAO addressDAO = new AddressDAO();
			Address address = addressDAO.findById(order.getDeliveryAddressId());
			if (address == null) {
				throw new ValidationException("Address not found");
			}
		} catch (PersistenceException e) {
			throw new ValidationException(e.getMessage());
		}
	}

}

package in.fssa.onlyhomefood.validator;

import in.fssa.onlyhomefood.dao.OrderDAO;
import in.fssa.onlyhomefood.dao.ProductDAO;
import in.fssa.onlyhomefood.dao.UserDAO;
import in.fssa.onlyhomefood.exception.PersistenceException;
import in.fssa.onlyhomefood.exception.ValidationException;
import in.fssa.onlyhomefood.model.OrderedItems;
import in.fssa.onlyhomefood.util.IntUtil;

public class OrderedItemsValidator {

	public static void validate(OrderedItems orderItems) throws ValidationException {

		if (orderItems == null) {
			throw new ValidationException("Items cannot be null");
		}

		if (orderItems.getDeliveryTime() == null) {
			throw new ValidationException("Please select the delivery time");
		}

		if (orderItems.getOrderStatus() == null) {
			throw new ValidationException("Order status cannot be null");
		}

//		Validations
		IntUtil.rejectIfInvalidId(orderItems.getProductId(), "Product Id");
		IntUtil.rejectIfInvalidId(orderItems.getUserId(), "User Id");
		IntUtil.rejectIfInvalidId(orderItems.getQuantityOrdered(), "Ouantity ordered");

//		Patterns
		IntUtil.rejectIfInvalidSize(orderItems.getQuantityOrdered(), "Item quantity");
	}
	
	public static void checkIdExist(OrderedItems orderItems) throws ValidationException {
		try {
			UserDAO userDAO = new UserDAO();
			userDAO.checkIdExists(orderItems.getUserId());
			ProductDAO productDAO = new ProductDAO();
			productDAO.checkIdExists(orderItems.getProductId());
		} catch (PersistenceException e) {
			throw new ValidationException(e.getMessage());
		}
	}

	public static void checkOrderIdExist(int orderId) throws ValidationException {
		try {
			OrderDAO orderDAO = new OrderDAO();
			orderDAO.checkIdExists(orderId);
		} catch (PersistenceException e) {
			throw new ValidationException(e.getMessage());
		}
	}
}

package in.fssa.onlyhomefood.validator;

import in.fssa.onlyhomefood.dao.ProductDAO;
import in.fssa.onlyhomefood.dao.UserDAO;
import in.fssa.onlyhomefood.exception.PersistanceException;
import in.fssa.onlyhomefood.exception.ValidationException;
import in.fssa.onlyhomefood.model.Order;
import in.fssa.onlyhomefood.util.IntUtil;
import in.fssa.onlyhomefood.util.StringUtil;

public class OrderValidator {
	
	public static void rejectIfInvalidQuanityOrder(int orderQuantity, String inp) throws ValidationException {
		if (orderQuantity < 1 || orderQuantity > 10) {
			throw new ValidationException(inp.concat(" must be below 10"));
		}
	}
	
	public static void validate(Order order) throws ValidationException {
		
		if (order == null) {
			throw new ValidationException("Order cannot be null");
		}
		if (order.getDelivery_time() == null) {
			throw new ValidationException("Invalid Order deliveryTime");
		}
		if (order.getOrder_status() == null) {
			throw new ValidationException("Invalid Order status");
		}
		
//		Validations
		StringUtil.rejectIfInvalidString(order.getAddress(), "Address");
		StringUtil.rejectIfInvalidAddress(order.getAddress());
		IntUtil.rejectIfInvalidId(order.getQuantity(), "Quantity");
		IntUtil.rejectIfInvalidId(order.getCreated_by(), "User Id");
		IntUtil.rejectIfInvalidId(order.getTotal_price(), "Total Price");
		IntUtil.rejectIfInvalidId(order.getProduct_id(), "Product Id");
		
//		Patterns
		rejectIfInvalidQuanityOrder(order.getQuantity(), "Quantity");
		
		
	}
	
	public static void checkIdExist(Order order) throws ValidationException {
		try {
			ProductDAO productDao = new ProductDAO();
			productDao.checkIdExists(order.getProduct_id());
			UserDAO userDao = new UserDAO();
			userDao.checkIdExists(order.getCreated_by());
		} catch (PersistanceException e) {
			throw new ValidationException(e.getMessage());
		}
		
	}

}

package in.fssa.onlyhomefood.validator;

import in.fssa.onlyhomefood.exception.ValidationException;
import in.fssa.onlyhomefood.model.Order;
import in.fssa.onlyhomefood.util.IntUtil;
import in.fssa.onlyhomefood.util.StringUtil;

public class OrderValidator {
	
	public static void validate(Order order) throws ValidationException {
		
		if (order == null) {
			throw new ValidationException("Invalid Order Input");
		}
		if (order.getDelivery_time() == null) {
			throw new ValidationException("Invalid Order deliveryTime");
		}
		if (order.getOrder_status() == null) {
			throw new ValidationException("Invalid Order status");
		}
		
//		Validations
		StringUtil.rejectIfInvalidString(order.getAddress(), "Address");
		StringUtil.rejectIfInvalidName(order.getAddress());
		
		IntUtil.rejectIfInvalidId(order.getQuantity(), "Quantity");
		IntUtil.rejectIfInvalidId(order.getTotal_price(), "Total Price");
		IntUtil.rejectIfInvalidId(order.getProduct_id(), "Product Id");
		
//		Patterns
		IntUtil.rejectIfInvalidQuanityOrder(order.getQuantity(), "Quantity");
		
	}

}

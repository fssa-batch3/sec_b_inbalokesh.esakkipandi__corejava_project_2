package in.fssa.onlyhomefood.service;

import java.util.List;
import java.util.Set;

import in.fssa.onlyhomefood.dao.OrderDAO;
import in.fssa.onlyhomefood.exception.PersistenceException;
import in.fssa.onlyhomefood.exception.ServiceException;
import in.fssa.onlyhomefood.exception.ValidationException;
import in.fssa.onlyhomefood.model.Order;
import in.fssa.onlyhomefood.model.OrderedItems;
import in.fssa.onlyhomefood.util.IntUtil;
import in.fssa.onlyhomefood.validator.OrderValidator;
import in.fssa.onlyhomefood.validator.OrderedItemsValidator;
import in.fssa.onlyhomefood.validator.UserValidator;

public class OrderService {
	/**
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public Set<Order> getAllOrders() throws ServiceException {

		Set<Order> orderList = null;
		try {
			OrderDAO orderDAO = new OrderDAO();
			orderList = orderDAO.findAll();

		} catch (PersistenceException e) {
			throw new ServiceException(e.getMessage());
		}

		return orderList;

	}

	/**
	 * 
	 * @param order
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public void createNewOrder(Order order, List<OrderedItems> orderedItems) throws ValidationException, ServiceException {

		int orderId;
		try {
			OrderValidator.validate(order);
			OrderValidator.checkIdExist(order);
			OrderDAO orderDAO = new OrderDAO();
			
			for(OrderedItems items : orderedItems) {
				OrderedItemsValidator.validate(items);
				OrderedItemsValidator.checkIdExist(items);
			}

			orderId = orderDAO.create(order);
			IntUtil.rejectIfInvalidId(orderId, "Order Id");
			for(OrderedItems items : orderedItems) {
				items.setOrderId(orderId);
				OrderedItemsValidator.checkOrderIdExist(orderId);
			}
			
			OrderItemService orderItemService = new OrderItemService();
			
			for(OrderedItems itemsList : orderedItems) {
				orderItemService.createOrderedItems(itemsList);
			}
			
			System.out.println("Order Created Successfully");

		} catch (PersistenceException e) {
			
			throw new ServiceException(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param userId
	 * @return
	 * @throws ServiceException
	 * @throws ValidationException
	 */
	public List<Order> getAllUserOrders(int userId) throws ServiceException, ValidationException {
		List<Order> orderList = null;
		try {
			IntUtil.rejectIfInvalidId(userId, "User Id");
			UserValidator.isIdValid(userId);
			OrderDAO orderDAO = new OrderDAO();
			orderList = orderDAO.findAllById(userId);

		} catch (PersistenceException e) {
			throw new ServiceException(e.getMessage());
		}

		return orderList;
	}

}

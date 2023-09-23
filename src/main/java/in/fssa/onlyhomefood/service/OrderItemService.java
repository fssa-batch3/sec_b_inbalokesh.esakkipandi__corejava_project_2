package in.fssa.onlyhomefood.service;

import java.util.List;

import in.fssa.onlyhomefood.dao.OrderedItemsDAO;
import in.fssa.onlyhomefood.exception.PersistenceException;
import in.fssa.onlyhomefood.exception.ServiceException;
import in.fssa.onlyhomefood.exception.ValidationException;
import in.fssa.onlyhomefood.model.OrderedItems;
import in.fssa.onlyhomefood.model.OrderedItems.OrderStatus;
import in.fssa.onlyhomefood.util.IntUtil;
import in.fssa.onlyhomefood.validator.OrderedItemsValidator;
import in.fssa.onlyhomefood.validator.UserValidator;

public class OrderItemService {

	/**
	 * 
	 * @param userId
	 * @return
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public List<OrderedItems> getAllUserOrders(int userId) throws ValidationException, ServiceException {

		OrderedItemsDAO orderItemsDAO = new OrderedItemsDAO();
		List<OrderedItems> itemsList = null;
		try {
			IntUtil.rejectIfInvalidId(userId, "User Id");
			UserValidator.isIdValid(userId);
			itemsList = orderItemsDAO.findAllById(userId);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return itemsList;
	}

	public List<OrderedItems> getAllOrdersById(int orderId) throws ValidationException, ServiceException {

		OrderedItemsDAO orderItemsDAO = new OrderedItemsDAO();
		List<OrderedItems> itemsList = null;
		try {
			IntUtil.rejectIfInvalidId(orderId, "Order Id");
			orderItemsDAO.findAllById(orderId);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return itemsList;
	}

	public void createOrderedItems(OrderedItems orderedItems) throws ServiceException, ValidationException {

		OrderedItemsDAO orderItemsDAO = new OrderedItemsDAO();

		try {
			OrderedItemsValidator.validate(orderedItems);
			OrderedItemsValidator.checkIdExist(orderedItems);
			orderItemsDAO.create(orderedItems);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	public void updateStatus(int id, OrderStatus status) throws ValidationException, ServiceException {

		OrderedItemsDAO orderItemsDAO = new OrderedItemsDAO();

		try {
			IntUtil.rejectIfInvalidId(id, "Item Id");
			orderItemsDAO.update(id, status);
			;
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
	
}

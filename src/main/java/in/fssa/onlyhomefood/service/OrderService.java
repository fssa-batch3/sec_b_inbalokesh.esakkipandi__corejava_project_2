package in.fssa.onlyhomefood.service;

import java.util.Set;

import in.fssa.onlyhomefood.dao.OrderDAO;
import in.fssa.onlyhomefood.exception.PersistenceException;
import in.fssa.onlyhomefood.exception.ServiceException;
import in.fssa.onlyhomefood.exception.ValidationException;
import in.fssa.onlyhomefood.model.Order;
import in.fssa.onlyhomefood.validator.OrderValidator;

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
			
		}catch(PersistenceException e) {
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
	public void createNewOrder (Order order) throws ValidationException, ServiceException {
		
		try {
			OrderValidator.validate(order);
			OrderValidator.checkIdExist(order);
			OrderDAO orderDAO = new OrderDAO();
			orderDAO.create(order);
			
		} catch (PersistenceException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	

}

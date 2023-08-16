package in.fssa.onlyhomefood.service;

import java.util.Set;

import in.fssa.onlyhomefood.dao.OrderDAO;
import in.fssa.onlyhomefood.exception.PersistanceException;
import in.fssa.onlyhomefood.exception.ServiceException;
import in.fssa.onlyhomefood.exception.ValidationException;
import in.fssa.onlyhomefood.model.Order;
import in.fssa.onlyhomefood.validator.OrderValidator;

public class OrderService {
	
	public Set<Order> getAll() throws ServiceException {
		
		Set<Order> orderList = null;
		try {
			OrderDAO orderDao = new OrderDAO();
			orderList = orderDao.findAll();
			
		}catch(PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}
		
		return orderList;
		
	}
	
	
	public void create (Order order) throws ValidationException, ServiceException {
		
		try {
			OrderValidator.validate(order);
			OrderDAO orderDao = new OrderDAO();
			orderDao.create(order);
			
		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	

}

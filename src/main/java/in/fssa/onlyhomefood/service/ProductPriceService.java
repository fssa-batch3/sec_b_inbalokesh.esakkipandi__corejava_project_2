package in.fssa.onlyhomefood.service;

import java.sql.Timestamp;
import java.util.Set;

import in.fssa.onlyhomefood.dao.ProductPriceDAO;
import in.fssa.onlyhomefood.exception.PersistanceException;
import in.fssa.onlyhomefood.exception.ServiceException;
import in.fssa.onlyhomefood.exception.ValidationException;
import in.fssa.onlyhomefood.model.ProductPrice;
import in.fssa.onlyhomefood.util.IntUtil;
import in.fssa.onlyhomefood.validator.ProductValidator;

public class ProductPriceService {
	
//	FindAllPrice
	public Set<ProductPrice> getAll() throws ServiceException {
		ProductPriceDAO productPriceDao = new ProductPriceDAO();
		Set<ProductPrice> productPriceList = null;
		try {
			productPriceList = productPriceDao.findAll();
			
		} catch (PersistanceException e) {
			System.out.println(e);
			throw new ServiceException(e.getMessage());
		}
		return productPriceList;
	}
	
	public void create(Timestamp uDate, int product_id, int price) throws ValidationException, ServiceException {
				
		try {
			IntUtil.rejectIfInvalidId(price, "Price");
			ProductValidator.isIdValid(product_id);	
			ProductPriceDAO productPriceDao = new ProductPriceDAO();	
			productPriceDao.create(uDate, product_id, price);
				
		}catch(PersistanceException e) {
			throw new ServiceException(e.getMessage());
			
		}	
	}
	
	public Timestamp getDate(int product_id) throws ValidationException, ServiceException {
		Timestamp d = null;
	try {
			ProductValidator.isIdValid(product_id);
			ProductPriceDAO productPriceDao = new ProductPriceDAO();	
			d = productPriceDao.getDate(product_id);
				
		}catch(PersistanceException e) {
			throw new ServiceException(e.getMessage());
			
		}	
		return d;
	}
	
	public void update(Timestamp uDate, int product_id, int price) throws ValidationException, ServiceException {
		
		try {
			IntUtil.rejectIfInvalidId(price, "Price");
			IntUtil.rejectIfInvalidQuantity(price, "Price");
			ProductValidator.isIdValid(product_id);	
			ProductPriceDAO productPriceDao = new ProductPriceDAO();
			productPriceDao.update(uDate, product_id);
			productPriceDao.create(uDate, product_id, price);
				
		}catch(PersistanceException e) {
			throw new ServiceException(e.getMessage());
			
		}
		
	}

}

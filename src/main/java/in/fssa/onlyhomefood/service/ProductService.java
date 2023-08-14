package in.fssa.onlyhomefood.service;

import java.sql.Timestamp;
import java.util.Set;

import in.fssa.onlyhomefood.dao.ProductDAO;
import in.fssa.onlyhomefood.exception.PersistanceException;
import in.fssa.onlyhomefood.exception.ServiceException;
import in.fssa.onlyhomefood.exception.ValidationException;
import in.fssa.onlyhomefood.model.Product;
import in.fssa.onlyhomefood.validator.ProductValidator;

public class ProductService {
	
	public Set<Product> getAll() throws ServiceException {
		ProductDAO productDao = new ProductDAO();
		Set<Product> productList = null;
		try {
			productList = productDao.findAll();
			
		} catch (PersistanceException e) {
			System.out.println(e);
			throw new ServiceException(e.getMessage());
		}
		return productList;
	}

	public Product findById(int productId) throws ValidationException, ServiceException {
		
		Product product = null;
		try {
			ProductValidator.isIdValid(productId);
			ProductDAO productDao = new ProductDAO();
			product = productDao.findById(productId);
			
		} catch (PersistanceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return product;
	}
	
	public void create (Product product, int price) throws ValidationException, ServiceException {
		
		int generatedId = -1;
		Timestamp d = null;
		
		try {
			ProductDAO productDao = new ProductDAO();
			ProductPriceService productPriceService = new ProductPriceService();
			ProductValidator.validate(product);
			generatedId = productDao.create(product);
			d = productPriceService.getDate(generatedId);
			productPriceService.create(d, generatedId, price);
			
		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}
	}
}

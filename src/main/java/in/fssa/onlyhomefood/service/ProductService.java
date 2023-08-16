package in.fssa.onlyhomefood.service;

import java.sql.Timestamp;
import java.util.Set;

import in.fssa.onlyhomefood.dao.ProductDAO;
import in.fssa.onlyhomefood.dao.ProductPriceDAO;
import in.fssa.onlyhomefood.exception.PersistanceException;
import in.fssa.onlyhomefood.exception.ServiceException;
import in.fssa.onlyhomefood.exception.ValidationException;
import in.fssa.onlyhomefood.model.Product;
import in.fssa.onlyhomefood.validator.ProductValidator;

public class ProductService {
	
	public Set<Product> getAll() throws ServiceException {
		
		Set<Product> productList = null;
		try {
			ProductDAO productDao = new ProductDAO();
			ProductPriceDAO priceDao = new ProductPriceDAO();
			productList = productDao.findAll();
			
			for(Product list : productList) {
				int price = priceDao.getPrices(list.getId());
				list.setPrice(price);
			}
			
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
			ProductPriceDAO priceDao = new ProductPriceDAO();
			product = productDao.findById(productId);
			int price = priceDao.getPrices(productId);
			product.setPrice(price);
			
		} catch (PersistanceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return product;
	}
	
	public void create (Product product) throws ValidationException, ServiceException {
		
		int generatedId = -1;
		Timestamp d = null;
		
		try {
			ProductDAO productDao = new ProductDAO();
			ProductPriceService productPriceService = new ProductPriceService();
			ProductValidator.validate(product);
			productDao.checkNameIsPresent(product);
			generatedId = productDao.create(product);
			d = productPriceService.getDate(generatedId);
			productPriceService.create(d, generatedId, product.getPrice());
			
		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public void update (int id, Product product) throws ValidationException, ServiceException {
		
		Timestamp d = null;
		try {
			ProductDAO productDao = new ProductDAO();
			ProductPriceService productPriceService = new ProductPriceService();
			
//			Vaidate id and product
			ProductValidator.validate(product);
			ProductValidator.isIdValid(id);
			
//			Update details
			productDao.update(id, product);
			d = productPriceService.getDate(id);
			productPriceService.update(d, id, product.getPrice());
			
		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}
		
	}
	
	public void delete(int id) throws ValidationException, ServiceException {
		
		try {
			ProductDAO productDao = new ProductDAO();
			ProductValidator.isIdValid(id);
			productDao.delete(id);
		}catch(PersistanceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
}

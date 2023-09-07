package in.fssa.onlyhomefood.service;

import java.sql.Timestamp;
import java.util.Set;

import in.fssa.onlyhomefood.dao.ProductDAO;
import in.fssa.onlyhomefood.dao.ProductPriceDAO;
import in.fssa.onlyhomefood.exception.PersistenceException;
import in.fssa.onlyhomefood.exception.ServiceException;
import in.fssa.onlyhomefood.exception.ValidationException;
import in.fssa.onlyhomefood.model.Product;
import in.fssa.onlyhomefood.validator.ProductValidator;

public class ProductService {
	/**
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public Set<Product> getAllProducts() throws ServiceException {

		Set<Product> productList = null;
		try {
			ProductDAO productDAO = new ProductDAO();
			ProductPriceDAO priceDAO = new ProductPriceDAO();
			productList = productDAO.findAll();

			for (Product list : productList) {
				int price = priceDAO.getPrice(list.getId());
				list.setPrice(price);
			}

		} catch (PersistenceException e) {
			throw new ServiceException(e.getMessage());
		}
		return productList;
	}

	/**
	 * 
	 * @param productId
	 * @return
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public Product findProductById(int productId) throws ValidationException, ServiceException {

		Product product = null;
		try {
			ProductValidator.isIdValid(productId);
			ProductDAO productDAO = new ProductDAO();
			ProductPriceDAO priceDAO = new ProductPriceDAO();
			product = productDAO.findById(productId);
			int price = priceDAO.getPrice(productId);
			product.setPrice(price);

		} catch (PersistenceException e) {
			throw new ServiceException(e.getMessage());
		}
		return product;
	}

	/**
	 * 
	 * @param product
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public void createNewProduct(Product product) throws ValidationException, ServiceException {

		int generatedId = -1;
		Timestamp d = null;

		try {
			ProductDAO productDAO = new ProductDAO();
			ProductPriceService productPriceService = new ProductPriceService();
			ProductValidator.validate(product);
			productDAO.checkNameIsPresent(product);
			generatedId = productDAO.create(product);
			d = productPriceService.getModifiedDate(generatedId);
			productPriceService.createPrice(d, generatedId, product.getPrice());

		} catch (PersistenceException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param id
	 * @param product
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public void updateProduct(int id, Product product) throws ValidationException, ServiceException {

		Timestamp d = null;
		try {
			ProductDAO productDAO = new ProductDAO();
			ProductPriceService productPriceService = new ProductPriceService();

//			Vaidate id and product
			ProductValidator.validate(product);
			ProductValidator.isIdValid(id);

//			Update details
			productDAO.update(id, product);
			d = productPriceService.getModifiedDate(id);
			productPriceService.updatePrice(d, id, product.getPrice());

		} catch (PersistenceException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param id
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public void deleteProduct(int id) throws ValidationException, ServiceException {

		try {
			ProductDAO productDAO = new ProductDAO();
			ProductValidator.isIdValid(id);
			productDAO.delete(id);
		} catch (PersistenceException e) {
			throw new ServiceException(e.getMessage());
		}
	}
}

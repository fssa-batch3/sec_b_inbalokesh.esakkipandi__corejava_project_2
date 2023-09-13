package in.fssa.onlyhomefood.service;

import java.sql.Timestamp;
import java.util.Set;

import in.fssa.onlyhomefood.dao.ProductPriceDAO;
import in.fssa.onlyhomefood.exception.PersistenceException;
import in.fssa.onlyhomefood.exception.ServiceException;
import in.fssa.onlyhomefood.exception.ValidationException;
import in.fssa.onlyhomefood.model.ProductPrice;
import in.fssa.onlyhomefood.util.IntUtil;
import in.fssa.onlyhomefood.validator.ProductValidator;

public class ProductPriceService {
	/**
	 * 
	 * @return
	 * @throws ServiceException
	 */
//	FindAllPrice
	public Set<ProductPrice> getAllProductPrice() throws ServiceException {
		ProductPriceDAO productPriceDAO = new ProductPriceDAO();
		Set<ProductPrice> productPriceList = null;
		try {
			productPriceList = productPriceDAO.findAll();

		} catch (PersistenceException e) {
			throw new ServiceException(e.getMessage());
		}
		return productPriceList;
	}

	/**
	 * 
	 * @param uDate
	 * @param product_id
	 * @param price
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public void createPrice(Timestamp uDate, int product_id, int price) throws ValidationException, ServiceException {

		try {
			IntUtil.rejectIfInvalidId(price, "Price");
			ProductValidator.isIdValid(product_id);
			ProductPriceDAO productPriceDAO = new ProductPriceDAO();
			productPriceDAO.create(uDate, product_id, price);

		} catch (PersistenceException e) {
			throw new ServiceException(e.getMessage());

		}
	}

	/**
	 * 
	 * @param product_id
	 * @return
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public Timestamp getModifiedDate(int product_id) throws ValidationException, ServiceException {
		Timestamp date = null;
		try {
			ProductValidator.isIdValid(product_id);
			ProductPriceDAO productPriceDAO = new ProductPriceDAO();
			date = productPriceDAO.getDate(product_id);

		} catch (PersistenceException e) {
			throw new ServiceException(e.getMessage());

		}
		return date;
	}

	/**
	 * 
	 * @param uDate
	 * @param product_id
	 * @param price
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public void updatePrice(Timestamp uDate, int product_id, int price) throws ValidationException, ServiceException {

		try {
			IntUtil.rejectIfInvalidId(price, "Price");
			IntUtil.rejectIfInvalidRange(price, "Set Price");
			ProductValidator.isIdValid(product_id);
			ProductPriceDAO productPriceDAO = new ProductPriceDAO();
			productPriceDAO.update(uDate, product_id);
			productPriceDAO.create(uDate, product_id, price);

		} catch (PersistenceException e) {
			throw new ServiceException(e.getMessage());
		}
	}

}

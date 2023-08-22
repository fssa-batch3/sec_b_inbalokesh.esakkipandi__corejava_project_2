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
	public Set<ProductPrice> getAll() throws ServiceException {
		ProductPriceDAO productPriceDao = new ProductPriceDAO();
		Set<ProductPrice> productPriceList = null;
		try {
			productPriceList = productPriceDao.findAll();

		} catch (PersistenceException e) {
			System.out.println(e);
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
	public void create(Timestamp uDate, int product_id, int price) throws ValidationException, ServiceException {

		try {
			IntUtil.rejectIfInvalidId(price, "Price");
			ProductValidator.isIdValid(product_id);
			ProductPriceDAO productPriceDao = new ProductPriceDAO();
			productPriceDao.create(uDate, product_id, price);

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
	public Timestamp getDate(int product_id) throws ValidationException, ServiceException {
		Timestamp d = null;
		try {
			ProductValidator.isIdValid(product_id);
			ProductPriceDAO productPriceDao = new ProductPriceDAO();
			d = productPriceDao.getDate(product_id);

		} catch (PersistenceException e) {
			throw new ServiceException(e.getMessage());

		}
		return d;
	}

	/**
	 * 
	 * @param uDate
	 * @param product_id
	 * @param price
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public void update(Timestamp uDate, int product_id, int price) throws ValidationException, ServiceException {

		try {
			IntUtil.rejectIfInvalidId(price, "Price");
			IntUtil.rejectIfInvalidRange(price, "Set Price");
			ProductValidator.isIdValid(product_id);
			ProductPriceDAO productPriceDao = new ProductPriceDAO();
			productPriceDao.update(uDate, product_id);
			productPriceDao.create(uDate, product_id, price);

		} catch (PersistenceException e) {
			throw new ServiceException(e.getMessage());
		}
	}

}

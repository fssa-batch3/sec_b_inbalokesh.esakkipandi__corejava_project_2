package in.fssa.onlyhomefood.validator;

import in.fssa.onlyhomefood.dao.ProductPriceDAO;
import in.fssa.onlyhomefood.exception.PersistanceException;
import in.fssa.onlyhomefood.exception.ValidationException;
import in.fssa.onlyhomefood.model.ProductPrice;
import in.fssa.onlyhomefood.util.IntUtil;


public class ProductPriceValidator {
	
	public static void validate(ProductPrice productPrice) throws ValidationException {
		
		if (productPrice == null) {
			throw new ValidationException("Price table cannot be null");
		}
		
		IntUtil.rejectIfInvalidId(productPrice.getPrice(), "Price");
		
	}
	
	public static void isIdValid(int id) throws ValidationException {
		
		try {
			IntUtil.rejectIfInvalidId(id, "Product Id");
			ProductPriceDAO productPriceDao = new ProductPriceDAO();
			productPriceDao.checkIdExists(id);
		} catch (PersistanceException e) {
			throw new ValidationException(e.getMessage());
		}
		
	}
	
	

}

package in.fssa.onlyhomefood.validator;

import in.fssa.onlyhomefood.dao.ProductDAO;
import in.fssa.onlyhomefood.exception.PersistanceException;
import in.fssa.onlyhomefood.exception.ValidationException;
import in.fssa.onlyhomefood.model.Product;
import in.fssa.onlyhomefood.util.IntUtil;
import in.fssa.onlyhomefood.util.StringUtil;

public class ProductValidator {
	
	public static void validate(Product product) throws ValidationException {
		
		if (product == null) {
			throw new ValidationException("Product cannot be null");
		}
		
//		Validations
		StringUtil.rejectIfInvalidString(product.getName(), "Food Name");
		StringUtil.rejectIfInvalidString(product.getType(), "Food Type");
		StringUtil.rejectIfInvalidString(product.getQuantityType(), "Quantity type");
		
//		Patterns
		StringUtil.rejectIfInvalidName(product.getName());
		StringUtil.rejectIfInvalidName(product.getType());
		StringUtil.rejectIfInvalidName(product.getQuantityType());
		IntUtil.rejectIfInvalidRange(product.getQuantity(), "Set Quantity");
		IntUtil.rejectIfInvalidRange(product.getPrice(), "Set Price");
	}
	
	public static void isIdValid(int id) throws ValidationException {
		
		try {
			IntUtil.rejectIfInvalidId(id, "Product Id");
			ProductDAO productDao = new ProductDAO();
			productDao.checkIdExists(id);
		} catch (PersistanceException e) {
			throw new ValidationException(e.getMessage());
		}
		
	}

}

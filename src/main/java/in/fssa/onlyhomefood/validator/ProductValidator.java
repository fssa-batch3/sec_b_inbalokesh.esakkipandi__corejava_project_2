package in.fssa.onlyhomefood.validator;

import in.fssa.onlyhomefood.dao.ProductDAO;
import in.fssa.onlyhomefood.exception.PersistenceException;
import in.fssa.onlyhomefood.exception.ValidationException;
import in.fssa.onlyhomefood.model.Product;
import in.fssa.onlyhomefood.util.IntUtil;
import in.fssa.onlyhomefood.util.StringUtil;

public class ProductValidator {
	/**
	 * 
	 * @param product
	 * @throws ValidationException
	 */
	public static void validate(Product product) throws ValidationException {
		
		if (product == null) {
			throw new ValidationException("Product cannot be null");
		}
		
//		Validations
		StringUtil.rejectIfInvalidString(product.getName(), "Food Name");
		StringUtil.rejectIfInvalidString(product.getType(), "Food Type");
		StringUtil.rejectIfInvalidString(product.getQuantityType(), "Quantity type");
		StringUtil.rejectIfInvalidString(product.getImage(), "Image url");
		
//		Patterns
		StringUtil.rejectIfInvalidName(product.getName(), "Food Name");
		StringUtil.rejectIfInvalidName(product.getType(), "Food type");
		StringUtil.rejectIfInvalidQuantityType(product.getQuantityType(), "Quantity type");
		IntUtil.rejectIfInvalidRange(product.getQuantity(), "Set Quantity");
		IntUtil.rejectIfInvalidRange(product.getPrice(), "Set Price");
	}
	/**
	 * 
	 * @param id
	 * @throws ValidationException
	 */
	public static void isIdValid(int id) throws ValidationException {
		
		try {
			IntUtil.rejectIfInvalidId(id, "Product Id");
			ProductDAO productDAO = new ProductDAO();
			productDAO.checkIdExists(id);
		} catch (PersistenceException e) {
			throw new ValidationException(e.getMessage());
		}
		
	}

}

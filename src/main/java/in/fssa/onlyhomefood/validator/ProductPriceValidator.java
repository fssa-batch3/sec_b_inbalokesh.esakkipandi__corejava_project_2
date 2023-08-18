package in.fssa.onlyhomefood.validator;

import in.fssa.onlyhomefood.exception.ValidationException;
import in.fssa.onlyhomefood.model.ProductPrice;
import in.fssa.onlyhomefood.util.IntUtil;


public class ProductPriceValidator {
	/**
	 * 
	 * @param productPrice
	 * @throws ValidationException
	 */
	public static void validate(ProductPrice productPrice) throws ValidationException {
		
		if (productPrice == null) {
			throw new ValidationException("Price table cannot be null");
		}
		
		IntUtil.rejectIfInvalidId(productPrice.getPrice(), "Price");
		IntUtil.rejectIfInvalidRange(productPrice.getPrice(), "Set Price");
		
	}

}

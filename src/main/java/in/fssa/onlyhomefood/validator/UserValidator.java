package in.fssa.onlyhomefood.validator;

import in.fssa.onlyhomefood.exception.ValidationException;
import in.fssa.onlyhomefood.model.User;

public class UserValidator {
	
	public static void validate(User user) throws ValidationException {
		
		if(user == null) {
			
			throw new ValidationException("Invalid User Input");
		}
	}

}

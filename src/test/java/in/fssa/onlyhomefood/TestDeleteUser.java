package in.fssa.onlyhomefood;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.onlyhomefood.dao.UserDAO;
import in.fssa.onlyhomefood.exception.ValidationException;
import in.fssa.onlyhomefood.service.UserService;

public class TestDeleteUser {
//	Delete User with valid Input
	@Test
	public void testDeleteUserWithValidInput() {
		UserService userService = new UserService();
		UserDAO userDAO = new UserDAO();
		
		int id = userDAO.getLastUpdatedUserId();
		
		assertDoesNotThrow(() -> {
			userService.deleteUser(id);
		});
	}

	// Id is Invalid
	@Test
	public void testDeleteUserWithInvalidId() {

		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.deleteUser(0);
		});

		String expectedMessage = "User Id cannot be zero or below zero";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}

	// Id check
	@Test
	public void testUserCheckIdPresent() {

		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.deleteUser(100);
		});

		String expectedMessage = "User not found";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}
}

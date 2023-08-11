package in.fssa.onlyhomefood;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.onlyhomefood.exception.ValidationException;
import in.fssa.onlyhomefood.service.UserService;

public class TestDeleteUser {
	
	@Test
	public void testCreateUserWithValidInput() {

		UserService userService = new UserService();

		assertDoesNotThrow(() -> {
			userService.delete(3);
		});
	}

	// Id is Invalid
	@Test
	public void testUserWithInvalidId() {

		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.delete(0);
		});

		String expectedMessage = "User Id cannot be zero or below zero";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}
	
	// Id check
	@Test
	public void testUserWithCheckIdPresent() {

		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.delete(10);
		});

		String expectedMessage = "User not found";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}
}

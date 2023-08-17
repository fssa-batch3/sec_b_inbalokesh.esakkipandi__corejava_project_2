package in.fssa.onlyhomefood;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

import in.fssa.onlyhomefood.exception.ValidationException;
import in.fssa.onlyhomefood.model.User;
import in.fssa.onlyhomefood.service.UserService;

public class TestGetAllUsers {
	
	@Test
	public void testGetAllUsers() {
		UserService userService = new UserService();
		assertDoesNotThrow(() -> {
			Set<User> users = userService.getAll();
			for(User u : users) {
				System.out.println(u);			
			}
		});
	}
	
	@Test
	public void testFindUserWithValidInput() {

		UserService userService = new UserService();

		assertDoesNotThrow(() -> {
			System.out.println(userService.findById(2));
		});
	}

	// Id is Invalid
	@Test
	public void testFindUserWithInvalidId() {

		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.findById(0);
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
			userService.findById(10);
		});

		String expectedMessage = "User not found";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}
}

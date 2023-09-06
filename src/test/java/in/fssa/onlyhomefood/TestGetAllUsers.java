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
			Set<User> users = userService.getAllUsers();
			for(User u : users) {
				System.out.println(u);			
			}
		});
	}
	
	@Test
	public void testFindUserWithValidInput() {

		UserService userService = new UserService();

		assertDoesNotThrow(() -> {
			System.out.println(userService.findUserById(2));
		});
	}

	// Id is Invalid
	@Test
	public void testFindUserWithInvalidId() {

		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.findUserById(0);
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
			userService.findUserById(1000);
		});

		String expectedMessage = "User not found";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}
	
	//Test Get user by phone number
	@Test
	public void testFindUserWithPhoneNumber() {

		UserService userService = new UserService();

		assertDoesNotThrow(() -> {
			System.out.println(userService.findUserByPhoneNumber(8888834458l));
		});
	}
	
	// Test With Invalid Phone_number
	@Test
	public void testFindUserWithInvalidPhoneNumber() {

		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.findUserByPhoneNumber(22222222222l);
		});

		String expectedMessage = "Phone Number must start between 6 - 9 and have total of 10 digits";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}
	
}

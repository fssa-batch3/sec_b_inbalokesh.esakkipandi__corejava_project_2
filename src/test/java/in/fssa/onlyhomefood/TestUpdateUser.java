package in.fssa.onlyhomefood;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.onlyhomefood.exception.ValidationException;
import in.fssa.onlyhomefood.model.User;
import in.fssa.onlyhomefood.service.UserService;

public class TestUpdateUser {

	@Test
	public void testCreateUserWithValidInput() {

		UserService userService = new UserService();

		User newUser = new User();
		newUser.setEmail("inba@gmail.com");
		newUser.setName("Akilesh");
		newUser.setMobNumber(6922334453l);
		newUser.setPassword("Akil1234");

		assertDoesNotThrow(() -> {
			userService.update(1, newUser);
		});
	}

	// Invalid Input
	@Test
	public void testCreateUserWithInvaidInput() {
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.update(0, null);
		});
		String expectedMessage = "Invalid User Input";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}

	// Password null check
	@Test
	public void testUserWithPasswordNull() {
		UserService userService = new UserService();

		User newUser = new User();
		newUser.setEmail("inba@gmail.com");
		newUser.setName("Inba");
		newUser.setMobNumber(9922334453l);
		newUser.setPassword(null);

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.update(1, newUser);
		});

		String expectedMessage = "Password cannot be null or empty";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}

	// Password is Empty
	@Test
	public void testUserWithPasswordEmpty() {

		UserService userService = new UserService();

		User newUser = new User();
		newUser.setEmail("inba@gmail.com");
		newUser.setName("Inba");
		newUser.setMobNumber(9922334453l);
		newUser.setPassword("");
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.update(1, newUser);

		});

		String expectedMessage = "Password cannot be null or empty";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}

	// Name null check
	@Test
	public void testUserWithNameNull() {
		UserService userService = new UserService();

		User newUser = new User();
		newUser.setEmail("inba@gmail.com");
		newUser.setName(null);
		newUser.setMobNumber(9922334453l);
		newUser.setPassword("Inba12345");

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.update(1, newUser);
		});

		String expectedMessage = "Name cannot be null or empty";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}

	// Name is empty
	@Test
	public void testUserWithNameEmpty() {

		UserService userService = new UserService();

		User newUser = new User();
		newUser.setEmail("inba@gmail.com");
		newUser.setName("");
		newUser.setMobNumber(9922334453l);
		newUser.setPassword("Inba12345");
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.update(1, newUser);

		});

		String expectedMessage = "Name cannot be null or empty";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}

	// Id is Invalid
	@Test
	public void testUserWithInvalidId() {

		UserService userService = new UserService();

		User newUser = new User();
		newUser.setEmail("inba@gmail.com");
		newUser.setName("Inba");
		newUser.setMobNumber(9922334453l);
		newUser.setPassword("Inba12345");
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.update(0, newUser);
		});

		String expectedMessage = "User Id cannot be zero or below zero";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}
	
	// Id check
	@Test
	public void testUserWithCheckIdPresent() {

		UserService userService = new UserService();

		User newUser = new User();
		newUser.setEmail("inba@gmail.com");
		newUser.setName("Inba");
		newUser.setMobNumber(9922334453l);
		newUser.setPassword("Inba12345");
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.update(10, newUser);
		});

		String expectedMessage = "User not found";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}
}

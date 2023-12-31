package in.fssa.onlyhomefood;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.onlyhomefood.exception.ValidationException;
import in.fssa.onlyhomefood.model.User;
import in.fssa.onlyhomefood.service.UserService;
import in.fssa.onlyhomefood.util.RandomGenerator;


public class TestUpdateUser {
	RandomGenerator random = new RandomGenerator();
	@Test
	public void testUpdateUserWithValidInput() {

		UserService userService = new UserService();

		User newUser = new User();
		newUser.setEmail("inba@gmail.com");
		newUser.setName(random.generateRandomString(7));
		newUser.setMobNumber(6922334453l);
		newUser.setPassword("Akil1234");

		assertDoesNotThrow(() -> {
			userService.updateUser(2, newUser);
		});
	}

	// Invalid Input
	@Test
	public void testUpdateUserWithInvaidInput() {
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.updateUser(2, null);
		});
		String expectedMessage = "User cannot be null";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}

	// Password null check
	@Test
	public void testUserWithPasswordNull() {
		UserService userService = new UserService();

		User newUser = new User();
		newUser.setEmail("loki@gmail.com");
		newUser.setName("Inba");
		newUser.setMobNumber(9922334453l);
		newUser.setPassword(null);

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.updateUser(4, newUser);
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
		newUser.setEmail("loki@gmail.com");
		newUser.setName("Inba");
		newUser.setMobNumber(9922334453l);
		newUser.setPassword("");
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.updateUser(4, newUser);

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
			userService.updateUser(4, newUser);
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
			userService.updateUser(4, newUser);

		});

		String expectedMessage = "Name cannot be null or empty";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}
	
	// Name Pattern check
	@Test
	public void testUserWithNameInValidPattern() {

		UserService userService = new UserService();
		User newUser = new User();
		newUser.setEmail("inba@gmail.com");
		newUser.setName("Akil123");
		newUser.setMobNumber(6234567898l);
		newUser.setPassword("Inba1234");

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.updateUser(4, newUser);

		});
		String expectedMessage = "Name must contain only alphabets with minimum 3 letters can have characters like(',-) with a single space and followed by letters";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));

	}
	
	// Password Pattern check
	@Test
	public void testUserWithPasswordInValidPattern() {

		UserService userService = new UserService();
		User newUser = new User();
		newUser.setEmail("inba@gmail.com");
		newUser.setName("Akil");
		newUser.setMobNumber(6234567898l);
		newUser.setPassword("inba1234");

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.updateUser(4, newUser);

		});
		String expectedMessage = "Password does not match the requested pattern";
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
			userService.updateUser(0, newUser);
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
			userService.updateUser(10000, newUser);
		});

		String expectedMessage = "User not found";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}
}

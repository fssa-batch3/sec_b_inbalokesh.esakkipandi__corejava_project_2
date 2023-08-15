package in.fssa.onlyhomefood;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.onlyhomefood.exception.ServiceException;
import in.fssa.onlyhomefood.exception.ValidationException;
import in.fssa.onlyhomefood.model.User;
import in.fssa.onlyhomefood.service.UserService;

public class TestCreateUser {

	// Valid Input
	@Test
	public void testCreateUserWithValidInput() {

		UserService userService = new UserService();
		User newUser = new User();
		newUser.setEmail("il@gmail.com");
		newUser.setName("Inba");
		newUser.setMobNumber(8888834453l);
		newUser.setPassword("Inba123098");

		assertDoesNotThrow(() -> {
			userService.create(newUser);
		});
	}

	// Invalid Input
	@Test
	public void testCreateUserWithInvaidInput() {
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.create(null);
		});
		String expectedMessage = "Invalid User Input";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}

	// Email null check
	@Test
	public void testUserWithEmailNull() {
		UserService userService = new UserService();

		User newUser = new User();
		newUser.setEmail(null);
		newUser.setName("Inba");
		newUser.setMobNumber(9922334453l);
		newUser.setPassword("Abcd1234");

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.create(newUser);
		});

		String expectedMessage = "Email cannot be null or empty";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}

	// Email is Empty
	@Test
	public void testUserWithEmailEmpty() {

		UserService userService = new UserService();

		User newUser = new User();
		newUser.setEmail("");
		newUser.setName("Inba");
		newUser.setMobNumber(9922334453l);
		newUser.setPassword("Abcd1234");
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.create(newUser);

		});

		String expectedMessage = "Email cannot be null or empty";
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
			userService.create(newUser);
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
			userService.create(newUser);

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
			userService.create(newUser);
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
			userService.create(newUser);

		});

		String expectedMessage = "Name cannot be null or empty";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}

	// Number check
	@Test
	public void testUserWithMoblieNumberInValid() {

		UserService userService = new UserService();
		User newUser = new User();
		newUser.setEmail("a@gmail.com");
		newUser.setName("Akil");
		newUser.setMobNumber(5234567898l);
		newUser.setPassword("Inba12345");

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.create(newUser);

		});
		String expectedMessage = "Mobile Number must start between 6 - 9 and have 10 digits";
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
			userService.create(newUser);

		});
		String expectedMessage = "Invalid Name";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));

	}

	// Email Pattern check
	@Test
	public void testUserWithEmailInValidPattern() {

		UserService userService = new UserService();
		User newUser = new User();
		newUser.setEmail("@.com");
		newUser.setName("Akil");
		newUser.setMobNumber(6234567898l);
		newUser.setPassword("Inba12345");

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.create(newUser);

		});
		String expectedMessage = "Invalid Email Id";
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
			userService.create(newUser);

		});
		String expectedMessage = "Password does not match the requested pattern";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));

	}
	
	// User already exist
	@Test
	public void testCreateUserEmailCheck() {

		UserService userService = new UserService();

		User newUser = new User();
		newUser.setEmail("inba@gmail.com");
		newUser.setName("Inba");
		newUser.setMobNumber(8882334453l);
		newUser.setPassword("Inba123098");

		Exception exception = assertThrows(ServiceException.class, () -> {
			userService.create(newUser);

		});
		String expectedMessage = "User already exist";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}
}
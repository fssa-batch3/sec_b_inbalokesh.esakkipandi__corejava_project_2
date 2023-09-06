package in.fssa.onlyhomefood;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.onlyhomefood.exception.ServiceException;
import in.fssa.onlyhomefood.exception.ValidationException;
import in.fssa.onlyhomefood.model.User;
import in.fssa.onlyhomefood.service.UserService;
import in.fssa.onlyhomefood.util.RandomGenerator;

public class TestCreateUser {

	RandomGenerator random = new RandomGenerator();

	// Valid Input
	@Test
	public void testCreateUserWithValidInput() {

		UserService userService = new UserService();
		User newUser = new User();
		String randomString = random.generateRandomString(5);
		long randomNumber = random.generateRandomNumber(10);
		newUser.setEmail(randomString + "@gmail.com");
		newUser.setName("Inba");
		newUser.setMobNumber(randomNumber);
		newUser.setPassword("Inba123098");

		assertDoesNotThrow(() -> {
			userService.createNewUser(newUser);
		});
	}

	// Invalid Input
	@Test
	public void testCreateUserWithInvaidInput() {
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.createNewUser(null);
		});
		String expectedMessage = "User cannot be null";
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
			userService.createNewUser(newUser);
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
			userService.createNewUser(newUser);

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
			userService.createNewUser(newUser);
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
			userService.createNewUser(newUser);

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
			userService.createNewUser(newUser);
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
			userService.createNewUser(newUser);

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
			userService.createNewUser(newUser);

		});
		String expectedMessage = "Mobile Number must start between 6 - 9 and have total of 10 digits";
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
			userService.createNewUser(newUser);

		});
		String expectedMessage = "Name must contain only alphabets with minimum 3 letters can have characters like(',-) with a single space and followed by letters";
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
			userService.createNewUser(newUser);

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
			userService.createNewUser(newUser);

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
			userService.createNewUser(newUser);

		});
		String expectedMessage = "User already exist";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}

	// User already exist
	@Test
	public void testCreateUserPhoneNumberCheck() {

		UserService userService = new UserService();

		User newUser = new User();
		newUser.setEmail("anna@gmail.com");
		newUser.setName("Inba");
		newUser.setMobNumber(8888834458l);
		newUser.setPassword("Inba123098");

		Exception exception = assertThrows(ServiceException.class, () -> {
			userService.createNewUser(newUser);

		});
		String expectedMessage = "User already exist";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}

	// Login test case

	// Test Longin With Valid Input
	@Test
	public void testLoginUserWithValidInput() {

		UserService userService = new UserService();

		assertDoesNotThrow(() -> {
			userService.loginUser(8888834458l, "Inba1234");
		});
	}

	// Password null check
	@Test
	public void testLoginUserWithPasswordNull() {
		UserService userService = new UserService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.loginUser(9999888877l, null);
		});

		String expectedMessage = "Password cannot be null or empty";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}

	// Password is Empty
	@Test
	public void testLoginUserWithPasswordEmpty() {

		UserService userService = new UserService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.loginUser(9999888877l, "");
		});

		String expectedMessage = "Password cannot be null or empty";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}

	// Number check
	@Test
	public void testLoginUserWithMoblieNumberInValid() {

		UserService userService = new UserService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.loginUser(1999888877l, "Inba1234");
		});

		String expectedMessage = "Mobile Number must start between 6 - 9 and have total of 10 digits";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));

	}

	// Password Pattern check
	@Test
	public void testLoginUserWithPasswordInValidPattern() {

		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.loginUser(9999888877l, "inba1234");
		});
		String expectedMessage = "Password does not match the requested pattern";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));

	}

	// Invalid login credentials
	@Test
	public void testLoginUserWithInvalidLoginCredentails() {
		UserService userService = new UserService();

		Exception exception = assertThrows(ServiceException.class, () -> {
			userService.loginUser(9999888877l, "Inba5465");
		});

		String expectedMessage = "Invalid Login Credentials";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}

	// Invalid login credentials2
	@Test
	public void testLoginUserWithInvalidLoginCredentails2() {
		UserService userService = new UserService();

		Exception exception = assertThrows(ServiceException.class, () -> {
			userService.loginUser(8888834458l, "Inba009988");
		});

		String expectedMessage = "Invalid Login Credentials";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}

}

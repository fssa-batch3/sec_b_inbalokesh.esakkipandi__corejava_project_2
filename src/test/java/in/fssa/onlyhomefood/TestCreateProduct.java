package in.fssa.onlyhomefood;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.onlyhomefood.exception.ServiceException;
import in.fssa.onlyhomefood.exception.ValidationException;
import in.fssa.onlyhomefood.model.Product;
import in.fssa.onlyhomefood.service.ProductService;
import in.fssa.onlyhomefood.util.RandomGenerator;

public class TestCreateProduct {

	RandomGenerator random = new RandomGenerator();

//	Test Product with valid input
	@Test
	public void testCreateProductWithValidInput() {

		ProductService productService = new ProductService();
		Product product = new Product();
		product.setName(random.generateRandomString(5));
		product.setType("Veg");
		product.setQuantity(2);
		product.setPrice(19);
		product.setQuantityType("piece");
		product.setImage("https://iili.io/HgW7hwF.md.jpg");

		assertDoesNotThrow(() -> {
			productService.createNewProduct(product);
		});
	}

//	Test Product with Invalid Input
	@Test
	public void testCreateProductWithInvalidInput() {

		ProductService productService = new ProductService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.createNewProduct(null);
		});
		String expectedMessage = "Product cannot be null";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}

//	Test Food Name is Null
	@Test
	public void testCreateProductWithNameNull() {
		ProductService productService = new ProductService();

		Product product = new Product();
		product.setName(null);
		product.setType("Veg");
		product.setQuantity(2);
		product.setPrice(19);
		product.setQuantityType("Dosa");
		product.setImage("https://iili.io/HgW7hwF.md.jpg");

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.createNewProduct(product);
		});

		String expectedMessage = "Food Name cannot be null or empty";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}

//	Test Food Name is Empty
	@Test
	public void testCreateProductWithNameEmpty() {
		ProductService productService = new ProductService();

		Product product = new Product();
		product.setName("");
		product.setType("Veg");
		product.setQuantity(2);
		product.setPrice(19);
		product.setQuantityType("Dosa");
		product.setImage("https://iili.io/HgW7hwF.md.jpg");

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.createNewProduct(product);
		});

		String expectedMessage = "Food Name cannot be null or empty";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}

//	Test Food Type is Null
	@Test
	public void testCreateProductWithTypeNull() {
		ProductService productService = new ProductService();

		Product product = new Product();
		product.setName("Dosa");
		product.setType(null);
		product.setQuantity(2);
		product.setPrice(19);
		product.setQuantityType("Dosa");
		product.setImage("https://iili.io/HgW7hwF.md.jpg");

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.createNewProduct(product);
		});

		String expectedMessage = "Food Type cannot be null or empty";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}

//	Test Food Type is Empty
	@Test
	public void testCreateProductWithTypeEmpty() {
		ProductService productService = new ProductService();

		Product product = new Product();
		product.setName("Dosa");
		product.setType("");
		product.setQuantity(2);
		product.setPrice(19);
		product.setQuantityType("Dosa");
		product.setImage("https://iili.io/HgW7hwF.md.jpg");

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.createNewProduct(product);
		});

		String expectedMessage = "Food Type cannot be null or empty";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}

//	Test Quantity type is Null
	@Test
	public void testCreateProductWithQuantityTypeNull() {
		ProductService productService = new ProductService();

		Product product = new Product();
		product.setName("Dosa");
		product.setType("Veg");
		product.setQuantity(2);
		product.setPrice(19);
		product.setQuantityType(null);
		product.setImage("https://iili.io/HgW7hwF.md.jpg");

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.createNewProduct(product);
		});

		String expectedMessage = "Quantity type cannot be null or empty";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}

//	Test Quantity type is Empty
	@Test
	public void testCreateProductWithQuantityTypeEmpty() {
		ProductService productService = new ProductService();

		Product product = new Product();
		product.setName("Dosa");
		product.setType("Veg");
		product.setQuantity(2);
		product.setPrice(19);
		product.setQuantityType("");

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.createNewProduct(product);
		});

		String expectedMessage = "Quantity type cannot be null or empty";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}

//	Test Image url is Null
	@Test
	public void testCreateProductWithImageNull() {
		ProductService productService = new ProductService();

		Product product = new Product();
		product.setName("Dosa");
		product.setType("Veg");
		product.setQuantity(2);
		product.setPrice(19);
		product.setQuantityType("Dosa");
		product.setImage(null);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.createNewProduct(product);
		});

		String expectedMessage = "Image url cannot be null or empty";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}

//	Test Image url is Empty
	@Test
	public void testCreateProductWithImageEmpty() {
		ProductService productService = new ProductService();

		Product product = new Product();
		product.setName("Dosa");
		product.setType("Veg");
		product.setQuantity(2);
		product.setPrice(19);
		product.setQuantityType("Dosa");
		product.setImage("");

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.createNewProduct(product);
		});

		String expectedMessage = "Image url cannot be null or empty";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}

//	Test Price range between 1 and 1000
	@Test
	public void testCreateProductWithPriceRange() {
		ProductService productService = new ProductService();

		Product product = new Product();
		product.setName("Dosa");
		product.setType("Veg");
		product.setQuantity(2);
		product.setPrice(0);
		product.setQuantityType("Dosa");
		product.setImage("https://iili.io/HgW7hwF.md.jpg");

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.createNewProduct(product);
		});

		String expectedMessage = "Set Price range between 1 and 1000";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}

//	Test Quantity range between 1 and 1000
	@Test
	public void testCreateProductWithQuantityRange() {
		ProductService productService = new ProductService();

		Product product = new Product();
		product.setName("Dosa");
		product.setType("Veg");
		product.setQuantity(1001);
		product.setPrice(19);
		product.setQuantityType("Dosa");
		product.setImage("https://iili.io/HgW7hwF.md.jpg");

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.createNewProduct(product);
		});

		String expectedMessage = "Set Quantity range between 1 and 1000";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}

//	Patterns
//	Test Invalid Name Pattern
	@Test
	public void testCreateProductNameWithPattern() {
		ProductService productService = new ProductService();

		Product product = new Product();
		product.setName("Dosa1234");
		product.setType("Veg");
		product.setQuantity(2);
		product.setPrice(19);
		product.setQuantityType("Dosa");
		product.setImage("https://iili.io/HgW7hwF.md.jpg");

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.createNewProduct(product);
		});

		String expectedMessage = "Food Name must contain only alphabets with minimum 3 letters can have characters like(',-) with a single space and followed by letters";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}

//	Test Invalid Type Pattern
	@Test
	public void testCreateProductTypeWithPattern() {
		ProductService productService = new ProductService();

		Product product = new Product();
		product.setName("Dosa");
		product.setType("Veg  12@#!");
		product.setQuantity(2);
		product.setPrice(19);
		product.setQuantityType("Dosa");
		product.setImage("https://iili.io/HgW7hwF.md.jpg");

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.createNewProduct(product);
		});

		String expectedMessage = "Food type must contain only alphabets with minimum 3 letters can have characters like(',-) with a single space and followed by letters";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}

//	Test Invalid Quantity Type Pattern
	@Test
	public void testCreateProductQuantityTypeWithPattern() {
		ProductService productService = new ProductService();

		Product product = new Product();
		product.setName("Dosa");
		product.setType("Veg");
		product.setQuantity(2);
		product.setPrice(19);
		product.setQuantityType("Dosa?");
		product.setImage("https://iili.io/HgW7hwF.md.jpg");

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.createNewProduct(product);
		});

		String expectedMessage = "Quantity type must contain only alphabets with minimum 2 letters can have characters like(',-) with a single space and followed by letters";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}

//	Test Product Already Exist With Name
	@Test
	public void testCreateProductWithNameAlreadyExist() {
		ProductService productService = new ProductService();

		Product product = new Product();
		product.setName("Idly");
		product.setType("Veg");
		product.setQuantity(2);
		product.setPrice(19);
		product.setQuantityType("Dosa");
		product.setImage("https://iili.io/HgW7hwF.md.jpg");

		Exception exception = assertThrows(ServiceException.class, () -> {
			productService.createNewProduct(product);
		});

		String expectedMessage = "Product already exist";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));

	}

}

package in.fssa.onlyhomefood;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.onlyhomefood.exception.ValidationException;
import in.fssa.onlyhomefood.model.Product;
import in.fssa.onlyhomefood.service.ProductService;
import in.fssa.onlyhomefood.util.RandomGenerator;

public class TestUpdateProduct {
	RandomGenerator random = new RandomGenerator();
//	Test update with Valid Input
	@Test
	public void testUpdateProductWithValidInput() {

		ProductService productService = new ProductService();
		Product updateProduct = new Product();
		updateProduct.setName("Dosa");
		updateProduct.setType("Veg");
		updateProduct.setQuantity(1);
		updateProduct.setPrice(18);
		updateProduct.setQuantityType(random.generateRandomString(4));
		updateProduct.setImage(random.generateRandomString(7));

		assertDoesNotThrow(() -> {
			productService.updateProduct(4, updateProduct);
		});
	}

// Test update with Invalid Input
	@Test
	public void testUpdateProductWithInvalidInput() {

		ProductService productService = new ProductService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.updateProduct(2, null);
		});
		String expectedMessage = "Product cannot be null";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}

//	Test Update Id Valid
	@Test
	public void testUpdateProductWithInvalidId() {

		ProductService productService = new ProductService();

		Product updateProduct = new Product();
		updateProduct.setName("Idly");
		updateProduct.setType("Veg");
		updateProduct.setQuantity(1);
		updateProduct.setPrice(18);
		updateProduct.setQuantityType("piece");
		updateProduct.setImage("https://iili.io/HgW7hwF.md.jpg");

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.updateProduct(0, updateProduct);
		});

		String expectedMessage = "Product Id cannot be zero or below zero";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));

	}

//	Test Update Id is present
	@Test
	public void testUpdateProductWithCheckIdPresent() {
		ProductService productService = new ProductService();

		Product updateProduct = new Product();
		updateProduct.setName("Idly");
		updateProduct.setType("Veg");
		updateProduct.setQuantity(1);
		updateProduct.setPrice(18);
		updateProduct.setQuantityType("piece");
		updateProduct.setImage("https://iili.io/HgW7hwF.md.jpg");


		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.updateProduct(1000, updateProduct);
		});

		String expectedMessage = "Product not found";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));

	}

//	Test Update Food Type is Null
	@Test
	public void testUpdateProductWithTypeNull() {
		ProductService productService = new ProductService();

		Product product = new Product();
		product.setName("Dosa");
		product.setType(null);
		product.setQuantity(2);
		product.setPrice(19);
		product.setQuantityType("Dosa");
		product.setImage("https://iili.io/HgW7hwF.md.jpg");


		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.updateProduct(1, product);
		});

		String expectedMessage = "Food Type cannot be null or empty";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}

//	Test Update Food Type is Empty
	@Test
	public void testUpdateProductWithTypeEmpty() {
		ProductService productService = new ProductService();

		Product product = new Product();
		product.setName("Dosa");
		product.setType("");
		product.setQuantity(2);
		product.setPrice(19);
		product.setQuantityType("Dosa");
		product.setImage("https://iili.io/HgW7hwF.md.jpg");


		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.updateProduct(1, product);
		});

		String expectedMessage = "Food Type cannot be null or empty";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}

//	Test Update Quantity type is Null
	@Test
	public void testUpdateProductWithQuantityTypeNull() {
		ProductService productService = new ProductService();

		Product product = new Product();
		product.setName("Dosa");
		product.setType("Veg");
		product.setQuantity(2);
		product.setPrice(19);
		product.setQuantityType(null);
		product.setImage("https://iili.io/HgW7hwF.md.jpg");

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.updateProduct(1, product);
		});

		String expectedMessage = "Quantity type cannot be null or empty";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}

//	Test Update Quantity type is Empty
	@Test
	public void testUpdateProductWithQuantityTypeEmpty() {
		ProductService productService = new ProductService();

		Product product = new Product();
		product.setName("Dosa");
		product.setType("Veg");
		product.setQuantity(2);
		product.setPrice(19);
		product.setQuantityType("");
		product.setImage("https://iili.io/HgW7hwF.md.jpg");

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.updateProduct(1, product);
		});

		String expectedMessage = "Quantity type cannot be null or empty";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}
	
//	Test Update Image url is Null
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
			productService.updateProduct(1, product);
		});

		String expectedMessage = "Image url cannot be null or empty";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}

//	Test Update Image url is Empty
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
			productService.updateProduct(1, product);
		});

		String expectedMessage = "Image url cannot be null or empty";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}

//	Test Update Price range between 1 and 1000
	@Test
	public void testUpdateProductWithPriceRange() {
		ProductService productService = new ProductService();

		Product product = new Product();
		product.setName("Dosa");
		product.setType("Veg");
		product.setQuantity(2);
		product.setPrice(0);
		product.setQuantityType("Dosa");
		product.setImage("https://iili.io/HgW7hwF.md.jpg");

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.updateProduct(1, product);
		});

		String expectedMessage = "Set Price range between 1 and 1000";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}

//	Test Update Quantity range between 1 and 1000
	@Test
	public void testUpdateProductWithQuantityRange() {
		ProductService productService = new ProductService();

		Product product = new Product();
		product.setName("Dosa");
		product.setType("Veg");
		product.setQuantity(1001);
		product.setPrice(19);
		product.setQuantityType("Dosa");
		product.setImage("https://iili.io/HgW7hwF.md.jpg");

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.updateProduct(1, product);
		});

		String expectedMessage = "Set Quantity range between 1 and 1000";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}

//	Test Update Invalid Type Pattern
	@Test
	public void testUpdateProductTypeWithPattern() {
		ProductService productService = new ProductService();

		Product product = new Product();
		product.setName("Dosa");
		product.setType("Veg  12@#!");
		product.setQuantity(2);
		product.setPrice(19);
		product.setQuantityType("Dosa");
		product.setImage("https://iili.io/HgW7hwF.md.jpg");

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.updateProduct(1, product);
		});

		String expectedMessage = "Food type must contain only alphabets with minimum 3 letters can have characters like(',-) with a single space and followed by letters";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}

//	Test Update Invalid Quantity Type Pattern
	@Test
	public void testUpdateProductQuantityTypeWithPattern() {
		ProductService productService = new ProductService();

		Product product = new Product();
		product.setName("Dosa");
		product.setType("Veg");
		product.setQuantity(2);
		product.setPrice(19);
		product.setQuantityType("Dosa?");
		product.setImage("https://iili.io/HgW7hwF.md.jpg");

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.updateProduct(1, product);
		});

		String expectedMessage = "Quantity type must contain only alphabets with minimum 2 letters can have characters like(',-) with a single space and followed by letters";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}
}

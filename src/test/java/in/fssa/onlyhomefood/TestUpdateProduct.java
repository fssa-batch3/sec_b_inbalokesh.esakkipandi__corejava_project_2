package in.fssa.onlyhomefood;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.onlyhomefood.exception.ValidationException;
import in.fssa.onlyhomefood.model.Product;
import in.fssa.onlyhomefood.service.ProductService;

public class TestUpdateProduct {
//	Test update with Valid Input
	@Test
	public void testUpdateProductWithValidInput() {

		ProductService productService = new ProductService();

		Product updateProduct = new Product();
		updateProduct.setName("Idly");
		updateProduct.setType("Veg");
		updateProduct.setQuantity(1);
		updateProduct.setPrice(18);
		updateProduct.setQuantityType("piece");

		assertDoesNotThrow(() -> {
			productService.update(1, updateProduct);
		});
	}

// Test update with Invalid Input
	@Test
	public void testUpdateProductWithInvalidInput() {

		ProductService productService = new ProductService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.update(1, null);
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

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.update(0, updateProduct);
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

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.update(11, updateProduct);
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

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.update(1, product);
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

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.update(1, product);
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

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.update(1, product);
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

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.update(1, product);
		});

		String expectedMessage = "Quantity type cannot be null or empty";
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

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.update(1, product);
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

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.update(1, product);
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

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.update(1, product);
		});

		String expectedMessage = "Invalid String Pattern";
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

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.update(1, product);
		});

		String expectedMessage = "Invalid String Pattern";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}
}

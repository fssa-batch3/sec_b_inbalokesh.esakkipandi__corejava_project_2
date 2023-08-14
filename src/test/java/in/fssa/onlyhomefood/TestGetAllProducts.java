package in.fssa.onlyhomefood;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

import in.fssa.onlyhomefood.exception.ValidationException;
import in.fssa.onlyhomefood.model.Product;
import in.fssa.onlyhomefood.service.ProductService;


public class TestGetAllProducts {
	
	@Test
	public void testGetAllUser() {

		ProductService productService = new ProductService();
		assertDoesNotThrow(() -> {
			Set<Product> users = productService.getAll();
			System.out.println(users);
		});
	}
	
	@Test
	public void testFindUserWithValidInput() {

		ProductService productService = new ProductService();
		assertDoesNotThrow(() -> {
			System.out.println(productService.findById(1));
		});
	}
	
	// Id is Invalid
	@Test
	public void testFindUserWithInvalidId() {

		ProductService productService = new ProductService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.findById(0);
		});

		String expectedMessage = "Product Id cannot be zero or below zero";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}
	
	// Id check
	@Test
	public void testUserWithCheckIdPresent() {

		ProductService productService = new ProductService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.findById(10);
		});

		String expectedMessage = "Product not found";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}

}

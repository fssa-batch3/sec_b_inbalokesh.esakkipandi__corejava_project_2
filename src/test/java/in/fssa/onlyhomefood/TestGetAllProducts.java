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
//	Test Get all products
	@Test
	public void testGetAllProducts() {
		
		ProductService productService = new ProductService();
		assertDoesNotThrow(() -> {
			Set<Product> products = productService.getAll();
			
			for(Product p : products) {
				System.out.println(p);
			}
			
		});
	}
//	Test get product with ID
	@Test
	public void testFindProductWithValidInput() {

		ProductService productService = new ProductService();
		assertDoesNotThrow(() -> {
			System.out.println(productService.findById(1));
		});
	}
	
	// Id is Invalid
	@Test
	public void testFindProductWithInvalidId() {

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

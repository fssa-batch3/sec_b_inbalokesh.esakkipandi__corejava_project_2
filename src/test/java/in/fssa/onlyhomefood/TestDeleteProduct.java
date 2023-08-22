package in.fssa.onlyhomefood;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.onlyhomefood.dao.ProductDAO;
import in.fssa.onlyhomefood.exception.ValidationException;
import in.fssa.onlyhomefood.service.ProductService;

public class TestDeleteProduct {
//	Test Delete product with valid Input
	@Test
	public void testDeleteProductWithValidInput() {

		ProductService productService = new ProductService();
		ProductDAO productDao = new ProductDAO();
		
		int id = productDao.getLastUpdatedUserId();
		assertDoesNotThrow(() -> {
			productService.delete(id);
		});
	}
	
//	Test Delete product with Invalid Id
	@Test
	public void testDeleteProductWithInvalidId() {
		
		ProductService productService = new ProductService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.delete(0);
		});
		
		String expectedMessage = "Product Id cannot be zero or below zero";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
		
	}
//	Test Delete product Check Id Exist
	@Test
	public void testDeleteProductCheckIdPresent() {
		
		ProductService productService = new ProductService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.delete(1000);
		});
		
		String expectedMessage = "Product not found";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
		
	}
	

}

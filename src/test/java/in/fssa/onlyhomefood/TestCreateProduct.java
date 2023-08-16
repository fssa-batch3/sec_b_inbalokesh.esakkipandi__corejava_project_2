package in.fssa.onlyhomefood;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import in.fssa.onlyhomefood.model.Product;
import in.fssa.onlyhomefood.service.ProductService;

public class TestCreateProduct {
	
	
	@Test
	public void testCreateUserWithValidInput() {

		ProductService productService = new ProductService();
		Product product = new Product();
		product.setName("Dosa");
		product.setType("Veg");
		product.setQuantity(2);
		product.setPrice(19);
		product.setQuantityType("Dosa");

		assertDoesNotThrow(() -> {
			productService.create(product);
		});
	}
}

package in.fssa.onlyhomefood;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import in.fssa.onlyhomefood.model.Product;
import in.fssa.onlyhomefood.model.ProductPrice;
import in.fssa.onlyhomefood.service.ProductService;

public class TestCreateProduct {
	
	@Test
	public void testCreateUserWithValidInput() {

		ProductService productService = new ProductService();
		Product product = new Product();
		product.setName("Mini Idly");
		product.setType("Veg");
		product.setQuantity(10);
		product.setQuantityType("piece");
		
		ProductPrice productPrice = new ProductPrice();
		productPrice.setPrice(19);

		assertDoesNotThrow(() -> {
			productService.create(product, productPrice.getPrice());
		});
	}
}

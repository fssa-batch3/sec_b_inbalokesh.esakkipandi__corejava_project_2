package in.fssa.onlyhomefood;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import in.fssa.onlyhomefood.model.Product;
import in.fssa.onlyhomefood.service.ProductService;

public class TestUpdateProduct {
	
	@Test
	public void testUpdateProductWithValidInput() {

		ProductService productService = new ProductService();
		
		Product updateProduct = new Product();
		updateProduct.setName("Idly");
		updateProduct.setType("Veg");
		updateProduct.setQuantity(1);
		updateProduct.setPrice(59);
		updateProduct.setQuantityType("piece");

		assertDoesNotThrow(() -> {
			productService.update(1, updateProduct);
		});
	}

}

package in.fssa.onlyhomefood;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import in.fssa.onlyhomefood.service.ProductService;

public class TestProductDelete {
	
	@Test
	public void testdeleteUserWithValidInput() {

		ProductService productService = new ProductService();

		assertDoesNotThrow(() -> {
			productService.delete(1);
		});
	}

}

package in.fssa.onlyhomefood;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.Set;

import org.junit.jupiter.api.Test;

import in.fssa.onlyhomefood.model.Order;
import in.fssa.onlyhomefood.service.OrderService;

public class TestGetAllOrders {
	
	@Test
	public void testGetAllOrders() {
		OrderService orderService = new OrderService();
		assertDoesNotThrow(() -> {
			Set<Order> orders = orderService.getAll();
	
			for(Order n : orders) {
				System.out.println(n);
			}
		});
	}
}

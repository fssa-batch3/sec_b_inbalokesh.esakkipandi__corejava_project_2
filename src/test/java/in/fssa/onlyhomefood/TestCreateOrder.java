package in.fssa.onlyhomefood;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import in.fssa.onlyhomefood.model.Order;
import in.fssa.onlyhomefood.model.OrderEntity.DeliveryTime;
import in.fssa.onlyhomefood.model.OrderEntity.OrderStatus;
import in.fssa.onlyhomefood.service.OrderService;


public class TestCreateOrder {
	
	// Valid Input
	@Test
	public void testCreateOrderWithValidInput() {

		OrderService orderService = new OrderService();
		Order newOrder = new Order();
		newOrder.setQuantity(2);
		newOrder.setTotal_price(118);
		newOrder.setDelivery_time(DeliveryTime.Breakfast);
		newOrder.setOrder_status(OrderStatus.Not_delivered);
		newOrder.setCreated_by(1);
		newOrder.setAddress("Perugudi Chennai");
		newOrder.setProduct_id(1);

		assertDoesNotThrow(() -> {
			orderService.create(newOrder);
		});
	}

}

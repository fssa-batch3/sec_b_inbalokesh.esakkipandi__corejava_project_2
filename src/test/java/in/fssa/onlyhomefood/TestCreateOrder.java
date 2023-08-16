package in.fssa.onlyhomefood;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.onlyhomefood.exception.ValidationException;
import in.fssa.onlyhomefood.model.Order;
import in.fssa.onlyhomefood.model.OrderEntity.DeliveryTime;
import in.fssa.onlyhomefood.model.OrderEntity.OrderStatus;
import in.fssa.onlyhomefood.service.OrderService;


public class TestCreateOrder {
	
	// Test order with Valid Input
	@Test
	public void testCreateOrderWithValidInput() {

		OrderService orderService = new OrderService();
		Order newOrder = new Order();
		newOrder.setQuantity(3);
		newOrder.setTotal_price(57);
		newOrder.setDelivery_time(DeliveryTime.Breakfast);
		newOrder.setOrder_status(OrderStatus.Not_delivered);
		newOrder.setCreated_by(1);
		newOrder.setAddress("Perugudi Chennai");
		newOrder.setProduct_id(3);

		assertDoesNotThrow(() -> {
			orderService.create(newOrder);
		});
	}

//	Test Order with Invalid Input
	@Test
	public void testCreateOrderWithInvalidInput() {
		OrderService orderService = new OrderService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			orderService.create(null);
		});
		String expectedMessage = "Order cannot be null";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}
	
//	Test Order Address is Null
	@Test
	public void testCreateOrderWithAddressNull() {
		
		OrderService orderService = new OrderService();
		Order newOrder = new Order();
		newOrder.setQuantity(3);
		newOrder.setTotal_price(57);
		newOrder.setDelivery_time(DeliveryTime.Breakfast);
		newOrder.setOrder_status(OrderStatus.Not_delivered);
		newOrder.setCreated_by(1);
		newOrder.setAddress(null);
		newOrder.setProduct_id(3);
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			orderService.create(newOrder);
		});
		String expectedMessage = "Address cannot be null or empty";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}
	
//	Test Order Address is Empty
	@Test
	public void testCreateOrderWithAddressEmpty() {
		
		OrderService orderService = new OrderService();
		Order newOrder = new Order();
		newOrder.setQuantity(3);
		newOrder.setTotal_price(57);
		newOrder.setDelivery_time(DeliveryTime.Breakfast);
		newOrder.setOrder_status(OrderStatus.Not_delivered);
		newOrder.setCreated_by(1);
		newOrder.setAddress("");
		newOrder.setProduct_id(3);
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			orderService.create(newOrder);
		});
		String expectedMessage = "Address cannot be null or empty";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}
	
//	Test Order Quantity valid
	@Test
	public void testCreateOrderWithInvalidQuantity() {
		
		OrderService orderService = new OrderService();
		Order newOrder = new Order();
		
		newOrder.setQuantity(0);
		newOrder.setTotal_price(57);
		newOrder.setDelivery_time(DeliveryTime.Breakfast);
		newOrder.setOrder_status(OrderStatus.Not_delivered);
		newOrder.setCreated_by(1);
		newOrder.setAddress("Perungudi Chennai");
		newOrder.setProduct_id(3);
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			orderService.create(newOrder);
		});
		
		String expectedMessage = "Quantity cannot be zero or below zero";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}
	
//	Test Order TotalPrice valid
	@Test
	public void testCreateOrderWithInvalidTotalPrice() {
		
		OrderService orderService = new OrderService();
		Order newOrder = new Order();
		
		newOrder.setQuantity(3);
		newOrder.setTotal_price(0);
		newOrder.setDelivery_time(DeliveryTime.Breakfast);
		newOrder.setOrder_status(OrderStatus.Not_delivered);
		newOrder.setCreated_by(1);
		newOrder.setAddress("Perungudi Chennai");
		newOrder.setProduct_id(3);
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			orderService.create(newOrder);
		});
		
		String expectedMessage = "Total Price cannot be zero or below zero";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}
	
//	Test Order Created By Id valid
	@Test
	public void testCreateOrderWithInvalidUserId() {
		
		OrderService orderService = new OrderService();
		Order newOrder = new Order();
		
		newOrder.setQuantity(3);
		newOrder.setTotal_price(57);
		newOrder.setDelivery_time(DeliveryTime.Breakfast);
		newOrder.setOrder_status(OrderStatus.Not_delivered);
		newOrder.setCreated_by(0);
		newOrder.setAddress("Perungudi Chennai");
		newOrder.setProduct_id(3);
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			orderService.create(newOrder);
		});
		
		String expectedMessage = "User Id cannot be zero or below zero";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}
	
//	Test Order Product Id valid
	@Test
	public void testCreateOrderWithInvalidProdcutId() {
		
		OrderService orderService = new OrderService();
		Order newOrder = new Order();
		
		newOrder.setQuantity(3);
		newOrder.setTotal_price(57);
		newOrder.setDelivery_time(DeliveryTime.Breakfast);
		newOrder.setOrder_status(OrderStatus.Not_delivered);
		newOrder.setCreated_by(3);
		newOrder.setAddress("Perungudi Chennai");
		newOrder.setProduct_id(0);
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			orderService.create(newOrder);
		});
		
		String expectedMessage = "Product Id cannot be zero or below zero";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}
	
//	Patterns
//	Test Order Quantity Pattern
	@Test
	public void testCreateOrderWithInvalidQuantityPattern() {
		
		OrderService orderService = new OrderService();
		Order newOrder = new Order();
		
		newOrder.setQuantity(12);
		newOrder.setTotal_price(57);
		newOrder.setDelivery_time(DeliveryTime.Breakfast);
		newOrder.setOrder_status(OrderStatus.Not_delivered);
		newOrder.setCreated_by(1);
		newOrder.setAddress("Perungudi Chennai");
		newOrder.setProduct_id(3);
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			orderService.create(newOrder);
		});
		
		String expectedMessage = "Quantity must be below 10";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}
	
//	Test Order Product Id is present
	@Test
	public void testCreateOrderWithNotPresentId() {
		
		OrderService orderService = new OrderService();
		Order newOrder = new Order();
		
		newOrder.setQuantity(3);
		newOrder.setTotal_price(57);
		newOrder.setDelivery_time(DeliveryTime.Breakfast);
		newOrder.setOrder_status(OrderStatus.Not_delivered);
		newOrder.setCreated_by(3);
		newOrder.setAddress("Perungudi Chennai");
		newOrder.setProduct_id(10);
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			orderService.create(newOrder);
		});
		
		String expectedMessage = "Product not found";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}
	
//	Test Order Created By Id is present
	@Test
	public void testCreateOrderCheckUserIsPresent() {
		
		OrderService orderService = new OrderService();
		Order newOrder = new Order();
		
		newOrder.setQuantity(3);
		newOrder.setTotal_price(57);
		newOrder.setDelivery_time(DeliveryTime.Breakfast);
		newOrder.setOrder_status(OrderStatus.Not_delivered);
		newOrder.setCreated_by(10);
		newOrder.setAddress("Perungudi Chennai");
		newOrder.setProduct_id(3);
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			orderService.create(newOrder);
		});
		
		String expectedMessage = "User not found";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}
	
//	Test Order Address Pattern
	@Test
	public void testCreateOrderWithAddressPattern() {
		
		OrderService orderService = new OrderService();
		Order newOrder = new Order();
		newOrder.setQuantity(12);
		newOrder.setTotal_price(57);
		newOrder.setDelivery_time(DeliveryTime.Breakfast);
		newOrder.setOrder_status(OrderStatus.Not_delivered);
		newOrder.setCreated_by(1);
		newOrder.setAddress("!@#$%^&*()(");
		newOrder.setProduct_id(3);
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			orderService.create(newOrder);
		});
		String expectedMessage = "Invalid Address Pattern";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}
}

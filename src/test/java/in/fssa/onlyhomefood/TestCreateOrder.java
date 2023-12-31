package in.fssa.onlyhomefood;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import in.fssa.onlyhomefood.model.Order;
import in.fssa.onlyhomefood.model.OrderedItems;
import in.fssa.onlyhomefood.model.OrderedItems.DeliveryTime;
import in.fssa.onlyhomefood.model.OrderedItems.OrderStatus;
import in.fssa.onlyhomefood.service.OrderService;

public class TestCreateOrder {

	// Test order with Valid Input
//	@Test
//	public void testCreateOrderWithValidInput() {
//
//		OrderService orderService = new OrderService();
//		Order newOrder = new Order();
//		newOrder.setCreatedBy(3);
//		newOrder.setDeliveryAddressId(1);
//		newOrder.setTotalPrice(59);
//
//		List<OrderedItems> itemsList = new ArrayList<>();
//
//		OrderedItems orderItems = new OrderedItems();
//
//		orderItems.setDeliveryTime(DeliveryTime.Breakfast);
//		orderItems.setOrderStatus(OrderStatus.Not_delivered);
//		orderItems.setProductId(2);
//		orderItems.setQuantityOrdered(4);
//		orderItems.setUserId(3);
//
//		OrderedItems orderItems1 = new OrderedItems();
//
//		orderItems1.setDeliveryTime(DeliveryTime.Breakfast);
//		orderItems1.setOrderStatus(OrderStatus.Not_delivered);
//		orderItems1.setProductId(0);
//		orderItems1.setQuantityOrdered(11);
//		orderItems1.setUserId(3);
//
//		OrderedItems orderItems2 = new OrderedItems();
//
//		orderItems2.setDeliveryTime(DeliveryTime.Breakfast);
//		orderItems2.setOrderStatus(OrderStatus.Not_delivered);
//		orderItems2.setProductId(1);
//		orderItems2.setQuantityOrdered(6);
//		orderItems2.setUserId(3);
//		
//		itemsList.add(orderItems);
//		itemsList.add(orderItems1);
//		itemsList.add(orderItems2);
//		
//		assertDoesNotThrow(() -> {
//			orderService.createNewOrder(newOrder, itemsList);
//		});
//	}
//
////	Test Order with Invalid Input
//	@Test
//	public void testCreateOrderWithInvalidInput() {
//		OrderService orderService = new OrderService();
//		Exception exception = assertThrows(ValidationException.class, () -> {
//			orderService.createNewOrder(null);
//		});
//		String expectedMessage = "Order cannot be null";
//		String receivedMessage = exception.getMessage();
//		assertTrue(expectedMessage.equals(receivedMessage));
//	}
//	
////	Test Order Delivery time is Null
//	@Test
//	public void testCreateOrderWithDeliveryTimeNull() {
//		
//		OrderService orderService = new OrderService();
//		Order newOrder = new Order();
//		newOrder.setQuantity(3);
//		newOrder.setTotal_price(57);
//		newOrder.setDelivery_time(null);
//		newOrder.setOrder_status(OrderStatus.Not_delivered);
//		newOrder.setCreated_by(1);
//		newOrder.setAddress("Chennai");
//		newOrder.setProduct_id(3);
//		
//		Exception exception = assertThrows(ValidationException.class, () -> {
//			orderService.createNewOrder(newOrder);
//		});
//		String expectedMessage = "Delivery Time is not selected";
//		String receivedMessage = exception.getMessage();
//		assertTrue(expectedMessage.equals(receivedMessage));
//	}
//	
////	Test Order Status is Null
//	@Test
//	public void testCreateOrderWithStatusNull() {
//		
//		OrderService orderService = new OrderService();
//		Order newOrder = new Order();
//		newOrder.setQuantity(3);
//		newOrder.setTotal_price(57);
//		newOrder.setDelivery_time(DeliveryTime.Breakfast);
//		newOrder.setOrder_status(null);
//		newOrder.setCreated_by(1);
//		newOrder.setAddress("Chennai");
//		newOrder.setProduct_id(3);
//		
//		Exception exception = assertThrows(ValidationException.class, () -> {
//			orderService.createNewOrder(newOrder);
//		});
//		String expectedMessage = "Invalid Order status";
//		String receivedMessage = exception.getMessage();
//		assertTrue(expectedMessage.equals(receivedMessage));
//	}
//	
////	Test Order Address is Null
//	@Test
//	public void testCreateOrderWithAddressNull() {
//		
//		OrderService orderService = new OrderService();
//		Order newOrder = new Order();
//		newOrder.setQuantity(3);
//		newOrder.setTotal_price(57);
//		newOrder.setDelivery_time(DeliveryTime.Breakfast);
//		newOrder.setOrder_status(OrderStatus.Not_delivered);
//		newOrder.setCreated_by(1);
//		newOrder.setAddress(null);
//		newOrder.setProduct_id(3);
//		
//		Exception exception = assertThrows(ValidationException.class, () -> {
//			orderService.createNewOrder(newOrder);
//		});
//		String expectedMessage = "Address cannot be null or empty";
//		String receivedMessage = exception.getMessage();
//		assertTrue(expectedMessage.equals(receivedMessage));
//	}
//	
////	Test Order Address is Empty
//	@Test
//	public void testCreateOrderWithAddressEmpty() {
//		
//		OrderService orderService = new OrderService();
//		Order newOrder = new Order();
//		newOrder.setQuantity(3);
//		newOrder.setTotal_price(57);
//		newOrder.setDelivery_time(DeliveryTime.Breakfast);
//		newOrder.setOrder_status(OrderStatus.Not_delivered);
//		newOrder.setCreated_by(1);
//		newOrder.setAddress("");
//		newOrder.setProduct_id(3);
//		
//		Exception exception = assertThrows(ValidationException.class, () -> {
//			orderService.createNewOrder(newOrder);
//		});
//		String expectedMessage = "Address cannot be null or empty";
//		String receivedMessage = exception.getMessage();
//		assertTrue(expectedMessage.equals(receivedMessage));
//	}
//	
////	Test Order Quantity valid
//	@Test
//	public void testCreateOrderWithInvalidQuantity() {
//		
//		OrderService orderService = new OrderService();
//		Order newOrder = new Order();
//		
//		newOrder.setQuantity(0);
//		newOrder.setTotal_price(57);
//		newOrder.setDelivery_time(DeliveryTime.Breakfast);
//		newOrder.setOrder_status(OrderStatus.Not_delivered);
//		newOrder.setCreated_by(1);
//		newOrder.setAddress("Perungudi Chennai");
//		newOrder.setProduct_id(3);
//		
//		Exception exception = assertThrows(ValidationException.class, () -> {
//			orderService.createNewOrder(newOrder);
//		});
//		
//		String expectedMessage = "Quantity cannot be zero or below zero";
//		String receivedMessage = exception.getMessage();
//		assertTrue(expectedMessage.equals(receivedMessage));
//	}
//	
////	Test Order TotalPrice valid
//	@Test
//	public void testCreateOrderWithInvalidTotalPrice() {
//		
//		OrderService orderService = new OrderService();
//		Order newOrder = new Order();
//		
//		newOrder.setQuantity(3);
//		newOrder.setTotal_price(0);
//		newOrder.setDelivery_time(DeliveryTime.Breakfast);
//		newOrder.setOrder_status(OrderStatus.Not_delivered);
//		newOrder.setCreated_by(1);
//		newOrder.setAddress("Perungudi Chennai");
//		newOrder.setProduct_id(3);
//		
//		Exception exception = assertThrows(ValidationException.class, () -> {
//			orderService.createNewOrder(newOrder);
//		});
//		
//		String expectedMessage = "Total Price cannot be zero or below zero";
//		String receivedMessage = exception.getMessage();
//		assertTrue(expectedMessage.equals(receivedMessage));
//	}
//	
////	Test Order Created By Id Invalid
//	@Test
//	public void testCreateOrderWithInvalidUserId() {
//		
//		OrderService orderService = new OrderService();
//		Order newOrder = new Order();
//		
//		newOrder.setQuantity(3);
//		newOrder.setTotal_price(57);
//		newOrder.setDelivery_time(DeliveryTime.Breakfast);
//		newOrder.setOrder_status(OrderStatus.Not_delivered);
//		newOrder.setCreated_by(0);
//		newOrder.setAddress("Perungudi Chennai");
//		newOrder.setProduct_id(3);
//		
//		Exception exception = assertThrows(ValidationException.class, () -> {
//			orderService.createNewOrder(newOrder);
//		});
//		
//		String expectedMessage = "User Id cannot be zero or below zero";
//		String receivedMessage = exception.getMessage();
//		assertTrue(expectedMessage.equals(receivedMessage));
//	}
//	
////	Test Order Product Id valid
//	@Test
//	public void testCreateOrderWithInvalidProdcutId() {
//		
//		OrderService orderService = new OrderService();
//		Order newOrder = new Order();
//		
//		newOrder.setQuantity(3);
//		newOrder.setTotal_price(57);
//		newOrder.setDelivery_time(DeliveryTime.Breakfast);
//		newOrder.setOrder_status(OrderStatus.Not_delivered);
//		newOrder.setCreated_by(3);
//		newOrder.setAddress("Perungudi Chennai");
//		newOrder.setProduct_id(0);
//		
//		Exception exception = assertThrows(ValidationException.class, () -> {
//			orderService.createNewOrder(newOrder);
//		});
//		
//		String expectedMessage = "Product Id cannot be zero or below zero";
//		String receivedMessage = exception.getMessage();
//		assertTrue(expectedMessage.equals(receivedMessage));
//	}
//	
////	Patterns
////	Test Order Quantity Pattern
//	@Test
//	public void testCreateOrderWithInvalidQuantityPattern() {
//		
//		OrderService orderService = new OrderService();
//		Order newOrder = new Order();
//		
//		newOrder.setQuantity(12);
//		newOrder.setTotal_price(57);
//		newOrder.setDelivery_time(DeliveryTime.Breakfast);
//		newOrder.setOrder_status(OrderStatus.Not_delivered);
//		newOrder.setCreated_by(1);
//		newOrder.setAddress("Perungudi Chennai");
//		newOrder.setProduct_id(3);
//		
//		Exception exception = assertThrows(ValidationException.class, () -> {
//			orderService.createNewOrder(newOrder);
//		});
//		
//		String expectedMessage = "Quantity must be below 10";
//		String receivedMessage = exception.getMessage();
//		assertTrue(expectedMessage.equals(receivedMessage));
//	}
//	
////	Test Order Product Id is present
//	@Test
//	public void testCreateOrderWithNotPresentId() {
//		
//		OrderService orderService = new OrderService();
//		Order newOrder = new Order();
//		
//		newOrder.setQuantity(3);
//		newOrder.setTotal_price(57);
//		newOrder.setDelivery_time(DeliveryTime.Breakfast);
//		newOrder.setOrder_status(OrderStatus.Not_delivered);
//		newOrder.setCreated_by(1);
//		newOrder.setAddress("Perungudi Chennai");
//		newOrder.setProduct_id(1000);
//		
//		Exception exception = assertThrows(ValidationException.class, () -> {
//			orderService.createNewOrder(newOrder);
//		});
//		
//		String expectedMessage = "Product not found";
//		String receivedMessage = exception.getMessage();
//		assertTrue(expectedMessage.equals(receivedMessage));
//	}
//	
////	Test Order Created By Id is present
//	@Test
//	public void testCreateOrderCheckUserIsPresent() {
//		
//		OrderService orderService = new OrderService();
//		Order newOrder = new Order();
//		
//		newOrder.setQuantity(3);
//		newOrder.setTotal_price(57);
//		newOrder.setDelivery_time(DeliveryTime.Breakfast);
//		newOrder.setOrder_status(OrderStatus.Not_delivered);
//		newOrder.setCreated_by(1000);
//		newOrder.setAddress("Perungudi Chennai");
//		newOrder.setProduct_id(3);
//		
//		Exception exception = assertThrows(ValidationException.class, () -> {
//			orderService.createNewOrder(newOrder);
//		});
//		
//		String expectedMessage = "User not found";
//		String receivedMessage = exception.getMessage();
//		assertTrue(expectedMessage.equals(receivedMessage));
//	}
//	
////	Test Order Address Pattern
//	@Test
//	public void testCreateOrderWithAddressPattern() {
//		
//		OrderService orderService = new OrderService();
//		Order newOrder = new Order();
//		newOrder.setQuantity(4);
//		newOrder.setTotal_price(57);
//		newOrder.setDelivery_time(DeliveryTime.Breakfast);
//		newOrder.setOrder_status(OrderStatus.Not_delivered);
//		newOrder.setCreated_by(1);
//		newOrder.setAddress("!@#$%^&*()(");
//		newOrder.setProduct_id(3);
//		
//		Exception exception = assertThrows(ValidationException.class, () -> {
//			orderService.createNewOrder(newOrder);
//		});
//		String expectedMessage = "Invalid Address Pattern";
//		String receivedMessage = exception.getMessage();
//		assertTrue(expectedMessage.equals(receivedMessage));
//	}
}

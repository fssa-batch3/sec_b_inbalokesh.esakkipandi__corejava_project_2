package in.fssa.onlyhomefood.model;

public class OrderedItems {

	private int orderId;
	private int productId;
	private int quantityOrdered;
	private int userId;
	private DeliveryTime deliveryTime;
	private OrderStatus orderStatus;

	public enum DeliveryTime {
		Breakfast, Lunch, Dinner
	}

	public enum OrderStatus {
		Delivered, Not_delivered, Cancelled
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuantityOrdered() {
		return quantityOrdered;
	}

	public void setQuantityOrdered(int quantityOrdered) {
		this.quantityOrdered = quantityOrdered;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public DeliveryTime getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(DeliveryTime deliverTime) {
		this.deliveryTime = deliverTime;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Override
	public String toString() {
		return "OrderedItems [orderId=" + orderId + ", productId=" + productId + ", quantityOrdered=" + quantityOrdered
				+ ", userId=" + userId + ", deliverTime=" + deliveryTime + ", orderStatus=" + orderStatus + "]";
	}

}

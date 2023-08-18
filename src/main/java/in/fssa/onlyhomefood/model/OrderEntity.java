package in.fssa.onlyhomefood.model;

public abstract class OrderEntity {
	
	private int id;
	private int quantity;
	private int total_price;
	private DeliveryTime delivery_time;
	private OrderStatus order_status;
	private int created_by;
	private String address;
	private int product_id;
	
	public enum DeliveryTime{
		Breakfast,
		Lunch,
		Dinner
	}
	
	public enum OrderStatus{
		Delivered,
		Not_delivered,
		Cancelled
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getTotal_price() {
		return total_price;
	}

	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}

	public DeliveryTime getDelivery_time() {
		return delivery_time;
	}

	public void setDelivery_time(DeliveryTime delivery_time) {
		this.delivery_time = delivery_time;
	}

	public OrderStatus getOrder_status() {
		return order_status;
	}

	public void setOrder_status(OrderStatus order_status) {
		this.order_status = order_status;
	}

	public int getCreated_by() {
		return created_by;
	}

	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	@Override
	public String toString() {
		return "OrderEntity [id = " + id + ", quantity = " + quantity + ", total_price = " + total_price + ", delivery_time = "
				+ delivery_time + ", order_status = " + order_status + ", created_by = " + created_by + ", address = "
				+ address + "]";
	}

}

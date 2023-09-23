package in.fssa.onlyhomefood.model;

import java.sql.Timestamp;

public abstract class OrderEntity {
	
	private int id;
	private int totalPrice;
	private int createdBy;
	private int deliveryAddressId;
	private Timestamp createdAt;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public int getDeliveryAddressId() {
		return deliveryAddressId;
	}

	public void setDeliveryAddressId(int deliveryAddressId) {
		this.deliveryAddressId = deliveryAddressId;
	}
	
	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "OrderEntity [id=" + id + ", totalPrice=" + totalPrice + ", createdBy=" + createdBy
			  + ", deliveryAddressId=" + deliveryAddressId + ", createdAt=" + createdAt + "]";
	}

}

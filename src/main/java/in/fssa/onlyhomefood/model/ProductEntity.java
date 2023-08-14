package in.fssa.onlyhomefood.model;

public class ProductEntity {

	private int id;
	private String name;
	private String type;
	private int quantity;
	private String quantityType;
	private boolean is_active;

	public ProductEntity() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quanity) {
		this.quantity = quanity;
	}

	public String getQuantityType() {
		return quantityType;
	}

	public void setQuantityType(String quantityType) {
		this.quantityType = quantityType;
	}

	public boolean isIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}

	
	@Override
	public String toString() {
		return "ProductEntity [id=" + id + ", name=" + name + ", type=" + type + ", quantity=" + quantity
				+ ", quantityType=" + quantityType + ", is_active=" + is_active + "]";
	}

	int compareTo(ProductEntity o) {

		if (this.getId() == o.getId()) {
			return 0;
		} else {
			if (this.getId() > o.getId()) {
				return 1;
			} else {
				return -1;
			}
//			 return ( this.getProduct_id() > o.getProduct_id()) ? 1: -1;
		}
	}

}

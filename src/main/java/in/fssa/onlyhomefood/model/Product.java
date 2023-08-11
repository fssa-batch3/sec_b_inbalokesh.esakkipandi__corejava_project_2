package in.fssa.onlyhomefood.model;

public class Product {
	
	private int product_id;
	private String name;
	private String type;
	private String description;
	private boolean is_active;
	
	public Product()
	{
		
	}
	public Product(int product_id, String name, String type, String description, boolean is_active) {
		super();
		this.product_id = product_id;
		this.name = name;
		this.type = type;
		this.description = description;
		this.is_active = is_active;
	}
	
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isIs_active() {
		return is_active;
	}
	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}
	
	@Override
	public String toString() {
		return "Product [product_id=" + product_id + ", name=" + name + ", type=" + type + ", description="
				+ description + ", is_active=" + is_active + "]";
	}
	
	public int compareTo(Product o) {

		if (this.getProduct_id() == o.getProduct_id()) {
			return 0;
		} else {
			if (this.getProduct_id() > o.getProduct_id()) {
				return 1;
			} else {
				return -1;
			}
//			 return ( this.getProduct_id() > o.getProduct_id()) ? 1: -1;
		}
	}
	

}

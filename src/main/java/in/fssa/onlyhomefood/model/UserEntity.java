package in.fssa.onlyhomefood.model;

public abstract class UserEntity {
	
	private int id;
	private String user_name;
	private String email;
	private long phone_number;
	private String password;
	private boolean is_active;
	
	public UserEntity() {
		
	}
	
	public UserEntity(int id, String user_name, String email, long phone_number, String password, boolean is_active) {
		super();
		this.id = id;
		this.user_name = user_name;
		this.email = email;
		this.phone_number = phone_number;
		this.password = password;
		this.is_active = is_active;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return user_name;
	}
	public void setName(String user_name) {
		this.user_name = user_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getMobNumber() {
		return phone_number;
	}
	public void setMobNumber(long phone_number) {
		this.phone_number = phone_number;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isActive() {
		return is_active;
	}
	public void setActive(boolean is_active) {
		this.is_active = is_active;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", user_name=" + user_name + ", email=" + email + ", phone_number=" + phone_number
				+ ", password=" + password + ", is_active=" + is_active + "]";
	}
	
	public int compareTo(UserEntity o) {

		if (this.getId() == o.getId()) {
			return 0;
		} else {
			if (this.getId() > o.getId()) {
				return 1;
			} else {
				return -1;
			}
		}
	}

}

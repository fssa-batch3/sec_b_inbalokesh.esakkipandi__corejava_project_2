package in.fssa.onlyhomefood.model;

public class Address {
	
	private int id;
	private String name;
	private long phoneNumber;
	private String streetName;
	private String location;
	private String townName;
	private String city;
	private String state;
	private int pinCode;
	private int userId;
	private boolean defaultStatus;
	private boolean isActive;
	
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
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getTownName() {
		return townName;
	}
	public void setTownName(String townName) {
		this.townName = townName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getPinCode() {
		return pinCode;
	}
	public void setPinCode(int pinCode) {
		this.pinCode = pinCode;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public boolean getDefaultStatus() {
		return defaultStatus;
	}
	public void setDefaultStatus(boolean defaultStatus) {
		this.defaultStatus = defaultStatus;
	}
	public boolean getActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	@Override
	public String toString() {
		return "id:" + id + "\nname:" + name + "\nphoneNumber:" + phoneNumber + "\nStreetName:" + streetName
				+ "\nlocation:" + location + "\ntownName:" + townName + "\ncity:" + city + "\nstate:" + state
				+ "\npinCode:" + pinCode + "\nuserId:" + userId + "\ndefaultStatus:" + defaultStatus + "\nisActive:"
				+ isActive + "\n";
	}
	
}

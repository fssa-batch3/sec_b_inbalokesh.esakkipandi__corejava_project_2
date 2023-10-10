package in.fssa.onlyhomefood;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import in.fssa.onlyhomefood.model.Address;
import in.fssa.onlyhomefood.service.AddressService;
import in.fssa.onlyhomefood.util.RandomGenerator;

public class TestCreateAddress {

	RandomGenerator random = new RandomGenerator();

	@Test
	void testCreateAddressWithValidInput() {
		AddressService addressService = new AddressService();
		Address address = new Address();
		
		address.setName("Inba");
		address.setPhoneNumber(9876543210l);
		address.setLocation("No.123, C.L.V nagar");
		address.setStreetName("No.123," + random.generateRandomString(5) + "street");
		address.setTownName("Kanathur");
		address.setCity("Kanchipuram");
		address.setState("TN");
		address.setPinCode(603112);
		address.setUserId(2);
		address.setDefaultStatus(false);
		
		assertDoesNotThrow(() -> {
			addressService.createNewAddress(address);
		});
	}
//	
//	@Test
//	void testCreateExistAddress() {
//		AddressService addressService = new AddressService();
//		Address address = new Address();
//		
//		address.setName("Inba");
//		address.setPhoneNumber(9876543210l);
//		address.setLocation("No.123, C.L.V nagar");
//		address.setStreetName("No.123, C.L.V nagar");
//		address.setTownName("Kanathur");
//		address.setCity("Chennai");
//		address.setState("TN");
//		address.setPinCode(603115);
//		address.setUserId(4);
//		address.setDefaultStatus(true);
//		
//		assertDoesNotThrow(() -> {
//			addressService.createNewAddress(address);
//		});
//	}

}

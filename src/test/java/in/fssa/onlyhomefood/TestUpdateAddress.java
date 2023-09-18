package in.fssa.onlyhomefood;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import in.fssa.onlyhomefood.model.Address;
import in.fssa.onlyhomefood.service.AddressService;

public class TestUpdateAddress {
	
	
	@Test
	void testUpdateAddressWithValidInput() {
		AddressService addressService = new AddressService();
		Address address = new Address();
		
		address.setName("Inba Lokesh");
		address.setPhoneNumber(9876543210l);
		address.setLocation("No.123, C.L.V nagar");
		address.setStreetName("No.123, C.L.V nagar");
		address.setTownName("Kanathur");
		address.setCity("Kanchipuram");
		address.setState("TN");
		address.setPinCode(603112);
		address.setUserId(3);
		address.setDefaultStatus(true);
		
		assertDoesNotThrow(() -> {
			addressService.updadeAddress(3, address);
		});
	}
	
//	Test for Update Default Staus
	
	@Test
	void testUpdateDefaultStatusWithValidInput() {
		
		AddressService addressService = new AddressService();
		
		assertDoesNotThrow(() -> {
			addressService.setAsDefault(3, 3);
		});

	}

}

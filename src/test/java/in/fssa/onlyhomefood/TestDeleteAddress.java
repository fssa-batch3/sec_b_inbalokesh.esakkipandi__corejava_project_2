package in.fssa.onlyhomefood;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import in.fssa.onlyhomefood.service.AddressService;

public class TestDeleteAddress {
	
	@Test
	void testDeleteAddressWithValidInput() {
		
		AddressService addressService = new AddressService();
		
		assertDoesNotThrow(() -> {
			addressService.deleteAddress(3);
		});
	}

}

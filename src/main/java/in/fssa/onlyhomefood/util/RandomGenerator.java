package in.fssa.onlyhomefood.util;

import java.util.Random;

public class RandomGenerator {
	
	public String generateRandomString(int length) {
	    String characters = "abcdefghijklmnopqrstuvwxyz";
	    StringBuilder randomString = new StringBuilder();
	    Random random = new Random();
	    for (int i = 0; i < length; i++) {
	        int index = random.nextInt(characters.length());
	        randomString.append(characters.charAt(index));
	    }
	    return randomString.toString();
	}
	
	public long generateRandomNumber(int length) {
		
		long min = 6000000001l; // Minimum value for the random number
		long max = 9999999999l; // Maximum value for the random number
		int numberOfRandomNumbers = 100; // Set the number of random numbers you want to generate

		Random rand = new Random();
		long randomNumber = 0;
		for (int i = 0; i < numberOfRandomNumbers; i++) {
			randomNumber = rand.nextLong(max - min + 1) + min;

		}
		return randomNumber;
	}


}

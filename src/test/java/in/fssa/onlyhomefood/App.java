package in.fssa.onlyhomefood;

import java.security.NoSuchAlgorithmException;

import in.fssa.onlyhomefood.util.PasswordUtil;

public class App {

	public static void main(String[] args) {

		
		String password = "karthiKN@354";
		
		String has;
		try {
			has = PasswordUtil.encryptPassword(password);
			System.out.println(has);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		
	}

}

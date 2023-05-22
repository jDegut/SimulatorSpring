package fr.polytech.simulatorspring.service;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Utils {

	public static String cryptPassword(String password) {
		return new BCryptPasswordEncoder().encode(password);
	}
	public static boolean comparePassword(String clearPassword, String cipheredPassword) {
		return BCrypt.checkpw(clearPassword, cipheredPassword);
	}

}

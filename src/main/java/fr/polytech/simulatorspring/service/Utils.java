package fr.polytech.simulatorspring.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Utils {

	public static String cryptPassword(String password) {
		return new BCryptPasswordEncoder().encode(password);
	}

}

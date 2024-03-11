package com.HopeLunchSystem.HLS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class HlsApplication {

	public static void main(String[] args) {
		String password = "123456";

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(password);

		System.out.println("Mật khẩu gốc: " + password);
		System.out.println("Mật khẩu đã mã hóa: " + encodedPassword);
		SpringApplication.run(HlsApplication.class, args);
	}

}

package com.nnk.springboot;

import com.nnk.springboot.config.AppProperties;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.domain.validators.BidValidator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class Application {

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}

}

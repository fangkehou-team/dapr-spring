package icu.fangkehou.test.daprtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DaprTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(DaprTestApplication.class, args);
	}

}

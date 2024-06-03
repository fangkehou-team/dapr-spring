package icu.fangkehou.demo.dapr_client_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DaprClientDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DaprClientDemoApplication.class, args);
	}

}

package isa.isaeurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class IsaEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(IsaEurekaServerApplication.class, args);
	}

}

package eu.dreamTeam.isabackend;

import eu.dreamTeam.isabackend.model.Account;
import eu.dreamTeam.isabackend.repository.AccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class IsaBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(IsaBackendApplication.class, args);
	}

	@Bean
	public PasswordEncoder encoder(){
		return new BCryptPasswordEncoder();
	}
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}


}
@Component
class DemoCommandLineRunner implements CommandLineRunner {
	@Override
	public void run(String... args) throws Exception {
	}
}
package eu.dreamTeam.isabackend;

import eu.dreamTeam.isabackend.model.*;
import eu.dreamTeam.isabackend.model.enums.BloodType;
import eu.dreamTeam.isabackend.model.enums.DayName;
import eu.dreamTeam.isabackend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@SpringBootApplication
public class IsaBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(IsaBackendApplication.class, args);
	}


}
@Component
class DemoCommandLineRunner implements CommandLineRunner {
	@Override
	public void run(String... args) throws Exception {



	}
}
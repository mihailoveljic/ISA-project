package eu.dreamTeam.isabackend;

import eu.dreamTeam.isabackend.model.BloodSample;
import eu.dreamTeam.isabackend.model.enums.BloodType;
import eu.dreamTeam.isabackend.repository.BloodSampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class IsaBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(IsaBackendApplication.class, args);
	}

	/*@Component
	class DemoCommandLineRunner implements CommandLineRunner {

		@Autowired
		private BloodSampleRepository bloodSampleRepository;

		@Override
		public void run(String... args) throws Exception {

			BloodSample bs1 = new BloodSample();
			bs1.setBloodType(BloodType.A_POSITIVE);
			bs1.setAmount(100);
			bloodSampleRepository.save(bs1);
			BloodSample bs2 = new BloodSample();
			bs2.setBloodType(BloodType.B_POSITIVE);
			bs2.setAmount(200);
			bloodSampleRepository.save(bs2);
			BloodSample bs3 = new BloodSample();
			bs3.setBloodType(BloodType.AB_NEGATIVE);
			bs3.setAmount(300);
			bloodSampleRepository.save(bs3);
			BloodSample bs4 = new BloodSample();
			bs4.setBloodType(BloodType.O_NEGATIVE);
			bs4.setAmount(400);
			bloodSampleRepository.save(bs4);

		}
	}*/
}

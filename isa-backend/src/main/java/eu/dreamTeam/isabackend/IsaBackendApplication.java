package eu.dreamTeam.isabackend;

import eu.dreamTeam.isabackend.model.ApiKey;
import eu.dreamTeam.isabackend.model.BloodBank;
import eu.dreamTeam.isabackend.model.BloodSample;
import eu.dreamTeam.isabackend.model.enums.BloodType;
import eu.dreamTeam.isabackend.repository.ApiKeyRepository;
import eu.dreamTeam.isabackend.repository.BloodBankRepository;
import eu.dreamTeam.isabackend.repository.BloodSampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@SpringBootApplication
public class IsaBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(IsaBackendApplication.class, args);
	}

	/*@Component
	class DemoCommandLineRunner implements CommandLineRunner {
		@Autowired
		private BloodBankRepository bloodBankRepository;

		@Override
		public void run(String... args) throws Exception {
			BloodBank bb = new BloodBank();
			bb.setDeleted(false);
			bb.setName("Blood bank 1");

			BloodSample bs1 = new BloodSample();
			bs1.setBloodType(BloodType.A_POSITIVE);
			bs1.setAmount(100);
			bs1.setBloodBank(bb);

			BloodSample bs2 = new BloodSample();
			bs2.setBloodType(BloodType.B_POSITIVE);
			bs2.setAmount(200);
			bs2.setBloodBank(bb);

			BloodSample bs3 = new BloodSample();
			bs3.setBloodType(BloodType.AB_NEGATIVE);
			bs3.setAmount(300);
			bs3.setBloodBank(bb);

			BloodSample bs4 = new BloodSample();
			bs4.setBloodType(BloodType.O_NEGATIVE);
			bs4.setAmount(400);
			bs4.setBloodBank(bb);

			Set<BloodSample> bss = new HashSet<>();
			bss.add(bs1);
			bss.add(bs2);
			bss.add(bs3);
			bss.add(bs4);
			bb.setBloodSamples(bss);

			ApiKey ak = new ApiKey();
			ak.setBloodBank(bb);
			ak.setApiKeyCode(UUID.randomUUID().toString());
			ak.setValid(true);

			Set<ApiKey> aks = new HashSet<>();
			aks.add(ak);
			bb.setApiKeys(aks);

			bloodBankRepository.save(bb);

		}
	}*/
}

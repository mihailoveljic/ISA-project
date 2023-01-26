package eu.DreamTeam.isabackend;
import eu.dreamTeam.isabackend.model.BloodBank;
import eu.dreamTeam.isabackend.service.BloodBankService;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RateLimiterExampleTests {

    private final Logger LOG = LoggerFactory.getLogger(BloodBankService.class);

    @Autowired
    private BloodBankService bloodBankService;


    @Test
    public void testRateLimiterExceptionScenario() throws InterruptedException {
        LOG.info("Prvi poziv metode anotirane sa @RateLimiter");
        List<BloodBank> bloodBanks = bloodBankService.getAllBloodBanks();
        assertNotEquals(0, bloodBanks.size());

        LOG.info("Poziv metode anotirane sa @RateLimiter U OKVIRU ogranicenog vremena od 5 sekundi");
        assertThrows(RequestNotPermitted.class, () -> bloodBankService.getAllBloodBanks());

        Thread.sleep(5000); // da bi ogranicen interval od 5 sekundi prosao

        LOG.info("Poziv metode anotirane sa @RateLimiter NAKON ogranicenog vremena od 5 sekundi");
        bloodBanks = bloodBankService.getAllBloodBanks();
        assertNotEquals(0, bloodBanks.size());
    }

}

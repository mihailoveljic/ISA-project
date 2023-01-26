package eu.DreamTeam.isabackend;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import eu.dreamTeam.isabackend.dto.BloodBankDTO;
import eu.dreamTeam.isabackend.model.Appointment;
import eu.dreamTeam.isabackend.model.BloodBank;
import eu.dreamTeam.isabackend.model.BloodSample;
import eu.dreamTeam.isabackend.model.enums.AppointmentStatus;
import eu.dreamTeam.isabackend.repository.AppointmentRepository;
import eu.dreamTeam.isabackend.repository.BloodBankRepository;
import eu.dreamTeam.isabackend.repository.BloodSampleRepository;
import eu.dreamTeam.isabackend.service.BloodBankService;
import eu.dreamTeam.isabackend.service.BloodSampleService;
import eu.dreamTeam.isabackend.service.appointment.AppointmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BloodSampleTests {


    @Autowired
    private BloodSampleService bloodSampleService;
    @Autowired
    private BloodSampleRepository bloodSampleRepository;

    @Test(expected = ObjectOptimisticLockingFailureException.class)
    public void testOptimisticLockingScenario() throws Throwable {

        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<?> future1 = executor.submit(new Runnable() {

            @Override
            public void run() {
                System.out.println("Startovan Thread 1");
                Optional<BloodSample> bloodSample = bloodSampleRepository.findById(1L);
                try { Thread.sleep(10); } catch (InterruptedException e) {}// thread uspavan na 3 sekunde da bi drugi thread mogao da izvrsi istu operaciju
                //
                bloodSampleService.updateBloodSampleForPurchase(bloodSample.orElse(new BloodSample()), 100);// bacice ObjectOptimisticLockingFailureException
            }
        });
        executor.submit(new Runnable() {

            @Override
            public void run() {
                System.out.println("Startovan Thread 2");
                Optional<BloodSample> bloodSample = bloodSampleRepository.findById(1L);
                bloodSampleService.updateBloodSampleForPurchase(bloodSample.orElse(new BloodSample()), 100);// bacice ObjectOptimisticLockingFailureException
            }
        });
        try {
            future1.get(); // podize ExecutionException za bilo koji izuzetak iz prvog child threada
        } catch (ExecutionException e) {
            System.out.println("Exception from thread " + e.getCause().getClass()); // u pitanju je bas ObjectOptimisticLockingFailureException
            throw e.getCause();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();

    }

}

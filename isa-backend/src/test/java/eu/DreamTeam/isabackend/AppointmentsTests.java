package eu.DreamTeam.isabackend;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import eu.dreamTeam.isabackend.model.Appointment;
import eu.dreamTeam.isabackend.model.enums.AppointmentStatus;
import eu.dreamTeam.isabackend.repository.AppointmentRepository;
import eu.dreamTeam.isabackend.service.appointment.AppointmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppointmentsTests {


    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Test(expected = ObjectOptimisticLockingFailureException.class)
    public void testOptimisticLockingScenario() throws Throwable {

        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<?> future1 = executor.submit(new Runnable() {

            @Override
            public void run() {
                System.out.println("Startovan Thread 1");
                Appointment defaultAppointment = new Appointment();
                Optional<Appointment> appointmentToUpdate = appointmentRepository.findById(1L);// ocitan objekat sa id 1
                try { Thread.sleep(1000); } catch (InterruptedException e) {}// thread uspavan na 3 sekunde da bi drugi thread mogao da izvrsi istu operaciju
                appointmentService.scheduleAppointment(appointmentService.FromAppointmentToScheduleAppointmentDto(appointmentToUpdate.orElse(defaultAppointment)));// bacice ObjectOptimisticLockingFailureException
            }
        });
        executor.submit(new Runnable() {

            @Override
            public void run() {
                System.out.println("Startovan Thread 2");
                Appointment defaultAppointment = new Appointment();
                Optional<Appointment> appointmentToUpdate = appointmentRepository.findById(1L);// ocitan isti objekat sa id 1 kao i iz prvog threada
                /*
                 * prvi ce izvrsiti izmenu i izvrsi upit:
                 * Hibernate:
                 *     update
                 *         product
                 *     set
                 *         name=?,
                 *         origin=?,
                 *         price=?,
                 *         version=?
                 *     where
                 *         id=?
                 *         and version=?
                 *
                 * Moze se primetiti da automatski dodaje na upit i proveru o verziji
                 */
                appointmentService.scheduleAppointment(appointmentService.FromAppointmentToScheduleAppointmentDto(appointmentToUpdate.orElse(defaultAppointment)));// bacice ObjectOptimisticLockingFailureException
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

package eu.DreamTeam.isabackend;

import eu.dreamTeam.isabackend.dto.CreateAppointmentDTO;
import eu.dreamTeam.isabackend.model.enums.AppointmentStatus;
import eu.dreamTeam.isabackend.service.appointment.AppointmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


@RunWith(SpringRunner.class)
@SpringBootTest
public class IsaBackendApplicationTests {

	@Autowired
	private AppointmentService appointmentService;

	private int index = 0;
	@Test(expected = InterruptedException.class)
	public void createFreeAppointment() throws Throwable {
		ExecutorService executor = Executors.newFixedThreadPool(2);

		Future<?> future1 = executor.submit(() -> {
			System.out.println("Stvoren Thread 1");
			Set<Long> staff = new HashSet<>();
			staff.add(Long.parseLong("1"));
			CreateAppointmentDTO appointment = new CreateAppointmentDTO("2023-02-23-12-30", 60, AppointmentStatus.FREE, "opis 1", 1000, Long.parseLong("1"), staff);
			try { Thread.sleep(6000); } catch (InterruptedException e) {}
			System.out.println("Pokrenut Thread 1");
			try {
				appointmentService.createAppointment(appointment);
			} catch (Exception e) {
				index = 1;
				throw new RuntimeException(e);
			}
		});

		executor.submit(() -> {
			System.out.println("Stvoren Thread 2");
			Set<Long> staff = new HashSet<>();
			staff.add(Long.parseLong("1"));
			CreateAppointmentDTO appointment = new CreateAppointmentDTO("2023-02-25-12-30", 60, AppointmentStatus.FREE, "opis 3", 1500, Long.parseLong("1"), staff);
			try { Thread.sleep(3000); } catch (InterruptedException e) {}
			System.out.println("Pokrenut Thread 2");
			try {
				appointmentService.createAppointment(appointment);
			} catch (Exception e) {
				index = 2;
				throw new RuntimeException(e);
			}
		});

		executor.submit(() -> {
			System.out.println("Stvoren Thread 3");
			Set<Long> staff = new HashSet<>();
			staff.add(Long.parseLong("1"));
			CreateAppointmentDTO appointment = new CreateAppointmentDTO("2023-02-24-12-30", 60, AppointmentStatus.FREE, "opis 2", 1200, Long.parseLong("1"), staff);
			System.out.println("Pokrenut Thread 3");
			try {
				appointmentService.createAppointment(appointment);
			} catch (Exception e) {
				index = 3;
				throw new RuntimeException(e);
			}
		});

		try {
			future1.get();
		} catch (ExecutionException e) {
			System.out.println("Exception from thread " + index + ": " + e.getCause().getCause().getClass());
			throw e.getCause().getCause();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		executor.shutdown();
	}

}

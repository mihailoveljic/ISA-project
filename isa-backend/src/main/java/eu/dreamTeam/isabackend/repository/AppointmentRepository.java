package eu.dreamTeam.isabackend.repository;

import eu.dreamTeam.isabackend.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}

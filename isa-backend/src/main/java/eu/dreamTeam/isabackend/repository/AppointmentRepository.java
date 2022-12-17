package eu.dreamTeam.isabackend.repository;

import com.fasterxml.jackson.annotation.JsonFormat;
import eu.dreamTeam.isabackend.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query(value = "select * from appointment where status like 'FREE'", nativeQuery = true)
    List<Appointment> findAllFreeAppointments();

    List<Appointment> findAllByUserEmail(String userEmail);
    
    @Query(value = "select * from appointment where date like ?1", nativeQuery = true)
    List<Appointment> findAllAppointmentsBySelectedDateTime(LocalDateTime selectedDateTime);
}

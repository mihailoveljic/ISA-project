package eu.dreamTeam.isabackend.repository;

import eu.dreamTeam.isabackend.model.BloodBank;
import eu.dreamTeam.isabackend.model.WorkTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkTimeRepository extends JpaRepository<WorkTime, Long> {
    WorkTime getWorkTimeById(Long id);
}

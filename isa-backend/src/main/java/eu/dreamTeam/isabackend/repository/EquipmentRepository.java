package eu.dreamTeam.isabackend.repository;

import eu.dreamTeam.isabackend.model.Equipment;
import eu.dreamTeam.isabackend.model.enums.EquipmentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

    @Query(value = "select count(*) from equipment where equipment_type like ?1", nativeQuery = true)
    int getEquipment(String equipmentType);

    @Query(value = "select sum(amount) from equipment where equipment_type like ?1", nativeQuery = true)
    double getEquipmentAmount(String equipmentType);

    Equipment findByEquipmentType(EquipmentType Equipment);
}

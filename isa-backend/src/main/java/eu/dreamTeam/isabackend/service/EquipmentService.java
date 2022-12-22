package eu.dreamTeam.isabackend.service;

import eu.dreamTeam.isabackend.model.Equipment;
import eu.dreamTeam.isabackend.model.enums.EquipmentType;
import eu.dreamTeam.isabackend.repository.EquipmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;
    public EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    public int getEquipment(String equipmentType){
        return equipmentRepository.getEquipment(equipmentType);
    }

    public double getEquipmentAmount(String equipmentType){
        return equipmentRepository.getEquipmentAmount(equipmentType);
    }

    public List<Equipment> getAll(){
        return equipmentRepository.findAll();
    }

    public void substractEquipment(EquipmentType equipmentType, double amount) {
        Equipment equipmentFromDb = equipmentRepository.findByEquipmentType(equipmentType);
        if(canSubstractBloodSamples(equipmentType, amount)){
            equipmentFromDb.setAmount(equipmentFromDb.getAmount() - amount);
            equipmentRepository.save(equipmentFromDb);
        }
    }
    public boolean canSubstractBloodSamples(EquipmentType equipmentType, double amount) {
        Equipment equipmentFromDb = equipmentRepository.findByEquipmentType(equipmentType);
        return !(equipmentFromDb.getAmount() - amount < 0);
    }
}

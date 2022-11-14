package eu.dreamTeam.isabackend.service;

import eu.dreamTeam.isabackend.model.SystemAdmin;
import eu.dreamTeam.isabackend.repository.SystemAdminRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemAdminService {
    private final SystemAdminRepository systemAdminRepository;

    public  SystemAdminService(SystemAdminRepository systemAdminRepository){
        this.systemAdminRepository = systemAdminRepository;
    }

    public List<SystemAdmin> getAllAdmins(){
        return systemAdminRepository.findAll();
    }
}

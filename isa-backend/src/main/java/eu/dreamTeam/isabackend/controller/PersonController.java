package eu.dreamTeam.isabackend.controller;

import eu.dreamTeam.isabackend.dto.PersonDTO;
import eu.dreamTeam.isabackend.dto.StaffDTO;
import eu.dreamTeam.isabackend.model.Person;
import eu.dreamTeam.isabackend.model.Staff;
import eu.dreamTeam.isabackend.model.SystemAdmin;
import eu.dreamTeam.isabackend.model.User;
import eu.dreamTeam.isabackend.service.AccountService;
import eu.dreamTeam.isabackend.service.StaffService;
import eu.dreamTeam.isabackend.service.SystemAdminService;
import eu.dreamTeam.isabackend.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/person")
public class PersonController {
    private final ModelMapper modelMapper;
    private final StaffService staffService;
    private final AccountService accountService;
    private final UserService userService;
    private final SystemAdminService systemAdminService;
    public PersonController(AccountService accountService, StaffService staffService, UserService userservice,
                            SystemAdminService systemAdminService, ModelMapper modelMapper){
        this.accountService = accountService;
        this.staffService = staffService;
        this.userService = userservice;
        this.systemAdminService = systemAdminService;
        this.modelMapper = modelMapper;
        TypeMap<Person, PersonDTO> propertyMapper = modelMapper.createTypeMap(Person.class, PersonDTO.class);
        propertyMapper.addMappings(mapper -> mapper.map(src -> src.getAddress().getCity(), PersonDTO::setCity));
        propertyMapper.addMappings(mapper -> mapper.map(src -> src.getAddress().getStreet(), PersonDTO::setStreet));
        propertyMapper.addMappings(mapper -> mapper.map(src -> src.getAddress().getCountry(), PersonDTO::setCountry));
        propertyMapper.addMappings(mapper -> mapper.map(src -> src.getAddress().getNumber(), PersonDTO::setNumber));
        propertyMapper.addMappings(mapper -> mapper.map(src -> src.getAccount().getEmail(), PersonDTO::setEmail));
    }

    @GetMapping
    public ResponseEntity<List<PersonDTO>> getAllPersons(){
        List<User> users = userService.getAllUsers();
        List <PersonDTO>  personDTOS = new ArrayList<PersonDTO>();
        for (User user: users){
            PersonDTO personDTO = modelMapper.map(user, PersonDTO.class);
            personDTOS.add(personDTO);
        }
        List<SystemAdmin> admins = systemAdminService.getAllAdmins();
        for (SystemAdmin admin: admins){
            PersonDTO personDTO = modelMapper.map(admin, PersonDTO.class);
            personDTOS.add(personDTO);
        }
        List<Staff> staff = staffService.getAllStaff();
        for (Staff s: staff){
            PersonDTO personDTO = modelMapper.map(s, PersonDTO.class);
            personDTOS.add(personDTO);
        }
        return new ResponseEntity<>(personDTOS, HttpStatus.OK);
    }
}


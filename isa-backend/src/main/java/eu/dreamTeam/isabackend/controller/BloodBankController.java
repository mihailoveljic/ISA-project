package eu.dreamTeam.isabackend.controller;

import eu.dreamTeam.isabackend.dto.BloodBankDTO;
import eu.dreamTeam.isabackend.dto.BloodBankDTOs;
import eu.dreamTeam.isabackend.handler.exceptions.BankNotExistedException;
import eu.dreamTeam.isabackend.handler.exceptions.FailedUpdateException;
import eu.dreamTeam.isabackend.handler.exceptions.InvalidApiKeyException;
import eu.dreamTeam.isabackend.handler.exceptions.WrongTimeRangeException;
import eu.dreamTeam.isabackend.logger.CacheLogger;
import eu.dreamTeam.isabackend.model.BloodBank;
import eu.dreamTeam.isabackend.service.AccountService;
import eu.dreamTeam.isabackend.service.ApiKeyService;
import eu.dreamTeam.isabackend.service.BloodBankService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/blood-bank")
public class BloodBankController {

    @Autowired
    private BloodBankService bloodBankService;
    private CacheLogger cacheLogger;
    private final ApiKeyService apiKeyService;
    private final ModelMapper modelMapper;

    //private final Path root = Paths.get("pdfs");
    private final Path root = Paths.get("isa-backend/src/main/resources/pdfs");


    public BloodBankController(ModelMapper modelMapper, ApiKeyService apiKeyService) {
        this.modelMapper = modelMapper;
        this.apiKeyService = apiKeyService;
        TypeMap<BloodBank, BloodBankDTO> propertyMapper = modelMapper.createTypeMap(BloodBank.class, BloodBankDTO.class);
        propertyMapper.addMappings(mapper -> mapper.map(src -> src.getAddress().getCity(), BloodBankDTO::setCity));
        propertyMapper.addMappings(mapper -> mapper.map(src -> src.getAddress().getLatitude(), BloodBankDTO::setLatitude));
        propertyMapper.addMappings(mapper -> mapper.map(src -> src.getAddress().getLongitude(), BloodBankDTO::setLongitude));
        propertyMapper.addMappings(mapper -> mapper.map(src -> src.getAddress().getStreet(), BloodBankDTO::setStreet));
        propertyMapper.addMappings(mapper -> mapper.map(src -> src.getAddress().getCountry(), BloodBankDTO::setCountry));
        propertyMapper.addMappings(mapper -> mapper.map(src -> src.getAddress().getNumber(), BloodBankDTO::setNumber));
        propertyMapper.addMappings(mapper -> mapper.map(src -> src.getWorkTime().getStartTime(), BloodBankDTO::setStartTime));
        propertyMapper.addMappings(mapper -> mapper.map(src -> src.getWorkTime().getEndTime(), BloodBankDTO::setEndTime));
        propertyMapper.addMappings(mapper -> mapper.map(src -> src.getName(), BloodBankDTO::setName));

    }
    @PreAuthorize("hasAnyRole('staff', 'admin')")
    @GetMapping
    public ResponseEntity<BloodBankDTO> getInfoByStaffEmail(
            @RequestParam String email) {
        BloodBank bloodBank = bloodBankService.getByStaffEmail(email);
        if (bloodBank == null)
            throw new BankNotExistedException();
        BloodBankDTO bloodBankDTO = modelMapper.map(bloodBank, BloodBankDTO.class);
        return new ResponseEntity<>(bloodBankDTO, HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('user', 'staff', 'admin')")
    @PostMapping(value = "/notification", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> receiveNotification(
            HttpServletRequest httpServletRequest,
            @RequestPart("pdf") @Valid MultipartFile f) {
        var apiKey = httpServletRequest.getHeader("Authorization");
        if (!apiKeyService.ValidateApiKey(apiKey)){
            throw new InvalidApiKeyException();
        }
        try {
            Files.copy(f.getInputStream(), this.root.toAbsolutePath().resolve("report_" +
                    UUID.randomUUID().toString() + ".pdf"));
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
        return new ResponseEntity<>("RECEIVED SUCCESSFULLY", HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('staff', 'admin')")
    @PutMapping
    public ResponseEntity<BloodBankDTO> updateInfo(
            @RequestBody @Valid BloodBankDTO updateBloodBankDTO) {
        if(updateBloodBankDTO.getEndTime().isBefore(updateBloodBankDTO.getStartTime()))
            throw new WrongTimeRangeException();
        BloodBank bloodBank = bloodBankService.update(updateBloodBankDTO);
        if (bloodBank == null)
            throw new FailedUpdateException();
        return new ResponseEntity<>(updateBloodBankDTO, HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('user', 'staff', 'admin')")
    @PostMapping
    public ResponseEntity<BloodBankDTO> createCenter(
            @RequestBody @Valid BloodBankDTO createBloodBankDTO) {
        if(createBloodBankDTO.getEndTime().isBefore(createBloodBankDTO.getStartTime()))
            throw new WrongTimeRangeException();
        BloodBank bloodBank = bloodBankService.create(createBloodBankDTO);
        if (bloodBank == null)
            throw new FailedUpdateException();
        return new ResponseEntity<>(createBloodBankDTO, HttpStatus.OK);
    }
    @GetMapping("/get-all")
    public ResponseEntity<List<BloodBankDTO>> getAllBloodBanks() {
        List<BloodBank> banks = bloodBankService.getAllBloodBanks();
        List<BloodBankDTO> bankDTOS = new ArrayList<>();
        for (BloodBank bank : banks) {
            BloodBankDTO bankDTO = modelMapper.map(bank, BloodBankDTO.class);
            bankDTOS.add(bankDTO);
        }
        return new ResponseEntity<>(bankDTOS, HttpStatus.OK);
    }
    @GetMapping("/getAll")
    public ResponseEntity<BloodBankDTOs> getAll(){
        List<BloodBank> bloodbanks = this.bloodBankService.getAll();

        List<BloodBankDTO> bloodBankDTOs = bloodbanks.stream().map(bloodBank -> modelMapper.map(bloodBank, BloodBankDTO.class)).toList();

        BloodBankDTOs response = BloodBankDTOs.builder()
                                        .bloodBanks(bloodBankDTOs)
                                        .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/getAll/{page}/{size}/{order}/{field}")
    public ResponseEntity<BloodBankDTOs> getAllWithPageable(@PathVariable int page, @PathVariable int size,
                                                @PathVariable String order, @PathVariable String field){
        Page<BloodBank> bloodbanks = this.bloodBankService.getAllWithPageable(page,size,order,field);

        List<BloodBankDTO> bloodBankDTOs = bloodbanks.stream().map(bloodBank -> modelMapper.map(bloodBank, BloodBankDTO.class)).toList();

        BloodBankDTOs response = BloodBankDTOs.builder()
                .bloodBanks(bloodBankDTOs)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

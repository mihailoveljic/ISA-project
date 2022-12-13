package eu.dreamTeam.isabackend.controller;
import eu.dreamTeam.isabackend.dto.SampleDTO;
import eu.dreamTeam.isabackend.dto.StaffMainInfoDTO;
import eu.dreamTeam.isabackend.handler.exceptions.AccountNotExistedException;
import eu.dreamTeam.isabackend.handler.exceptions.InvalidApiKeyException;
import eu.dreamTeam.isabackend.handler.exceptions.StaffNotExistedException;
import eu.dreamTeam.isabackend.model.BloodSample;
import eu.dreamTeam.isabackend.model.Staff;
import eu.dreamTeam.isabackend.service.AccountService;
import eu.dreamTeam.isabackend.service.ApiKeyService;
import eu.dreamTeam.isabackend.service.BloodSampleService;
import eu.dreamTeam.isabackend.service.StaffService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/blood-sample")
@Validated
public class BloodSampleController {

    private final ApiKeyService apiKeyService;
    private final BloodSampleService bloodSampleService;
    private final StaffService staffService;
    private final AccountService accountService;
    private final ModelMapper modelMapper;


    @Autowired
    public BloodSampleController(BloodSampleService bloodSampleService, ApiKeyService apiKeyService, StaffService staffService, AccountService accountService, ModelMapper modelMapper) {
        this.bloodSampleService = bloodSampleService;
        this.apiKeyService = apiKeyService;
        this.staffService = staffService;
        this.accountService = accountService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<String> getBloodSample(
            HttpServletRequest httpServletRequest,
            @RequestParam @Pattern(regexp = "^(A|B|AB|ZERO)_(POS|NEG)$") String bloodType,
            @RequestParam @Pattern(regexp = "^([1-9]|[1-9][0-9]|[1-9][0-9][0-9]|[1-9][0-9][0-9][0-9])$") String amount
    )
        {
            var apiKey = httpServletRequest.getHeader("Authorization");
            if (!apiKeyService.ValidateApiKey(apiKey)){
                throw new InvalidApiKeyException();
            }
            int bs = bloodSampleService.getBloodSample(bloodType);
            if(bs == 0)
                return new ResponseEntity<>("Blood type not found", HttpStatus.NOT_FOUND);
            double availableAmount = bloodSampleService.getBloodSampleAmount(bloodType);
            if(availableAmount < Double.parseDouble(amount))
                return new ResponseEntity<>("Not enough blood for chosen blood type. There is currently " + availableAmount + "ml in supplies.", HttpStatus.NOT_FOUND);

            return new ResponseEntity<>("Blood type exists in chosen amount. There is currently " + availableAmount + "ml in supplies.", HttpStatus.OK);
        }

    @GetMapping("/supplies")
    public ResponseEntity<List<SampleDTO>> getBloodSample(
            @RequestParam String email) {
        if(!accountService.check(email))
            throw new AccountNotExistedException();
        Staff staff = staffService.getByEmail(email);
        if (staff == null)
            throw new StaffNotExistedException();
        List<BloodSample> samples = bloodSampleService.getBloodSamplesForCenter(staff.getBloodBank().getId());
        List<SampleDTO> sampleDTOS = new ArrayList<>();
        for(BloodSample bs: samples){
            SampleDTO sampleDTO = modelMapper.map(bs, SampleDTO.class);
            sampleDTOS.add(sampleDTO);
        }
        return new ResponseEntity<>(sampleDTOS, HttpStatus.OK);
    }


}

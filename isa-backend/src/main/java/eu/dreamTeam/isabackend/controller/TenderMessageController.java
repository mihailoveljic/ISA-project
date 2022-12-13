package eu.dreamTeam.isabackend.controller;

import eu.dreamTeam.isabackend.dto.message.TenderOfferConfirmationMessage;
import eu.dreamTeam.isabackend.handler.exceptions.InvalidApiKeyException;
import eu.dreamTeam.isabackend.rabbitmq.publisher.TenderOfferConfirmationMessagePublisher;
import eu.dreamTeam.isabackend.service.ApiKeyService;
import eu.dreamTeam.isabackend.service.BloodSampleService;
import eu.dreamTeam.isabackend.service.TenderMessageService;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/tender-message")
public class TenderMessageController {

    @Autowired
    private TenderMessageService tenderMessageService;
    @Autowired
    private BloodSampleService bloodSampleService;


    @Autowired
    private TenderOfferConfirmationMessagePublisher tenderOfferConfirmationMessagePublisher;

    @GetMapping(value = "/getAll")
    public ResponseEntity<Object> getAllTenderMessages(HttpServletRequest httpServletRequest){
        var tenderMessages = tenderMessageService.getAll();
        return new ResponseEntity<>(tenderMessages, HttpStatus.OK);

    }

    @GetMapping(value = "/confirm/{tenderOfferId}")
    public ResponseEntity<Object> confirmTenderOffer(@PathVariable int tenderOfferId){

        var tenderOffer = tenderMessageService.getTenderOfferById(tenderOfferId);
        if(tenderOffer == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        var bids = tenderOffer.getBids();
        boolean canSubstract = true;
        for(var bid: bids) {
            var result = bloodSampleService.canSubstractBloodSamples(bid.getBloodRequest().getBloodType(), bid.getBloodRequest().getQuantity());
            if(!result) canSubstract = false;
        }
        if(canSubstract) {
            for(var bid: bids) {
                bloodSampleService.substractBloodSamples(bid.getBloodRequest().getBloodType(), bid.getBloodRequest().getQuantity());
            }
            var tenderOfferConfirmationMessage = TenderOfferConfirmationMessage.builder()
                    .tenderOfferId(tenderOffer.getId())
                    .tenderId(tenderOffer.getTenderId())
                    .confirmed(true)
                    .build();

            tenderOfferConfirmationMessagePublisher.sendMessage(tenderOfferConfirmationMessage);
            tenderMessageService.deleteTenderMessage(tenderOfferId);

            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);



    }

    @GetMapping(value = "/revoke/{tenderOfferId}")
    public ResponseEntity<Object> revokeTenderOffer(@PathVariable int tenderOfferId){
        var tenderOffer = tenderMessageService.getTenderOfferById(tenderOfferId);
        if(tenderOffer == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        var tenderOfferConfirmationMessage = TenderOfferConfirmationMessage.builder()
                .tenderOfferId(tenderOffer.getId())
                .tenderId(tenderOffer.getTenderId())
                .confirmed(false)
                .build();

        tenderOfferConfirmationMessagePublisher.sendMessage(tenderOfferConfirmationMessage);
        tenderMessageService.deleteTenderMessage(tenderOfferId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/delete/{tenderOfferId}")
    public ResponseEntity<Object> deleteTenderMessage(@PathVariable int tenderOfferId){
        var response = tenderMessageService.deleteTenderMessage(tenderOfferId);
        if(response) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}

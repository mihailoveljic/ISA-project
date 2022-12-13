package eu.dreamTeam.isabackend.service;

import eu.dreamTeam.isabackend.dto.message.TenderMessage;
import eu.dreamTeam.isabackend.dto.message.TenderMessages;
import eu.dreamTeam.isabackend.model.tender.TenderOffer;
import eu.dreamTeam.isabackend.repository.TenderOfferRepository;
import eu.dreamTeam.isabackend.repository.TenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TenderMessageService {
    @Autowired
    TenderRepository tenderRepository;
    @Autowired
    TenderOfferRepository tenderOfferRepository;

    public void save(TenderMessage tenderMessage){
        tenderRepository.save(tenderMessage.getTender());
        tenderOfferRepository.save(tenderMessage.getAcceptedTenderOffer());
    }

    public TenderMessages getAll(){
        var tenders = tenderRepository.findAll();
        var tenderOffers = tenderOfferRepository.findAll();

        var tenderMessages = new TenderMessages();

        for(var tender: tenders){
            for (var tenderOffer : tenderOffers){
                if(tenderOffer.getTenderId() == tender.getId()){
                    tenderMessages.getTenderMessages().add(TenderMessage.builder()
                                                            .acceptedTenderOffer(tenderOffer)
                                                            .tender(tender)
                                                      .build());
                    break;
                }
            }
        }
        return tenderMessages;
    }
    public TenderOffer getTenderOfferById(int tenderOfferId){
        return tenderOfferRepository.findById(tenderOfferId).orElse(null);
    }

    public boolean deleteTenderMessage(int tenderOfferId){
        var tenderOffer = tenderOfferRepository.findById(tenderOfferId).orElse(null);
        if(tenderOffer == null) return true;
        try{
            tenderRepository.deleteById(tenderOffer.getTenderId());
            tenderOfferRepository.deleteById(tenderOfferId);
            return true;
        }catch (Exception e){
            return false;
        }
    }

}

import { AuthService } from 'src/app/auth/services/auth.service';
import { TenderMessageService } from '../../services/tender-message.service';
import { Component, OnInit, AfterViewInit } from '@angular/core';
import { User } from 'src/app/auth/models/user';

@Component({
  templateUrl: './tenders.component.html',
  styleUrls: ['./tenders.component.css']
})
export class TendersComponent implements OnInit, AfterViewInit {


  tenderMessages: any;
  user: User | undefined;
  
  constructor(
    private tenderMessageService: TenderMessageService,
    private authService: AuthService) { }

  ngOnInit(): void {
    this.getAllTenderMessages();
  }
  ngAfterViewInit(){
    this.user = this.authService.getUser();
  }

  getAllTenderMessages(){
    this.tenderMessageService.getAll().subscribe(
      (response: any) =>{
        this.tenderMessages = response.tenderMessages;
        this.tenderMessages.forEach((tenderMessage:any) => {
          tenderMessage.Tender.FromDate = new Date(tenderMessage.Tender.FromDate);
          tenderMessage.Tender.ToDate = new Date(tenderMessage.Tender.ToDate);
        });
      }
    );
  }
  
  deleteTenderMessage(tenderMessage: any) {
    this.tenderMessageService.deleteTenderMessage(tenderMessage.AcceptedTenderOffer.Id).subscribe(
      (response: any) =>{
        this.getAllTenderMessages();
      }
    );
  }

  confirmTenderOffer(tenderMessage: any) {
    this.tenderMessageService.confirmTenderOffer(tenderMessage.AcceptedTenderOffer.Id).subscribe(
      (response: any) =>{
        this.getAllTenderMessages();
      }
    );
  }
  
  revokeTenderOffer(tenderMessage: any) {
    this.tenderMessageService.revokeTenderOffer(tenderMessage.AcceptedTenderOffer.Id).subscribe(
      (response: any) =>{
        this.getAllTenderMessages();
      }
    );
  }

}

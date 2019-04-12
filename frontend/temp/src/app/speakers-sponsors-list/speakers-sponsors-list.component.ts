import { OptionalDetails } from './optionaldetails';
import { SpeakerSponsorService } from './speaker-sponsor.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ActivatedRoute, Params } from '@angular/router'; 
import { ToastrService } from 'ngx-toastr';
@Component({
  selector: 'app-speakers-sponsors-list',
  templateUrl: './speakers-sponsors-list.component.html',
  styleUrls: ['./speakers-sponsors-list.component.css']
})
export class SpeakersSponsorsListComponent implements OnInit {
 speakerDetail:OptionalDetails[];
 sponsorDetail:OptionalDetails[];
  event_id:number;
  constructor(private toastr: ToastrService,private role:SpeakerSponsorService,private router: Router,private route: ActivatedRoute,) { }

  ngOnInit() {
   this.route.params.subscribe(params => {
      this.event_id=+params['eventid'] 
     
   })

  
   this.role.displaySpeaker().subscribe((response)=>{
     this.speakerDetail=response
   })
   this.role.displaySponsor().subscribe((response)=>{
    this.sponsorDetail=response
  })
  }

 toSpeaker(speakerid:number)
 {

  this.role.sendToSpeaker(speakerid,this.event_id).subscribe(
    (response)=>{
      this.toastr.success('Success', response);
      
    }
  )
 }

 toSponsor(sponsorid:number)
 {
 this.role.sendToSponsor(sponsorid,this.event_id).subscribe(
  (response)=>{
    this.toastr.success('Success', response);
    
  }
)

}

 
  

}
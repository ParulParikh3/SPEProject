import { Event } from './../event';
import { ParticipantService } from './participant.service';
import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router';
import { ActivatedRoute, Params } from '@angular/router'; 
import 'rxjs/add/operator/switchMap'; 
import { DatePipe } from '@angular/common';
@Component({
  selector: 'app-participant',
  
 templateUrl: './participant.component.html',
 providers:[DatePipe],
  styleUrls: ['./participant.component.css']
})
export class ParticipantComponent implements OnInit {
  status:String;
 eventDetail:Event;
 currentDate=new Date().toLocaleDateString();
 buttonstatus:boolean;
  constructor(private participant:ParticipantService,private router: Router,private route: ActivatedRoute,private datePipe: DatePipe) 
  {
    this.currentDate = this.datePipe.transform(this.currentDate, 'yyyy-MM-dd');
   }

  ngOnInit() {
    this.route.params
.switchMap((params: Params) => this.participant.display(params['eventname']))
. subscribe((response:Event)=>{this.eventDetail=response
this.buttonstatus=this.eventDetail.last_date<this.currentDate
console.log(this.buttonstatus)})
 
}
register(id:number,fees:number)
{
  this.participant.addparticipant(id,fees).subscribe((response)=>{alert(response)
     this.router.navigateByUrl('dashboard/eventlist')})
}
withdraw(id:number)
{
  this.participant.deleteparticipant(id).subscribe((response)=>{alert(response)
    this.router.navigateByUrl('dashboard/eventlist')})
}
showStatus(id:number)
{
this.participant.show(id).subscribe((response)=>{this.status=response})
}

}

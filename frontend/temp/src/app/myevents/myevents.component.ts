import { Component, OnInit } from '@angular/core';
import {MyEventsSercive} from './myevents.service';
import { ActivatedRoute, Params } from '@angular/router'; 
import 'rxjs/add/operator/switchMap'; 
import {Event} from '../event';
import {DashboardService} from '../dash-board/dashboard.service';

@Component({
  selector: 'app-myevents',
  templateUrl: './myevents.component.html',
  styleUrls: ['./myevents.component.css']
})
export class MyeventsComponent implements OnInit {

  eventList:Event[]=[] ;
  eventnames=[];
  index;
  waitingevent=[];
  appliedevent=[];
  createdevents=[];
  requestednames=[];
  requestednamessponser=[];

  constructor(private myEvents:MyEventsSercive,private route: ActivatedRoute, private dashboardservice:DashboardService) { }

  ngOnInit() {
    
    this.myEvents.ListOfMyEvents()
    .subscribe((eventList) =>{
      this.eventList=eventList;
                })

      this.invitation();      
      }

      invitation()
      {
        this.dashboardservice.displayNotification()
        .subscribe((names) =>{
            this.eventnames=names;
  
        })
      }

      waitinglist()
      {
        this.myEvents. getListByStatus('waiting')
        .subscribe((eventnames) =>{
          this.waitingevent=eventnames;
          console.log("waiting list");
        });
      }

      AppliedForEvent()
      {
        this.myEvents.getListByStatus('applied')
        .subscribe((eventnames) =>{
          this.appliedevent=eventnames;
        });
      }

      CreatedEvents()
      {
        this.myEvents.getListByStatus('created')
        .subscribe((eventnames) =>{
          this.createdevents=eventnames;
        });

      }


      requestAccepted()
      {
        this.myEvents.StatusChange("approved")
        .subscribe((response)=>{
          console.log(response)
        });
      }
      requestRejected()
      {
        this.myEvents.StatusChange("rejected")
        .subscribe((response)=>{
          console.log(response)
        });
      }

      requestFromSpeakers()
      {
        this.myEvents.requestFromSpeakers()
        .subscribe((response)=>{
         this.requestednamessponser=response;
         console.log(this.requestednames);

        })
      }
      
      requestFromSponsor()
      {

        this.myEvents.requestFromSponsor()
        .subscribe((response)=>{
         this.requestednames=response;
         console.log(this.requestednamessponser);

        })

      }

      requestAcceptedFromOrgainzer(id:number)
      {
        
        this.myEvents.StatusChangeForSpeakerSponser("approved",id)
        .subscribe((response)=>{
          console.log(response)
        });
      }

      requestRejectedFromOrgainzer(id:number)
      {
        this.myEvents.StatusChangeForSpeakerSponser("rejected",id)
        .subscribe((response)=>{
          console.log(response)
        });
      }

      setVariable(index:number)
      {
        this.index=index;
        if(index==1)
          this.waitinglist();
        else if(index==2)
         {
           this.CreatedEvents();
           this.requestFromSpeakers(); 
           this.requestFromSponsor();
          } 
        else if(index==3|| index==4)
        this.AppliedForEvent();
      }

}

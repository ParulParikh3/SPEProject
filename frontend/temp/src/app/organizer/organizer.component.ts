import { Component, OnInit } from '@angular/core';
import { OrganizerService } from './organizer.service';
import { EventCreation } from '../event-creation-form/eventcreation';

@Component({
  selector: 'app-organizer',
  templateUrl: './organizer.component.html',
  styleUrls: ['./organizer.component.css']
})
export class OrganizerComponent implements OnInit {

  id:number;
  creation_phase_events:EventCreation[]=[];
  constructor(private organizerservice:OrganizerService) { }

  ngOnInit() {
    this.getUnderConstructedEvents();
  }

  getUnderConstructedEvents()
  {
    this.id=+sessionStorage.getItem('userid');
    this.organizerservice.getEventList().subscribe(
      (creation_phase_events)=>{
        this.creation_phase_events=creation_phase_events;
      }
    )

  }
}

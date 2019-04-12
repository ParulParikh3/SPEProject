import { Component, OnInit } from '@angular/core';
import { RouterModule, Routes ,Router} from '@angular/router';
import {DashboardService} from './dashboard.service';
import { AuthServiceService } from '../auth-service.service';


@Component({
  selector: 'app-dash-board',
  templateUrl: './dash-board.component.html',
  styleUrls: ['./dash-board.component.css']
})
export class DashBoardComponent implements OnInit {
  active:string;
  eventnames=[]; 
  userid=sessionStorage.getItem("userid");
  username= sessionStorage.getItem("username");
  constructor(private router: Router, private authGuard:AuthServiceService, private dashboardservice:DashboardService) {
    this.router.events.subscribe((val) => {
      this.routeChanged(val);
    });
   }

  ngOnInit() {
    this.dashboardservice.displayNotification()
    .subscribe(
      (notification) =>{
           this.eventnames=notification;
      },
      (error) =>console.log(error)
    );  

  }
  routeChanged(val){
    this.active = val.url;
  }
  logOut(){
    this.authGuard.logout();
  }

  myFunction()
  {
    
    var x = document.getElementById("Demo");
      if (x.className.indexOf("w3-show") == -1) {
        x.className += " w3-show";
      } else { 
        x.className = x.className.replace(" w3-show", "");
      }
  }
}

import { Injectable } from '@angular/core';
import { Headers, Http,Response } from "@angular/http";
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class SpeakerSponsorService {
  private baseUrl:string ='http://localhost:3000/speakersponsor'
  constructor(private http:Http) { }

  displaySpeaker()
  {
  const headers = new Headers({'Content-Type': 'text/plain'});
   return this.http.get(this.baseUrl+'/speakerdetail',{headers: headers})
        .map((res: Response) => res.json());

  }

  displaySponsor()
  {
  const headers = new Headers({'Content-Type': 'text/plain'});
   return this.http.get(this.baseUrl+'/sponsordetail',{headers: headers})
        .map((res: Response) => res.json());

  }
  
  sendToSpeaker(speakerid:number,event_id:number)
  {
    const headers = new Headers({'Content-Type': 'application/json'});
   return this.http.post(this.baseUrl+'/requesttospeaker',{userid:speakerid,eventid:event_id},{headers: headers})
        .map((res: Response) => res.text());

  }

sendToSponsor(sponsorid:number,event_id:number)
{
  const headers = new Headers({'Content-Type': 'application/json'});
   return this.http.post(this.baseUrl+'/requesttosponsor',{userid:sponsorid,eventid:event_id},{headers: headers})
        .map((res: Response) => res.text());

}




}
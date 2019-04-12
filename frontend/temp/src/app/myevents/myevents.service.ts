import { Injectable } from "@angular/core";
import { Headers, Http,Response } from "@angular/http";
import 'rxjs/add/operator/map';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class MyEventsSercive
{
    private baseUrl:string ='http://localhost:3000'
  constructor(private http:Http) { }

    ListOfMyEvents()
    {
        let params=sessionStorage.getItem("userid");
        console.log(params);
        return this.http.get(this.baseUrl+'/myevents/'+params)
        .map((res: Response) => res.json()).catch(this.errorHandler);
    }

    StatusChange(status)
    {
      let params=sessionStorage.getItem("userid");
      const headers = new Headers({'Content-Type': 'application/text'});
      return this.http.post(this.baseUrl+'/statusChange/'+params,status,{headers:headers})
      .map((res: Response) => res.text()).catch(this.errorHandler);
    }

    StatusChangeForSpeakerSponser(status,id)
    {
      const headers = new Headers({'Content-Type': 'application/text'});
      return this.http.post(this.baseUrl+'/statusChange/'+id,status,{headers:headers})
      .map((res: Response) => res.text()).catch(this.errorHandler);
    }

    getListByStatus(status)
    {
      console.log(status)
      let params=sessionStorage.getItem("userid");
      const headers = new Headers({'Content-Type': 'application/text'});
      return this.http.post(this.baseUrl+'/listByStatus/'+params,status,{headers:headers})
      .map((res: Response) => res.json()).catch(this.errorHandler);
    }

    requestFromSpeakers()
    {
      let params=sessionStorage.getItem("userid");
        console.log(params);
        return this.http.get(this.baseUrl+'/requestspeakers/'+params)
        .map((res: Response) => res.json()).catch(this.errorHandler);
    }

    requestFromSponsor()
    {
      let params=sessionStorage.getItem("userid");
        console.log(params);
        return this.http.get(this.baseUrl+'/requestsponsor/'+params)
        .map((res: Response) => res.json()).catch(this.errorHandler);
    }

    errorHandler(error:Response){

      return Observable.throw(error||"SERVER ERROR");
 }
}
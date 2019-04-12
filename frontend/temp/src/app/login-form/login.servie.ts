import { Injectable } from "@angular/core";
import { Headers, Http,Response } from "@angular/http";
import {Login} from '../login';
import 'rxjs/add/operator/map';
import { variable } from "@angular/compiler/src/output/output_ast";

@Injectable()
export class LoginService{
    private baseUrl:string ='http://localhost:3000';
    
    constructor(private http:Http){}
    

    checklogin(login : Login)
    {
      const headers = new Headers({'Content-Type': 'application/json'});
         return this.http.post(this.baseUrl+'/login',login,{headers: headers})
        .map((res: Response) => res.text());
    }

    
  }
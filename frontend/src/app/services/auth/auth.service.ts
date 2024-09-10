import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { UserStorageService } from '../storage/user-storage.service';

const BASIC_URL= ["http://localhost:8080/"];

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/cors-test';
  // constructor(private http: HttpClient) { }

  checkCors() {
    return this.http.get(BASIC_URL+"cors-test", { responseType: 'text' });
  }
  testCorsPost(message: string): Observable<any> {
    return this.http.post(BASIC_URL + 'cors-test', message, { responseType: 'text' }); // Expect plain text
  }
  constructor(private http: HttpClient,
    private userStorageService: UserStorageService
  ) { }

  login(username: string, password: string):any  {
    const headers=new HttpHeaders().set('Content-Type', 'application/json');
    const body={username, password};

    return this.http.post(BASIC_URL+'authenticate', body ,{headers, observe:'response'}).pipe(
      map((res)=>{
        const token=res.headers.get('authorization').substring(7);
        const user=res.body;
        console.log(token)
        console.log(user);
        if(token && user){
          this.userStorageService.saveToken(token);
          this.userStorageService.saveUser(user);
          return true;
        }
        return false;
      })
    )
  }
    
  register(signupRequest:any): Observable<any>{
    return this.http.post(BASIC_URL + 'sign-up', signupRequest);

  }
  // register(msg:String): Observable<String>{
  //   return this.http.post(BASIC_URL + 'sign-up', msg,{ responseType: 'text' });
  // }
}

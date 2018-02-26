import { Injectable } from "@angular/core";
import { Http, Response, RequestOptions,Headers } from '@angular/http';
import 'rxjs/Rx';

@Injectable()
export class UserService {
    constructor(private http: Http) {}
    isLoggedIn: boolean = false;
    baseurl: "http://localhost:8080/api/users/";

    getUsers() {
        return this.http.get('http://localhost:8080/api/users')
        .map(
            (response: Response)=> {
                const data = response.json();
                return data;
            }
        );
      }

      setLogin() {
          this.isLoggedIn = true;
      }

      isUserLoggedIn() {
          return this.isLoggedIn;
      }

      auhenticateUser(user: string) {  
            return this.http.post("http://localhost:8080/api/users/login", user);
           }
    
    
        getIndividualUser(userID: string) {
            let headers = new Headers();
            headers.append('Content-Type', 'application/json');
            let data = JSON.parse(localStorage.getItem('currentUser'));
            
            
            headers.append('AUTHORIZATION', `Bearer ` + data.token);
            let options = new RequestOptions({ headers: headers });
            let url = this.baseurl + userID;
            console.log(url); 
            return this.http.get("http://localhost:8080/api/users/" + userID, options).map(
                (response: Response)=> {
                    const data = response.json();
                    return data;
                }
            );
        }
      

      registerUser(user: string) {
         return this.http.post("http://localhost:8080/api/users", user);
      }
}
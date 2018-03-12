import { Injectable } from "@angular/core";
import { Http, Response, RequestOptions,Headers } from '@angular/http';
import 'rxjs/Rx';

@Injectable()
export class UserService {
    constructor(private http: Http) {}
    isLoggedIn: boolean = false;
    baseurl: "http://localhost:8080/api/users/";

    getUsers() {
        let headers = new Headers();
            headers.append('Content-Type', 'application/json');
            let data = JSON.parse(localStorage.getItem('currentUser'));
            headers.append('AUTHORIZATION', `Bearer ` + data.token);
            let options = new RequestOptions({ headers: headers });
        return this.http.get('http://localhost:8080/api/users', options)
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

      evaluateApplicant(userID: string, evals: string) {
        var headers = new Headers();
        headers.append("Accept", 'application/json');
        headers.append("Content-Type", 'application/json');
        let options = new RequestOptions({ headers: headers });
          let recruiter = JSON.parse(localStorage.getItem('currentUser'));
          console.log("http://localhost:8080/api/users/" + userID);
          console.log( JSON.stringify({recruiterID: recruiter.userId, evaluation: evals }));
            return this.http.put("http://localhost:8080/api/users/" + userID, JSON.stringify({recruiterID: recruiter.userId, eval: evals }), options)
      }
}
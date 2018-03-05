import { Injectable } from "@angular/core";
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx'
import 'rxjs/Rx';

@Injectable()
export class AuthenticationService {
    public token: string;
    public userId: string;
    public role: string;
    constructor(private http: Http) {
        var currentUser = JSON.parse(localStorage.getItem('currentUser'));
        this.token = currentUser && currentUser.token;
    }

    getRole() {
        return this.role;
    }

    authenticate(user: string) {
        console.log("Authenticating...")
        return this.http.post("http://localhost:8080/api/users/login", user)
        .map
        ((response: Response) => {
            let token = response.json() && response.json().token;
            let userId = response.json().userId;
            let role = response.json().role;

                if (token) {
                    // set token property
                    this.token = token;
 
                    // store username and jwt token in local storage to keep user logged in between page refreshes
                    localStorage.setItem('currentUser', JSON.stringify({ userId,role, token: token }));
                    return true;
                 }else {
                     return false;
                 }
        }).catch((error: any) => {
            let body = error.text();
            return Observable.throw(new Error(body));    
        });

    }

    logout(): void {
        // clear token remove user from local storage to log user out
        this.token = null;
        localStorage.removeItem('currentUser');
    }




}

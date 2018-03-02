import { Injectable } from "@angular/core";
import { Http, Response, RequestOptions,Headers } from '@angular/http';
import 'rxjs/Rx';

@Injectable()
export class ApplicationService {

    constructor(private http: Http){}

    getCompetencies() {
        return this.http.get('http://localhost:8080/api/competencies')
        .map(
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
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { UserService } from '../users/user.service';
import {Router} from '@angular/router'
import { Response } from '@angular/http';
import { AuthenticationService } from '../authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  error: string;

  constructor(private userService: UserService, private router: Router, private authenticationService: AuthenticationService) { }

  ngOnInit() {
    this.authenticationService.logout();
  }

  onAuthenticateUser(form: NgForm) {
    const value = form.value;
    console.log(value);
   this.authenticationService.authenticate(value)
   .subscribe(result => {
     if(result === true){
      let data = JSON.parse(localStorage.getItem('currentUser'));
       if(data.role === "applicant") {
        this.router.navigate(["userpage"]);
      }else {
        this.router.navigate(["users"]);
        
      }
     }
     else {
       this.error = "Incorrect login detail";
     }
   },
  (error: string) => this.error = error);
}

}

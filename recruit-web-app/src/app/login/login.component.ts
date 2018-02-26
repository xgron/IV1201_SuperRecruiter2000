import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { UserService } from '../users/user.service';
import { Response } from '@angular/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private userService: UserService) { }

  ngOnInit() {
  }

  onAuthenticateUser(form: NgForm) {
    const value = form.value;
    console.log(value);
   this.userService.registerUser(value)
   .subscribe(
    (response: Response) => console.log(response),

    (error) => console.log(error)
  );
  
}

}

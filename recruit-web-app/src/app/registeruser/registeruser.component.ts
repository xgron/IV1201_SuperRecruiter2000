import { Component, OnInit } from '@angular/core';
import { NgForm, MinLengthValidator, MaxLengthValidator } from '@angular/forms';
import { Response } from '@angular/http';

import { UserService } from '../users/user.service';

@Component({
  selector: 'app-registeruser',
  templateUrl: './registeruser.component.html',
  styleUrls: ['./registeruser.component.css']
})
export class RegisteruserComponent implements OnInit {


  constructor(private userService: UserService) { }

  ngOnInit() {
  }

  onRegisterUser(form: NgForm) {
    const value = form.value;
    console.log(value);
   this.userService.registerUser(value)
   .subscribe(
    (response: Response) => console.log(response),

    (error) => console.log(error)
  );
  
}

}

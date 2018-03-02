import { Component, OnInit } from '@angular/core';
import { NgForm, MinLengthValidator, MaxLengthValidator } from '@angular/forms';
import { Response } from '@angular/http';

import { UserService } from '../users/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registeruser',
  templateUrl: './registeruser.component.html',
  styleUrls: ['./registeruser.component.css']
})
export class RegisteruserComponent implements OnInit {


  constructor(private userService: UserService, private router: Router) { }

  ngOnInit() {
  }

  onRegisterUser(form: NgForm) {
    const value = form.value;
    console.log(value);
   this.userService.registerUser(value)
   .subscribe(
    (response: Response) => {console.log(response)
    this.router.navigate(['application'])},

    (error) => console.log(error)
  );
  
}

}

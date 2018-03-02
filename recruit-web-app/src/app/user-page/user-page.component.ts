import { Component, OnInit } from '@angular/core';
import { UserService } from '../users/user.service';

@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.css']
})
export class UserPageComponent implements OnInit {
  users = [];

  constructor(private userService: UserService) { }

  ngOnInit() {
    let data = JSON.parse(localStorage.getItem('currentUser'));
    this.userService.getIndividualUser(data.userId)
    .subscribe(
      (users: any[]) =>{ this.users = users;
        console.log(users);
      },
      (error) => console.log(error)
    );
  }

}

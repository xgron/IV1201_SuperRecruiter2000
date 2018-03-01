import { Component, OnInit } from '@angular/core';
import { UserService } from './user.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {
  users = []

  constructor(private userService: UserService) { 
  }

  ngOnInit() {
    this.userService.getUsers()
    .subscribe(
      (users: any[]) => this.users = users,
      (error) => console.log(error)
    );
  }

  onGetUsers() {
    this.userService.getUsers()
    .subscribe(
      (users: any[]) => this.users = users,
      (error) => console.log(error)
    );
  }
  

}

import { Component, OnInit } from '@angular/core';
import { UserService } from './user.service';
import { Response } from '@angular/http';
import * as jsPDF from 'jspdf'

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

acceptApplicant(user) {
    console.log(user.userID);
    this.userService.evaluateApplicant(user.userID, "true")
    .subscribe(
      (response: Response) => console.log(response),
      (error) => console.log(error)
    );
    this.refresh();
}

denyApplicant(user) {
  this.userService.evaluateApplicant(user.userID, "false")
  .subscribe(
    (response: Response) => console.log(response),
    (error) => console.log(error)
  );
  this.refresh();
}

refresh(): void {
  window.location.reload();
}



  public download(user) {

    var doc = new jsPDF();
    doc.text(20, 20, user.name + " " + user.surname);
    doc.text(20, 30, "Registration date: " + user.registrationdate);
    doc.text(20,40, "Application status: " + user.hired);
    doc.text(20, 60, "Experiences");
    let i = 70;
    user.experience.forEach(exp => {
        doc.text(20, i, "Experiece: " + exp.name + ": " + exp.years);
        i = i +10;
    });
    i = i +10;
    doc.text(20, i, "Availabilities");
    i = i+10;
    user.availabilities.forEach(avail => {
      doc.text(20, i, "From " + avail.start + " to " + avail.end);
      i = i +10;
  });
    
    






    /*
    doc.text(20, 30, 'This is client-side Javascript, pumping out a PDF.');
    doc.addPage();
    doc.text(20, 20, 'Do you like that?');
    */

    // Save the PDF
    doc.save('application.pdf');
}
  

}

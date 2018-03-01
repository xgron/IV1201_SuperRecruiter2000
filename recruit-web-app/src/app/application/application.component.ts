import { Component, OnInit } from '@angular/core';
import { ApplicationService } from './application.service';

@Component({
  selector: 'app-application',
  templateUrl: './application.component.html',
  styleUrls: ['./application.component.css']
})
export class ApplicationComponent implements OnInit {
  competencies = [];

  constructor(private applicationService: ApplicationService) { }

  ngOnInit() {
    this.applicationService.getCompetencies()
    .subscribe(
      (competencies: any[]) => {this.competencies = competencies
      console.log(this.competencies)},
      (error) => console.log(error)
    );
  }

}

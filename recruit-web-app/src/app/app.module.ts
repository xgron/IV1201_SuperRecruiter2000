import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';


import { AppComponent } from './app.component';
import { UsersComponent } from './users/users.component';
import { UserService } from './users/user.service';
import { HttpModule } from '@angular/http';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { HomepageComponent } from './homepage/homepage.component';
import { RegisteruserComponent } from './registeruser/registeruser.component';

import { AppRoutingModule } from './app-routing.module';
import { AuthenticationService } from './authentication.service';
import { LoginComponent } from './login/login.component';
import { UserPageComponent } from './user-page/user-page.component';
import { ApplicationComponent } from './application/application.component';
import { ApplicationService } from './application/application.service';


@NgModule({
  declarations: [
    AppComponent,
    UsersComponent,
    HeaderComponent,
    FooterComponent,
    HomepageComponent,
    RegisteruserComponent,
    LoginComponent,
    UserPageComponent,
    ApplicationComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    FormsModule,
    AppRoutingModule,
    ReactiveFormsModule
  ],
  providers: [UserService, AuthenticationService,
     ApplicationService],
  bootstrap: [AppComponent]
})
export class AppModule { }

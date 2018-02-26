import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomepageComponent } from './homepage/homepage.component';
import { RegisteruserComponent } from './registeruser/registeruser.component';
import { UsersComponent } from './users/users.component';
import { LoginComponent } from './login/login.component';
import { UserPageComponent } from './user-page/user-page.component';


const appRoutes: Routes = [
    {path: '', redirectTo: '/home', pathMatch:'full'},
    { path: 'register', component: RegisteruserComponent},
    { path: 'users', component: UsersComponent},
    { path: 'userpage', component: UserPageComponent},
    { path: 'home', component: HomepageComponent},
    { path: 'login', component: LoginComponent}
]
@NgModule({
    imports: [RouterModule.forRoot(appRoutes)],
    exports: [RouterModule]
})
export class AppRoutingModule {

}
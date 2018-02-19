import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomepageComponent } from './homepage/homepage.component';
import { RegisteruserComponent } from './registeruser/registeruser.component';


const appRoutes: Routes = [
    {path: '', redirectTo: '/home', pathMatch:'full'},
    { path: 'register', component: RegisteruserComponent},
    { path: 'home', component: HomepageComponent}
]
@NgModule({
    imports: [RouterModule.forRoot(appRoutes)],
    exports: [RouterModule]
})
export class AppRoutingModule {

}
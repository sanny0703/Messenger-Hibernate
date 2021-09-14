import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import {SignupComponent} from './signup/signup.component';
import {ViewUsersComponent} from './view-users/view-users.component';
import {ViewMessagesComponent} from './view-messages/view-messages.component'
const routes: Routes = [{ path: '', redirectTo: 'login', pathMatch: 'full' },
  {
    path:"login",
    component:LoginComponent
  },
  {
    path:"signUp",
    component:SignupComponent
  },
  {
    path:"viewUsers",
    component:ViewUsersComponent
  },
  {
    path:"viewMessages",
    component:ViewMessagesComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

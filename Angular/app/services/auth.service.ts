import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../model/User';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  my_user:User
  constructor(private http:HttpClient) {
    this.my_user = new User()
   }
   login(user:User){
     return  this.http.post<User>("http://localhost:8080/HibernateMessenger/webapi/users/findUser",user)
   }
   signup(user:User){
      return this.http.post<any>("http://localhost:8080/HibernateMessenger/webapi/users/add",user)
   }
}

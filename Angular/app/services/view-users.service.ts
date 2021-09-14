import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../model/User';

@Injectable({
  providedIn: 'root'
})
export class ViewUsersService {

  constructor(private http:HttpClient) { }
  getUsers(){
   return this.http.get<User[]>("http://localhost:8080/HibernateMessenger/webapi/users/view_all")
  }
  searchUsersByName(searchModel:any){
    return this.http.post<User[]>("http://localhost:8080/HibernateMessenger/webapi/users/searchUserByName",searchModel)
  }
}

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Message } from '../model/Message';
import { SearchModel } from '../model/SearchModel';

@Injectable({
  providedIn: 'root'
})
export class ViewMessagesService {

  constructor(private http:HttpClient) { }
  getMessages(){
    
    return this.http.get<Message[]>("http://localhost:8080/HibernateMessenger/webapi/messages/view_all")
  }
  getMessagesById(searchModel:SearchModel){
    return this.http.post<Message[]>("http://localhost:8080/HibernateMessenger/webapi/messages/searchByUserId",searchModel)
  }
  deleteMessage(searchModel:any){
    return this.http.post<any>("http://localhost:8080/HibernateMessenger/webapi/messages/delete",searchModel)
  }
  addMessage(msg:any){
   return this.http.post<any>("http://localhost:8080/HibernateMessenger/webapi/messages/add",msg)
  }
  searchMessageByName(searchModel:SearchModel){
    return this.http.post<Message[]>("http://localhost:8080/HibernateMessenger/webapi/messages/searchMessageByName",searchModel)
  }
  editMessage(model:any){
    return this.http.post<any>("http://localhost:8080/HibernateMessenger/webapi/messages/editMessage",model)
  }
}

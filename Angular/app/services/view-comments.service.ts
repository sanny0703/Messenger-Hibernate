import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Comment } from '../model/Comment';
import { SearchModel } from '../model/SearchModel';

@Injectable({
  providedIn: 'root'
})
export class ViewCommentsService {

  constructor(private http:HttpClient) { }

  getCommentsByMessageId(searchModel:any){
     return this.http.post<Comment[]>("http://localhost:8080/HibernateMessenger/webapi/comments/getCommentsByMessageId",searchModel)
  }
  addComment(model:any){
    return this.http.post<any>("http://localhost:8080/HibernateMessenger/webapi/comments/add",model)
  }
  removeComment(model:any){
    return this.http.post<any>("http://localhost:8080/HibernateMessenger/webapi/comments/delete",model)
  }
}

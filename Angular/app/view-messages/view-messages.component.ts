import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Comment } from '../model/Comment';
import { Message } from '../model/Message';
import { SearchModel } from '../model/SearchModel';
import { User } from '../model/User';
import { AuthService } from '../services/auth.service';
import { ViewCommentsService } from '../services/view-comments.service';
import { ViewMessagesService } from '../services/view-messages.service';
@Component({
  selector: 'app-view-messages',
  templateUrl: './view-messages.component.html',
  styleUrls: ['./view-messages.component.css'],
})
export class ViewMessagesComponent implements OnInit {
  newComment: Comment = new Comment();
  my_id? :number = undefined
  data: Message[];
  commentsData: Comment[];
  userSearchModel: SearchModel = new SearchModel();
  searchModel: SearchModel = new SearchModel();
  success: boolean = false;
  fail: boolean = false;
  showComments: boolean = false;
  newMessage: Message = new Message();
  edit: Message = new Message();
  comment_count?: number = undefined;
  showEditMessage: boolean = false;
  showEditComment: boolean = false;
  constructor(
    private http: HttpClient,
    public authService: AuthService,
    private router: Router,
    private messageService: ViewMessagesService,
    private commentService: ViewCommentsService
  ) {
    this.data = [];
    this.commentsData = [];
  }

  ngOnInit(): void {
    if (
      this.authService.my_user.uId == undefined ||
      this.authService.my_user.uId === 0
    ) {
      this.router.navigateByUrl('login');
    }
    this.userSearchModel.id = this.authService.my_user.uId;
    this.newMessage.uId = this.authService.my_user.uId;
    this.newMessage.name = this.authService.my_user.name;
  }
  getMessages() {
    this.messageService.getMessages().subscribe(
      (res) => {
        if (res != null) {
          this.data = res;
          this.success = true;
          this.fail = false;
        } else {
          this.fail = true;
          this.success = false;
        }
      },
      (error) => {
        this.fail = true;
        this.success = false;
      }
    );
  }
  getMessagesByUId() {
    this.messageService.getMessagesById(this.userSearchModel).subscribe(
      (res) => {
        if (res != null) {
          this.data = res;
          this.success = true;
          this.fail = false;
        } else {
          this.fail = true;
          this.success = false;
        }
      },
      (error) => {
        this.fail = true;
        this.success = false;
      }
    );
  }
  deleteMessage(i: number) {
    const searchModel = {
      id: this.data[i].id,
    };
    this.messageService.deleteMessage(searchModel).subscribe(
      (res) => {
        if (res.status === 'Success') {
          alert('Deleted Successfully');
          this.getMessages();
        } else {
          alert('Something went wrong');
        }
      },
      (error) => {
        alert('Something went wrong');
      }
    );
  }
  logout() {
    this.authService.my_user = new User();
  }
  NavigateUsers() {
    this.router.navigateByUrl('viewUsers');
  }
  searchPostsByName() {
    this.messageService.searchMessageByName(this.searchModel).subscribe(
      (res) => {
        if (res != null) {
          this.data = res;
          this.success = true;
          this.fail = false;
        } else {
          this.fail = true;
          this.success = false;
        }
      },
      (error) => {
        this.fail = true;
        this.success = false;
      }
    );
  }
  getUserPosts() {
    this.messageService.getMessagesById(this.userSearchModel).subscribe(
      (res) => {
        if (res != null) {
          this.data = res;
          this.success = true;
          this.fail = false;
        } else {
          this.fail = true;
          this.success = false;
        }
      },
      (error) => {
        this.fail = true;
        this.success = false;
      }
    );
  }
  addPost(myForm: any) {
    const messageModel = {
      uId: this.authService.my_user.uId,
      name: this.authService.my_user.name,
      message: this.newMessage.message,
    };

    this.messageService.addMessage(messageModel).subscribe(
      (res) => {
        if (res.status === 'Success') {
          alert('Post added Successfully');
          this.newMessage = new Message();
          myForm.form.markAsPristine();
          this.getMessages();
        } else {
          alert('Something went wrong');
        }
      },
      (error) => {
        alert('Something went wrong');
      }
    );
  }
  editPost(myForm: any, i: number) {
    const model3 = {
      id: this.data[i].id,
      name: this.edit.message,
    };

    this.messageService.editMessage(model3).subscribe(
      (res) => {
        if (res.status === 'Success') {
          alert('Post updated Successfully');
          this.edit = new Message();
          myForm.form.markAsPristine();
          this.getMessages();
          this.showEditMessage = false;
        } else {
          alert('Something went wrong');
        }
      },
      (error) => {
        alert('Something went wrong');
      }
    );
  }
  getComments(i: number) {
    this.showComments = !this.showComments;
    this.commentsData = [];
    const commentSearchModel = {
      id: this.data[i].id,
    };
    this.commentService.getCommentsByMessageId(commentSearchModel).subscribe(
      (res) => {
        if (res != null) {
          this.commentsData = res;
          this.comment_count = this.commentsData.length;
        } else {
          alert('Something went wrong');
        }
      },
      (error) => {
        alert('Something went wrong');
      }
    );
  }
  addComment(myForm3: any, i: number) {
    const model = {
      id: this.data[i].id,
      uId: this.authService.my_user.uId,
      comment: this.newComment.comment,
      author: this.authService.my_user.name,
    };
    this.commentService.addComment(model).subscribe(
      (res) => {
        if (res.status === 'Success') {
          this.newComment = new Comment();
          alert('Comment Added');
          myForm3.form.markAsPristine();
          this.getComments(i);
        } else {
          alert('Something went wrong');
        }
      },
      (error) => {
        alert('Something went wrong');
      }
    );
  }
  deleteComment(j: number, i: number) {
    const model2 = {
      id: this.commentsData[j].cId,
    };
    this.commentService.removeComment(model2).subscribe(
      (res) => {
        if (res.status === 'Success') {
          alert('Comment Removed Successfully');
          this.getComments(i);
        } else {
          alert('Something went wrong');
        }
      },
      (error) => {
        alert('Something went wrong');
      }
    );
  }
  cancelEditComment() {
    this.showEditComment = false;
    this.newComment = new Comment();
  }
  cancelEditMessage() {
    this.showEditMessage = false;
    this.edit = new Message();
  }
}

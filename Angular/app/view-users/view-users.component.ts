import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../model/User';
import { AuthService } from '../services/auth.service';
import { ViewUsersService } from '../services/view-users.service';

@Component({
  selector: 'app-view-users',
  templateUrl: './view-users.component.html',
  styleUrls: ['./view-users.component.css'],
})
export class ViewUsersComponent implements OnInit {
  data: User[];
  success: boolean = false;
  fail: boolean = false;
  searchModel={
    name:""
  }
  constructor(
    public usersService: ViewUsersService,
    public authService: AuthService,
    public router: Router
  ) {
    this.data = [];
  }

  ngOnInit(): void {
    if(this.authService.my_user.uId==undefined || this.authService.my_user.uId===0 || this.authService.my_user.isAdmin!="Y"){
      this.router.navigateByUrl("login")}
  }
  getUsers() {
    this.usersService.getUsers().subscribe(
      (res) => {
        if (res) {
          this.data = res;
          this.success = true;
          this.fail = false;
        } else {
          this.fail = true;
          this.success = false;
          alert("Something went wrong")
        }
      },
      (error) => {
        this.fail = true;
        this.success = false;
        alert("Something went wrong")
      }
    );
  }

  searchUsersByName() {
    console.warn(this.searchModel)
    this.usersService.searchUsersByName(this.searchModel).subscribe(
      (res) => {
        if (res) {
          this.data = res;
          this.success = true;
          this.fail = false;
        } else {
          this.fail = true;
          this.success = false;
          alert("Something went wrong")
        }
      },
      (error) => {
        this.fail = true;
        this.success = false;
        alert("Something went wrong")
      }
    );
  }
  logout(){
    this.authService.my_user = new User()
    
    
    
  }
  NavigatePosts(){
    this.router.navigateByUrl("viewMessages")
  }
}

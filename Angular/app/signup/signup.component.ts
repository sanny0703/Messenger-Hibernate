
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../model/User';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css'],
})
export class SignupComponent implements OnInit {
  user: User;
  success: boolean = false;
  fail: boolean = false;
  checkbox:string=''
  constructor(public authService: AuthService, private router: Router) {
    this.user = new User();
  }

  ngOnInit(): void {}

  submit(signUpForm: any) {
    this.authService.signup(this.user).subscribe(
      (res) => {
        if (res.status === 'Success') {
          this.success = true;
          this.fail = false;
          this.user = new User()
          signUpForm.form.markAsPristine()
          alert("Regestration Successfull")
        } else {
          this.fail = true;
          this.success = false;
          alert("Oops something went wrong")
        }
      },
      (error) => {
        this.fail = true;
        this.success = false;
        alert("Oops something went wrong")
      }
    );
  }
  NavigateSignIn(){
    this.router.navigateByUrl("login")
  }
  NavigatePosts(){
    this.router.navigateByUrl("viewMessages")
  }
}

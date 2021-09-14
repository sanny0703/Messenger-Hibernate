import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../model/User';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  user: User;
  success: boolean = false;
  fail: boolean = false;
  valid:boolean = false
  invalid:boolean= true
  constructor(public authService: AuthService, private router: Router) {
    this.user = new User();
  }

  ngOnInit(): void {}

  submit(loginForm: any) {
    this.authService.login(this.user).subscribe(
      (res) => {
        if (res) {
          this.user = new User();
          loginForm.form.markAsPristine();
          this.authService.my_user = res;

          this.success = true;
          this.fail = false;
          this.valid = true
          this.invalid = false
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
  
  NavigateSignUp() {
    this.router.navigateByUrl('signUp');
  }
  logout(){
    this.authService.my_user = new User()
    
    
    
  }
  NavigateUsers(){
    this.router.navigateByUrl("viewUsers")
  }
  NavigateMessages(){
    this.router.navigateByUrl("viewMessages")
  }
}

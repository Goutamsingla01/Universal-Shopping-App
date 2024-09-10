import { CommonModule } from '@angular/common';
import { Component, NgModule } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule, Router } from '@angular/router';
import { AuthService } from '../services/auth/auth.service';
import { HttpClientModule } from '@angular/common/http';
import { uptime } from 'process';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [CommonModule,ReactiveFormsModule,RouterModule,FormsModule],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})
export class SignupComponent {

  signupForm!:FormGroup;

  constructor(private fb:FormBuilder,
    private snackBar: MatSnackBar,
    private authService:AuthService,
    private router:Router
  ){}

    ngOnInit():void{
     this.signupForm=this.fb.group({
      name:[null,[Validators.required]],
      email:[null,[Validators.required,Validators.email]],
      password:[null,[Validators.required]],
      confirmPassword:[null,[Validators.required]],
     })
    }
  OnSignup():void{
  const password=this.signupForm.get("password")?.value;
  const confirmPassword=this.signupForm.get("confirmPassword")?.value;
  console.log(this.signupForm.value);
  if(password !== confirmPassword){
    alert("Password are not same!")
    return; 
  }

  this.authService.register(this.signupForm.value).subscribe({
    next: (response)=>{
       alert("Signup Successful!");
       this.router.navigateByUrl('home/login');
    },
    error: (error)=>{
      console.log(error);
       alert(error.message);
    }
  })
  
  // this.authService.register('hello form').subscribe({
  //   next: (response)=>{
  //      alert(response);
  //     //  this.router.navigate(['../login']);
  //   },
  //   error: (error)=>{
  //     console.log(error);
  //      alert(error.message);
  //   }
  // })
  
 }
}

import { CommonModule } from '@angular/common';
import { Component, NgModule } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, NgForm, ReactiveFormsModule, Validators } from '@angular/forms';
import { RouterModule, Router } from '@angular/router';
import { AuthService } from '../services/auth/auth.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserStorageService } from '../services/storage/user-storage.service';
@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule,FormsModule,RouterModule,ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  loginForm: FormGroup;

  constructor(private router: Router,
    private authService:AuthService,
    private snackBar: MatSnackBar,
    private formBuilder: FormBuilder 
  ){}

  ngOnInit():void{
    this.loginForm=this.formBuilder.group({
      email:[null, [Validators.required]],
      password:[null, [Validators.required]]
    })
  }
onLogin():void{
  const username=this.loginForm.get('email')!.value;
  const password=this.loginForm.get('password')!.value;
  
  this.authService.login(username,password).subscribe({
    next: (res)=>{
      if(UserStorageService.isAdminLoggedIn()){
        this.router.navigateByUrl('home/admin/dashboard');
      }else if(UserStorageService.isAdminLoggedIn ){
        this.router.navigateByUrl('home/customer/dashboard');
      }
    },
    error: (error)=>{
      console.log(error);
      alert("Bad credentials")
    }
  })

}
}

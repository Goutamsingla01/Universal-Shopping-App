import { CommonModule } from '@angular/common';
import { Component,Input } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { UserStorageService } from '../services/storage/user-storage.service';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule,RouterModule],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {
  isCustomerLoggedIn: boolean=UserStorageService.isCustomerLoggedIn();
  isAdminLoggedIn:boolean= UserStorageService.isAdminLoggedIn();

  constructor(private router:Router){ }

  ngOnInit():void{
    this.router.events.subscribe(event=>{
      this.isCustomerLoggedIn=UserStorageService.isCustomerLoggedIn();
      this.isAdminLoggedIn= UserStorageService.isAdminLoggedIn();
    })
  }

  logout(){
    UserStorageService.signOut();
    this.router.navigateByUrl('home/login');
  }
}

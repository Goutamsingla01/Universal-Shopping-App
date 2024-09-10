import { Component } from '@angular/core';
import { HeaderComponent } from '../header/header.component';
import { Router, RouterOutlet } from '@angular/router';
import { UserStorageService } from '../services/storage/user-storage.service';
@Component({
  selector: 'app-home',
  standalone: true,
  imports: [HeaderComponent,RouterOutlet],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
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
    this.router.navigateByUrl('login');
  }
}

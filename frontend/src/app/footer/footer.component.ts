import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { UserStorageService } from '../services/storage/user-storage.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-footer',
  standalone: true,
  imports: [CommonModule,RouterModule],
  templateUrl: './footer.component.html',
  styleUrl: './footer.component.css'
})
export class FooterComponent {
  cururl:string='';
  isCustomerLoggedIn: boolean=UserStorageService.isCustomerLoggedIn();
  
  constructor(public router:Router){ }
  
  ngOnInit():void{
    this.router.events.subscribe(event=>{
      this.isCustomerLoggedIn=UserStorageService.isCustomerLoggedIn();
    })
    this.cururl="http://localhost:4200"+this.router.url;
  }
}

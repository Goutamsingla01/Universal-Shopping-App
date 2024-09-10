import { Component } from '@angular/core';

import { UserStorageService } from '../../../services/storage/user-storage.service';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-support-form-submitted',
  standalone: true,
  imports: [CommonModule,RouterModule],
  templateUrl: './support-form-submitted.component.html',
  styleUrl: './support-form-submitted.component.css'
})
export class SupportFormSubmittedComponent {
  isCustomerLoggedIn: boolean=UserStorageService.isCustomerLoggedIn();
  
  constructor(
    private router:Router
  ){}
  ngOnInit():void{
    this.router.events.subscribe(event=>{
      this.isCustomerLoggedIn=UserStorageService.isCustomerLoggedIn();
    })
  }
}

import { Component } from '@angular/core';
import { UserStorageService } from '../../../services/storage/user-storage.service';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-support',
  standalone: true,
  imports: [RouterModule,CommonModule],
  templateUrl: './support.component.html',
  styleUrl: './support.component.css'
})
export class SupportComponent {
  cururl:string='';
  isCustomerLoggedIn: boolean=UserStorageService.isCustomerLoggedIn();
  
  constructor(
    private router:Router
  ){}
  ngOnInit():void{
    this.router.events.subscribe(event=>{
      this.isCustomerLoggedIn=UserStorageService.isCustomerLoggedIn();
    })
    this.cururl="http://localhost:4200"+this.router.url+"/form-submitted";
      console.log(this.cururl);
  }

}

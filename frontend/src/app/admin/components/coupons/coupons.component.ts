import { Component } from '@angular/core';
import { AdminService } from '../../service/admin.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-coupons',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './coupons.component.html',
  styleUrl: './coupons.component.css'
})
export class CouponsComponent {
     
  coupons:any;

  constructor(private adminService: AdminService){}
  
  ngOnInit(){
    this.getAllCoupons();
  }

  getAllCoupons(){
    this.adminService.getAllCoupons().subscribe(res =>{
      this.coupons=res;
    })
  }
}

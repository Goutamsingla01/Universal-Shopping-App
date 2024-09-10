import { Component } from '@angular/core';
import { CustomerService } from '../../services/customer.service';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-my-orders',
  standalone: true,
  imports: [CommonModule,RouterModule],
  templateUrl: './my-orders.component.html',
  styleUrl: './my-orders.component.css'
})
export class MyOrdersComponent {
  myOrders:any;
  
  constructor(private customerService:CustomerService){}

  ngOnInit(){
    this.getMyOrders();
  }

  getMyOrders(){
    this.customerService.getOrdersByUserId().subscribe(res=>{
      this.myOrders=res;
    })
  }
}

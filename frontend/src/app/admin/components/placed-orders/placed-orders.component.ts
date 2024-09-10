import { Component } from '@angular/core';
import { AdminService } from '../../service/admin.service';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-placed-orders',
  standalone: true,
  imports: [CommonModule,RouterModule],
  templateUrl: './placed-orders.component.html',
  styleUrl: './placed-orders.component.css'
})
export class PlacedOrdersComponent {
    

  orders:  any;

  constructor(private adminService: AdminService){}
  
  ngOnInit(){
    this.getPlacedOrders();
  }

  getPlacedOrders(){
    this.adminService.getAllPlacedOrders().subscribe(res=>{
      this.orders=res;
      console.log(this.orders.cartItems);
    })
  }

  changeorderStatus(orderId:number, status:string){
      this.adminService.changeOrderStatus(orderId,status).subscribe(res=>{
        if(res.id !=null){
          alert("Order status changed successfully");
          this.getPlacedOrders();
        }else{
          alert("Something went wrong!");
        }
      })
  }
}

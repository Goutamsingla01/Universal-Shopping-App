import { Component } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AdminService } from '../../service/admin.service';

@Component({
  selector: 'app-view-order-details-admin',
  standalone: true,
  imports: [CommonModule,RouterModule],
  templateUrl: './view-order-details-admin.component.html',
  styleUrl: './view-order-details-admin.component.css'
})
export class ViewOrderDetailsAdminComponent {
 
  orderId:string;
  orderedProductDetailsList=[];
  orderAmount:any;
  discount:any;
  totalAmount:any;

  constructor(
    private activatedRoute:ActivatedRoute,
    private adminService:AdminService
  ){}

  ngOnInit(){
    this.orderId=this.activatedRoute.snapshot.params['orderId'];
    this.getOrderedProductsDetailsByOrderId();
  }

  getOrderedProductsDetailsByOrderId(){
    console.log(this.orderId);
    this.adminService.getOrderedProducts(this.orderId).subscribe(res=>{
       res.productDtoList.forEach(element =>{
        element.processedImg='data:image/jpeg;base64,'+element.byteImg;
        this.orderedProductDetailsList.push(element);
       });
       console.log(res);
       this.orderAmount=res.orderAmount;
       this.discount=res.discount;
       this.totalAmount=res.totalAmount;
    })
  }
}

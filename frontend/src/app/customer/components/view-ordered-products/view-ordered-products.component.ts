import { Component } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { CustomerService } from '../../services/customer.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-view-ordered-products',
  standalone: true,
  imports: [CommonModule,RouterModule],
  templateUrl: './view-ordered-products.component.html',
  styleUrl: './view-ordered-products.component.css'
})
export class ViewOrderedProductsComponent {
  
  orderId:string;
  orderedProductDetailsList=[];
  orderAmount:any;
  discount:any;
  totalAmount:any;
  orderStatus:any;

  constructor(
    private activatedRoute:ActivatedRoute,
    private customerService:CustomerService
  ){}

  ngOnInit(){
    this.orderId=this.activatedRoute.snapshot.params['orderId'];
    this.getOrderedProductsDetailsByOrderId();
  }

  getOrderedProductsDetailsByOrderId(){
    this.customerService.getOrderedProducts(this.orderId).subscribe(res=>{
       res.productDtoList.forEach(element =>{
        element.processedImg='data:image/jpeg;base64,'+element.byteImg;
        this.orderedProductDetailsList.push(element);
       });
       this.totalAmount=res.totalAmount;
       this.orderAmount=res.orderAmount;
       this.discount=res.discount;
       this.orderStatus=res.orderStatus;
    })
  }
}

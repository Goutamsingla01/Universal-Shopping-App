import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { CustomerService } from '../../services/customer.service';
import { validateHeaderName } from 'http';
import { PlaceOrderComponent } from '../place-order/place-order.component';
import { MatDialog } from '@angular/material/dialog';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [CommonModule,FormsModule,ReactiveFormsModule,RouterModule],
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.css'
})
export class CartComponent {
   
  cartItems: any[]= [];
  order: any;

  couponForm!: FormGroup;
  
  constructor(private customerService: CustomerService,
    private fb:FormBuilder,
    private dialog:MatDialog
  ){}

  ngOnInit(): void {
    this.couponForm=this.fb.group({
      code:[null, [Validators.required]]
    })
    this.getCart();
  }

  applyCoupon(){
    this.customerService.applyCoupon(this.couponForm.get(['code'])!.value).subscribe({
      next: (res) =>{
      alert("Coupon applied successfully");
      this.getCart();
      },
      error: (error) =>{
          alert(error.error);
      }
    })
  }

  getCart(){
    this.cartItems=[];
    this.customerService.getCartByUserId().subscribe(res =>{
      this.order=res;
      console.log(res);
      res.cartItems.forEach(element =>{
        element.processedImg= 'data:image/jpeg;base64,' +element.returnedImg;
        this.cartItems.push(element);
      })
    })
  }

  increaseQuantity(productId:any){
    this.customerService.increaseProductQuantity(productId).subscribe(res=>{
      alert("product quantity increased");
      this.getCart();
    })
  }

  decreaseQuantity(productId:any){
    this.customerService.decreaseProductQuantity(productId).subscribe(res=>{
      alert("product quantity decreased");
      this.getCart();
    })
  }
  removeFromCart(productId,quantity){
    console.log(productId);
    this.customerService.removeCartProduct(productId,quantity).subscribe(res=>{
      alert("removed from cart");
      this.getCart();
    })
  }

  placeOrder(){
    this.dialog.open(PlaceOrderComponent)
  }

  //old methods
 
  // constructor(){
  //   this.products=this.productsservice.getAllProducts();
  //   for(let prod of this.products){
  //     if(prod.cart){
  //       this.totalPrice+=prod.price;
  //     }
  //   }
  //   this.discPrice=this.totalPrice;
  // }
  
  
}

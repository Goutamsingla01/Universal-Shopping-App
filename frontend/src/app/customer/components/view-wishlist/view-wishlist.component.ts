import { Component } from '@angular/core';
import { CustomerService } from '../../services/customer.service';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-view-wishlist',
  standalone: true,
  imports: [CommonModule,RouterModule],
  templateUrl: './view-wishlist.component.html',
  styleUrl: './view-wishlist.component.css'
})
export class ViewWishlistComponent {
  
  products=[];

  constructor(
    private customerService: CustomerService,
    private router:Router
  ){}

  ngOnInit(){
    this.getWishlistByUserId();
  }

  getWishlistByUserId(){
    this.customerService.getWishlistByUserId().subscribe(res=>{
      console.log(res);
      res.forEach(element=>{
        element.processedImg='data:image/jpeg;base64,' +element.returnedImg;
        this.products.push(element);
      })
    })
  }

  removeFromWishlist(productId){
    console.log(productId);
    this.customerService.removeWishlistProduct(productId).subscribe(res=>{
      alert("remove from wishlist");
      this.products = [];
      this.getWishlistByUserId();
    })
  }
}

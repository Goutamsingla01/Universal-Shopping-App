import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { CustomerService } from '../../services/customer.service';
import { CommonModule } from '@angular/common';
import { UserStorageService } from '../../../services/storage/user-storage.service';
import { FooterComponent } from '../../../footer/footer.component';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule,RouterModule, FooterComponent],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {
  products: any[]=[];

  constructor(private customerService:CustomerService,
    private router:Router
  ){}
  ngOnInit(){
    this.getAllProducts();
  }
  getAllProducts(){
    this.products=[];
    this.customerService.getAllProducts().subscribe(res=>{
      res.forEach(element =>{
        element.processedImg='data:image/jpeg;base64,'+element.byteImg;
        this.products.push(element);
      })
      console.log(this.products)
    })
  }
  onCartClick(id:any){
    console.log(id)
     this.customerService.addToCart(id).subscribe((res)=>{
      alert("product added to cart successfully!");
     });
  }
  addToWishlist(productId:number){
    const wishlistDto={
      userId: UserStorageService.getUserId(),
      productId: productId
    }
    console.log(wishlistDto.productId);
    this.customerService.addProductToWishList(wishlistDto).subscribe(res=>{
      if(res.id!=null){
        alert("Product Added to wishlist");
      }else{
        alert("Something went wrong!");
      }
    })
  }
  isCustomerloggedin

}

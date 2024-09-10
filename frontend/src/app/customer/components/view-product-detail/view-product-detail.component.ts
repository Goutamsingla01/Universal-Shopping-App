import { Component } from '@angular/core';
import { CustomerService } from '../../services/customer.service';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { UserStorageService } from '../../../services/storage/user-storage.service';
import { FooterComponent } from '../../../footer/footer.component';

@Component({
  selector: 'app-view-product-detail',
  standalone: true,
  imports: [CommonModule,FooterComponent],
  templateUrl: './view-product-detail.component.html',
  styleUrl: './view-product-detail.component.css'
})
export class ViewProductDetailComponent {
   
  productId:number;
  product:any;
  reviews:any[]=[];

  constructor(
    private customerService:CustomerService,
    private activatedRoute:ActivatedRoute
  ){}
  
  ngOnInit(){
    this.productId=this.activatedRoute.snapshot.params["productId"];
    this.getProductDetailsById();
  }

  getProductDetailsById(){
    this.customerService.getProductDetailsById(this.productId).subscribe(res=>{
      this.product=res.productDto;
      this.product.processedImg='data:image/png;base64,'+ res.productDto.byteImg;
      
      res.reviewDtoList.forEach(element=>{
        element.processedImg='data:image/png;base64,'+element.returnedImg;
        this.reviews.push(element);
      });

    })
  }

  addToWishlist(){
    const wishlistDto={
      userId: UserStorageService.getUserId(),
      productId: this.productId
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
  onCartClick(){
     this.customerService.addToCart(this.productId).subscribe((res)=>{
      alert("product added to cart successfully!");
     });
  }
}

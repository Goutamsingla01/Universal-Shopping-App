import { Component } from '@angular/core';
import { AdminService } from '../../service/admin.service';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule,RouterModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {
  products: any[]=[];

  constructor(private adminService:AdminService,
    private router:Router
  ){}
  ngOnInit(){
    this.getAllProducts();
  }
  getAllProducts(){
    this.products=[];
    this.adminService.getAllProducts().subscribe(res=>{
      res.forEach(element =>{
        element.processedImg='data:image/jpeg;base64,'+element.byteImg;
        this.products.push(element);
      })
      console.log(this.products)
    })
  }
   deleteProduct(productId:any){
    if(confirm("Want to delete the product?")){
    this.adminService.deleteProduct(productId).subscribe((res)=>{
      if(res==null){
        alert("product deleted successfully!");
        this.getAllProducts();
      }else{
        alert(res.message);
      }
    })
  }
   }
}

import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { CustomerService } from '../../services/customer.service';

import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { UserStorageService } from '../../../services/storage/user-storage.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-review-ordered-product',
  standalone: true,
  imports: [RouterModule,FormsModule,ReactiveFormsModule,CommonModule],
  templateUrl: './review-ordered-product.component.html',
  styleUrl: './review-ordered-product.component.css'
})
export class ReviewOrderedProductComponent {
  
  productId:number;
  reviewForm!:FormGroup;

  selectedFile:File|null;
  imagePreview:string|ArrayBuffer|null; 

  constructor(
    private fb:FormBuilder,
    private customerService:CustomerService,
    private router:Router,
    private activatedRoute:ActivatedRoute
  ){}

  ngOnInit(){
    this.reviewForm=this.fb.group({
      rating:[null,Validators.required],
      description:[null,[Validators.required]]
    })
    this.productId=this.activatedRoute.snapshot.params["productId"];
  }

  onFileSelected(event:any){
    this.selectedFile=event.target.files[0];
    this.previewImage();
  }

  previewImage(){
    const reader=new FileReader();
    reader.onload=()=>{
      this.imagePreview=reader.result;
    }
    reader.readAsDataURL(this.selectedFile);
  }

  submitForm(){
    const formData:FormData=new FormData();
   
    formData.append('img', this.selectedFile);
    formData.append('productId', this.productId.toString());
    formData.append('userId',UserStorageService.getUserId());
    formData.append('rating',this.reviewForm.get('rating').value);
    formData.append('description',this.reviewForm.get('description').value);
    

    this.customerService.giveReview(formData).subscribe({
      next: (res)=>{
      alert("Review Posted Successfully");
      this.router.navigateByUrl('home/customer/my-orders');
      },
    error: (error)=>{
      alert("Something went wrong!");}
  });
  }
}

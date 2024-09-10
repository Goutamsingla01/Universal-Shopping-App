import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AdminService } from '../../service/admin.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-update-product',
  standalone: true,
  imports: [CommonModule,FormsModule, ReactiveFormsModule],
  templateUrl: './update-product.component.html',
  styleUrl: './update-product.component.css'
})
export class UpdateProductComponent {
  
  productId :string;
  productForm: FormGroup;
  existingImage:string|null=null;
  imagePreview:string|ArrayBuffer|null=null;
  selectedFile:any;
  imgChange=false;
  
  constructor(
    private fb:FormBuilder,
    private router: Router,
    private adminService:AdminService,
    private activatedroute: ActivatedRoute
  ){}

  ngOnInit(){
    this.productForm=this.fb.group({
      name:[null, [Validators.required]],
      price:[null,[Validators.required]],
      description:[null,[Validators.required]]
    })
    this.productId=this.activatedroute.snapshot.params['productId'];
    this.getProductById();
    console.log(this.productId);
  }
  
  onFileSelected(event:any){
     this.selectedFile=event.target.files[0];
     this.previewImage();
     this.imgChange=true;
     this.existingImage=null;
  }
  previewImage(){
    const reader=new FileReader();
    reader.onload=()=>{
      this.imagePreview=reader.result;
    }
    reader.readAsDataURL(this.selectedFile);
  }
  getProductById(){
    this.adminService.getProductById(this.productId).subscribe(res=>{
      this.productForm.patchValue(res);

      this.existingImage='data:image/jpeg;base64,' +res.byteImg;
    })
  }

  updateProduct(){
    if(this.productForm.valid){
      const formData:FormData=new FormData();
      if(this.imgChange&& this.selectedFile){
        formData.append('img', this.selectedFile);
      }

      formData.append('name', this.productForm.get('name').value);
      formData.append('description', this.productForm.get('description').value);
      formData.append('price', this.productForm.get('price').value);
      
      this.adminService.updateProduct(this.productId,formData).subscribe((res)=>{
       if(res.id !=null){
         alert('Product Updated Successfully!');
         this.router.navigateByUrl('home/admin/dashboard');
       }else{
         alert(res.message);
       }
      })
    }else{
     for(const i in this.productForm.controls){
       this.productForm.controls[i].markAsDirty();
       this.productForm.controls[i].updateValueAndValidity();
     }
    }
  }

  
}

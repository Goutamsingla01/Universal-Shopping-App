import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Route, Router } from '@angular/router';
import { AdminService } from '../../service/admin.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-post-product',
  standalone: true,
  imports: [FormsModule,ReactiveFormsModule,CommonModule],
  templateUrl: './post-product.component.html',
  styleUrl: './post-product.component.css'
})
export class PostProductComponent {
   
  productForm: FormGroup;
  selectedFile: File| null;
  imagePreview: string|ArrayBuffer| null;

  constructor(
    private fb:FormBuilder,
    private router:Router,
    private adminService: AdminService
  ){}

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

  ngOnInit():void{
    this.productForm=this.fb.group({
      name:[null,[Validators.required]],
      price:[null,[Validators.required]],
      description:[null,[Validators.required]]
    });

  }

  addProduct():void{
     if(this.productForm.valid){
       const formData:FormData=new FormData();
       formData.append('img', this.selectedFile);
       formData.append('name', this.productForm.get('name').value);
       formData.append('description', this.productForm.get('description').value);
       formData.append('price', this.productForm.get('price').value);
       
       this.adminService.addProduct(formData).subscribe((res)=>{
        if(res.id !=null){
          alert('Product Posted Successfully!');
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

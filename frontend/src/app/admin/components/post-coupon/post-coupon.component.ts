import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { AdminService } from '../../service/admin.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-post-coupon',
  standalone: true,
  imports: [CommonModule, FormsModule,ReactiveFormsModule],
  templateUrl: './post-coupon.component.html',
  styleUrl: './post-coupon.component.css'
})
export class PostCouponComponent {
   couponForm: FormGroup;

   constructor(private fb: FormBuilder,
    private router:Router,
    private adminService: AdminService
   ){}

   ngOnInit(){
    this.couponForm=this.fb.group({
      name:[null, [Validators.required]],
      code:[null, [Validators.required]],
      discount:[null, [Validators.required]],
      expirationDate:[null, [Validators.required]]
    })
   }

   addCoupon(){
      if(this.couponForm.valid){
        this.adminService.addCoupon(this.couponForm.value).subscribe(res =>{
          if(res.id !=null){
            alert("Coupon created successfully");
            this.router.navigateByUrl('home/admin/dashboard');
          }else{
            alert(res.message);
          }
        })
      }else{
        this.couponForm.markAllAsTouched();
      }
   }
}

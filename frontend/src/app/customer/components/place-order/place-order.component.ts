import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { CustomerService } from '../../services/customer.service';
import { Router } from '@angular/router';
import {MatDialog} from '@angular/material/dialog';
import {MatCardModule} from '@angular/material/card';
import {MatError, MatFormField, MatLabel} from '@angular/material/form-field'
import {MatInputModule} from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
@Component({
  selector: 'app-place-order',
  standalone: true,
  imports: [FormsModule,ReactiveFormsModule,CommonModule, MatCardModule,MatFormField,MatLabel,MatError,MatInputModule, MatButtonModule],
  templateUrl: './place-order.component.html',
  styleUrl: './place-order.component.css'
})
export class PlaceOrderComponent {
  

  orderForm!: FormGroup;

  constructor(
    private fb:FormBuilder,
    private customerService: CustomerService,
    private router: Router,
    private dialog: MatDialog
  ){}

  ngOnInit(){
    this.orderForm=this.fb.group({
      address:[null, [Validators.required]],
      orderDescription:[null],
      
    })
  }

  placeOrder(){
    console.log("hi")
    this.customerService.placeOrder(this.orderForm.value).subscribe(res=>{
      if(res.id !=null){
        alert("Order placed successfully");
        this.router.navigateByUrl('home/customer/my-orders');
        this.closeForm();
      }else{
        alert("Something went wrong");
      }
    })
  }

  closeForm(){
      this.dialog.closeAll();
  }


}

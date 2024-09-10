import { Injectable } from '@angular/core';
import { Product } from './product';

@Injectable({
  providedIn: 'root'
})
export class ProductsService {
  products!:Product[];
  product:Product|undefined
  constructor(){
    this.products=[{
      title:"SmartPhone1",
      model:"Some quick example",
    desc:"Lorem ipsum dolor sit amet consectetur adipisicing elit. Totam, iure?e",
    price:7000,
    url:"product1.png",
    like:false,
    cart:true,
    id:1
    },{
      title:"SmartPhone2",
      model:"Some quick example",
    desc:"Lorem ipsum dolor sit amet consectetur adipisicing elit. Totam, iure?e",
    price:8000,
    url:"product2.png",
    like:true,
    cart:false,
    id:2
    },{
      title:"SmartPhone3",
      model:"Some quick example",
    desc:"Lorem ipsum dolor sit amet consectetur adipisicing elit. Totam, iure?e",
    price:9000,
    url:"product3.png",
    like:false,
    cart:false,
    id:3
    },{
      title:"SmartPhone4",
      model:"Some quick example",
    desc:"Lorem ipsum dolor sit amet consectetur adipisicing elit. Totam, iure?e",
    price:10500,
    url:"product4.png",
    like:false,
    cart:false,
    id:4
    },{
      title:"SmartPhone5",
      model:"Some quick example",
    desc:"Lorem ipsum dolor sit amet consectetur adipisicing elit. Totam, iure?e",
    price:5000,
    url:"product5.png",
    like:false,
    cart:false,
    id:5
    },{
      title:"SmartPhone6",
      model:"Some quick example",
    desc:"Lorem ipsum dolor sit amet consectetur adipisicing elit. Totam, iure?e",
    price:7000,
    url:"product6.png",
    like:false,
    cart:false,
    id:6
    },{
      title:"SmartPhone7",
      model:"Some quick example",
    desc:"Lorem ipsum dolor sit amet consectetur adipisicing elit. Totam, iure?e",
    price:8000,
    url:"product7.png",
    like:false,
    cart:false,
    id:7
    },]
  }
  getAllProducts():Product[]{
     return this.products
  }
  getproduct(id:number):Product | undefined {
    return this.products.find((value)=>value.id===id)
  }
  updateLike(id:number ){
    this.product=this.products.find((value)=>value.id===id)
    if(this.product){
      this.product.like=!this.product.like;
      this.product=undefined;
      console.log("like changed")
    }
  }
  updateCart(id:number ){
    this.product=this.products.find((value)=>value.id===id)
    if(this.product){
      this.product.cart=!this.product.cart;
      this.product=undefined;
      console.log("cart changed")
    }
  }
}

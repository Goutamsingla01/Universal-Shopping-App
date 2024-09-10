import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserStorageService } from '../../services/storage/user-storage.service';
import { Observable } from 'rxjs';

const BASIC_URL="http://localhost:8080/";
@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http:HttpClient) { }
  
  addProduct(productDto:any):Observable<any>{
    return this.http.post(BASIC_URL+'api/admin/product',productDto,{
      headers: this.createAuthorizationHeader(),
    })
  }
  updateProduct(productId:any,productDto:any):Observable<any>{
    return this.http.put(BASIC_URL+`api/admin/product/${productId}`,productDto,{
      headers: this.createAuthorizationHeader(),
    })
  }
  getAllProducts(): Observable<any>{
    return this.http.get(BASIC_URL+'api/admin/products',{
      headers: this.createAuthorizationHeader(),
    })
  }

  deleteProduct(productId:any):Observable<any>{
    return this.http.delete(BASIC_URL+`api/admin/product/${productId}`,{
      headers: this.createAuthorizationHeader(),
    })
  }

  addCoupon(couponDto:any):Observable<any>{
    return this.http.post(BASIC_URL+'api/admin/coupons',couponDto,{
      headers: this.createAuthorizationHeader(),
    })
  }
  getOrderedProducts(orderId): Observable<any>{
    console.log(orderId);
   return this.http.get(BASIC_URL+`api/admin/ordered-products/${orderId}`, {
     headers: this.createAuthorizationHeader(),
   })
  }

  getAllCoupons(): Observable<any>{
    return this.http.get(BASIC_URL+'api/admin/coupons',{
      headers: this.createAuthorizationHeader(),
    })
  }

  getAllPlacedOrders(): Observable<any>{
    return this.http.get(BASIC_URL+'api/admin/placed-orders',{
      headers: this.createAuthorizationHeader(),
    })
  }

 changeOrderStatus(orderId: number, status:string): Observable<any>{
    console.log(orderId);
    console.log(status);
    return this.http.get(BASIC_URL+`api/admin/order/${orderId}/${status}`,{
      headers: this.createAuthorizationHeader(),
    })
  }

  getProductById(productId): Observable<any>{
    return this.http.get(BASIC_URL+`api/admin/product/${productId}`,{
      headers: this.createAuthorizationHeader(),
    })
  }
  
  private createAuthorizationHeader(): HttpHeaders{
    return new HttpHeaders().set(
      'Authorization', 'Bearer '+UserStorageService.getToken()
    )
  }
}

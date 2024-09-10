import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CustomerComponent } from './customer.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { CartComponent } from './components/cart/cart.component';
import { MyOrdersComponent } from './components/my-orders/my-orders.component';
import { ViewOrderedProductsComponent } from './components/view-ordered-products/view-ordered-products.component';
import { ReviewOrderedProductComponent } from './components/review-ordered-product/review-ordered-product.component';
import { ViewProductDetailComponent } from './components/view-product-detail/view-product-detail.component';
import { ViewWishlistComponent } from './components/view-wishlist/view-wishlist.component';
import { SupportComponent } from './components/support/support.component';
import { LandingpageComponent } from '../landingpage/landingpage.component';
import { SupportFormSubmittedComponent } from './components/support-form-submitted/support-form-submitted.component';

const routes: Routes = [
  { path: '', component: CustomerComponent },
  { path: 'dashboard', component: DashboardComponent},
  { path: 'cart', component: CartComponent}, // another child route component that the router renders
  {path: 'land', component: LandingpageComponent,},
  {path: 'my-orders', component: MyOrdersComponent,},
  {path: 'ordered-products/:orderId', component: ViewOrderedProductsComponent,},
  {path: 'review/:productId', component: ReviewOrderedProductComponent,},
  {path: 'product/:productId', component: ViewProductDetailComponent,},
  {path: 'wishlist', component: ViewWishlistComponent,},
  {path: 'support', component: SupportComponent,},
  {path: 'support/form-submitted', component: SupportFormSubmittedComponent,}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomerRoutingModule { }

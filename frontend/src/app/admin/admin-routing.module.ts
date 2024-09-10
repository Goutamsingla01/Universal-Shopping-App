import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { PostProductComponent } from './components/post-product/post-product.component';
import { PostCouponComponent } from './components/post-coupon/post-coupon.component';
import { CouponsComponent } from './components/coupons/coupons.component';
import { PlacedOrdersComponent } from './components/placed-orders/placed-orders.component';
import { UpdateProductComponent } from './components/update-product/update-product.component';
import { ViewOrderDetailsAdminComponent } from './components/view-order-details-admin/view-order-details-admin.component';

const routes: Routes = [
  { path: '', component: AdminComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'product', component: PostProductComponent },
  { path: 'post-coupon', component: PostCouponComponent },
  { path: 'coupons', component: CouponsComponent },
  { path: 'orders', component: PlacedOrdersComponent },
  { path: 'product/:productId', component: UpdateProductComponent },
  { path: 'orders/orderdetails/:orderId', component: ViewOrderDetailsAdminComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }

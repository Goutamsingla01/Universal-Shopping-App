import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { LandingpageComponent } from './landingpage/landingpage.component';
import { SignupComponent } from './signup/signup.component';
import { SupportComponent } from './customer/components/support/support.component';
import { DashboardComponent } from './customer/components/dashboard/dashboard.component';
import { SupportFormSubmittedComponent } from './customer/components/support-form-submitted/support-form-submitted.component';

export const routes: Routes = [
    {
        path: 'home',
        component: HomeComponent, // this is the component with the <router-outlet> in the template
        children: [
          {path: '',component: LandingpageComponent },
          { path: 'login', component: LoginComponent },
          { path: 'signup', component: SignupComponent },
          { path: 'customer', loadChildren: () => import('./customer/customer.module').then(m => m.CustomerModule) },
          { path: 'admin', loadChildren: () => import('./admin/admin.module').then(m => m.AdminModule) },
          {path: 'support',component: SupportComponent, },
          {path: 'support/form-submitted',component: SupportFormSubmittedComponent, }
        ],
      },
  { path: '',   redirectTo: '/home', pathMatch: 'full' },
  { path: '**',   redirectTo: '/home', pathMatch: 'full' },
];

// {
//   path: 'product/details/:id',
//   component: ProductdetailComponent, // another child route component that the router renders
// }

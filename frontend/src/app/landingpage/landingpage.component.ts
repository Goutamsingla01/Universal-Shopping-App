import { Component } from '@angular/core';
import { AuthService } from '../services/auth/auth.service';
import { FooterComponent } from '../footer/footer.component';
import { UserStorageService } from '../services/storage/user-storage.service';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-landingpage',
  standalone: true,
  imports: [FooterComponent,CommonModule,RouterModule],
  templateUrl: './landingpage.component.html',
  styleUrl: './landingpage.component.css'
})
export class LandingpageComponent {
  message: string;
  isCustomerLoggedIn: boolean=UserStorageService.isCustomerLoggedIn();

  constructor(private corsTestService: AuthService,
    private router:Router
  ) {}

  ngOnInit():void{
    this.router.events.subscribe(event=>{
      this.isCustomerLoggedIn=UserStorageService.isCustomerLoggedIn();
    })
  }
  // testCors() {
  //   this.corsTestService.checkCors().subscribe(
  //     (response) => {
  //       this.message = response;
  //     },
  //     (error) => {
  //       this.message = 'CORS failed: ' + error.message;
  //     }
  //   );
  // }
  responseMessage: string;

  testCors(): void {
    this.corsTestService.testCorsPost('Hello from Angular').subscribe({
      next: (response) => this.responseMessage = response,
      error: (error) => {
        console.error('Error:', error);
        this.responseMessage = 'Error: ' + error.message;
      }
    });
  }
}

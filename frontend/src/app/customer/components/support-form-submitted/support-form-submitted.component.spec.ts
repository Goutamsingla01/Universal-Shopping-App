import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SupportFormSubmittedComponent } from './support-form-submitted.component';

describe('SupportFormSubmittedComponent', () => {
  let component: SupportFormSubmittedComponent;
  let fixture: ComponentFixture<SupportFormSubmittedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SupportFormSubmittedComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SupportFormSubmittedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

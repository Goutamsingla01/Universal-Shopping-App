import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewOrderDetailsAdminComponent } from './view-order-details-admin.component';

describe('ViewOrderDetailsAdminComponent', () => {
  let component: ViewOrderDetailsAdminComponent;
  let fixture: ComponentFixture<ViewOrderDetailsAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ViewOrderDetailsAdminComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewOrderDetailsAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

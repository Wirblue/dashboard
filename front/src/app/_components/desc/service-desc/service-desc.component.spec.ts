import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ServiceDescComponent } from './service-desc.component';

describe('ServiceDescComponent', () => {
  let component: ServiceDescComponent;
  let fixture: ComponentFixture<ServiceDescComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ServiceDescComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ServiceDescComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WidgetDescComponent } from './widget-desc.component';

describe('WidgetDescComponent', () => {
  let component: WidgetDescComponent;
  let fixture: ComponentFixture<WidgetDescComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WidgetDescComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WidgetDescComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

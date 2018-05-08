import { ConfirmComponent } from './confirm.component' ;  
import { CommonModule } from '@angular/common';  
import { NgModule } from '@angular/core';  
import { FormsModule } from '@angular/forms';  
import { HttpModule } from '@angular/http';  
  
@NgModule({  
  declarations: [  
    ConfirmComponent  
  ],  
  imports: [  
    CommonModule,  
    FormsModule,  
    HttpModule,  
  ],  
  providers: [],  
  exports: [ConfirmComponent],  
})  
export class ConfirmModule { }  
import { ModalComponent } from './modal.component' ;  
import { CommonModule } from '@angular/common';  
import { NgModule } from '@angular/core';  
import { FormsModule } from '@angular/forms';  
import { HttpModule } from '@angular/http';  
  
@NgModule({  
  declarations: [  
    ModalComponent  
  ],  
  imports: [  
    CommonModule,  
    FormsModule,  
    HttpModule,  
  ],  
  providers: [],  
  exports: [ModalComponent],  
})  
export class ModalModule { }  
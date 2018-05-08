import {GridComponent} from './grid.component' ;  
import {CommonModule} from '@angular/common';  
import { NgModule } from '@angular/core';  
import { FormsModule } from '@angular/forms';  
import { HttpModule } from '@angular/http';  
  
@NgModule({  
  declarations: [  
    GridComponent  
  ],  
  imports: [  
    CommonModule,  
    FormsModule,  
    HttpModule,  
  ],  
  providers: [],  
  exports: [GridComponent],  
})  
export class GridModule { }  
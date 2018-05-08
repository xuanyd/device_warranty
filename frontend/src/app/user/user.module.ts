import { NgModule }   from '@angular/core';
import { CommonModule }       from '@angular/common';
import { FormsModule, ReactiveFormsModule} from "@angular/forms";
import { UserRoutingModule } from './user.routing.module';
import { UserComponent } from './user.component';
import { ChangePwdComponent } from './pwd/changepwd.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    UserRoutingModule
  ],
  declarations: [
    UserComponent,
    ChangePwdComponent
  ],
  exports: [
  ],
  providers: []
})

export class UserModule {
}

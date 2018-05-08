import { NgModule }   from '@angular/core';
import { CommonModule }       from '@angular/common';
import { FormsModule, ReactiveFormsModule} from "@angular/forms";
import { SearchRoutingModule } from './search.routing.module';
import { SearchComponent } from './search.component';
import { GridModule  } from '../common/lib/grid';
import { ConfirmModule  } from '../common/lib/confirm';
import { ModalModule  } from '../common/lib/modal';
import { PatientComponent } from './patient/patient.component';
import { CheckTemplateComponent } from './checktemplate/checktemplate.component';
import { CheckTemplateAddComponent } from './checktemplate/checktemplate-add.component';
import { CheckComponent } from './check/check.component';
import { CheckAddComponent } from './check/check-add.component';
import { CheckEditComponent } from './check/check-edit.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    SearchRoutingModule,
    GridModule,
    ConfirmModule,
    ModalModule
  ],
  declarations: [
    SearchComponent,
    PatientComponent,
    CheckTemplateComponent,
    CheckTemplateAddComponent,
    CheckComponent,
    CheckAddComponent,
    CheckEditComponent
  ],
  exports: [
  ],
  providers: []
})

export class SearchModule {
}

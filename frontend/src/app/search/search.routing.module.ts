import { NgModule }   from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SearchComponent } from './search.component';
import { PermissionGuard   } from '../common/util/PermissionGuard';
import { PatientComponent } from './patient/patient.component';
import { CheckTemplateComponent } from './checktemplate/checktemplate.component';
import { CheckTemplateAddComponent } from './checktemplate/checktemplate-add.component';
import { CheckComponent } from './check/check.component';
import { CheckAddComponent } from './check/check-add.component';
import { CheckEditComponent } from './check/check-edit.component';

const searchRoutes: Routes = [
  {
    path: '', component: SearchComponent,
    children: [
      {
        path:'patient',
        canActivate:[PermissionGuard],
        component:PatientComponent
      },
      {
        path:'check-template',
        canActivate:[PermissionGuard],
        component:CheckTemplateComponent
      },
      {
        path:'check-template-add',
        canActivate:[PermissionGuard],
        component:CheckTemplateAddComponent
      },
      {
        path:'check-list/:id',
        canActivate:[PermissionGuard],
        component:CheckComponent
      },
      {
        path:'check-add/:id',
        canActivate:[PermissionGuard],
        component:CheckAddComponent
      },
      {
        path:'check-edit/:id',
        canActivate:[PermissionGuard],
        component:CheckEditComponent
      }
    ]
  }
]

@NgModule({
  imports: [
    RouterModule.forChild(searchRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class SearchRoutingModule { }
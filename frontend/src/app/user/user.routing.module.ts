import { NgModule }   from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserComponent } from './user.component';
import { PermissionGuard   } from '../common/util/PermissionGuard';
import { ChangePwdComponent } from './pwd/changepwd.component';


const userRoutes: Routes = [
  {
    path: '', component: UserComponent,
    children: [
      {
        path:'change-pwd',
        canActivate:[PermissionGuard],
        component:ChangePwdComponent
      }
    ]
  }
]

@NgModule({
  imports: [
    RouterModule.forChild(userRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class UserRoutingModule { }
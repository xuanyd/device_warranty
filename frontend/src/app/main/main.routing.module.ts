import { NgModule, OnInit } from '@angular/core'
import { RouterModule, Routes, Router } from '@angular/router'
import { MainComponent }   from './main.component'

/**
 * 主体路由
 */
const mainRoutes: Routes = [
  {
    path: '',
    component: MainComponent,
    children: [
      { path: 'home', loadChildren: 'app/home/home.module#HomeModule' },
      { path: 'user', loadChildren: 'app/user/user.module#UserModule' },
      { path: 'search', loadChildren: 'app/search/search.module#SearchModule' }
    ]
  }
]

@NgModule({
  imports: [
    RouterModule.forChild(mainRoutes)
  ],
  exports: [
    RouterModule
  ]
})

export class MainRoutingModule { }
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { EditComponent } from './components/edit/edit.component';
import { LoginComponent } from './components/login/login.component';
import { AuthGuard } from './_helper/auth.guard';
import { LogoutComponent } from './components/logout/logout.component';

const routes: Routes = [
  {
    path: '', 
    component: HomeComponent,
    canActivate: [AuthGuard], 
  },
  {
    path: 'edit',
    component: EditComponent,
    canActivate: [AuthGuard],
  },
  {
    path: 'edit/:id',
    component: EditComponent,
    canActivate: [AuthGuard],
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'logout', 
    component: LogoutComponent,
    canActivate: [AuthGuard], 
  },
  { 
    path: '**', 
    redirectTo: '' 
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { EditComponent } from './components/edit/edit.component';
import { LoginComponent } from './components/login/login.component';

const routes: Routes = [
  {
    path: '', 
    component: HomeComponent, 
  },
  {
    path: 'edit',
    component: EditComponent,
  },
  {
    path: 'edit/:id',
    component: EditComponent
  },
  {
    path: 'login',
    component: LoginComponent
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

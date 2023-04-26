import { Component, Input, OnInit } from '@angular/core';
import { IPet } from 'src/app/_model/pet';
import { Router } from '@angular/router';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent {

  @Input() 
  loadedPets: IPet[]

  constructor(private router: Router){}

  handleEditPet(pet: IPet): void {
    this.router.navigate(['edit', pet.id]);
  }
}

import { Component, Input, OnInit } from '@angular/core';
import { IPet } from 'src/app/_model/pet';
import { Router } from '@angular/router';
import { PetService } from 'src/app/_service/pet.service';
import { StateManager } from 'src/app/_helper/state.manager';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent {

  @Input() 
  loadedPets: IPet[]

  constructor(private router: Router, public petService: PetService, public stateManager: StateManager){}

  handleEditPet(pet: IPet): void {
    this.router.navigate(['edit', pet.id]);
  }
}

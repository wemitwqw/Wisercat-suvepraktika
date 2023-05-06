import { Component, Input, OnInit } from '@angular/core';
import { IPet } from 'src/app/_model/pet';
import { Router } from '@angular/router';
import { PetService } from 'src/app/_service/pet.service';
import { StateManager } from 'src/app/_helper/state.manager';
// import {tableSort} from 'src/assets/table-sort.js'


@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css'],
})
export class TableComponent implements OnInit {

  // @Input() 
  // loadedPets: IPet[] = this.stateManager.loadedPets;
  // callYep() {tableSort();}

  constructor(private router: Router, public petService: PetService, public stateManager: StateManager){}

  ngOnInit(): void {
      // this.petService.getPets().subscribe((pets) => this.stateManager.loadedPets = pets);
      this.petService.getPets().subscribe({
        next: (data) => {
          this.stateManager.loadedPets = data;
          // console.log(data);
        },
        error: (e) => console.error(e)
      });
    }

  handleEditPet(pet: IPet): void {
    this.router.navigate(['edit', pet.id]);
  }
}

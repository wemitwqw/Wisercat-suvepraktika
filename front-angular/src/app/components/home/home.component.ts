import { Component, OnInit } from '@angular/core';
import { IPet } from 'src/app/_model/pet';
import { PetService } from '../../_service/pet.service'
import { StateManager } from 'src/app/_helper/state.manager';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  // loadedPets: IPet[] = [];

  constructor(private petService: PetService, private stateManager: StateManager) {}

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
}
import { Injectable } from '@angular/core';
import { IPet } from '../_model/pet';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class StateManager {
  loadedPets!: IPet[];

  constructor(private router: Router) { }

  updatePet(pet: IPet): void {
    this.loadedPets[this.loadedPets.findIndex(petFromList => petFromList.id == pet.id)] = pet;
    this.router.navigate(['/']);
  }

  addPet(pet: IPet): void {
    this.loadedPets = [...this.loadedPets, pet];
    this.router.navigate(['/']);
  }

}

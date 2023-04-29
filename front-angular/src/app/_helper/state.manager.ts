import { Injectable } from '@angular/core';
import { IPet } from '../_model/pet';

@Injectable({
  providedIn: 'root'
})
export class StateManager {
  loadedPets: IPet[]

  constructor() { }

  updatePet(pet: IPet): void {
    this.loadedPets.map((petFromList) => (petFromList.id === pet.id ? { ...petFromList, ...pet } : petFromList))
  }

  addPet(pet: IPet): void {
    this.loadedPets.push(pet);
  }
}

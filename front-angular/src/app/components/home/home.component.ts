import { Component, OnInit } from '@angular/core';
import { IPet } from 'src/app/models/pet';
import { PetsDataService as PetService } from '../../services/pets-data.service'

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  loadedPets: IPet[] = [];

  constructor(private petService: PetService) {}

  ngOnInit(): void {
    this.petService.getPets().subscribe((pets) => this.loadedPets = pets);
  }
}

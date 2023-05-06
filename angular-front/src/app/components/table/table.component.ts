import { Component, OnInit } from '@angular/core';
import { IPet } from 'src/app/_model/pet';
import { Router } from '@angular/router';
import { PetService } from 'src/app/_service/pet.service';
import { StateManager } from 'src/app/_helper/state.manager';
import { OrderPipe } from 'ngx-order-pipe';


@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css'],
})
export class TableComponent implements OnInit {

  order: string = 'id';
  reverse: boolean = false;

  constructor(private router: Router, public petService: PetService, public stateManager: StateManager, private orderPipe: OrderPipe){}

  setOrder(value: string) {
    if (this.order === value) {
      this.reverse = !this.reverse;
    }
    this.order = value;
  }

  ngOnInit(): void {
      this.petService.getPets().subscribe({
        next: (data) => {
          this.stateManager.loadedPets = data;
        },
        error: (e) => console.error(e)
      });
    }

  handleEditPet(pet: IPet): void {
    this.router.navigate(['edit', pet.id]);
  }
}

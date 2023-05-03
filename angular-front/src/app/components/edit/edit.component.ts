import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PetService } from '../../_service/pet.service'
import { IPet } from 'src/app/_model/pet';

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class EditComponent implements OnInit {
  id!: string | null;
  loadedPet!: IPet;

  constructor(private activatedRoute: ActivatedRoute, private petService: PetService) {} 

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(params => {
      this.id = params.get('id');
    });

    if(this.id) {
      this.petService.getPetById(this.id).subscribe((pet) => {
        this.loadedPet = pet;
      });
    }
  }
}

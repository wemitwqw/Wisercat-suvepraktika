import { Component, Input, OnInit, OnChanges, SimpleChanges, createPlatform } from '@angular/core';
import { IPet } from 'src/app/_model/pet';
import { FormControl, FormGroup, Validators  } from '@angular/forms'; 
import { SelectorsService } from 'src/app/_service/selectors.service'
import { Router } from '@angular/router';
import { ISelectors } from 'src/app/_model/selectors';
import { PetService } from 'src/app/_service/pet.service';
import { StateManager } from 'src/app/_helper/state.manager';

@Component({
  selector: 'app-edit-form',
  templateUrl: './edit-form.component.html',
  styleUrls: ['./edit-form.component.css']
})
export class EditFormComponent implements OnChanges, OnInit {  
  @Input() 
  pet!: IPet; 

  selectors!: ISelectors;

  form!: FormGroup;

  error!: string

  constructor(private router: Router, private petService: PetService, private selectorsService: SelectorsService, private stateManager: StateManager) {} 

  ngOnInit() {
    this.selectorsService.getSelectorData().subscribe((data) => {
      this.selectors = data;
    });

    this.form = new FormGroup({
      name: new FormControl(null, [Validators.required, Validators.minLength(1), Validators.maxLength(30)]),
      code: new FormControl(null, [Validators.required, Validators.minLength(8), Validators.maxLength(8), Validators.pattern('[a-z0-9]+')]),
      animalType: new FormControl('defTyp', [Validators.required, Validators.pattern('[a-z]+'), Validators.minLength(3), Validators.maxLength(20)]),
      furColor: new FormControl('defCol', [Validators.required, Validators.pattern('[a-z]+'), Validators.minLength(3), Validators.maxLength(20)]),
      country: new FormControl('defCountr', [Validators.required, Validators.minLength(3), Validators.maxLength(20)])
    });
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes['pet'].currentValue) {
      this.form.setValue({
        name: this.pet.name,
        code: this.pet.code,
        animalType: this.pet.animalType,
        furColor: this.pet.furColor,
        country: this.pet.country
      });
    }
  }
  
  get f() { return this.form.controls; }

  onSubmit(): void {
    if(this.form.valid){
      const fields = this.f;

      const petToSubmit = {
        "name": fields['name'].value,
        "code": fields['code'].value,
        "animalType": fields['animalType'].value,
        "furColor": fields['furColor'].value,
        "country": fields['country'].value
      };

      if(this.pet){
        this.petService.updatePet(this.pet.id, petToSubmit).subscribe({
          next: (res) => {
            if(this.error) {this.error = ""}
            this.stateManager.updatePet(petToSubmit);
          },
          error: (e) => {this.error = e;}
        });
      }

      if(!this.pet){
        this.petService.addPet(petToSubmit).subscribe({
          next: (res) => {
            this.stateManager.addPet(petToSubmit);
          },
          error: (e) => {this.error = e;}
        });
      }
    }
  }
}

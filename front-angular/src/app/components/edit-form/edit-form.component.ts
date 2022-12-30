import { Component, Input, OnInit, OnChanges, SimpleChanges, createPlatform } from '@angular/core';
import { IPet } from 'src/app/models/pet';
import { FormControl, FormGroup, Validators } from '@angular/forms'; 
import { PetsDataService as PetService } from '../../services/pets-data.service'
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-edit-form',
  templateUrl: './edit-form.component.html',
  styleUrls: ['./edit-form.component.css']
})
export class EditFormComponent implements OnChanges, OnInit {  
  @Input() 
  pet: IPet; 

  animal_types: any;
  animal_fur_colors: any;
  animal_countries: any;

  form: FormGroup;

  constructor(private activatedRoute: ActivatedRoute, private router: Router, private petService: PetService) {} 

  ngOnInit() {
    this.petService.getSelectorData().subscribe((data) => {
      const parsed = Object.values(data);
      this.animal_types = parsed[0];
      this.animal_fur_colors = parsed[1];
      this.animal_countries = parsed[2];
    });

    this.form = new FormGroup({
      name: new FormControl(null, [Validators.required]),
      code: new FormControl(null, [Validators.required]),
      animalType: new FormControl('defTyp', [Validators.required]),
      fur_color: new FormControl('defCol', [Validators.required]),
      country: new FormControl('defCountr', [Validators.required])
    });
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes['pet'].currentValue) {
      this.form.setValue({
        name: this.pet.name,
        code: this.pet.code,
        animalType: this.pet.animalType.animalType,
        fur_color: this.pet.fur_color.animalFurColor,
        country: this.pet.country.animalCountry
      });
    }
  }
  
  get f() { return this.form.controls; }

  onSubmit(): void {
    if(this.form.valid){
      const fields = this.f;

      const typeToSubmit = (this.animal_types.filter((obj: { animalType: any; }) => {
        return obj.animalType == fields.animalType.value
      }))
      const furToSubmit = this.animal_fur_colors.find((obj: { animalFurColor: any; }) => {
        return obj.animalFurColor == fields.fur_color.value
      })
      const countryToSubmit = (this.animal_countries.filter((obj: { animalCountry: any; }) => {
        return obj.animalCountry == fields.country.value
      }))

      const submittedPet = {
        "name": fields.name.value,
        "code": fields.code.value,
        "animalType": typeToSubmit[0],
        "fur_color": furToSubmit,
        "country": countryToSubmit[0]
      };

      if(this.pet){
        this.petService.updatePet(this.pet.id, submittedPet).subscribe();
        this.router.navigate(['/']);
      }
      if(!this.pet){
        this.petService.addPet(submittedPet).subscribe();
        this.router.navigate(['/']);
      }
    }
  }
}

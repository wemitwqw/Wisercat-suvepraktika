import { Component, Input, OnInit, OnChanges, SimpleChanges, createPlatform } from '@angular/core';
import { IPet } from 'src/app/_model/pet';
import { AbstractControl, FormControl, FormGroup, Validators } from '@angular/forms'; 
import { SelectorsService } from 'src/app/_service/selectors.service'
import { ActivatedRoute, Router } from '@angular/router';
import { ISelectors } from 'src/app/_model/selectors';
import { PetService } from 'src/app/_service/pet.service';

@Component({
  selector: 'app-edit-form',
  templateUrl: './edit-form.component.html',
  styleUrls: ['./edit-form.component.css']
})
export class EditFormComponent implements OnChanges, OnInit {  
  @Input() 
  pet: IPet; 

  selectors: ISelectors;

  form: FormGroup;

  constructor(private activatedRoute: ActivatedRoute, private router: Router, private petService: PetService, private selectorsService: SelectorsService) {} 

  ngOnInit() {
    this.selectorsService.getSelectorData().subscribe((data) => {
      this.selectors = data;
    });

    this.form = new FormGroup({
      name: new FormControl(null, [Validators.required, Validators.minLength(1), Validators.maxLength(30), Validators.pattern('\b[A-Z][a-zA-Z]*\b')]),
      code: new FormControl(null, [Validators.required, Validators.minLength(8), Validators.maxLength(8), Validators.pattern('[a-z0-9]+')]),
      animalType: new FormControl('defTyp', [Validators.required, Validators.pattern('[a-z]+'), Validators.minLength(3), Validators.maxLength(20)]),
      furColor: new FormControl('defCol', [Validators.required, Validators.pattern('[a-z]+'), Validators.minLength(3), Validators.maxLength(20)]),
      country: new FormControl('defCountr', [Validators.required, Validators.pattern('\b[A-Z][a-zA-Z]*\b'), Validators.minLength(3), Validators.maxLength(20)])
    });
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes['pet'].currentValue) {
      this.form.setValue({
        name: this.pet.name,
        code: this.pet.code,
        animalType: this.selectors.types,
        fuColor: this.selectors.colors,
        country: this.selectors.countries
      });
    }
  }
  
  get f() { return this.form.controls; }

  onSubmit(): void {
    if(this.form.valid){
      const fields = this.f;

      const submittedPet = {
        "name": fields.name.value,
        "code": fields.code.value,
        "animalType": fields.animalType.value,
        "furColor": fields.furColor.value,
        "country": fields.country.value
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

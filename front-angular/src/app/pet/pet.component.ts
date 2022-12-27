import { Component, Input } from "@angular/core";
import { IPet } from "../models/pet";


@Component({
    selector: 'app-pet',
    templateUrl: './pet.component.html'
})

export class PetComponent {
    @Input() pet: IPet;
}
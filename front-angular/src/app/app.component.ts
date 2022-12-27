import { Component } from '@angular/core';
import { IPet } from './models/pet';
import { pets as data } from './data/pets';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'front-angular';

  pets: IPet[] = data;
}

import { Component } from '@angular/core';
import Rellax from 'rellax';
import { NavComponent } from '../nav/nav.component';
import { TranslateModule } from '@ngx-translate/core';

@Component({
  selector: 'app-hero-about',
  standalone: true,
  imports: [NavComponent, TranslateModule],
  templateUrl: './hero-about.component.html',
  styleUrl: './hero-about.component.css',
})
export class HeroAboutComponent {
  ngOnInit() {
    var rellaxHeader = new Rellax('.rellax-header');

    var body = document.getElementsByTagName('body')[0];
    body.classList.add('profile-page');
    var navbar = document.getElementsByTagName('nav')[0];
    navbar.classList.add('navbar-transparent');
  }
}

import { Component, ElementRef, Injector } from '@angular/core';
import { Location } from '@angular/common';
import { TranslateModule, TranslateService } from '@ngx-translate/core';
import {
  NgbDropdown,
  NgbDropdownToggle,
  NgbModule,
} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-nav',
  standalone: true,
  imports: [NgbModule, NgbDropdown, NgbDropdownToggle, TranslateModule],
  templateUrl: './nav.component.html',
  styleUrl: './nav.component.css',
})
export class NavComponent {
  private toggleButton: any;
  private sidebarVisible: boolean;
  constructor(
    public location: Location,
    private element: ElementRef,
    private translate: TranslateService
  ) {
    this.sidebarVisible = false;
  }
  switchLanguage(language: string) {
    this.translate.use(language);
  }
  ngOnInit() {
    const navbar: HTMLElement = this.element.nativeElement;
    this.toggleButton = navbar.getElementsByClassName('navbar-toggler')[0];
  }
  sidebarOpen() {
    const toggleButton = this.toggleButton;
    const html = document.getElementsByTagName('html')[0];
    setTimeout(function () {
      toggleButton.classList.add('toggled');
    }, 500);
    html.classList.add('nav-open');

    this.sidebarVisible = true;
  }
  sidebarClose() {
    const html = document.getElementsByTagName('html')[0];
    // console.log(html);
    this.toggleButton.classList.remove('toggled');
    this.sidebarVisible = false;
    html.classList.remove('nav-open');
  }
  sidebarToggle() {
    const toggleButton = this.toggleButton;
    const body = document.getElementsByTagName('body')[0];
    if (this.sidebarVisible === false) {
      this.sidebarOpen();
    } else {
      this.sidebarClose();
    }
  }

  // isDocumentation() {
  //     var titlee = this.location.(this.location.path());
  //     if( titlee === '/documentation' ) {
  //         return true;
  //     }
  //     else {
  //         return false;
  //     }
  // }
}

import { Component } from '@angular/core';
import { ProductsService } from '../products.service';
import { CommonModule } from '@angular/common';
import { TranslateModule } from '@ngx-translate/core';

@Component({
  selector: 'app-products',
  standalone: true,
  imports: [CommonModule, TranslateModule],
  templateUrl: './products.component.html',
  styleUrl: './products.component.css'
})
export class ProductsComponent {
  produits: any[] = [];

  constructor(private productsService: ProductsService) {}


  ngOnInit(): void {
    this.produits = this.productsService.getProduits();
  }

  voirPlus(url: string) {
    window.open(url, '_blank'); // Ouvre l'URL dans un nouvel onglet
  }
}

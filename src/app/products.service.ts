import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ProductsService {

  constructor() { }

  produits = [
    {
      id: 1,
      name: 'SMS School Web Site',
      description: 'The SMS School Web Site is a modern and attractive platform specifically designed for educational institutions.',
      image: 'assets/img/sts.png',
      url: 'https://school-template.s3.amazonaws.com/index.html'
    },
    {
      id: 2,
      name: 'Car Rental Site',
      description: 'An easy-to-use website for renting cars, offering a wide range of options for all your travels.',
      image: 'assets/img/landing.jpg',
      url: ''
    }
    // Add more products here
  ];
  

  getProduits() {
    return this.produits;
  }
}



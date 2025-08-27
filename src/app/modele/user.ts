export interface User {
  id: number;
  nom: string;
  prenom: string;
  email: string;
  password: string;
  role: 'ADMIN' | 'CLIENT' | 'DEVIN';
  dateInscription: Date;
}

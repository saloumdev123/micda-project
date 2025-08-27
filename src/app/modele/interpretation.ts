import { Ramli } from '../modele/ramli';


export interface Interpretation {
  id: number;
  texteInterpretation: string;
  ramli: Ramli;
  tirageId: number;
  dateInterpretation: Date;
}
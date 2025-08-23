import { Ligne } from "./ligne";

export interface Ramli {
  id: number;
  nomFigure: string;
  description: string;
  symbolisme: string;
  lignes: Ligne[];
}



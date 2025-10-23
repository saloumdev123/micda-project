import { Interpretation } from "./interpretation";
import { Ramli } from "./ramli";

export interface Tirage {
  id: number;
  dateTirage: Date;
  utilisateurId: number;
  figures: Ramli[];
  interpretation: Interpretation;
}

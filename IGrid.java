package no.uib.inf101.grid;

public interface IGrid<E> extends GridDimension, Iterable<GridCell<E>> {
  
  /**
  * Setter verdien til posisjonen i grid. Etterpå vil en kall til {@link #get}
  * med en lik posisjon som argument returnere verdien som ble satt. Metoden
  * vil overskrive eventuell tidligere verdi som var lagret på lokasjonen
  * 
  * @param pos posisjonen hvor man skal lagre verdien
  * @param value nye verdien
  * @throws IndexOutOfBoundsException hvis posisjonen ikke eksisterer på grid
  */
  void set(CellPosition pos, E value);
  
  /**
  * Får den lagrede verdien på posisjonen i grid
  * 
  * @param pos posisjonen å hente
  * @return verdien lagret på posisjonen
  * @throws IndexOutOfBoundsException hvis posisjonen ikke eksisterer på grid
  */
  E get(CellPosition pos);
  
  /**
  * Rapporterer om posisjonen er innenfor grensene for gridet
  * 
  * @param pos positionen som skal sjekkes
  * @return true hvis posisjonen er innenfor gridet, false ellers
  */
  boolean positionIsOnGrid(CellPosition pos);
}

package no.uib.inf101.grid;

/*  GridCell består av en posisjon og en verdi,
 * 
 *  @param pos  posisjonen til cellen
 *  @param value  verdien til cellen
 */

public record GridCell<E>(CellPosition pos, E value) {}

package no.uib.inf101.tetris.model;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.Grid;

public class TetrisBoard extends Grid<Character> {

    /* Objekter i denne klassen representerer et tetrisbrett. */

    public TetrisBoard(int rows, int cols) {
        
        // konstruktør gir kall til super-konstruktøren
        super(rows, cols);

        // initierer alle cellene med '-'
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                this.set(new CellPosition(i, j), '-');
        }
    }}
    
    public String prettyString() {
       String prettyString = "";

        for (int i = 0; i < this.rows(); i++){
            for (int j = 0; j < this.cols(); j++){
                prettyString += this.get(new CellPosition(i, j));
            }
            if (i < rows()-1){
                prettyString += "\n";
            }
        }
        //returnerer en string representasjon av brettet på en fin måte
        // hver rad adskilt med linjeskift, og bokstavene i hver rad limt sammen.
        return prettyString;
    }

    // Hovedmetode
    // fjerner alle fulle rader fra brettet, og returnerer hvor mange rader som ble fjernet.
    public int removeFullRows() {
        // Opprett tellevariabel for å telle hvor mange rader som blir forkastet
        int removedRows = 0;

        // La a og b være variabler, begge med verdien 'antall rader - 1' (nederste rad på brettet)
        int a = this.rows()-1;
        int b = this.rows()-1;

        //Så lenge a er en rad på brettet (er større enn eller lik 0):
        while (a >= 0){
            // Så lenge b er en rad på brettet og raden b ikke inneholder en blank rute
            while (b >= 0 && this.checkIfRowIsFull(b) == true){
                removedRows++;
                b--;
            }
            // hvis b er på brettet
            if (b >= 0){
                this.copyRow(b, a);
            }
            else{
                this.setRow(a, '-');
            }
            a--;
            b--;
        }

        return removedRows;
    }



    // hjelpemetode for removeFullRows: sjekker om et element eksisterer i en rad på brettet.
    // returnerer true hvis alle elementene i raden er ulike fra '-'
    public boolean checkIfRowIsFull(int row) {
        for (int i = 0; i < this.cols(); i++){
            if (this.get(new CellPosition(row, i)) == '-'){
                return false;
            }
        }
        return true;
    }

    // hjelpemetode for removeFullRows: setter alle rutene i en rad til en gitt verdi
    public void setRow(int row, char value) {
        for (int i = 0; i < this.cols(); i++){
            this.set(new CellPosition(row, i), value);
        }
    }

    // hjelpemetode for removeFullRows: kopierer alle verdiene fra én rad inn i en annen
    public void copyRow(int from, int to) {
        for (int i = 0; i < this.cols(); i++){
            this.set(new CellPosition(to, i), this.get(new CellPosition(from, i)));
        }
    }
}

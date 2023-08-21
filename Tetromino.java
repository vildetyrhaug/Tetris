package no.uib.inf101.tetris.model.tetromino;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;


public final class Tetromino implements Iterable<GridCell<Character>> {
    // Tre egenskaper
    // * et symbol som representer familen brikken tilhører: 
    //         for eksempel 'T'-familien, 
    //          'I'-familien, 'S'-familien, etc. 
    //          Dette bestemmer hvilken farge tetrominoen skal tegnes med.
    // * en fasong; for eksempel en stående T, eller T som er sidelengs, 
    //          eller en T som er opp ned, eller en stående I, eller en liggende I etc.
    // * en posisjon på brettet

    char symbolOfPiece;
    boolean[][] shapeOfPiece;
    CellPosition posTopLeft;

    private Tetromino(char symbolOfPiece, boolean[][] shapeOfPiece, CellPosition posTopLeft){
        this.symbolOfPiece = symbolOfPiece;
        this.shapeOfPiece = shapeOfPiece;
        this.posTopLeft = posTopLeft;
    }

    static Tetromino newTetromino(char symbolOfPiece){
        // returnerer et nytt Tetromino-objekt med fasong basert på symbolet som blir gitt
        boolean[][] tetrominoObject = switch(symbolOfPiece) {

        case 'L' -> new boolean [][]{
            { false, false, false },
            { true,  true,  true },
            { true, false, false }};
        case 'J' -> new boolean [][]{
            { false, false, false },
            { true,  true,  true },
            { false, false,  true }};
        case 'S' -> new boolean [][]{
            { false, false, false },
            { false,  true,  true },
            {  true,  true, false }};
        case 'Z' -> new boolean [][]{
            { false, false, false },
            {  true,  true, false },
            { false,  true,  true }};
        case 'T' -> new boolean [][]{
            { false, false, false },
            {  true,  true,  true },
            { false,  true, false }};
        case 'I' -> new boolean [][]{
            { false, false, false, false },
            {  true,  true,  true,  true },
            { false, false, false, false },
            { false, false, false, false }};
        case 'O' -> new boolean [][]{
            { false, false, false, false },
            { false,  true,  true, false },
            { false,  true,  true, false },
            { false, false, false, false }};
        default -> throw new IllegalArgumentException(
                "No available shape for '" + symbolOfPiece + "'");
        };
        return new Tetromino(symbolOfPiece, tetrominoObject, new CellPosition(0,0));
    }
    
    public Tetromino shiftedBy(int deltaRow, int deltaCol){
        // returnerer et nytt Tetromino-objekt som er flyttet 
        // deltaRow rader nedover og deltaCol kolonner til høyre
        CellPosition newPos = new CellPosition(posTopLeft.row() + deltaRow, posTopLeft.col() + deltaCol);
        Tetromino copy = new Tetromino(symbolOfPiece, shapeOfPiece, newPos);
        return copy;
    }

    public Tetromino shiftedToTopCenterOf(GridDimension gridDimension) {
        // metoden oppretter en flyttet kopi som er sentrert rundt midterste 
        // eller de to midterste kolonnene i et rutenett med de gitte dimensjonene, 
        // og som er slik at øverste reelle rute i brikken kommer på rad 0
        return shiftedBy(- 1, gridDimension.cols()/2 - 2);
    }

    @Override
    public Iterator<GridCell<Character>> iterator() {
        // returnerer en iterator som itererer over kun de reelle posisjonene tetrominoen dekker
        // opprett en liste som skal inneholde GridCell<Character> -objekter
        // Benytt en dobbel for-løkke for å gå gjennom alle posisjoner i fasongen
        // dersom fasongen er true på en posisjon, regn ut hvilken posisjon dette tilsvarer på brettet
        // og legg til et nytt GridCell-objekt i listen
        // Til slutt, gjør et kall til .iterator på listen og returner resultatet.

        List<GridCell<Character>> list = new ArrayList<>();
        for (int i = 0; i < shapeOfPiece.length; i++) {
            for (int j = 0; j < shapeOfPiece[i].length; j++){
                if (shapeOfPiece[i][j] == true){
                    CellPosition pos = new CellPosition(posTopLeft.row() + i, posTopLeft.col() + j);
                    list.add(new GridCell<Character>(pos, symbolOfPiece));
                }
            }
        }
        return list.iterator(); 
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + symbolOfPiece;
        result = prime * result + Arrays.deepHashCode(shapeOfPiece);
        result = prime * result + ((posTopLeft == null) ? 0 : posTopLeft.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Tetromino other = (Tetromino) obj;
        if (symbolOfPiece != other.symbolOfPiece)
            return false;
        if (!Arrays.deepEquals(shapeOfPiece, other.shapeOfPiece))
            return false;
        if (posTopLeft == null) {
            if (other.posTopLeft != null)
                return false;
        } else if (!posTopLeft.equals(other.posTopLeft))
            return false;
        return true;

    }

    public Tetromino rotated() {
        // roteterer tetromino
        boolean[][] rotatedShape = new boolean[shapeOfPiece[0].length][shapeOfPiece.length];
        for (int i = 0; i < shapeOfPiece.length; i++) {
            for (int j = 0; j < shapeOfPiece[i].length; j++){
                rotatedShape[j][shapeOfPiece.length - 1 - i] = shapeOfPiece[i][j];
            }
        }
        return new Tetromino(symbolOfPiece, rotatedShape, posTopLeft);
    }}

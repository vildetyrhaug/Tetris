package no.uib.inf101.tetris.view;

import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.tetris.model.GameState;

public interface ViewableTetrisModel {
    
    /*  returnerer dimensjonene til brettet. 
        Mer presist, en metode som returnerer et objekt som 
        har metoder for å finne antall rader og kolonner. */
    GridDimension getDimension();
        

    /*  itererer over alle flisene på brettet. 
        Mer presist, en metode som returnerer et objekt som, 
        når det itereres over, 
        gir alle posisjonene på brettet med tilhørende symbol. */
    Iterable<GridCell<Character>> getTilesOnBoard();
        

    /* itererer over alle flisene på brikken som faller. 
        Mer presist, en metode som returnerer et objekt som, 
        når det itereres over, 
        gir alle posisjonene på brikken som faller med tilhørende symbol.
     */
    Iterable<GridCell<Character>> getTilesOnFallingPiece();


    /* 
     * @return tilstanden til spillet
     */
    GameState getGameState();
}
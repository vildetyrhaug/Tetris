package no.uib.inf101.tetris.controller;

import no.uib.inf101.tetris.model.GameState;

public interface ControllableTetrisModel {
    
    /* 
     * flytter brikken rundt på brettet
     * @return en boolean som indikrerer om flyttingen var vellykket eller ikke.
     */
    boolean moveTetromino(int deltaRow, int deltaCol);

    /* 
     * roteterer brikken
     * 
     */
    void rotateTetromino();


    /* 
     * setter brikken så langt ned på brettet den kan
     */
    void dropTetromino();

    /* 
     * @return en GameState som indikerer om spillet er aktivt, eller GameOver
     */
    GameState getGameState();

    /* 
     * en metode som henter ut hvor mange millisekunder (som integer) 
     * det skal være mellom hvert klokkeslag 
     */
    Integer getTickTime();

    /* 
     * kalles hver gang klokken slår.
     */
    void clockTick();

}

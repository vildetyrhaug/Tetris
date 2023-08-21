package no.uib.inf101.tetris.controller;

import java.awt.event.KeyEvent;

import javax.swing.Timer;

import java.awt.event.ActionEvent;

import no.uib.inf101.tetris.midi.TetrisSong;
import no.uib.inf101.tetris.model.GameState;
import no.uib.inf101.tetris.view.TetrisView;

public class TetrisController implements java.awt.event.KeyListener {
    
    ControllableTetrisModel model;
    TetrisView tetrisView;
    Timer timer;
    TetrisSong song = new TetrisSong();

    public TetrisController(ControllableTetrisModel model, TetrisView tetrisView) {
        tetrisView.setFocusable(true);
        this.tetrisView = tetrisView;
        this.model = model;
        tetrisView.addKeyListener(this);
        // timer 
        this.timer = new Timer(model.getTickTime(), this::clockTick);
        this.timer.start();
        song.run();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (model.getGameState() != GameState.GAME_OVER){
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                // Left arrow was pressed
                // Move the tetromino left
                if (model.moveTetromino(0, -1))
                    tetrisView.repaint();
            }
            else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                // Right arrow was pressed
                // Move the tetromino right
                if (model.moveTetromino(0, 1))
                    tetrisView.repaint();
            }
            else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                // Down arrow was pressed
                // Move the tetromino down
                if (model.moveTetromino(1, 0)){
                    tetrisView.repaint();
                    timer.restart();    // resetter timeren
                }
            }
            else if (e.getKeyCode() == KeyEvent.VK_UP) {
                // Up arrow was pressed
                // Rotate the tetromino
                model.rotateTetromino();
                tetrisView.repaint();
            }
            else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                // Spacebar was pressed
                // Drop the tetromino
                model.dropTetromino();
                timer.restart();    // resetter timeren
                tetrisView.repaint();
            }}
        
    }

    // hjelpemetode
    // henter ut riktig delay fra modellen
    // kaller  setDelay og setInitialDelay på timer-objektet med den nye verdien.
    public void updateTimer() {
        int delay = model.getTickTime();
        timer.setDelay(delay);
        timer.setInitialDelay(delay);
    }

    public void clockTick(ActionEvent event) {
        if (model.getGameState() == GameState.ACTIVE_GAME) {
            model.clockTick();
            // kall til hjelpemetoden som oppdaterer delay for timeren
            updateTimer();
            tetrisView.repaint();
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
    }



    } 

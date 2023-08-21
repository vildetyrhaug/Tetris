package no.uib.inf101.tetris;

import javax.swing.JFrame;

import no.uib.inf101.tetris.controller.TetrisController;
import no.uib.inf101.tetris.model.TetrisBoard;
import no.uib.inf101.tetris.model.TetrisModel;
import no.uib.inf101.tetris.model.tetromino.RandomTetrominoFactory;
import no.uib.inf101.tetris.model.tetromino.TetrominoFactory;
import no.uib.inf101.tetris.view.TetrisView;


public class TetrisMain {
  public static final String WINDOW_TITLE = "INF101 Tetris";
  
  public static void main(String[] args) {
    // Lager et brett
    TetrisBoard board = new TetrisBoard(15, 10);
    
    // Lager en factory
    TetrominoFactory factory = new RandomTetrominoFactory();

    TetrisModel model = new TetrisModel(board, factory);
    TetrisView view = new TetrisView(model);

    // Lager en kontroller
    // gir tetrisview og tetrismodel til kontrolleren
    TetrisController controller = new TetrisController(model, view);    

    // The JFrame is the "root" application window.
    // We here set som properties of the main window, 
    // and tell it to display our tetrisView
    JFrame frame = new JFrame(WINDOW_TITLE);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    // Here we set which component to view in our window
    frame.setContentPane(view);
    
    // Call these methods to actually display the window
    frame.pack();
    frame.setVisible(true);
  }
  
}

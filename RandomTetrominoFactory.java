package no.uib.inf101.tetris.model.tetromino;

public class RandomTetrominoFactory implements TetrominoFactory {

    @Override
    public Tetromino getNext() {
        String LJSZTIO = "LJSZTIO";
        // Velger tilfeldig bokstav fra string
        char randomLetter = LJSZTIO.charAt((int) (Math.random() * LJSZTIO.length()));

        // Lager nytt tetromino objekt
        Tetromino nextTetromino = Tetromino.newTetromino(randomLetter);
        return nextTetromino;
    }    
}

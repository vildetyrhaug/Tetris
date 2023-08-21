package no.uib.inf101.tetris.model;

import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.tetris.controller.ControllableTetrisModel;
import no.uib.inf101.tetris.model.tetromino.Tetromino;
import no.uib.inf101.tetris.model.tetromino.TetrominoFactory;
import no.uib.inf101.tetris.view.ViewableTetrisModel;

public class TetrisModel implements ViewableTetrisModel, ControllableTetrisModel {
    
    TetrisBoard board;
    TetrominoFactory factory;
    Tetromino fallingPiece;
    GameState gameState;
    private int score;
    private int level;
    int removedRowsInTotalPerLevel;
    int removedRowsLastRemoval;

    public TetrisModel(TetrisBoard board, TetrominoFactory factory) {
        this.board = board;
        this.factory = factory;

        this.fallingPiece = factory.getNext().shiftedToTopCenterOf(board);
        this.gameState = GameState.ACTIVE_GAME;

    }

    @Override
    public GridDimension getDimension() {
        return this.board;
    }

    @Override
    public Iterable<GridCell<Character>> getTilesOnBoard() {
        return this.board;
    }

    @Override
    public Iterable<GridCell<Character>> getTilesOnFallingPiece() {
        return this.fallingPiece;
    }

    @Override
    public boolean moveTetromino(int deltaRow, int deltaCol) {
        // moves the piece around on the board
        // returns a boolean indicating whether the move was successful or not.
        Tetromino newPiece = this.fallingPiece.shiftedBy(deltaRow, deltaCol);
        if (this.legalPlacement(newPiece)) {
            this.fallingPiece = newPiece;
            return true;
        }
        return false;
    }

    // hjelpemetode til moveTetromino
    public boolean legalPlacement(Tetromino newPiece) {
        // sjekker om en tetromino kan plasseres på brettet
        // returnerer true hvis det er lovlig, false ellers
        for (GridCell<Character> cell : newPiece) {
            if (!board.positionIsOnGrid(cell.pos())) {
                return false;
            }
            if (board.get(cell.pos()) != '-') {
                return false;
            }
        }
        return true;
    }

    @Override
    public void rotateTetromino() {
        Tetromino newPiece = this.fallingPiece.rotated();
        if (this.legalPlacement(newPiece)) {
            this.fallingPiece = newPiece;
        }
    }

    @Override
    public void dropTetromino() {
        // drops the tetromino to the bottom of the board
        Tetromino newPiece = this.fallingPiece.shiftedBy(1, 0);
        while (this.legalPlacement(newPiece)) {
            this.fallingPiece = newPiece;
            newPiece = this.fallingPiece.shiftedBy(1, 0);
        }
        placeTetromino();
    }
    
    // hjelpemetode til dropTetromino
    public void getNewTetromino() {
        Tetromino newPiece = factory.getNext().shiftedToTopCenterOf(board);
        if (!this.legalPlacement(newPiece)) {
            this.gameState = GameState.GAME_OVER;
        }
        this.fallingPiece = newPiece;
    }


    // hjelpemetode til dropTetromino
    public void placeTetromino() {
        for (GridCell<Character> cell : this.fallingPiece) {
            this.board.set(cell.pos(), cell.value());
        }
        
        removedRowsLastRemoval = board.removeFullRows();
        removedRowsInTotalPerLevel += removedRowsLastRemoval;
        //kalle på metode som oppdaterer poengsum og nivå
        this.updateScore();        
        getNewTetromino();
    }

    // Oppdaterer poengsum og nivå
    public void updateScore() {
        // Oppdaterer poengsum
        this.setScore(this.getScore());
        // Oppdaterer nivå
        this.setLevel();

    }

    // hjelpemetode for updateScore: setter poengsum
    private void setScore(int i) {
       this.score += i;
    }

    // hjelpemetode for updateScore: setter level
    private void setLevel() {
        // for each level you need to remove 5 rows * level
        if (removedRowsInTotalPerLevel >= 5 * (this.level())) {
            this.level++;
            removedRowsInTotalPerLevel = 0;
        }

    }

    // hjelpemetode for updateScore: returnerer poengsummen
    private int getScore() {
        System.out.println("her getScore");
        if (removedRowsLastRemoval == 1)
            return 100 * (this.level() + 1);
        else if (removedRowsLastRemoval == 2)
            return 300 * (this.level() + 1);
        else if (removedRowsLastRemoval == 3) 
            return 800 * (this.level() + 1);
        else if (removedRowsLastRemoval == 4) 
            return 1200 * (this.level() + 1);
        else 
            return 0;
    }

    // hjelpemetode for updateScore: returnerer hvilket nivå man er på
    public int level() {
        return this.level;
    }

    public int Score() {
        return this.score;
    }

    @Override
    public GameState getGameState() {
        return this.gameState;
    }

    @Override
    public Integer getTickTime() {
        // angir hvor lang tid (i millisekunder) mellom hvert klokkeslag basert på nivået man er på
        // høyere nivå --> kortere tid mellom klokkeslag
        int time = switch(this.level()) 
        {
            case 0 -> 1000;
            case 1 -> 900;
            case 2 -> 800;
            case 3 -> 700;
            case 4 -> 600;
            case 5 -> 500;
            case 6 -> 400;
            case 7 -> 300;
            case 8 -> 200;
            case 9 -> 100;
            default -> 50;
        };

        System.out.println("time: " + time);
        return time;
    }

    @Override
    public void clockTick() {
        // kalles hver gang klokken slår.
        // Flytt den fallende brikken en rad nedover. 
        // Dersom den ikke fikk lov til å flytte seg (sjekk returverdien!), 
        // lim den fast i stedet.
        
        if (!this.moveTetromino(1, 0)) {
            placeTetromino();
        }
    }
}

package no.uib.inf101.grid;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Grid<E> implements IGrid<E> {
    
    private int rows; 
    private int cols;

    List<List<E>> grid;

    private E defaultValue;


    public Grid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new ArrayList<>();

        this.defaultValue = null;
        
        for (int i = 0; i < rows; i++){
            this.grid.add(new ArrayList<E>());
      
            for (int j = 0; j < cols; j++){
              grid.get(i).add(defaultValue);
            }
        }
    }

    public Grid(int rows, int cols, E defaultValue) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new ArrayList<>();

        this.defaultValue = defaultValue;
        
        for (int i = 0; i < rows; i++){
            this.grid.add(new ArrayList<E>());
      
            for (int j = 0; j < cols; j++){
              grid.get(i).add(defaultValue);
            }
          }
    }

    
    @Override
    public void set(CellPosition pos, E value) {
        int rowindex = pos.row();
        int colindex = pos.col();
        
        //endrer verdien på posisjonen
        this.grid.get(rowindex).set(colindex, value);
    }

    @Override
    public boolean positionIsOnGrid(CellPosition pos) {
        if ((pos.row() >= 0 && pos.row() < this.rows()) 
            && (pos.col() >= 0 && pos.col() < this.cols())) 
            return true;
        return false;
    }

    @Override
    public Iterator<GridCell<E>> iterator() {
        
        ArrayList<GridCell<E>> cellList = new ArrayList<GridCell<E>>();

        for (int i = 0; i < this.rows(); i++) {
          for (int j = 0; j < this.cols(); j++) {
            cellList.add(new GridCell<E>(new CellPosition(i, j), get(new CellPosition(i, j))));
          }
        }
        return cellList.iterator();
    }
    
    @Override
    public int cols() {
        return this.cols;
    }

    @Override
    public int rows() {
        return this.rows;
    }

    @Override
    public E get(CellPosition pos) {
        int rowindex = pos.row();
        int colindex = pos.col();        
    
        List<E> row = this.grid.get(rowindex);
        E result = (E) row.get(colindex);
        
        return result;
    }
}

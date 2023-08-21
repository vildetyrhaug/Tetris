package no.uib.inf101.tetris.view;

import javax.swing.JPanel;

import no.uib.inf101.grid.GridCell;
import no.uib.inf101.tetris.model.GameState;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class TetrisView extends JPanel {
    
    private static final double OUTERMARGIN = 10;

    private ColorTheme colorTheme;
    private ViewableTetrisModel tetrisModel;
    
  // Constructor
    public TetrisView(ViewableTetrisModel tetrisModel) {
      this.tetrisModel = tetrisModel;
      this.colorTheme = new DefaultColorTheme();
        
      this.setPreferredSize(new Dimension(300, 400));
      this.setFocusable(true);
}
  
  // Tegner brettet 
  private void drawGame(Graphics2D canvas){
    
    // Setter fast størrelse i forhold til sidene på brettet 
    double cellWidth = this.getWidth() - 2 * OUTERMARGIN;
    double cellHeight = this.getHeight() - 2 * OUTERMARGIN;
    
    // Tegner bakgrunnen
    Rectangle2D rectangle = new Rectangle2D.Double(OUTERMARGIN, OUTERMARGIN, cellWidth, cellHeight);
    canvas.setColor(colorTheme.getBackgroundColor());
    canvas.fill(rectangle);
    
    // Tegner cellene
    CellPositionToPixelConverter position = new CellPositionToPixelConverter(rectangle, tetrisModel.getDimension(), 2);
    drawCells(canvas, tetrisModel.getTilesOnBoard(), position, colorTheme);

    // Tegner falling piece
    drawCells(canvas, tetrisModel.getTilesOnFallingPiece(), position, colorTheme);

    if (tetrisModel.getGameState()== GameState.GAME_OVER) {
      /* Lager en grå firkant over hele brettet
      canvas.setColor(Color.LIGHT_GRAY.darker().darker());
      canvas.fill(new Rectangle2D.Double(OUTERMARGIN, OUTERMARGIN, cellWidth, cellHeight));*/
      // Tegner en rute med gjennomsiktig farge som dekker hele skjermen,
      // og tegner teksten "GAME OVER" oppå
      canvas.setColor(colorTheme.getSeethroughColor());  
      canvas.fill(new Rectangle2D.Double(OUTERMARGIN, OUTERMARGIN, cellWidth, cellHeight));
      canvas.setColor(colorTheme.getGameOverColor()); 
      canvas.setFont(new Font("Monospaced", Font.BOLD, 50));
      Inf101Graphics.drawCenteredString(canvas, "GAME OVER", OUTERMARGIN, OUTERMARGIN, cellWidth, cellHeight);
    }
  }

  private static void drawCells(Graphics2D canvas, Iterable<GridCell<Character>> gridCellCharacter, CellPositionToPixelConverter cellPosToPixConvert, ColorTheme colorTheme){
    // itererer over cellene og tegner dem
    for(GridCell<Character> cell : gridCellCharacter){
        Rectangle2D rectangle = cellPosToPixConvert.getBoundsForCell(cell.pos());
        Color cellColor = colorTheme.getCellColor(cell.value());
        
        
        canvas.setColor(cellColor);
        canvas.fill(rectangle);
        }
    }

  // The paintComponent method is called by the Java Swing framework every time
  // either the window opens or resizes, or we call .repaint() on this object. 
  // Note: NEVER call paintComponent directly yourself
  @Override
  public void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D) g;
      drawGame(g2);
    }
  }


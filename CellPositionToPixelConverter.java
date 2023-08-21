package no.uib.inf101.tetris.view;

import java.awt.geom.Rectangle2D;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridDimension;

public class CellPositionToPixelConverter {

  // har en parameter av typen CellPosition og
  // returnerer et Rectangle2D -objekt.  
  // innefor hvilket område rutenettet befinner seg
  // hvor mange rader og kolonner det er i rutenettet som helhet, og
  // hvor stor avstanden mellom rutene skal være.

  // beskriver innenfor hvilket område rutenettet skal tegnes
  Rectangle2D box;
  // beskriver størrelsen til rutenettet rutene vil være en del av
  GridDimension gd;
  // beskriver hvor stor avstanden skal være mellom rutene
  Double margin; 

  // Konstruktør
  public CellPositionToPixelConverter(Rectangle2D box, GridDimension gd, double margin){
    this.box = box;
    this.gd = gd;
    this.margin = margin;
  }

  // Metode som returnerer et Rectangle2D -objekt
  public Rectangle2D getBoundsForCell(CellPosition cellPos) {
    
    // beregner størrelsen til cellen
    double cellWidth = ((box.getWidth() - ((gd.cols()+1) * margin))/ gd.cols());
    double cellHeight = ((box.getHeight() - ((gd.rows()+1) * margin))/ gd.rows());
    
    // beregner posisjonen til cellen
    double cellX = box.getX() + margin + ((cellWidth + margin) * cellPos.col());
    double cellY = box.getY() + margin + ((cellHeight + margin) * cellPos.row());

    // Tegner cellen
    Rectangle2D rectangle2d = new Rectangle2D.Double(cellX, cellY, cellWidth, cellHeight); 

    return rectangle2d;
  }

}


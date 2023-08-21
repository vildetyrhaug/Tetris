package no.uib.inf101.tetris.view;

import java.awt.Color;

public class DefaultColorTheme implements ColorTheme {

    private Color frameColor = new Color(0, 0, 0, 0);
    private Color backgroundColor = Color.GRAY;
    private Color seethroughColor = new Color(0, 0, 0, 128);
    private Color gameOverColor = Color.WHITE;
    
    @Override
    public Color getCellColor(Character c) {    
        Color color = switch(c) {
            case 'r' -> Color.RED;
            case 'g' -> Color.GREEN;
            case 'b' -> Color.BLUE;
            case 'y' -> Color.YELLOW;
            case 'o' -> Color.ORANGE;
            case 'p' -> Color.PINK;
            case 'c' -> Color.CYAN;
            case '-' -> Color.BLACK;
            
            case 'S' -> Color.CYAN;
            case 'Z' -> Color.BLUE;
            case 'L' -> Color.YELLOW;
            case 'J' -> Color.GREEN;
            case 'T' -> Color.ORANGE;
            case 'I' -> Color.PINK;
            case 'O' -> Color.MAGENTA;


            default -> throw new IllegalArgumentException(
                "No available color for '" + c + "'");
          };
          return color;
        
    }
    
    @Override
    public Color getFrameColor() {
        return frameColor;
    }

    @Override
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    @Override
    public Color getSeethroughColor() {
        return seethroughColor;   
    }

    @Override
    public Color getGameOverColor() {
        return gameOverColor;
        }


}

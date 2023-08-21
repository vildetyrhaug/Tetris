package no.uib.inf101.tetris.view;

import java.awt.Color;


public interface ColorTheme {
    
        /* 
        * Returnerer fargen til cellen med gitt verdi.
        * @param c  karakteren i cellen
        * return verdi kan ikke være null
        */
    Color getCellColor(Character c);
        
    
        /* 
        * @return fargen til rammen rundt brettet 
        * return verdien burde ikke være null men det kan være en gjennomsiktig ramme
        *     med (new Color(0, 0, 0, 0) 
        */
    Color getFrameColor(); 
        

    
        /* 
        * @return fargen til bakgrunnen 
        * farge kan ikke være gjennomsiktig
        * farge kan være null --> da er bakgrunnen Java bakgrunnsfarge
        */
    Color getBackgroundColor(); 
        
        /* 
        * @return fargen til den gjennomsiktige overlayen når spillet er game over
        * farge bør være new Color(0, 0, 0, 128)
        */
    Color getSeethroughColor();

        /* 
        * @return fargen på Game over teksten 
        * farge skal ikke være null eller gjennomsiktig.
        */
    Color getGameOverColor();

}

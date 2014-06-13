/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package App;

import GraphicObjects.GraphicObject;
import ListView.ListController;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 *
 * @author tim.giesenberg@me.com
 */
public class GraphicClickEventHandler implements javafx.event.EventHandler<MouseEvent>{

    final private GraphicObject graphicObject;
    final private ListController ListController;
    
    /**
     *
     * @param o
     * @param lc
     */
    public GraphicClickEventHandler(GraphicObject o, ListController lc){
        
        graphicObject = o;
        ListController = lc;
        
    }
    
    /**
     *
     * @param t
     */
    @Override
    public void handle(MouseEvent t) {
        
        System.out.println("Das Objekt: " + graphicObject.getName() + " wurde ausgewählt.");
        graphicObject.toFront();
        
        // Konturrand beim letzten ausgewählten Objekt entfernen
        GraphicObject selectedItem = this.ListController.getSelectedItem();
        if (selectedItem != null) selectedItem.setStroke(Color.TRANSPARENT);
        
        // Konturrand zu diesem Objekt hinzufügen
        // Zeichen, dass dieses Objekt ausgewählt ist
        Color fill = (Color) this.graphicObject.getFill();
        this.graphicObject.setStroke(
            Color.hsb(
                fill.getHue() + 180, 
                1, 
                1
            )
        );
        this.graphicObject.setStrokeWidth(3);
        
        ListController.setFocus(graphicObject);
        
    }
    
}

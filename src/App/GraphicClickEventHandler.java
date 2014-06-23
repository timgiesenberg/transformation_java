/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package App;

import GraphicObjects.GraphicObject;
import GraphicObjects.Line;
import ListView.ListController;
import javafx.animation.StrokeTransition;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 *
 * @author tim.giesenberg@me.com
 */
public class GraphicClickEventHandler implements javafx.event.EventHandler<MouseEvent>{

    final private GraphicObject graphicObject;
    final private ListController listController;
    final private AppController appController;
    
    public GraphicClickEventHandler(GraphicObject o, ListController lc, AppController ac){
        
        graphicObject = o;
        listController = lc;
        appController = ac;
        
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
        GraphicObject selectedItem = this.listController.getSelectedItem();
        if (selectedItem != null) {
            if (selectedItem instanceof Line) selectedItem.setStroke(selectedItem.getFill());
            else selectedItem.setStroke(Color.TRANSPARENT);
        }
        StrokeTransition oldST = appController.getStrokeTransition();
        if (oldST != null) oldST.stop();
        
        
        // Konturrand zu diesem Objekt hinzufügen
        this.graphicObject.setStrokeWidth(3);
        Color fill = (Color) this.graphicObject.getFill();
        Color strokeColor = Color.hsb(fill.getHue() + 180, 1, 1);
        Color antiStrokeColor = Color.hsb(fill.getHue() + 90, 0.5, 0.5);
        StrokeTransition aktuelleST = new StrokeTransition(Duration.millis(500), this.graphicObject, strokeColor, antiStrokeColor);
        aktuelleST.setCycleCount(StrokeTransition.INDEFINITE);
        aktuelleST.setAutoReverse(true);
        aktuelleST.play();
        appController.setStrokeTransition(aktuelleST);
        
        listController.setFocus(graphicObject);
        
        appController.setInputFieldValues(graphicObject);
        
    }
    
}

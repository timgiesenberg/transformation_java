/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package App;

import GraphicObjects.GraphicObject;
import javafx.event.Event;

/**
 *
 * @author tim.giesenberg@me.com
 * @param <MouseEvent>
 */
public class GraphicClickEventHandler<MouseEvent> implements javafx.event.EventHandler<Event>{

    final private GraphicObject graphicObject;
    
    public GraphicClickEventHandler(GraphicObject o){
        graphicObject = o;
    }
    
    @Override
    public void handle(Event t) {
        System.out.println("Das Objekt: " + graphicObject.getName() + " wurde ausgewählt.");
        graphicObject.toFront();
        //graphicObject.get
        //TODO 
        //System.out.println("Der Kreis " + o.getName() + " wurde ausgewählt.");
    }
    
}

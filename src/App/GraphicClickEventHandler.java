/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package App;

import GraphicObjects.GraphicObject;
import javafx.event.MouseEvent;

/**
 *
 * @author tim.giesenberg@me.com
 */
public class GraphicClickEventHandler implements javafx.event.EventHandler<MouseEvent>{

    final private GraphicObject graphicObject;
    
    public GraphicClickEventHandler(GraphicObject o){
        graphicObject = o;
    }
    
    @Override
    public void handle(MouseEvent t) {
        System.out.println("Das Objekt: " + graphicObject.getName() + " wurde ausgewählt.");
        graphicObject.toFront();
        //graphicObject.get
        //TODO 
        //System.out.println("Der Kreis " + o.getName() + " wurde ausgewählt.");
        
        //selectedItem vom ListView auf dieses aktuelle GraphicObject setzen? 
        //damit danach z.b. transformiert werden kann, auch wenn das Objekt nicht 
        //über die Liste ausgewählt wurde, sondern hier per Maus
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ListView;

import GraphicObjects.GraphicObject;
import javafx.scene.control.ListCell;

/**
 * Stellt eine selbst definierte Zelle für den ListView dar.
 * @author tim.giesenberg@me.com
 * @version 1.0
 */
public class GraphicCell extends ListCell<GraphicObject>{
    
    /**
     * Setzt den Namen des Objektes als sichtbare Eigenschaft einer Zelle
     * @param item - Das Grafikobjekt
     * @param empty - Boolscher Wert
     */
    @Override
    public void updateItem(GraphicObject item, boolean empty) {
        
        super.updateItem(item, empty);

        if (item != null) {
            setText(item.getName());
        }
    }
    
}

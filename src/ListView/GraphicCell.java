/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ListView;

import GraphicObjects.GraphicObject;
import javafx.scene.control.ListCell;

/**
 *
 * @author tim.giesenberg@me.com
 */
public class GraphicCell extends ListCell<GraphicObject>{
    
    @Override
    public void updateItem(GraphicObject item, boolean empty) {
        
        super.updateItem(item, empty);

        if (item != null) {
            setText(item.getName());
        }
    }
    
}
